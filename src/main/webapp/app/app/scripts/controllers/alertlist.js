'use strict';

angular.module('appApp')
  .controller('AlertListCtrl',['$scope', '$alertService', function ($scope, $alertService) {
    $scope.totalItems = 0;
    $scope.currentPage = 0;
    $scope.itemsPerPage = 4;
    $scope.pageChanged = function() {
      fetchAlerts();
    };

    function fetchAlerts() {
      var page = $scope.currentPage - 1;
      $alertService.search(page, $scope.itemsPerPage, fetchAlertsCallback);
    }

    function fetchAlertsCallback(response) {
      var responseData =  response.data._embedded;
      if (!responseData) {
        $scope.alerts = [];
      } else {
        $scope.alerts = responseData.alertResourceList;
      }
      var page = response.data.page;
      $scope.currentPage = page.number + 1;
      $scope.totalItems = page.totalElements;
      $scope.itemsPerPage = page.size;
    }

    fetchAlerts();
  }]);
