package com.servlets;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.FishOrderDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteCartServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long orderId = Long.parseLong(request.getParameter("order_id"));
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		FishOrderDao fishOrderDao = context.getBean("fishOrderDao", FishOrderDao.class);
		fishOrderDao.deleteOrder(orderId);
		((ClassPathXmlApplicationContext) context).close();
		response.sendRedirect("cart.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
