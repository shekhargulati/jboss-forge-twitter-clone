angular.module('twt').factory('TweetResource', function($resource){
    var resource = $resource('rest/tweets/:TweetId',{TweetId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});