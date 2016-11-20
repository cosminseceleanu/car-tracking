'use strict';

angular.module('appApp')
  .factory('$securityService', ['$http', 'jwtHelper', 'DOMAIN', function ($http, jwtHelper, domain) {
      var service = {};
      service.login = function (credentials) {
          var data = 'username=' + credentials.username + '&password=' + credentials.password;
          var config = {
             headers: {'Content-Type': 'application/x-www-form-urlencoded'}
          };

          return $http.post(domain + '/login', data, config)
              .then(function (response) {
                  localStorage.setItem('jwt_token', response.data);
              }, function () {
                  alert('incorect email or password');
              });
      };

      service.logout = function () {
          localStorage.removeItem('jwt_token');
      };

      service.getToken = function () {
        return localStorage.getItem('jwt_token');
      };

      service.isLogged = function () {
          var token = service.getToken();
          return token != null && !jwtHelper.isTokenExpired(token);
      };

      service.getUserId = function () {
        var tokenData = jwtHelper.decodeToken(service.getToken());

        return tokenData.id;
      };

      return service;
  }]);
