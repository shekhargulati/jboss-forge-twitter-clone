'use strict';

angular.module('twt',['ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/Tweets',{templateUrl:'views/Tweet/search.html',controller:'SearchTweetController'})
      .when('/Tweets/new',{templateUrl:'views/Tweet/detail.html',controller:'NewTweetController'})
      .when('/Tweets/edit/:TweetId',{templateUrl:'views/Tweet/detail.html',controller:'EditTweetController'})
      .when('/Users',{templateUrl:'views/User/search.html',controller:'SearchUserController'})
      .when('/Users/new',{templateUrl:'views/User/detail.html',controller:'NewUserController'})
      .when('/Users/edit/:UserId',{templateUrl:'views/User/detail.html',controller:'EditUserController'})
      .otherwise({
        redirectTo: '/'
      });
  }])
  .controller('NavController', function NavController($scope, $location) {
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  });
