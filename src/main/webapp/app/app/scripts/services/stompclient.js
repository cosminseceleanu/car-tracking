'use strict';

angular.module('appApp')
  .service('$stompClient',['$rootScope', function ($rootScope) {
    var socket = {};
    var url = "http://localhost:8080/ws";
    var sockJs = new SockJS(url);
    var stompClient = Stomp.over(sockJs);
    stompClient.debug = null;
    var isConnected = false;

    socket.connect = function (callback) {
      stompClient.connect({}, function (frame) {
        isConnected = true;
        $rootScope.$broadcast("connected", frame);
        if (callback) {
          callback();
        }
      }, function (error) {
        isConnected = false;
      })
    };

    socket.disconnect = function () {
      stompClient.disconnect();
      isConnected = false;
      $rootScope.$broadcast("disconnect", {});
    };

    socket.subscribe = function (topic, callback) {
      console.log("subsribed to topic " + topic);
      return stompClient.subscribe(topic, function (message) {
        callback(JSON.parse(message.body), message.headers);
      })
    };

    socket.send = function (destination, headers, object) {
      stompClient.send(destination, headers, object);
    };

    socket.isConnected = function () {
      return isConnected;
    };

    return socket;
  }]);
