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
        $stompClient.subscribe("/queue/tasks");
        $stompClient.subscribe("/topic/tasks.position.*");
      });

      $scope.sendMessage = function () {
        console.log("send message");
        $stompClient.send("/app/tasks", {}, "aaaa adasd");
      }
  }]);
