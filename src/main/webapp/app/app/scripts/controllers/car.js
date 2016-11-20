'use strict';

angular.module('appApp')
  .controller('CarCtrl',['$carService', '$scope', '$employeeService', function ($carService, $scope, $employeeService) {
    var car = {rid: '', model: '', brand: '', number:'', employee: ''};
    var availableEmployees = [];

    var fetchCars = function () {
      $carService.getAll(function (response) {
        $scope.cars = response.data.carResourceList;
      });
    };

    var submit = function (car) {
      car.employee = angular.fromJson(car.employee);
      if (car.rid) {
        $carService.update(car, function () {
          fetchCars();
        })
      } else {
        $carService.create(car, function () {
          fetchCars();
        })
      }
    };

    $employeeService.getWithoutCar(function (response) {
      availableEmployees = response.data.users;

      $scope.employees = response.data.users;
    });

    var edit = function (car) {
      $scope.car = car;
      $scope.employees.push(car.employee);
    };

    var reset = function (form) {
      if (form) {
        form.$setPristine();
        form.$setUntouched();
      }
      $scope.employees = availableEmployees;
      $scope.car = car;
    };

    fetchCars();
    $scope.car = car;
    $scope.submit = submit;
    $scope.reset = reset;
    $scope.edit = edit;
  }]);
