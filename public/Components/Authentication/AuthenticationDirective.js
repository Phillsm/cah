var current = angular.module('myAppRename')
    .directive('authentication', function() {
        return {
            restrict: 'E',
            transclude: true,
            templateUrl: 'Components/Authentication/Authentication.html'
        };
    });