

angular.module('twt').controller('EditTweetController', function($scope, $routeParams, $location, TweetResource , UserResource) {
    var self = this;
    $scope.disabled = false;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.tweet = new TweetResource(self.original);
            UserResource.queryAll(function(items) {
                $scope.postedBySelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.tweet.postedBy && item.id == $scope.tweet.postedBy.id) {
                        $scope.postedBySelection = labelObject;
                        $scope.tweet.postedBy = wrappedObject;
                        self.original.postedBy = $scope.tweet.postedBy;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Tweets");
        };
        TweetResource.get({TweetId:$routeParams.TweetId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.tweet);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.tweet.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Tweets");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Tweets");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.tweet.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("postedBySelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.tweet.postedBy = {};
            $scope.tweet.postedBy.id = selection.value;
        }
    });
    
    $scope.get();
});