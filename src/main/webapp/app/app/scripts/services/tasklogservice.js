'use strict';

angular.module('appApp')
  .service('$taskLogService',['$http', 'API', function ($http, API) {
    var service = {};
    service.get = function(taskId, callback) {
      var url = API + '/tasks/' + taskId + "/logs";
      $http.get(url).then(callback);
    };

    return service;
  }]);
