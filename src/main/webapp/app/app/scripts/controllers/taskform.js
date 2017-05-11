'use strict';

angular.module('appApp')
  .controller('TaskFormCtrl',['$scope', '$employeeService', '$taskService', '$state', 'taskId',
    function ($scope, $employeeService, $taskService, $state, taskId) {
      var task = {
        rid: '',
        destinationLatitude: '', destinationLongitude: '',
        sourceLatitude: '', sourceLongitude: '',
        endDate: '', address: '', employee: ''
      };
      $scope.isOpen = false;

      var openCalendar = function (e) {
        e.preventDefault();
        e.stopPropagation();
        $scope.isOpen = true;
      };

      var reset = function (form) {
        if (form) {
          form.$setPristine();
          form.$setUntouched();
        }
        $scope.task = task;
      };

      var submitCallback = function () {
        $state.go('admin.tasks');
      };

      var submit = function (task) {
        task.employee = angular.fromJson(task.employee);
        if (task.rid) {
          $taskService.update(task, submitCallback);
        } else {
          $taskService.create(task, submitCallback);
        }
      };

      if (taskId !== null) {
        $taskService.get(taskId, function (response) {
          var task = response.data;
          $scope.task = task;
          task.limitDate = new Date(task.limitDate);
          $scope.map = TaskMapSettings.getMapSettings($scope);
          $scope.markers = TaskMapSettings.getMarkersSettings($scope);
        });
      }

      var fetchEmployees = function () {
        $employeeService.getAll(function (response) {
          $scope.employees = response.data.users;
        });
      };

      fetchEmployees();
      $scope.reset =reset;
      $scope.task = task;
      $scope.map = TaskMapSettings.getMapSettings($scope);
      $scope.options = {
        scrollwheel: false
      };
      $scope.markers = TaskMapSettings.getMarkersSettings($scope);
      $scope.openCalendar = openCalendar;
      $scope.submit = submit;
    }]);
