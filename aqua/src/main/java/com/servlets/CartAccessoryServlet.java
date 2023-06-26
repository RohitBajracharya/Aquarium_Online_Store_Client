package com.servlets;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.AccessoriesOrderDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig
public class CartAccessoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CartAccessoryServlet() {
        super();
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long orderId = Long.parseLong(request.getParameter("order_id"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		
		AccessoriesOrderDao accessoryOrderDao = context.getBean("accessoriesOrderDao", AccessoriesOrderDao.class);
		accessoryOrderDao.updateProductQuantity(orderId,quantity);
		((ClassPathXmlApplicationContext) context).close();
		response.sendRedirect("cart.jsp");
	}

}
