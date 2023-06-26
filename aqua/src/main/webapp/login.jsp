<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>The Aqua Infinity - Login</title>

<link rel="stylesheet" href="css/login.css" />
<%@include file="all_css.jsp"%>
</head>
<body
	style="background-image: url('resources/icons/Loginbg.jpg'); background-repeat: no-repeat; background-position: center; background-size: cover;">
	<%@include file="utilities/navbar.jsp"%>

	<!-- Login Page -->
	<div style="height: 70px"></div>
	 <div class="login">
      <h1>Login</h1>
      <form action="LoginServlet" id="LoginForm" method="post"
        enctype="multipart/form-data">
        <% String loginMessage = (String) request.getAttribute("loginMessage"); %>
        <% if (loginMessage != null) { %>
        <div class="message" style="color: red;"><%= loginMessage %></div>
        <% } %>
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <% if (errorMessage != null) { %>
        <div class="message" style="color: red;"><%= errorMessage %></div>
        <% } %>
        <input type="text" placeholder="Username" name="username">
        <input type="password" placeholder="Password" name="password">
        <div class="message"></div>
        <input type="submit" value="Login">
      </form>
      <p>Don't have an account? <a href="register.jsp">Sign up</a></p>
    </div>
	<!-- Footer -->
	<%@include file="utilities/footer.jsp"%>

	<script type="text/javascript" src="js/withoutCart.js"></script>

</body>
</html>
