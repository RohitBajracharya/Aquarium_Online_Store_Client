package com.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.FishOrderDao;
import com.entities.FishOrder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@MultipartConfig
public class AddFishOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddFishOrderServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Date orderDate = new Date();
		String fishName = request.getParameter("fish-name");
		int fishPrice = Integer.parseInt(request.getParameter("fish-price"));
		int quantity = Integer.parseInt(request.getParameter("fish-quantity"));
		// String username = request.getParameter("username");
		Part fishImagePart = request.getPart("fish-image");
		InputStream fishImage = fishImagePart.getInputStream();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		for (int length = 0; (length = fishImage.read(buffer)) > 0;) {
			output.write(buffer, 0, length);
		}
		byte[] fishImageBytes = output.toByteArray();
		if (username == null || username.equals("")) {
			String loginMessage = "Please login first to add to cart";
			request.setAttribute("loginMessage", loginMessage);
			response.sendRedirect("login.jsp");
		} else {
			ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
			String deliveredStatus = "New Order";
			String checkoutStatus = "Not Checkout";
			FishOrderDao fishOrderDao = context.getBean("fishOrderDao", FishOrderDao.class);
			FishOrder fishOrder = new FishOrder(username, fishName, fishImageBytes, fishPrice, quantity,
					deliveredStatus, checkoutStatus, orderDate);
//			FishOrder fishOrder = new FishOrder(username, fishName, fishImageBytes, fishPrice, quantity,
//					deliveredStatus, checkoutStatus, orderDate);
			long r = fishOrderDao.insert(fishOrder);
			if (r > 0) {
				((ClassPathXmlApplicationContext) context).close();
				response.sendRedirect("cart.jsp");
			}
		}

	}

}
