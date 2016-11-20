'use strict';

angular.module('appApp')
  .service('$stompClient', function () {
    var stompClient;
    var socket = {};
    socket.init = function(url) {
      var sockJs = new SockJS(url);
      stompClient = Stomp.over(sockJs);
    };
    socket.connect = function (callback) {
      stompClient.connect({}, function (frame) {
        console.log('connected');
        callback(frame);
      }, function (error) {
        console.log('error');
        console.log(error);
      })
    };

    socket.disconnect = function () {
      stompClient.disconnect();
    };

    socket.subscribe = function (destination) {
      stompClient.subscribe(destination, function (message) {
        console.log('message received');
        console.log(message);
      });
    };

    socket.send = function (destination, headers, object) {
      stompClient.send(destination, headers, object);
    };

    return socket;
  });
