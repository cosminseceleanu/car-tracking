'use strict';

angular.module('appApp')
  .controller('EmployeeLocationCtrl', ['$scope', '$employeeService', 'id', 'uiGmapIsReady',
      function ($scope, $employeeService, id, uiGmapIsReady) {
        var employee = {rid: '', email: '', name: ''};
        var marker = {
          id: id,
          coords: {
            latitude: null,
            longitude: null
          }
        };

        $employeeService.get(id, function (response) {
          employee = response.data;
          $scope.employee = employee;
          marker.options = {
            labelClass:'marker_labels',
              labelAnchor: "36 61",
              labelContent: employee.name + '(' + employee.rid +')'
          };
          $scope.marker = marker;
        });

        $scope.$on('locationUpdate', function (event, message) {
          if (message.employeeId == id) {
            return updateLocation(message);
          }
        });


        function updateLocation(taskLog) {
          uiGmapIsReady.promise().then(function() {
            marker.coords.latitude = taskLog.latitude;
            marker.coords.longitude = taskLog.longitude;
            $scope.marker = marker;
          });
        }

        $scope.map = { center: { latitude: 44.4377397, longitude: 25.9542109 }, zoom: 8 };
        $scope.marker = marker;
        $scope.employee = employee;
  }]);
