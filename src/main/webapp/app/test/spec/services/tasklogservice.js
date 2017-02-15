'use strict';

describe('Service: taskLogService', function () {

  // load the service's module
  beforeEach(module('appApp'));

  // instantiate service
  var taskLogService;
  beforeEach(inject(function (_taskLogService_) {
    taskLogService = _taskLogService_;
  }));

  it('should do something', function () {
    expect(!!taskLogService).toBe(true);
  });

});
