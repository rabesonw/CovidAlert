<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<style>
    body, html {
        height: 100%;
        margin: 0;
    }

    .bgimg {
        background-image: url('https://www.w3schools.com/w3images/forestbridge.jpg');
        height: 100%;
        background-position: center;
        background-size: cover;
        position: relative;
        color: white;
        font-family: "Courier New", Courier, monospace;
        font-size: 25px;
    }

    .topleft {
        position: absolute;
        top: 0;
        left: 16px;
    }

    .bottomleft {
        position: absolute;
        bottom: 0;
        left: 16px;
    }

    .middle {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        text-align: center;
    }

    hr {
        margin: auto;
        width: 40%;
    }

    a {
        color: white;
    }
</style>
<body onload="getLocation()">

<div class="bgimg">
    <div class="topleft">
        <p>CovidAlert</p>
    </div>
    <div class="middle">
        <h1>Welcome to CovidAlert</h1>
        <div>
            <p>
                We need your location for the application to function. Please select "Allow location".
                Your location will be stored for a month if you declare yourself positive to COVID-19.
            </p>
            <div id="demo">
                <form:form action="/createLocation" method="POST">
                    <label for="latitude">Your latitude</label>
                    <input  id="latitude" type="text" id="latitude" name="latitude"/>
                    <br/>
                    <label for="longitude">Your longitude</label>
                    <input id="longitude" type="text" id="longitude" name="longitude"/>
                    <button onclick=showPosition() type="button">Fill form</button>
                    <input type="submit" value="Activate Location" role="button" class="btn btn-lg btn-primary"/>
                </form:form>
            </div>
        </div>

    </div>
    <div class="bottomleft">
        <p>Alexandra, Emma and Weslie are working!</p>
        <p>
            <a href="/doLogout">Logout</a>
        </p>
    </div>
</div>
<script>
    var x = document.getElementById("demo");
    var latitude = document.getElementById("latitude");
    var longitude = document.getElementById("longitude")

    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.watchPosition(showPosition);
        } else {
            x.innerHTML = "Geolocation is not supported by this browser.";
        }
    }

    function showPosition(position) {
        latitude.value = position.coords.latitude;
        longitude.value = position.coords.longitude;
    }

    function showError(error) {
        switch(error.code) {
            case error.PERMISSION_DENIED:
                x.innerHTML = "User denied the request for Geolocation."
                break;
            case error.POSITION_UNAVAILABLE:
                x.innerHTML = "Location information is unavailable."
                break;
            case error.TIMEOUT:
                x.innerHTML = "The request to get user location timed out."
                break;
            case error.UNKNOWN_ERROR:
                x.innerHTML = "An unknown error occurred."
                break;
        }
    }
</script>
</body>
</html><%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
