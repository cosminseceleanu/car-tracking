'use strict';

angular.module('appApp')
  .directive('header', function () {
    return {
      templateUrl: 'views/directives/header.html',
      restrict: 'E',
      replace: true
    };
  });
