package com.servlets;

import java.io.IOException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.AccessoriesOrderDao;
import com.dao.CheckoutDao;
import com.dao.CustomerDao;
import com.dao.FishOrderDao;
import com.entities.AccessoriesOrder;
import com.entities.Checkout;
import com.entities.Customer;
import com.entities.FishOrder;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@MultipartConfig
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckoutServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customerId=Integer.parseInt(request.getParameter("customerId"));
		String username=request.getParameter("username");
		 String paymentMethod = request.getParameter("method");
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		
		FishOrderDao fishOrderDao=context.getBean("fishOrderDao",FishOrderDao.class);
		List<FishOrder> orders = fishOrderDao.getAllFishOrdersByUsername(username);
		double fishTotal = 0;
		for (FishOrder order : orders) {
			fishTotal += order.getTotal();
		}
		
		AccessoriesOrderDao accessoriesOrderDao=context.getBean("accessoriesOrderDao",AccessoriesOrderDao.class);
		List<AccessoriesOrder> accessoriesOrders = accessoriesOrderDao.getAllAccessoriesOrdersByUsername(username);
		double accessoriesTotal = 0;
		for (AccessoriesOrder order : accessoriesOrders) {
			accessoriesTotal += order.getTotal();
		}
		double grandTotal=fishTotal+accessoriesTotal;
		
		CheckoutDao checkoutDao=context.getBean("checkoutDao",CheckoutDao.class);
		Checkout checkout = new Checkout();
		checkout.setGrandTotal(grandTotal);

		CustomerDao customerDao = context.getBean("customerDao", CustomerDao.class);
		Customer customer = customerDao.getCustomerById(customerId);
		checkout.setCheckoutId(customerId);
		checkout.setCustomerName(username);
		checkout.setCustomer(customer);
		checkout.setPaymentMethod(paymentMethod);
		int r=checkoutDao.insert(checkout,username);
		if(r>0) {
			HttpSession session = request.getSession();
		    session.setAttribute("checkout", "checkout");
			((ClassPathXmlApplicationContext) context).close();
			 String successMessage = "Order placed successfully";
		        request.setAttribute("successMessage", successMessage);
			RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");	
			rd.forward(request, response);
		}else {
			((ClassPathXmlApplicationContext) context).close();
			 String errorMessage = "Sorry, your order couldn't be placed. Please contact us by phone for more information.";
		        request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");	
			rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customerId=Integer.parseInt(request.getParameter("customerId"));
		String username=request.getParameter("username");
		String paymentMethod=request.getParameter("method");
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		
		FishOrderDao fishOrderDao=context.getBean("fishOrderDao",FishOrderDao.class);
		List<FishOrder> orders = fishOrderDao.getAllFishOrdersByUsername(username);
		double fishTotal = 0;
		for (FishOrder order : orders) {
			fishTotal += order.getTotal();
		}
		
		AccessoriesOrderDao accessoriesOrderDao=context.getBean("accessoriesOrderDao",AccessoriesOrderDao.class);
		List<AccessoriesOrder> accessoriesOrders = accessoriesOrderDao.getAllAccessoriesOrdersByUsername(username);
		double accessoriesTotal = 0;
		for (AccessoriesOrder order : accessoriesOrders) {
			accessoriesTotal += order.getTotal();
		}
		double grandTotal=fishTotal+accessoriesTotal;
		
		CheckoutDao checkoutDao=context.getBean("checkoutDao",CheckoutDao.class);
		Checkout checkout = new Checkout();
		checkout.setGrandTotal(grandTotal);

		CustomerDao customerDao = context.getBean("customerDao", CustomerDao.class);
		Customer customer = customerDao.getCustomerById(customerId);
		checkout.setCheckoutId(customerId);
		checkout.setCustomerName(username);
		checkout.setCustomer(customer);
		checkout.setPaymentMethod(paymentMethod);
		int r=checkoutDao.insert(checkout,username);
		if(r>0) {
			HttpSession session = request.getSession();
		    session.setAttribute("checkout", "checkout");
			((ClassPathXmlApplicationContext) context).close();
			String successMessage = "Order placed successfully";
	        request.setAttribute("successMessage", successMessage);
		RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");	
			rd.forward(request, response);
		}else {
			((ClassPathXmlApplicationContext) context).close();
			String errorMessage = "Sorry, your order couldn't be placed. Please contact us by phone for more information.";
	        request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
			rd.forward(request, response);
		}
	}
	

}
