'use strict';

angular.module('appApp')
  .filter('taskStatus', function () {
    return function (input) {
      var statuses = {
        NEW: 'nou',
        FINISHED: 'finalizat',
        IN_PROGRESS: 'in curs de finalizare'
      };

      if (!statuses[input]) {
        return 'statusul nu e de definit';
      }

      return statuses[input];
    };
  });
