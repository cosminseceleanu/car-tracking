'use strict';

describe('Controller: CarcontrollerCtrl', function () {

  // load the controller's module
  beforeEach(module('floteMonitoringApp'));

  var CarcontrollerCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CarcontrollerCtrl = $controller('CarcontrollerCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(CarcontrollerCtrl.awesomeThings.length).toBe(3);
  });
});
