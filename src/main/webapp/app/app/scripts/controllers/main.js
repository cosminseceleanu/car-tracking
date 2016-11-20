'use strict';

angular.module('appApp')
  .controller('MainCtrl',['$securityService', '$location', function ($securityService, $location) {
      if ($securityService.isLogged()) {
          $location.path('admin');
      }
  }]);
