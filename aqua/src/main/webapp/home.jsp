
<%
    HttpSession session1 = request.getSession();
Integer customerId = (Integer) session.getAttribute("customer_id");
String customerIdString = String.valueOf(customerId);
System.out.println(customerIdString);
String username = (String) session.getAttribute("username");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>The Aqua Infinity - Home</title>
<link rel="stylesheet" href="css/index.css" />
<link rel="stylesheet" href="css/fish.css" />

<%@include file="all_css.jsp"%>
<body>
	<%@include file="utilities/navbar.jsp"%>
	<section class="home">
		<video class="video-slide" src="resources/video/video.mp4" autoplay
			muted loop></video>
		<div class="home-content">
			<h1>Online Aquarium Store</h1>
			<a href="fish.jsp" class="add-to-cart-btn">SHOP NOW &#8594;</a>
		</div>
	</section>

	<!-- Featured Fish -->

	<section class="featured" id="featured">
		<div class="center-text">
			<h2 class="title">Featured Fishes</h2>
		</div>
		<div class="wrapper">
			<div class="product-card">
				<div class="product-image">
					<img src="resources/fish-images/blackmoscowguppy.jpeg"
						alt="Product Image" />
					<h3 class="card-title">Guppy</h3>
				</div>

			</div>
			<div class="product-card">
				<div class="product-image">
					<img src="resources/fish-images/greenglofish.jpg"
						alt="Product Image" />
					<h3 class="card-title">Glo Fish</h3>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<img src="resources/fish-images/Blue Betta.jpg" alt="Product Image" />
					<h3 class="card-title">Betta Fish</h3>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<img src="resources/fish-images/lemonTetra.jpeg"
						alt="Product Image" />
					<h3 class="card-title">Tetra Fish</h3>
				</div>
			</div>


		</div>
	</section>
	<!-- Featured Accessories -->
	<section class="featured" id="featured">
		<div class="center-text">
			<h2 class="title">Featured Accessories</h2>
		</div>
		<div class="wrapper">
			<div class="product-card">
				<div class="product-image">
					<img src="resources/accessories/Air Pump 3w Silent Series.jpg"
						alt="Product Image" />
					<h3 class="card-title">Pump</h3>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<img src="resources/accessories/filter.jpg" alt="Product Image" />
					<h3 class="card-title">Filter</h3>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<img src="resources/accessories/Sobo Led light 8w.jpg"
						alt="Product Image" />
					<h3 class="card-title">Light</h3>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<img src="resources/accessories/food.jpeg" alt="Product Image" />
					<h3 class="card-title">Food</h3>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<img src="resources/accessories/heater.jpg" alt="Product Image" />
					<h3 class="card-title">Heater</h3>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<img src="resources/accessories/stones.jpeg"
						alt="Product Image" />
					<h3 class="card-title">Stones</h3>
				</div>
			</div>
		</div>
	</section>

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
