MapHandler = {
    map: null,
    geomArray: new Array(),
    markers: new Array(),
    mapContainer: null,
    setBounds: function (bounds) {
        MapHandler.bounds = bounds;
    },
    getBounds: function () {
        return MapHandler.bounds;
    },
    clearAutoLatLon: function () {
        MapHandler.Mlat = null;
        MapHandler.Mlon = null;
    },
    clearAll: function () {
        MapHandler.loader.hide();
        this.map.fitBounds(MapHandler.mapbounds);
    },
    setMapBound: function (bounds) {
        MapHandler.map.fitBounds(bounds);
    },
    /* bounding the map according to poly line*/
    mapBounds: function () {
        return this.map.getBounds();
    },
    getMap: function () {
        return this.map;
    },
    setCenter: function (lat, lng, zoom) {
        this.map.setView([lat, lng], zoom);
    },
//    addLoader: function() {
//        MapHandler.loader = L.control.loader().addTo(MapHandler.map);
//    },
    removeRoutMarkers: function () {
        var length = MapHandler.routMarkers.length;
        while (length > 0) {
            var marker = MapHandler.routMarkers.pop();
            MapHandler.map.removeLayer(marker);
            length--;
        }
    },
    removePopUP: function () {
        if ($(".leaflet-popup-close-button")[0] != undefined) {
            $(".leaflet-popup-close-button")[0].click();
        }
    }
    ,
    /* initilazing map  and loading it to jsp*/
    initialise: function (mapContainer, centerLat, centerLng, zoom) {
        MapHandler.mapContainer = mapContainer;
        this.map = new MapmyIndia.Map(mapContainer, {
            center: [centerLat, centerLng],
            zoomControl: true,
            hybrid: true
        });
        this.map.setZoom(zoom);
        MapHandler.mapbounds = this.map.getBounds();
    },
    plotMarker: function (lat, lng, imageIcon, html) {// for plotting single
        // marker on map
        var myIcon = L.icon({
            iconUrl: imageIcon,
            iconRetinaUrl: imageIcon,
            iconSize: [10, 10],
            iconAnchor: [8, 2]
        });
        var marker = L.marker([lat, lng], {
            icon: myIcon
        });
        if (html != undefined) {
            marker.bindPopup(html);
        }
        this.markers.push(marker);
        marker.addTo(this.map);
        // marker.addTo(map);
         return marker;
    },
    removeMarkers: function (markerArray) {
        var length = markerArray.length;
        while (length > 0) {
            var marker = markerArray.pop();
            if (marker != undefined)
                MapHandler.map.removeLayer(marker);
            length--;
        }
    },
    plotGeom: function (geojson, color, fillColor, fillOpacity, opacity, weight) {
        var fillOpacity_ = 0.4;
        if (fillOpacity) {
            fillOpacity_ = fillOpacity;
        }
        var opacity_ = 1;
        if (opacity) {
            opacity_ = opacity;
        }
        var weight_ = 1;
        if (weight) {
            weight_ = weight;
        }
        var myStyle = {
            "color": color,
            "fillColor": fillColor,
            "weight": weight_,
            "fillOpacity": fillOpacity_,
            "opacity": opacity_
        };
        var geojsonData = {
            "type": "Feature",
            "geometry": geojson
        };
        var polyLayer = L.geoJson(geojsonData, {
            style: myStyle
        }).addTo(MapHandler.map);
        return polyLayer;
    },
    fitBoundOnMap: function (layer) {
        debugger;
//        if (layer.length > 0) {
//            var featureGroup = L.featureGroup(layer);
//            MapHandler.map.fitBounds(featureGroup.getBounds());
//        }
      
            var maxX = Array.max(layer);
            var maxY = Array.max(layer);
            var minX = Array.min(layer);
            var minY = Array.min(layer);
            var southWest = new L.LatLng(minY, minX);
            var northEast = new L.LatLng(maxY, maxX);
            var bound = new L.LatLngBounds(southWest, northEast);
            MapHandler.map.fitBounds(bound);

   var featureGroup = L.featureGroup(layer); map.fitBounds(featureGroup.getBounds());
        }
    
};