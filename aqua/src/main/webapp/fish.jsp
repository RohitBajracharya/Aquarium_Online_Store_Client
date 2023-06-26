
<%
    HttpSession session1 = request.getSession();
Integer customerId = (Integer) session.getAttribute("customer_id");
String customerIdString = String.valueOf(customerId);
System.out.println(customerIdString);
String username = (String) session.getAttribute("username");
System.out.println("session key in fish username:"+username);

%>

<%@page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashSet"%>

<%@ page import="java.util.Base64"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="com.dao.FishDao"%>
<%@page import="com.entities.Fish"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>The Aqua Infinity - Fish</title>
<link rel="stylesheet" href="css/fish.css" />
<%@include file="all_css.jsp"%>
<body>
	<%@include file="utilities/navbar.jsp"%>

	<!-- Featured Fish -->
	<div class="small-container" id="products">
		<div class="form-container">
			<h1 class="section-name">All Fishes</h1>

			<form action="FishServlet" method="post"
				enctype="multipart/form-data" id="option">
				<select name="fishType">
					<option value="">Select Fish Type</option>
					<%
    ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
    FishDao fishDao = context.getBean("fishDao", FishDao.class);
    List<String> allfishes = fishDao.getFishTypes();
    String selectedFishType = request.getParameter("fishType"); // get the selected value
    Set<String> uniqueFishes = new HashSet<String>(allfishes);

    if (selectedFishType != null) {
%>
					<option value="<%= selectedFishType %>" selected><%= selectedFishType %></option>
					<%
    }

    for (String fishType : uniqueFishes) {	
        if (!fishType.equals(selectedFishType)) {
%>
					<option value="<%= fishType %>"><%= fishType %></option>
					<%
        }
    }
%>


				</select>
				<button type="submit" id="displaybtn">Display</button>
			</form>
		</div>
		<section class="featured" id="featured">
			<div class="fish-wrapper">
				<%
					List<Fish> fishes = (List<Fish>)request.getAttribute("fishes");
					if (fishes != null && !fishes.isEmpty()) {
					for (Fish fish : fishes) {
				%>
				<div class="product-card">
					<div class="product-image">
						<%
 							byte[] imageData = fish.getFishImage();
							String base64Image=Base64.getEncoder().encodeToString(imageData);
						%>
						<img src="data:image/jpeg;base64,<%=base64Image%>"
							alt="Product Image" />
						<h3 class="card-title"><%=fish.getFishName()%></h3>
					</div>
					<div class="product-details">
					<h3 class="product-title"><%=fish.getFishName()%></h3>
						<p class="product-price"><%=fish.getFishPrice()%></p>
						<p class="product-stock">
							In Stock:
							<%=fish.getFishStock()%>
							<%=fish.getFishUnit()%></p>
						<form action="AddFishOrderServlet" id="cardform" method="post"
							enctype="multipart/form-data">
							<label for="quantity">Quantity:</label>
							<div class="quantity">
								<button class="minus-btn" type="button" name="button"
									data-id="<%=fish.getFishId()%>">
									<i class="bx bx-minus"></i>
								</button>
								<input type="number" id="quantity-<%=fish.getFishId()%>"
									name="fish-quantity" value="1" min="1"
									max="<%=fish.getFishStock()%>" />

								<button class="plus-btn" type="button" name="button"
									data-id="<%=fish.getFishId()%>">
									<i class="bx bx-plus"></i>
								</button>
							</div>
							<input type="hidden" name="fish-name"
								value="<%=fish.getFishName()%>"> <input type="hidden"
								name="username" value="<%=username%>"> <input
								type="hidden" name="fish-image" value="<%=base64Image%>">
							<input type="hidden" name="fish-price"
								value="<%=fish.getFishPrice()%>"> <input type="hidden"
								name="fish-id" value="<%=fish.getFishId()%>">
							<button type="submit" class="add-to-cart-btn">Add to
								Cart</button>
						</form>
					</div>
				</div>
				<%
					}

				}else {
							ApplicationContext contexts = new ClassPathXmlApplicationContext("config.xml");
							FishDao fishDaos = contexts.getBean("fishDao", FishDao.class);
							List<Fish> allfishess = fishDao.getAllFishes();
							int i=1;
							for (Fish  fishs: allfishess) {
				%>
				<div class="product-card">
					<%
 									byte[] imageDatas = fishs.getFishImage();
									String base64Image=Base64.getEncoder().encodeToString(imageDatas);
								%>
					<div class="product-image">
						<img src="data:image/jpeg;base64,<%=base64Image%>"
							alt="Product Image" />
						<h3 class="card-title"><%=fishs.getFishName()%></h3>
					</div>
					<div class="product-details">
					<h3 class="product-title"><%=fishs.getFishName()%></h3>
						<p class="product-price"><%=fishs.getFishPrice()%></p>
						<p class="product-stock">
							In Stock:
							<%=fishs.getFishStock()%>
							<%=fishs.getFishUnit()%></p>
						<form action="AddFishOrderServlet" id="cardform" method="post"
							enctype="multipart/form-data">
							<label for="quantity">Quantity:</label>
							<div class="quantity">
								<button class="minus-btn" type="button" name="button"
									data-id="<%=fishs.getFishId()%>">
									<i class="bx bx-minus"></i>
								</button>
								<input type="number" id="quantity-<%=fishs.getFishId()%>"
									name="fish-quantity" value="1" min="1"
									max="<%=fishs.getFishStock()%>" />

								<button class="plus-btn" type="button" name="button"
									data-id="<%=fishs.getFishId()%>">
									<i class="bx bx-plus"></i>
								</button>
							</div>
							<input type="hidden" name="fish-name"
								value="<%=fishs.getFishName()%>"> <input type="hidden"
								name="username" value="<%=username%>"> <input
								type="hidden" name="fish-image" value="<%=base64Image%>">
							<input type="hidden" name="fish-price"
								value="<%=fishs.getFishPrice()%>"> <input type="hidden"
								name="fish-id" value="<%=fishs.getFishId()%>">
							<button type="submit" class="add-to-cart-btn">Add to
								Cart</button>
						</form>
					</div>
				</div>
				<%
							i++;
							}
}
							%>

			</div>
		</section>
	</div>

	<!-- Footer -->
	<%@include file="utilities/footer.jsp"%>
	<script>
  const minusBtns = document.querySelectorAll(".minus-btn");
  const plusBtns = document.querySelectorAll(".plus-btn");

  minusBtns.forEach((btn) => {
    btn.addEventListener("click", function() {
      const id = btn.getAttribute("data-id");
      const quantityInput = document.querySelector(`#quantity-${id}`);
      let quantity = parseInt(quantityInput.value);
      if (quantity > 1) {
        quantity--;
        quantityInput.value = quantity;
      }
    });
  });

  plusBtns.forEach((btn) => {
    btn.addEventListener("click", function() {
      const id = btn.getAttribute("data-id");
      const quantityInput = document.querySelector(`#quantity-${id}`);
      let quantity = parseInt(quantityInput.value);
      const maxStock = parseInt(quantityInput.getAttribute("max"));
      if (quantity < maxStock) {
        quantity++;
        quantityInput.value = quantity;
      }
    });
  });
</script>


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
