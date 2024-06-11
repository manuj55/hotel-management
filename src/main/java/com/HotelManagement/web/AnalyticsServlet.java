package com.HotelManagement.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.HotelManagement.dao.BookingDAO;
import com.HotelManagement.dao.CustomerDAO;
import com.HotelManagement.dao.RoomDAO;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/Analytics")
public class AnalyticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private CustomerDAO customerDAO = new CustomerDAO();
	    private BookingDAO bookingDAO = new BookingDAO();
	    private RoomDAO roomDAO = new RoomDAO();
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int totalCustomers = customerDAO.getTotalCustomers();
        int totalBookings = bookingDAO.getTotalBookings();
        int totalAvailableRooms = roomDAO.getTotalAvailableRooms();
        
        System.out.println("Total Customers: " + totalCustomers);
        System.out.println("Total Bookings: " + totalBookings);
        System.out.println("Total Available Rooms: " + totalAvailableRooms);
        
        
        request.setAttribute("totalCustomers", totalCustomers);
        request.setAttribute("totalBookings", totalBookings);
        request.setAttribute("totalAvailableRooms", totalAvailableRooms);
        request.getRequestDispatcher("analytics/Analytics.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // TODO Auto-generated
	 * method stub doGet(request, response); }
	 */
}
