'use strict';

angular.module('appApp')
  .controller('TasksCtrl', ['$scope', '$employeeService', '$taskService', '$securityService',
    function ($scope, $employeeService, $taskService, $securityService) {
      $scope.totalItems = 0;
      $scope.currentPage = 0;
      $scope.itemsPerPage = 10;
      $scope.filters =  {employee: null, status: null};
      $scope.isOpen = false;

      $scope.pageChanged = function() {
        fetchTasks();
      };

      var fetchTasks = function () {
        var page = $scope.currentPage - 1;
        var filters = {
          'employee.admin.id': $securityService.getUserId(),
          'employee.id': $scope.filters.employee,
          'status': $scope.filters.status
        };
        $taskService.search(fetchTasksCallback, page, $scope.itemsPerPage, filters);
      };

      var fetchTasksCallback = function (response) {
        var responseData =  response.data._embedded;
        if (!responseData) {
          $scope.tasks = [];
        } else {
          $scope.tasks = responseData.taskResourceList;
        }
        var page = response.data.page;
        $scope.currentPage = page.number + 1;
        $scope.totalItems = page.totalElements;
        $scope.itemsPerPage = page.size;
      };

      fetchTasks();

      $scope.search = fetchTasks;
  }]);
