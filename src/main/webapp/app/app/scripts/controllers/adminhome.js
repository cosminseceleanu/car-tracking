'use strict';

angular.module('appApp')
  .controller('AdminHomeCtrl',['$scope', 'uiGmapIsReady', '$employeeService',
      function ($scope, uiGmapIsReady, $employeeService) {
        var markers = [];
        var employees = [];
        $scope.markers = markers;
        $scope.map = {center: { latitude: 44.4377397, longitude: 25.9542109 }, zoom: 8 };

        $scope.$on('locationUpdate', function (event, message) {
            updateLocation(message);
        });
        function updateLocation(message) {
          uiGmapIsReady.promise().then(function() {
            var id = message.employeeId;
            if (!employees[id]) {
              return fetchEmployee(id, message.latitude, message.longitude);
            }
            updateMarker(id, message.latitude, message.longitude);
          });
        }

        function fetchEmployee(id, lat, lon) {
          $employeeService.get(id, function (response) {
            employees[id] = response.data;
            updateMarker(id, lat, lon);
          })
        }

        function updateMarker(id, lat, lon) {
          var employee = employees[id];
          markers[id] = {
            id: id,
            title: employee.rid,
            coords: {
              latitude: lat,
              longitude: lon
            },
            options: {
              labelClass:'marker_labels',
              labelAnchor: "36 61",
              labelContent: employee.name + '(' + employee.rid +')'
            }
          };
          $scope.markers = markers;
        }
  }]);
