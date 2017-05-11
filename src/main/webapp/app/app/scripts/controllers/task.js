'use strict';

angular.module('appApp')
  .controller('TaskCtrl',['$scope', '$taskService', 'taskId', '$taskLogService',
    function ($scope, $taskService, taskId, $taskLogService) {
      $scope.task = {
        rid: '', destinationLatitude: '', destinationLongitude: '',
        endDate: '', address: '', employee: '',
        sourceLatitude: '', sourceLongitude: ''
      };
      $scope.path = [];

      $taskService.get(taskId, function (response) {
        $scope.task = response.data;
        setupMap();
        fetchTaskPath($scope.task.rid);
      });

      function fetchTaskPath(taskId) {
        $taskLogService.get(taskId, function (response) {
          var logs = response.data.resourceList;
          if (!logs) {
            return;
          }
          $scope.path = logs;
        });
      }

      var setupMap = function () {
        $scope.map = TaskMapSettings.getMapSettings($scope, 8);
        $scope.options = {
          scrollwheel: false
        };
        $scope.markers = TaskMapSettings.getMarkersSettings($scope);
      };

      setupMap();
  }]);
