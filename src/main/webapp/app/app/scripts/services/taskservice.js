'use strict';

angular.module('appApp')
  .service('$taskService',['$http', 'API', '$securityService', function ($http, API, $securityService) {
    var service = {};

    service.create = function (task, callback) {
      console.log(task);
      doRequest($http.post(API + "/employees/" + task.employee.rid + "/tasks", task), callback)
    };

    service.update = function (task, callback) {
      var uri = API + "/employees/" + task.employee.rid + "/tasks/" + task.rid;
      doRequest($http.put(uri, task), callback)
    };

    service.getAll = function (callback, page, sort, size) {
      page = page || 0;
      size = size || 20;
      var url = API + '/users/' + $securityService.getUserId() + '/tasks?page=' + page + '&size=' + size;
      doRequest($http.get(url), callback);
    };

    service.get = function(id, callback) {
      doRequest($http.get(API + '/tasks/' + id), callback);
    };

    function doRequest(promise, successCallback) {
      promise.then(function (response) {
        successCallback(response);
      }, function () {
        alert('Server error');
      });
    }

    return service;
  }]);
