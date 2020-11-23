
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Sign up</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Login Page">
    <title>Page d'authentification</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <meta name="theme-color" content="#563d7c">
    <style>
        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }

    </style>
</head>
<body>
<div class="container">
    <h2>My Account</h2>
    <form:form modelAttribute="user" method="GET" action="/users/{id}">
        <form:errors path="*" cssClass="errorblock" element="div"/>
        <form:input type="text" path="username"/>
        <div><label for="username"></label>Username: <input class="form-control" type="text" name="username" id="username" path="username"></div>
        <div><label for="first_name">First name: </label><input class="form-control" type="text" name="first_name" id="first_name" placeholder="firstname"></div>
        <div><label for="last_name">Last name: </label><input class="form-control" type="text" name="last_name" id="last_name" placeholder="Enter Last Name"></div>
        <div><label for="email">Email: </label><input class="form-control" type="email" name="email" id="email" placeholder="Enter Email Address"></div>
        <div><label for="phone_number">Phone Number: </label><input class="form-control" type="text" name="phone_number" id="phone_number" placeholder="Enter Phone Number"></div>
        <div><label for="password">Password: </label><input class="form-control" type="password" name="password" id="password" placeholder="Enter Password"></div>
        <div><label for="matchingPassword">Confirm Password: </label><input class="form-control" type="password" name="matchingPassword" id="matchingPassword" placeholder="Confirm Entered Password above"></div>
        <div><input type="submit" class="btn btn-lg btn-primary" style="margin-top: 20px;" role="button" value="Signup"></div>
    </form:form>
</div>
</body>
</html>
