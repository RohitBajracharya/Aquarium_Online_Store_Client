package com.servlets;

import java.io.IOException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.AccessoriesDao;
import com.entities.Accessories;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig
public class AccessoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AccessoriesServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		AccessoriesDao accessoriesDao = context.getBean("accessoriesDao", AccessoriesDao.class);

		String selectedAccessoriesCategory = request.getParameter("accessoriesCategory");
		System.out.println("category: "+selectedAccessoriesCategory);
		List<Accessories> accessories = accessoriesDao.getAccessoriesesByType(selectedAccessoriesCategory);
		System.out.println("Number of accessories: " + accessories.size());
		RequestDispatcher dispatcher = request.getRequestDispatcher("accessories.jsp");
		request.setAttribute("accessories", accessories);
		((ClassPathXmlApplicationContext) context).close();
		dispatcher.forward(request, response);
	}

}
