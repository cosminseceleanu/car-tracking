'use strict';

angular.module('appApp')
  .service('$googleMapDirection',[function () {
    var directionsService = null;
    var service = {};

    function setupDirectionServices() {
      directionsService = new google.maps.DirectionsService;
    }

    function getRoute(map, start, end, waypoints, travelMode) {
      directionsService.route({
        origin: start,
        destination: end,
        waypoints: waypoints,
        optimizeWaypoints: true,
        travelMode: travelMode
      }, function(response, status) {
        var directionsDisplay = new google.maps.DirectionsRenderer({
          map: map,
          directions: response,
          suppressMarkers: true
        });
      });
    }

    service.printRoute = function (map, start, end, waypoints, travelMode) {
      waypoints = waypoints || [];
      travelMode = travelMode || 'DRIVING';
      setupDirectionServices(map);
      getRoute(map, start, end, waypoints, travelMode);
    };

    return service;
  }]);
