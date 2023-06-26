
<%
    HttpSession session1 = request.getSession();
Integer customerId = (Integer) session.getAttribute("customer_id");
String customerIdString = String.valueOf(customerId);
System.out.println(customerIdString);
String username = (String) session.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>The Aqua Infinity - About</title>

<link rel="stylesheet" href="css/about.css" />
<%@include file="all_css.jsp"%>
</head>
<body>
	<%@include file="utilities/navbar.jsp"%>

	<!-- About -->
	<div class="aboutus">
		<div class="title-text">
			<h2>ABOUT US</h2>
			<p class="welcome-text">
				Welcome to <b>The Aqua Infinity</b>!
			</p>
			<p class="text">Our mission is to provide the highest quality
				products and exceptional customer service to all our valued
				customers. We are passionate about fish and the hobby of
				fishkeeping, and our knowledgeable team is dedicated to helping you
				create the perfect aquatic environment for your fish.</p>
			<p class="text">
				At <b>The Aqua Infinity</b>, we believe in creating a community of
				like-minded individuals who share a love for fish and the natural
				world.
			</p>
			<p class="text">Thank you for choosing us as your aquarium store,
				and we look forward to supporting you on your fishkeeping journey.</p>
		</div>
	</div>
	<div class="wrapper">
		<h1>Our Products</h1>
		<div class="team">
			<div class="team_member">
				<div class="team_img">
					<img src="resources/fish-images/blackmoscowguppy.jpeg"
						alt="Team_image" />
				</div>
				<h3>Fishes</h3>
				<p>We offer a wide variety of fishes like Tetra, Goldfish,
					Guppy, Snails, Glo Fish and Many More.</p>
			</div>
			<div class="team_member">
				<div class="team_img">
					<img src="resources/accessories/filter.jpg" alt="Team_image" />
				</div>
				<h3>Accessories</h3>
				<p>We offer variety of Heater, Filter, Pump, Thermometer,
					Sponges.</p>
			</div>
			<div class="team_member">
				<div class="team_img">
					<img src="resources/accessories/stones.jpeg" alt="Team_image" />
				</div>
				<h3>Decoration Items</h3>
				<p>We offer variety of Decoration Items like Colorful Stones,
					Black and White Sand, Colorful Sands, Plants and many more.</p>
			</div>
			<div class="team_member">
				<div class="team_img">
					<img src="resources/accessories/food.jpeg" alt="Team_image" />
				</div>
				<h3>Fish Food</h3>
				<p>We offer variety of healthy foods for your fish to keep your
					fish healthy.</p>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<%@include file="utilities/footer.jsp"%>


	<%
		if(username==null){
	%>
	<script type="text/javascript" src="js/withoutCart.js"></script>
	<%	
		}else{
	%>

	<script type="text/javascript" src="js/withCart.js"></script>
	<%
		}
	%>
</body>
</html>
