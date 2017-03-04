'use strict';

angular.module('appApp')
  .service('$alertService',['$http', '$securityService', 'API', function ($http, $securityService, API) {
    var service = {};

    service.search = function (page, size, callback) {
      var userid = $securityService.getUserId();
      var urlParams = {
        page: page || 0,
        size: size || 20,
        sort: 'id,desc',
        'user.id': userid
      };
      var url = API + '/alerts/search?' + $.param(urlParams);

      $http.get(url).then(function (response) {
        callback(response);
      }, function () {
        alert('alertele nu au putut fi incarcate');
      })
    };

    return service;
  }]);
