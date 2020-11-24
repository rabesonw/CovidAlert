<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<link rel="stylesheet" href="css/stylesheet.css">
<script src="static/location.js"></script>
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

</body>
</html><%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
