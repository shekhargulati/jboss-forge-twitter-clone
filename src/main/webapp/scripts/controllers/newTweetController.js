
angular.module('twt').controller('NewTweetController', function ($scope, $location, locationParser, TweetResource , UserResource) {
    $scope.disabled = false;
    $scope.tweet = $scope.tweet || {};
    
    $scope.postedByList = UserResource.queryAll(function(items){
        $scope.postedBySelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    
    $scope.$watch("postedBySelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.tweet.postedBy = {};
            $scope.tweet.postedBy.id = selection.value;
        }
    });

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Tweets/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        TweetResource.save($scope.tweet, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Tweets");
    };
});