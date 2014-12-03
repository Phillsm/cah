/**
 * Created by drajz on 12/2/2014.
 */

'use strict';

angular.module('myAppRename.newGameView', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/newGameView', {
            templateUrl: 'app/newGameView/newGameView.html'
        });
    }])

    .controller('View1Ctrl', function() {
    });