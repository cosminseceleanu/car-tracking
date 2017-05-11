var TaskMapSettings = {
  getMapSettings: function ($scope, zoom) {
    return {
      center: {
        latitude: 44.4377397,
        longitude: 25.9542109
      },
      control: {},
      zoom: zoom || 10
    };
  },

  getMarkersSettings: function ($scope) {
    var markers = [];
    markers[0] = TaskMapSettings.createMarkerSettings(
      $scope,
      $scope.task.destinationLatitude || 44.4477397,
      $scope.destinationLongitude || 25.9542109,
      0, "Destinatie"
    );
    markers[1] = TaskMapSettings.createMarkerSettings(
      $scope,
      $scope.task.sourceLatitude || 44.430259,
      $scope.sourceLongitude || 26.108731,
      1, "Sursa",
      "https://maps.google.com/mapfiles/ms/icons/blue-dot.png"
    );

    return markers;
  },

  createMarkerSettings: function ($scope, lat, lng, id, labelContent, icon) {
    return {
      id: id,
      coords: {
        latitude: lat,
        longitude: lng
      },
      options: {
        icon: icon,
        draggable: true,
        labelClass:'marker_labels',
        labelAnchor: "25 45",
        labelContent: "<strong>" + labelContent + "</strong>"
      },
      events: {
        dragend: function (marker, eventName, args) {
          var lat = marker.getPosition().lat();
          var lon = marker.getPosition().lng();
          if (marker.key === 0) {
            $scope.task.destinationLatitude = lat;
            $scope.task.destinationLongitude = lon;
          } else {
            $scope.task.sourceLatitude = lat;
            $scope.task.sourceLongitude = lon;
          }
        }
      }
    }
  }
};
