<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>The Aqua Infinity - Register</title>

<link rel="stylesheet" href="css/register.css" />
<%@include file="all_css.jsp"%>
</head>
<body
	style="background-image: url('resources/icons/Loginbg.jpg'); background-repeat: no-repeat; background-position: center; background-size: cover;">
	<%@include file="utilities/navbar.jsp"%>

	<!-- Signup Page -->
	<div style="height: 70px"></div>
	<div class="register">
		<h1>Register</h1>
		<form action="RegisterServlet" id="LoginForm" method="post"
			enctype="multipart/form-data">
			<% String emailMessage = (String) request.getAttribute("emailMessage"); %>
			<% if (emailMessage != null) { %>
			<div class="message" style="color: red;"><%= emailMessage %></div>
			<% } %>
			<input type="text" placeholder="Email" name="email"
				value="<% if (request.getParameter("email") != null) { %><%= request.getParameter("email") %><% } %>"
				required />
			<% String phoneMessage = (String) request.getAttribute("phoneMessage"); %>
			<% if (phoneMessage != null) { %>
			<div class="message" style="color: red;"><%= phoneMessage %></div>
			<% } %>
			<input type="text" placeholder="Phone Number" name="phone"
				value="<% if (request.getParameter("phone") != null) { %><%= request.getParameter("phone") %><% } %>"
				required />
			<% String userMessage = (String) request.getAttribute("userMessage"); %>
			<% if (userMessage != null) { %>
			<div class="message" style="color: red;"><%= userMessage %></div>
			<% } %>
			<input type="text" placeholder="Username" name="username"
				value="<% if (request.getParameter("emusernameail") != null) { %><%= request.getParameter("username") %><% } %>"
				required />
			<% String passwordMessage = (String) request.getAttribute("passwordMessage"); %>
			<% if (passwordMessage != null) { %>
			<div class="message" style="color: red;"><%= passwordMessage %></div>
			<% } %>
			<input type="password" placeholder="Password" name="password"
				value="<% if (request.getParameter("password") != null) { %><%= request.getParameter("password") %><% } %>"
				required />
			<div class="message" style="color: red;"></div>
			<input type="submit" value="Register" />
		</form>
		<p>
			Already have an account? <a href="login.jsp">Login</a>
		</p>
	</div>
	<!-- Footer -->
	<%@include file="utilities/footer.jsp"%>



	<script type="text/javascript" src="js/withoutCart.js"></script>

</body>
</html>
