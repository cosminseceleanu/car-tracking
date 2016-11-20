'use strict';

angular.module('appApp')
  .controller('AdminCtrl', ['$securityService', '$scope', '$location', '$stompClient',
    function ($securityService, $scope, $location, $stompClient) {
      if (!$securityService.isLogged()) {
          $location.path('home');
      }
      $scope.logout = function () {
          $securityService.logout();
          $location.path('home');
      };
      $stompClient.init('http://localhost:8080/ws');
      $stompClient.connect(function (frame) {
        $stompClient.subscribe("/topic/task.logs.1");
      });

      $scope.sendMessage = function () {
        console.log("send message");
        var message = {message: "send from js"};
        $stompClient.send("/app/task.logs.1", {}, JSON.stringify(message));
      }
  }]);
