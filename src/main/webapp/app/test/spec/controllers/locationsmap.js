'use strict';

describe('Controller: LocationsmapCtrl', function () {

  // load the controller's module
  beforeEach(module('appApp'));

  var LocationsmapCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    LocationsmapCtrl = $controller('LocationsmapCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(LocationsmapCtrl.awesomeThings.length).toBe(3);
  });
});
