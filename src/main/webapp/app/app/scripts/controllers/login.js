'use strict';

angular.module('appApp').controller('LoginCtrl',
  ['$scope', '$securityService', '$location', function ($scope, $securityService, $location) {

      $scope.login = function () {
          $securityService.login($scope.credentials).then(function () {
              $location.path('admin');
          });
       };
  }]);
