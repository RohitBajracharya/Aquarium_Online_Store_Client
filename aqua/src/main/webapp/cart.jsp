
<%
    HttpSession session1 = request.getSession();
Integer customerId = (Integer) session.getAttribute("customer_id");
String customerIdString = String.valueOf(customerId);
String username = (String) session.getAttribute("username");
String checkout=(String) session.getAttribute("checkout");
%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.io.File"%>
<%@ page import="java.nio.file.Files"%>
<%@ page import="java.util.Base64"%>
<%@page import="java.util.List"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="com.dao.FishOrderDao"%>
<%@page import="com.dao.AccessoriesOrderDao"%>
<%@page import="com.entities.FishOrder"%>
<%@page import="com.entities.AccessoriesOrder"%>

<%@page import="com.entities.Fish"%>
<%@page import="com.dao.FishDao"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>The Aqua Infinity - Cart</title>

<link rel="stylesheet" href="css/cart.css" />
<%@include file="all_css.jsp"%>
<script
	src="https://khalti.s3.ap-south-1.amazonaws.com/KPG/dist/2020.12.17.0.0.0/khalti-checkout.iffe.js"></script>
</head>
<body>
	<%@include file="utilities/navbar.jsp"%>
	<!-- About -->

	<div class="wrapper">
		<h1>Shopping Cart</h1>
		<div class="table-responsive">

			<table>
				<thead>
					<tr>

						<th class="name-col">Product</th>
						<th class="price-col">Price</th>
						<th class="quantity-col">Quantity</th>
						<th class="total-col">Total</th>

					</tr>
				</thead>
				<tbody>


					<%
							ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
    FishOrderDao fishOrderDao = context.getBean("fishOrderDao", FishOrderDao.class);
    List<FishOrder> allOrders = fishOrderDao.getFishNameAndQuantityByUsername(username);
    int grandTotal=0;
	int subTotal1=0;
	int subTotal2=0;
							for (FishOrder  o: allOrders) {
							%>
					<tr>
						<%System.out.println(o.getPrice());%>
						<td class="name-col"><%=o.getFishName()%></td>
						<td class="price-col"><%=o.getPrice()%></td>
						<td class="quantity-col">
							<form method="post" action="CartServlet"
								enctype="multipart/form-data">
								<input type="hidden" name="order_id" value="<%=o.getOrderId()%>">
								<input id="quantity-input-<%=o.getOrderId()%>" max="10"
									type="number" name="quantity" min="1"
									value="<%=o.getQuantity()%>">
								<div class="operation-btn">
									<button type="submit" class="update-btn">Update</button>
									<a class="delete-btn"
										href="DeleteCartServlet?order_id=<%=o.getOrderId()%>">Remove</a>
								</div>

							</form>

						</td>
						<%
					int price=o.getPrice();
					int quantity=o.getQuantity();
					subTotal1=price*quantity;
					
				%>
						<td class="total-col"><%=subTotal1%></td>


					</tr>
					<%
				}
			%>
					<%
							ApplicationContext context1 = new ClassPathXmlApplicationContext("config.xml");
    AccessoriesOrderDao accessoriesOrderDao = context1.getBean("accessoriesOrderDao", AccessoriesOrderDao.class);
    List<AccessoriesOrder> allAccessoriesOrders = accessoriesOrderDao.getAccessoriesNameAndQuantityByUsername(username);
    int grandTotal1 = 0;
System.out.println(allAccessoriesOrders);
							for (AccessoriesOrder  a: allAccessoriesOrders) {
							%>
					<tr>
						<%System.out.println("product name: "+a.getProductName());%>
						<td class="name-col"><%=a.getProductName()%></td>
						<td class="price-col"><%=a.getPrice()%></td>
						<td class="quantity-col"><form method="post"
								action="CartAccessoryServlet" enctype="multipart/form-data">
								<input type="hidden" name="order_id" value="<%=a.getOrderId()%>">
								<input id="quantity-input" type="number" name="quantity" min="1"
									max="10" value="<%=a.getQuantity()%>">
								<div class="operation-btn">
									<button type="submit" class="update-btn">Update</button>
									<a class="delete-btn"
										href="DeleteAccessoriesCartServlet?order_id=<%=a.getOrderId()%>">Remove</a>
								</div>
							</form></td>
						<%
					int price=a.getPrice();
					int quantity=a.getQuantity();
					subTotal2=price*quantity;
					
				%>
						<td class="total-col"><%=subTotal2%></td>


					</tr>
					<%
				}
			%>
					<tr>
						<td class="total-col"></td>


						<td>Grand Total</td>
						<%
						grandTotal=subTotal1+subTotal2;
						%>
						<td class="total-col"><%=grandTotal%></td>
						<td>
							<button class="checkout-btn" type="submit"
								onclick="openPaymentModal()">Checkout</button>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="checkout"></div>
		</div>
	</div>
	<div id="payment-modal" class="modal">
		<div class="modal-content">
			<span class="close" onclick="closePaymentModal()">&times;</span>
			<h2>Payment Form</h2>
			<!-- ... your existing form fields ... -->
			<div class="payment">
				<div>Choose Payment Option:</div>
				<div class="centered">
					<div class="payment-type">
						<div class="column">
							<form action="CheckoutServlet" method="post">
								<input type="hidden" name="method" value="cash"><input
									type="hidden" name="username" value="<%=username%>"> <input
									type="hidden" name="customerId" value="<%=customerId%>">
								<button class="cash-button">Cash on Delivery</button>
							</form>
						</div>
						<div class="column">

							<button id="payment-button">Pay with Khalti</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<% String successMessage = (String) request.getAttribute("successMessage"); %>
	<% if (successMessage != null) { %>
	<div id="popup" class="popup">
		<div class="popup-content">
			<span class="close" onclick="closePopup()">&times;</span>
			<h2>Success!</h2>
			<p style="color: green;"><%= successMessage %></p>
		</div>
	</div>
	<% } %>
	<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
	<% if (errorMessage != null) { %>
	<div id="popup" class="popup">
		<div class="popup-content">
			<span class="close" onclick="closePopup()">&times;</span>
			<h2>Failed!</h2>
			<p style="color: red;"><%= errorMessage %></p>
		</div>
	</div>
	<% } %>
	<!-- Footer -->
	<%@include file="utilities/footer.jsp"%>


	<script>
	var modal = document.getElementById("payment-modal");
	var paymentButton = document.getElementById("payment-button");
	var closePaymentButton = document.getElementsByClassName("close")[0];

	function openPaymentModal() {
	  modal.style.display = "block";
	}

	
	function closePaymentModal() {
	  modal.style.display = "none";
	}

	paymentButton.addEventListener("click", openPaymentModal);

	closePaymentButton.addEventListener("click", closePaymentModal);

	window.addEventListener("click", function (event) {
	  if (event.target == modal) {
	    closePaymentModal();
	  }
	});

</script>
	<script>

        var config = {
            // replace the publicKey with yours
            "publicKey": "test_public_key_80051e585f854b01aa9fd15f81a35731",
            "productIdentity": "<%=customerId%>",
            "productName": "<%=username%>",
            "productUrl": "http://gameofthrones.wikia.com/wiki/Dragons",
            "paymentPreference": [
                "KHALTI",
                "EBANKING",
                "MOBILE_BANKING",
                "CONNECT_IPS",
                "SCT",
                ],
            "eventHandler": {
            	 onSuccess (payload) {
                     console.log(payload);
                     window.location.href = "CheckoutServlet?username=<%=username%>&customerId=<%=customerId%>&method=khalti";	
                 },
                onError (error) {
                    console.log(error);
                },
                onClose () {
                    console.log('widget is closing');
                }
            }
        };

        var checkout = new KhaltiCheckout(config);
        var btn = document.getElementById("payment-button");
        btn.onclick = function () {
            // minimum transaction amount must be 10, i.e 1000 in paisa.
            checkout.show({amount: <%=grandTotal*100%>});
        }
    </script>
	<script>

		function showPopup() {
		    var popup = document.getElementById("popup");
		    popup.style.display = "block";
		}

			function closePopup() {
			    var popup = document.getElementById("popup");
			    popup.style.display = "none";
			}
			
			var successMessage = "<%= successMessage %>";
			if (successMessage) {
			    showPopup();
			}
			var errorMessage = "<%= errorMessage %>";
			if (errorMessage) {
			    showPopup();
			}
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
