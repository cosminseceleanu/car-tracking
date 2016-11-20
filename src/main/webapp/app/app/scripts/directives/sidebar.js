'use strict';

angular.module('appApp')
  .directive('sidebar', function () {
    return {
      templateUrl: 'views/directives/sidebar.html',
      restrict: 'E',
      replace: true
    };
  });
