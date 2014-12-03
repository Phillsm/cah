/**
 * Created by Anders Kaae on 12/2/2014.
 */

'use strict';

angular.module('myAppRename.myGamesView', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/myGamesView', {
            templateUrl: 'app/myGamesView/myGamesView.html'
        });
    }])

    .controller('View1Ctrl', function() {
    });