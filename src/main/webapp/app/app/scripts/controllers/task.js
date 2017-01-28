'use strict';

angular.module('appApp')
  .controller('TaskCtrl',['$scope', '$taskService', 'taskId', function ($scope, $taskService, taskId) {
    $scope.task = {rid: '', destinationLatitude: '', destinationLongitude: '', endDate: '', address: '', employee: ''};

    $taskService.get(taskId, function (response) {
      $scope.task = response.data;
      setupMap();
    });

    var setupMap = function () {
      $scope.map = TaskMapSettings.getMapSettings($scope);
      $scope.options = {
        scrollwheel: false
      };
      $scope.marker = TaskMapSettings.getMarkerSettings($scope);
    };
    setupMap();
  }]);
