'use strict';

angular.module('appApp')
  .directive('headerNotification', function () {
    return {
      templateUrl: 'views/directives/header-notification.html',
      restrict: 'E',
      replace: true
    };
  });
