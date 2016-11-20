'use strict';

angular.module('appApp')
  .factory('$carService', ['$http', '$securityService', 'API', function ($http, $securityService, api) {
    var service = {};
    var baseUrl = api + '/users/' + $securityService.getUserId() + "/cars";

    service.getAll = function (callback) {
      $http.get(baseUrl).then(function (response) {
        callback(response);
      }, function () {
        alert('eroare');
      });
    };

    service.create = function (car, callback) {
      $http.post(baseUrl, car, {}).then(function (response) {
        callback(response);
      }, function () {
        alert('nu sa putut salva');
      });
    };

    service.update = function (car, callback) {
      var url = baseUrl + "/" + car.rid;
      $http.put(url, car, {}).then(function (response) {
        callback(response);
      }, function () {
        alert('nu sa putut salva');
      });
    };

    return service;
  }]);

