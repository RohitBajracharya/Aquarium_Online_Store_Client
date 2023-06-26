
<%
    HttpSession session1 = request.getSession();
Integer customerId = (Integer) session.getAttribute("customer_id");
String customerIdString = String.valueOf(customerId);
System.out.println(customerIdString);
String username = (String) session.getAttribute("username");
%>

<%@page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashSet"%>
<%@ page import="java.util.Base64"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="com.dao.AccessoriesDao"%>
<%@page import="com.entities.Accessories"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>The Aqua Infinity - Accessories</title>
<link rel="stylesheet" href="css/fish.css" />
<%@include file="all_css.jsp"%>
<body>
	<%@include file="utilities/navbar.jsp"%>

	<!-- Featured Accessories -->
	<div class="small-container" id="products">
		<div class="form-container">
			<h1>All Accessories</h1>

			<form action="AccessoriesServlet" method="post"
				enctype="multipart/form-data" id="option">
				<select name="accessoriesCategory">
					<option value="1">Select Accessories Category</option>
					<%
    ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
    AccessoriesDao accessoriesDao = context.getBean("accessoriesDao", AccessoriesDao.class);
    List<String> allaccessories = accessoriesDao.getAccessoriesTypes();
    String selectedAccessoriesCategory = request.getParameter("accessoriesCategory"); 
    Set<String> uniqueAccessories = new HashSet<String>(allaccessories);

    if (selectedAccessoriesCategory != null) {
%>
					<option value="<%= selectedAccessoriesCategory %>" selected><%= selectedAccessoriesCategory %></option>
					<%
    }

    for (String accessoriesCategory : uniqueAccessories) {	
        if (!accessoriesCategory.equals(selectedAccessoriesCategory)) {
%>
					<option value="<%= accessoriesCategory %>"><%= accessoriesCategory %></option>
					<%
        }
    }
%>

				</select>
				<button type="submit" class="add-to-cart-btn" id="displaybtn">Display</button>
			</form>
		</div>
		<section class="featured" id="featured">
			<div class="fish-wrapper">
				<%
					List<Accessories> accessories = (List<Accessories>) request.getAttribute("accessories");
					if (accessories != null && !accessories.isEmpty()) {
					for (Accessories accessory : accessories) {
				%>
				<div class="product-card">
					<div class="product-image">
						<%
 							byte[] imageData = accessory.getProductImage();
							String base64Image=Base64.getEncoder().encodeToString(imageData);
						%>
						<img src="data:image/jpeg;base64,<%=base64Image%>"
							alt="Product Image" />
						<h3 class="card-title"><%=accessory.getProductName()%></h3>
					</div>
					<div class="product-details">
						<h3 class="product-title"><%=accessory.getProductName()%></h3>
						<p class="product-price"><%=accessory.getProductPrice()%></p>
						<p class="product-stock">
							In Stock:
							<%=accessory.getProductStock()%>
							<%=accessory.getProductUnit()%></p>
						<form action="AddAccessoriesOrderServlet" method="post"
							enctype="multipart/form-data"  class="product-form">
							<label for="quantity">Quantity:</label>
							<div class="quantity">
								<button class="minus-btn" type="button" name="button"
									data-id="<%=accessory.getProductId()%>">
									<i class="bx bx-minus"></i>
								</button>
								<input type="text" id="quantity-<%=accessory.getProductId()%>"
									name="product-quantity" value="1" min="1"
									max="<%=accessory.getProductStock()%>" />
								<button class="plus-btn" type="button" name="button"
									data-id="<%=accessory.getProductId()%>">
									<i class="bx bx-plus"></i>
								</button>
							</div>
							<input type="hidden" name="product-name"
								value="<%=accessory.getProductName()%>"> <input
								type="hidden" name="username" value="<%=username%>"> <input
								type="hidden" name="product-image" value="<%=base64Image%>">
							<input type="hidden" name="product-price"
								value="<%=accessory.getProductPrice()%>"> <input
								type="hidden" name="product-id"
								value="<%=accessory.getProductId()%>">
							<button type="submit" class="add-to-cart-btn">Add to
								Cart</button>
						</form>
					</div>
				</div>
				<%
					}
				}else {
							ApplicationContext contexts = new ClassPathXmlApplicationContext("config.xml");
							AccessoriesDao accessoriesDaos = contexts.getBean("accessoriesDao", AccessoriesDao.class);
							List<Accessories> allAccessoriess = accessoriesDaos.getAllAccessorieses();
							int i=1;
							for (Accessories  accessoriess: allAccessoriess) {
				%>
				<div class="product-card">
					<div class="product-image">
						<%
 							byte[] imageData = accessoriess.getProductImage();
							String base64Image=Base64.getEncoder().encodeToString(imageData);
						%>
						<img src="data:image/jpeg;base64,<%=base64Image%>"
							alt="Product Image" />
						<h3 class="card-title"><%=accessoriess.getProductName()%></h3>
					</div>
					<div class="product-details">
						<h3 class="product-title"><%=accessoriess.getProductName()%></h3>
						<p class="product-price"><%=accessoriess.getProductPrice()%></p>
						<p class="product-stock">
							In Stock:
							<%=accessoriess.getProductStock()%>

							<%=accessoriess.getProductUnit()%></p>
						<form action="AddAccessoriesOrderServlet" method="post"
							enctype="multipart/form-data" class="product-form">
							<label for="quantity">Quantity:</label>
							<div class="quantity">
								<button class="minus-btn" type="button" name="button"
									data-id="<%=accessoriess.getProductId()%>">
									<i class="bx bx-minus"></i>
								</button>
								<input type="text"
									id="quantity-<%=accessoriess.getProductId()%>"
									name="product-quantity" value="1" min="1"
									max="<%=accessoriess.getProductStock()%>" />
								<button class="plus-btn" type="button" name="button"
									data-id="<%=accessoriess.getProductId()%>">
									<i class="bx bx-plus"></i>
								</button>
							</div>
							<input type="hidden" name="product-name"
								value="<%=accessoriess.getProductName()%>"> <input
								type="hidden" name="username" value="<%=username%>"> <input
								type="hidden" name="product-image" value="<%=base64Image%>">
							<input type="hidden" name="product-price"
								value="<%=accessoriess.getProductPrice()%>"> <input
								type="hidden" name="product-id"
								value="<%=accessoriess.getProductId()%>">
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
