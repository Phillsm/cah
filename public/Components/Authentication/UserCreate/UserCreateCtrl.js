/*
 * Dependencies:
 *      /Libraries/angular.js
 *      /Libraries/angular-ui/ui-bootstrap.js
 *      UserCreate-Config.js
 *      UserCreate-ModalDialog.html
 *
 * jsHint: unknown
 */
/*! UserCreate-AngularCtrl.js - Version: 0.3*/

angular.module('myAppRename.UserCreateCtrl', ['ngRoute'])
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/uc', {
        });
    }])
    .controller('UserCreateCtrl', function ($scope, $modal, $log,$http) {

    $scope.items = {};

     //open dialog
    $scope.open = function (size) {

        var modalInstance = $modal.open({
            templateUrl: '/cah/public/Components/Authentication/UserCreate/UserCreateModalDialog.html',
            controller: 'ModalInstanceCtrl',
            size: size,
            resolve: {
                items: function () {
                    return $scope.items;
                }
            }
        });

        //When dialog close
        modalInstance.result.then(function (DialogResult) {
            //Close with $modalInstance.close($scope);

            //Just some log stuff
            $log.info('Modal close at: ' + new Date());
            $log.info('Username: ' + DialogResult.Username );
            $log.info('User password: ' + DialogResult.Password );

            //Http ajax to create user. - NOTE: This can be removed if you using line 84 instead of 85
    //        $http.post(window.conf.UserCreate.Create, {Username: DialogResult.Username, Password : DialogResult.Password  }).
            //             success(function(data, status, headers, config) {
                    //Create all did go well use is created

            //  }).
            //   error(function(data, status, headers, config) {
                    //oh snap! something did go wrong user not created
            //                    if (status == 404) { //Ack we cant find the service

                        //Show user new dialog with error message
            //          $modal.open({
            //              templateUrl: '/cah/public/Frameworks/404-Dialog.html',
            //              controller: 'ModalInstanceCtrl',
            //              windowClass: 'newUser-Modal',
                            //size: size,
            //              resolve: {
            //                  items: function () {
            //                      return window.conf.UserCreate.Create;
            //              }
            //          }
            //      });
            //      }

            //                });
        }, function () {
            //Closed with $modalInstance.dismiss('cancel');
            $log.info('Modal dismissed at: ' + new Date());
        });
    };
});

// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $modal service used above.

//inside dialog
angular.module('myAppRename.UserCreateCtrl').controller('ModalInstanceCtrl', function ($scope,$http, $modalInstance, items) {

    //When click ok
    $scope.ok = function () {
        //Call service to see if username is free
       // $http.post(window.conf.UserCreate.Create, {Username: $scope.Username, Password : $scope.Password  }). //if allow this line instead of line below you remove ajax call outside dialog
        $http.post(window.conf.UserCreate.isFree, {Username: $scope.Username  }).
            success(function(data, status, headers, config) {
                //Create user is free.
                $modalInstance.close($scope);
            }).
            error(function(data, status, headers, config) {
                //oh snap! something did go wrong
                if (status == 404 || status == 503 ) { //Ack Service is missing - Show message to user
                    //ng-show isServiceMissing is set to true
                    $scope.isServiceMissing = true;
                }
                if (status == 409) { //oh user already exist - Show message to user
                    //ng-show isFree is set to true
                    $scope.isFree = true;
                }

            });

    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});