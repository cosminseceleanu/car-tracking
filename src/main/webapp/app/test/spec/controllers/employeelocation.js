'use strict';

describe('Controller: EmployeelocationCtrl', function () {

  // load the controller's module
  beforeEach(module('appApp'));

  var EmployeelocationCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EmployeelocationCtrl = $controller('EmployeelocationCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(EmployeelocationCtrl.awesomeThings.length).toBe(3);
  });
});
