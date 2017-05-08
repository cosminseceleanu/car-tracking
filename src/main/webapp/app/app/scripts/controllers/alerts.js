'use strict';

angular.module('appApp')
  .controller('AlertsCtrl', ['$scope', '$alertService', '$stompClient', '$securityService',
    function ($scope, $alertService, $stompClient, $securityService) {
      var subscription = null;
      var page = 0;
      var size = 4;
      var alerts = [];
      var counter = 0;

      function fetchAlerts() {
        $alertService.search(page, size, function (response) {
          var responseData =  response.data._embedded;
          if (!responseData) {
            alerts = [];
          } else {
            alerts = responseData.alertResourceList;
          }

          $scope.alerts = alerts;
        });
      }

      $scope.$on('stomp.connected', function () {
        var topic = "/topic/user." + $securityService.getUserId() + ".alerts";
        subscription = $stompClient.subscribe(topic, onAlertReceived);
      });
      $scope.$on('stomp.disconnect', function () {
        subscription.unsubscribe();
      });

      function onAlertReceived(message) {
        alerts.unshift(message);
        if (alerts.length > size) {
          alerts.pop();
        }
        counter = counter + 1;
        $scope.$apply(function(){
          $scope.counter = counter;
          $scope.alerts = alerts;
        });
      }

      $scope.counter = counter;
      $scope.resetCounter = function () {
        counter = 0;
        $scope.counter = counter;
      };

      fetchAlerts();
  }]);
