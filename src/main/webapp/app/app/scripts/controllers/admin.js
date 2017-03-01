'use strict';

angular.module('appApp')
  .controller('AdminCtrl', ['$securityService', '$scope', '$location', '$stompClient',
    function ($securityService, $scope, $location, $stompClient) {
      if (!$securityService.isLogged()) {
          $location.path('home');
      }
      var topic = "/topic/user." + $securityService.getUserId() + ".employee.*.task.logs";
      var subscription = null;

      $scope.logout = function () {
        $securityService.logout();
        $stompClient.disconnect();
        $location.path('home');
      };
      $stompClient.connect(function () {
        subscription = $stompClient.subscribe(topic, broadcastUpdateLocation);
      });

      function broadcastUpdateLocation (message) {
        $scope.$broadcast("locationUpdate", message);
      }

      function disconnect() {
        if (subscription) {
          subscription.unsubscribe();
        }
      }

      $scope.$on("$destroy", disconnect);
      $scope.$on('disconnect', disconnect);
      // $state.go('admin.home');
  }]);
