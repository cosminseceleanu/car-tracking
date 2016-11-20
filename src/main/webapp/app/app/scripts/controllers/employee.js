'use strict';

angular.module('appApp')
  .controller('EmployeeCtrl',['$scope', '$employeeService', function ($scope, $employeeService) {
    var employee = {rid: '', email: '', name: '', password: ''};

    var fetchEmployees = function () {
      $employeeService.getAll(function (response) {
        $scope.employees = response.data.users;
      });
    };

    var reset = function (form) {
      if (form) {
        form.$setPristine();
        form.$setUntouched();
      }
      $scope.employee = employee;
    };

    var submit = function (employee) {
      $employeeService.create(employee, function () {
        fetchEmployees();
      });
    };

    fetchEmployees();
    $scope.employee = employee;
    $scope.reset = reset;
    $scope.submit = submit;
}]);
