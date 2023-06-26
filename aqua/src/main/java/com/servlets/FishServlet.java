package com.servlets;

import java.io.IOException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.FishDao;
import com.entities.Fish;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig
public class FishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FishServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		FishDao fishDao = context.getBean("fishDao", FishDao.class);

		String selectedFishType = request.getParameter("fishType");
		List<Fish> fishes = fishDao.getFishesByType(selectedFishType);
		System.out.println("Number of fishes: " + fishes.size());
		// Get the RequestDispatcher for the JSP page
		RequestDispatcher dispatcher = request.getRequestDispatcher("fish.jsp");

		// Set the fishes attribute on the request object
		request.setAttribute("fishes", fishes);
		((ClassPathXmlApplicationContext) context).close();
		// Forward the request to the JSP page
		dispatcher.forward(request, response);

	}

}
