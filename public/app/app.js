'use strict';

// Declare app level module which depends on views, and components
angular.module('myAppRename', [
  'ngRoute',
  'ui.bootstrap', //Added this is a lib and is add to allow bootstrap inside angularjs

  'myAppRename.controllers',
  'myAppRename.directives',
  'myAppRename.services',
  'myAppRename.factories',
  'myAppRename.filters',
  'myAppRename.view1',
  'myAppRename.view2',
  'myAppRename.view3',
  'myAppRename.myGamesView',
  'myAppRename.newGameView',
  'myAppRename.rateGameView',
  'myAppRename.UserCreateCtrl' //User creator
]).
config(['$routeProvider', function($routeProvider) {
    $routeProvider.otherwise({redirectTo: '/view1'});
}])
.config(function ($httpProvider) {
    $httpProvider.interceptors.push('authInterceptor');
  });



