var current = angular.module('myAppRename')
    .directive('usercreate', function() {
        return {
            restrict: 'E',
            transclude: true,
            templateUrl: 'Components/Authentication/UserCreate/UserCreate.html'
        };
    });