/**
 * Created by drajz on 12/2/2014.
 */

'use strict';

angular.module('myAppRename.rateGameView', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/rateGameView', {
            templateUrl: 'app/rateGameView/rateGameView.html'
        });
    }])

    .controller('View1Ctrl', function() {
    });