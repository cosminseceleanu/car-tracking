'use strict';

angular.module('appApp')
  .controller('TasksCtrl', ['$scope', '$employeeService', '$taskService',
    function ($scope, $employeeService, $taskService) {
      $scope.totalItems = 0;
      $scope.currentPage = 0;
      $scope.itemsPerPage = 4;

      $scope.pageChanged = function() {
        fetchTasks();
      };

      var fetchTasks = function () {
        $taskService.getAll(function (response) {
          $scope.tasks = response.data._embedded.taskResourceList;
          var page = response.data.page;
          $scope.currentPage = page.number + 1;
          $scope.totalItems = page.totalElements;
          $scope.itemsPerPage = page.size;
        }, $scope.currentPage - 1, null, $scope.itemsPerPage);
      };

      fetchTasks();
  }]);
