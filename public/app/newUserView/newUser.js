'use strict';

angular.module('myAppRename.newUser', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/newUser', {
            templateUrl: 'app/newUserView/newUser.html',
            controller: 'newUserCtrl'
        });
    }])

    .controller('newUserCtrl', [
        '$scope', function($scope){
            $scope.addNewUser = function(){
                if(!$scope.username || $scope.username === ''){
                    return;
                }
                console.log($scope.username);
                $scope.username = '';
                $scope.password = '';
            }
    }]);

/*
    //Skal omskrives til en POST metode, der sender brugernavn og password til databaselagene.
    // Skal først sammenligne brugernavn med eksisterende brugernavne.

    .controller('newUserCtrl', function ($scope, $http) {
        $http({
            method: 'GET',
            url: 'adminApi/user'
        }).
            success(function (data, status, headers, config) {
                $scope.users = data;
                $scope.error = null;
            }).
            error(function (data, status, headers, config) {
                if(status == 401){
                    $scope.error ="You are not authenticated to request these data";
                    return;
                }
                $scope.error = data;
            });
    });

    */