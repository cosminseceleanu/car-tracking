angular.module('appApp')
  .factory('$userService', ['$resource', 'API', function ($resource, api) {
    var service = {};
    service.test = function () {
      var User = $resource(api + '/users/:userId', {userId:'@id'});
      var user = User.get({userId:2}, function() {
        console.log('success');
      }, function () {
        console.log('error');
      });

      console.log(user);

    };

    return service;
  }]);

'use strict';
