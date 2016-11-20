var TaskMapSettings = {
  getMapSettings: function ($scope) {
    return {
      center: {
        latitude: $scope.task.destinationLatitude || 44.4377397,
        longitude: $scope.task.destinationLongitude || 25.9542109
      },
      zoom: 8,
      events: {
        dblclick: function (map, eventName, eventArgs) {
          var e = eventArgs[0];
          var lat = e.latLng.lat();
          var lon = e.latLng.lng();
          var markerSettings = TaskMapSettings.getMarkerSettings($scope);
          markerSettings.coords = {
            latitude: lat,
            longitude: lon
          };
          $scope.marker = markerSettings;
        }
      },
      options: {disableDoubleClickZoom: true}
    };
  },
  getSearchBoxSettings: function($scope) {
    return {
      template:'searchbox.html',
      events:{
        places_changed: function (searchBox) {
          var place = searchBox.getPlaces();
          if (!place || place == 'undefined') {
            console.log('no place data :(');
            return;
          }
          var lat = place[0].geometry.location.lat();
          var lon = place[0].geometry.location.lng();
          $scope.task.address = place[0].formatted_address;

          //refresh the map
          $scope.map = {
            center:{
              latitude: lat,
              longitude: lon
            },
            zoom:12
          };
          TaskMapSettings.updateLocation($scope, lat, lon);
        }
      }
    }
  },

  getMarkerSettings: function ($scope) {
    return {
      id: 0,
      coords: {
        latitude: $scope.task.destinationLatitude || 44.4377397,
        longitude: $scope.task.destinationLongitude || 25.9542109
      },
      options: {draggable: true},
      events: {
        dragend: function (marker, eventName, args) {
          console.log(marker);
          var lat = marker.getPosition().lat();
          var lon = marker.getPosition().lng();
          $scope.marker.options = {
            draggable: true,
            labelAnchor: "100 0",
            labelClass: "marker-labels"
          };
          TaskMapSettings.updateLocation($scope, lat, lon);
        }
      }
    }
  },

  updateLocation: function($scope, lat, long) {
    $scope.task.destinationLatitude = lat;
    $scope.task.destinationLongitude = long;
    var markerSettings = TaskMapSettings.getMapSettings($scope);
    markerSettings.coords = {
      latitude: lat,
      longitude: long
    };
    $scope.marker = markerSettings;
  }
};
