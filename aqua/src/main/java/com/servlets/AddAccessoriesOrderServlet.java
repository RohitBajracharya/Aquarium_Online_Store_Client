package com.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.AccessoriesOrderDao;
import com.entities.AccessoriesOrder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;;

@MultipartConfig
public class AddAccessoriesOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddAccessoriesOrderServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Date orderDate=new Date();
		String productName = request.getParameter("product-name");
		int productPrice = Integer.parseInt(request.getParameter("product-price"));
		int quantity = Integer.parseInt(request.getParameter("product-quantity"));

		Part productImagePart = request.getPart("product-image");
		InputStream productImage = productImagePart.getInputStream();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		for (int length = 0; (length = productImage.read(buffer)) > 0;) {
			output.write(buffer, 0, length);
		}
		byte[] productImageBytes = output.toByteArray();
		if (username == null || username.equals("")) {
			String loginMessage = "Please login first to add to cart";
			request.setAttribute("loginMessage", loginMessage);
			response.sendRedirect("login.jsp");
		} else {
			ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
			AccessoriesOrderDao accessoriesOrderDao = context.getBean("accessoriesOrderDao", AccessoriesOrderDao.class);
			String deliveredStatus="New Order";
			String checkoutStatus="Not Checkout";
			AccessoriesOrder accessoriesOrder = new AccessoriesOrder(username, productName, productImageBytes,
					productPrice, quantity,deliveredStatus,checkoutStatus,orderDate);
			long r = accessoriesOrderDao.insert(accessoriesOrder);
			if (r > 0) {
				((ClassPathXmlApplicationContext) context).close();
				response.sendRedirect("cart.jsp");
			}
		}
	}

}
