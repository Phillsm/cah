angular.module('myAppRename')
 .service('userService', function($cacheFactory, $http) {

    var cache = $cacheFactory('dataCache');

    this.authenticate = function(username,password, callback) {
        var cacheId = "CurrentUser";
        var cachedData = cache.get(cacheId);

        // Return the data if we already have it
        if (cachedData) {
            callback(cachedData);
            return;
        }

        $http.post(window.conf.UserLogin.Logon, {UserName: username, Password: password}).success(function(data, status, headers, configs){
            cache.put(cacheId, data);
            callback(data);

        } ).error(function(data, status, headers, configs){
            cache.put(cacheId, false);
            callback(data);

        });
    };

    this.getCurrentUser = function (callback) {
        var cacheId = "CurrentUser";
        var cachedData = cache.get(cacheId);

        // Return the data if we already have it
        callback(cachedData);
    }

    this.logout = function(callback){

        var cacheId = "CurrentUser";
        var cachedData = cache.get(cacheId);

        // Return the data if we already have it
        if (!cachedData) {
            callback(false);
            return;
        }

        $http.post(window.conf.UserLogin.Logout, {UserName: username, Password: password}).success(function(data, status, headers, configs){
            cache.put(cacheId, false);
            callback(data);

        } ).error(function(data, status, headers, configs){
            cache.put(cacheId, false);
            callback(data);

        });
    };
});