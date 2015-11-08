/**
 * Created by davidraleigh on 11/7/15.
 */
var map = L.map('map').setView([51.505, -0.09], 13);


//http://otile1.mqcdn.com/tiles/1.0.0/map/8/126/87.jpg
L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
    maxZoom: 18,
    id: 'davidraleigh.kh7og6i2',
    accessToken: 'pk.eyJ1IjoiZGF2aWRyYWxlaWdoIiwiYSI6InE0UnFZUGMifQ.lu_PnQlTypNzQ10tBGCkdw'
}).addTo(map);