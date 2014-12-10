angular.module('myAppRename')
    .factory('UserFactory', function ( $http) {
        return {
            Create: function (username,password,callback ) {
                var user = {
                    Username: username,
                    Password: password
                };

                $http.post(window.conf.UserFactory.Create, user).success(function(data, status, headers, configs){
                    callback(data, status, headers, configs);

                } ).error(function(data, status, headers, configs){
                    callback(data, status, headers, configs);
                });
                //Same as above
                //$http.post(window.conf.UserFactory.Create, user)
                //    .success(callback)
                //    .error(callback)
                //);
            }
        }
    })