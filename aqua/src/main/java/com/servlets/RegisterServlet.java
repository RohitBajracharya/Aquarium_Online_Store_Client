package com.servlets;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


@MultipartConfig
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		CustomerDao customerDao = context.getBean("customerDao", CustomerDao.class);

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		System.out.println(username+","+email+","+phone+","+password );
		// email validation
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		Pattern emailPattern = Pattern.compile(emailRegex);
		Matcher emailMatcher = emailPattern.matcher(email);
		// phone number validation
		String phoneRegex = "^[0-9]+$";
		Pattern phonePattern = Pattern.compile(phoneRegex);
		Matcher phoneMatcher = phonePattern.matcher(phone);
		
		String emailMessage=null;
		String userMessage=null;
		String phoneMessage=null;
		String passwordMessage=null;
		
		if (!emailMatcher.matches()) {
			emailMessage = "Please enter a valid email address";	
		}
		if (!phoneMatcher.matches() && phone.length()!=10) {
			phoneMessage = "Phone number must be 10 digits number.";
		}
		if(password.length()<8) {
			passwordMessage="Password must be more than 8 letters";
		}
		if(username.length()<8) {
			userMessage="Username must be more than 8 letters";
		}
		if (emailMessage != null || phoneMessage != null || userMessage!=null || passwordMessage!=null) {
			request.setAttribute("email", email);
		    request.setAttribute("phone", phone);
		    request.setAttribute("username", username);
		    request.setAttribute("password", password);
		    request.setAttribute("emailMessage", emailMessage);
		    request.setAttribute("phoneMessage", phoneMessage);
		    request.setAttribute("userMessage", userMessage);
		    request.setAttribute("passwordMessage", passwordMessage);
		    RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
		    rd.forward(request, response);
		}else {
			Customer customer = new Customer(username, email, phone, password);
			int r = customerDao.insert(customer);
			if (r > 0) {
				((ClassPathXmlApplicationContext) context).close();
				response.sendRedirect("login.jsp");
			}
		}
		
		
		
		
	}

}
