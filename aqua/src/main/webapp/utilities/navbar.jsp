<header class="header">
	<div class="logo">
		<img src="resources/icons/logo.png"
			style="width: 50%; height: 30%; max-width: 200px" id="logo-img" />
	</div>

	<div class="menu-btn"></div>
	<div class="navigation">
		<div class="navigation-items">
			<a id="homeurl" href="home.jsp">Home</a> <a id="fishurl"
				href="fish.jsp">Fish</a> <a id="accessoriesurl"
				href="accessories.jsp">Accessories</a> <a id="abouturl"
				href="about.jsp">About</a> 
				
				<% if (session.getAttribute("username") == null) { %>
				<a id="loginurl" href="login.jsp">Login</a>
			
			<% } else { %>
			<a id="carturl" href="cart.jsp"><img id="cartIcon"
				src="resources/icons/cart-white.svg" width="30px" height="30px"
				id="cart-icon" /> </a>
				<a id="logouturl" href="logout.jsp">Logout</a>
				
			<% } %>
				
		</div>
	</div>
</header>
