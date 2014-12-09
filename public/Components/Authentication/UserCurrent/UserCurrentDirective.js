var current = angular.module('myAppRename')
    .directive('usercurrent', function() {
        return {
            restrict: 'E',
            transclude: true,
            templateUrl: 'Components/Authentication/UserCurrent/UserCurrent.html'
        };
    });