var current = angular.module('myAppRename')
    .directive('userlogin', function() {
        return {
            restrict: 'E',
            transclude: true,
            templateUrl: 'Components/Authentication/UserLogin/UserLogin.html'
        };
    });