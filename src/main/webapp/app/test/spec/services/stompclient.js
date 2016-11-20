'use strict';

describe('Service: stompclient', function () {

  // load the service's module
  beforeEach(module('appApp'));

  // instantiate service
  var stompclient;
  beforeEach(inject(function (_stompclient_) {
    stompclient = _stompclient_;
  }));

  it('should do something', function () {
    expect(!!stompclient).toBe(true);
  });

});
