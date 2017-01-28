'use strict';

angular.module('appApp')
  .factory('$employeeService', ['$http', '$securityService', 'API', function ($http, $securityService, API) {
    var service = {};
    var baseUrl = API + "/users/" + $securityService.getUserId() + "/employees";
    service.getAll = function (callback) {
      doRequest($http.get(baseUrl), callback);
    };

    service.getWithoutCar = function (callback) {
      doRequest($http.get(baseUrl + "?filter=without-car"), callback);
    };

    service.create = function (employee, callback) {
      doRequest($http.post(baseUrl, employee), callback);
    };

    service.get = function (id, callback) {
      var url = API + "/users/" + id;
      doRequest($http.get(url), callback);
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
