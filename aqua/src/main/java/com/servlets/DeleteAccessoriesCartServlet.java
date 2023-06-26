package com.servlets;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.AccessoriesOrderDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteAccessoriesCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteAccessoriesCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long orderId = Long.parseLong(request.getParameter("order_id"));
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		AccessoriesOrderDao accessoriesOrderDao = context.getBean("accessoriesOrderDao", AccessoriesOrderDao.class);
		accessoriesOrderDao.deleteOrder(orderId);
		((ClassPathXmlApplicationContext) context).close();
		response.sendRedirect("cart.jsp");

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
