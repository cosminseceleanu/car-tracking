'use strict';

angular.module('appApp')
  .controller('TaskCtrl',['$scope', '$taskService', 'taskId', '$taskLogService',
    function ($scope, $taskService, taskId, $taskLogService) {
      $scope.task = {
        rid: '', destinationLatitude: '', destinationLongitude: '',
        endDate: '', address: '', employee: '',
        sourceLatitude: '', sourceLongitude: ''
      };
      $scope.routeMarkers = [];

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
          var markers = [];
          logs.forEach(function (log) {
            markers.push(createPathMarker(log.latitude, log.longitude));
          });
          $scope.routeMarkers = markers;
        });
      }

      function createPathMarker(lat, lng) {
        var date = new Date();
        return {
          id: date.getTime(),
          coords: {
            latitude: lat,
            longitude: lng
          },
          options: {
            icon: "https://maps.google.com/mapfiles/ms/icons/green-dot.png"
          }
        }
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
