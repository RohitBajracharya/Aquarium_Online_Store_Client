package com.servlets;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.CustomerDao;
import com.entities.Customer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@MultipartConfig
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
        super();
 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		CustomerDao customerDao = context.getBean("customerDao", CustomerDao.class);
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		Customer customer=new Customer(username,password);
		int r=customerDao.getCustomer(customer); 
		System.out.println(r);
		if(r>0) {

			int customerId = customerDao.getCustomerIdByUsername(username);
		    HttpSession session = request.getSession();
		    session.setAttribute("customer_id", customerId);
		    session.setAttribute("username",username);
			((ClassPathXmlApplicationContext) context).close();
			if(session.getAttribute("customer_id") == null) {
			    System.out.println("Session attribute 'customer_id' not set"+customerId);
			    System.out.println("Session attribute 'username' not set"+username);
			} else {
			    System.out.println("Session attribute 'customer_id' set"+customerId);
			    System.out.println("Session attribute 'username' set"+username);
			}
			response.sendRedirect("home.jsp");
		}else {
	        String errorMessage = "Wrong username or password";
	        request.setAttribute("errorMessage", errorMessage);
	        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	        rd.forward(request, response);
	    }
	}

}
