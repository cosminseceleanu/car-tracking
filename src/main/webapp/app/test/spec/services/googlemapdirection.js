'use strict';

describe('Service: googleMapDirection', function () {

  // load the service's module
  beforeEach(module('appApp'));

  // instantiate service
  var googleMapDirection;
  beforeEach(inject(function (_googleMapDirection_) {
    googleMapDirection = _googleMapDirection_;
  }));

  it('should do something', function () {
    expect(!!googleMapDirection).toBe(true);
  });

});
