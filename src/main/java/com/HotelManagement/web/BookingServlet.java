package com.HotelManagement.web;

import com.HotelManagement.dao.AvailabilityRoomDAO;
import com.HotelManagement.dao.BookingDAO;
import com.HotelManagement.model.Booking;
import com.HotelManagement.model.AvailabilityRoom;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roomNumber = request.getParameter("roomNumber");
        String checkIn = request.getParameter("checkIn");
        String checkOut = request.getParameter("checkOut");

        request.setAttribute("roomNumber", roomNumber);
        request.setAttribute("checkIn", checkIn);
        request.setAttribute("checkOut", checkOut);

        request.getRequestDispatcher("/booking-confirmation.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	int customerID = (int) session.getAttribute("customerID");
    	String email = (String) session.getAttribute("email");
    	System.out.println(email + "email");
//        int customerID = Integer.parseInt(request.getParameter("customer_id"));
//        String email = request.getParameter("email");
    	int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
        String roomType = request.getParameter("roomType");
        String roomView = request.getParameter("roomView");
        String checkInStr = request.getParameter("checkIn");
        String checkOutStr = request.getParameter("checkOut");
        int price = Integer.parseInt("1000");

        // Parse the check-in and check-out dates
        Date checkIn = null;
        Date checkOut = null;
        try {
            checkIn = Date.valueOf(checkInStr);
            checkOut = Date.valueOf(checkOutStr);
        } catch (IllegalArgumentException e) {
            // Handle the error if the date format is incorrect
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format");
            return;
        }
        
        // Create booking
        Booking booking = new Booking();
        booking.setCustomerID(customerID);
        booking.setEmail(email);
        booking.setCheckInDate(checkIn);
        booking.setCheckOutDate(checkOut);
        booking.setRoomType(roomType);
        booking.setRoomView(roomView);
        booking.setPrice(price);

        BookingDAO bookingDAO = new BookingDAO();
        int bookingID = bookingDAO.addBooking(booking);

		
		  // Update room availability 
        AvailabilityRoom availabilityRoom = new AvailabilityRoom(); 
		  availabilityRoom.setBookingId(bookingID);
		  availabilityRoom.setRoomNumber(roomNumber);
		  availabilityRoom.setStatus("Booked"); 
		  availabilityRoom.setRoomType(roomType);
		  availabilityRoom.setRoomView(roomView); 
		  availabilityRoom.setCheckIn(checkIn);
		  availabilityRoom.setCheckOut(checkOut);
		  
		  AvailabilityRoomDAO availabilityRoomDAO = new AvailabilityRoomDAO();
		  availabilityRoomDAO.updateAvailabilityRoom(availabilityRoom);
		 

        // Redirect to booking confirmation
        response.sendRedirect("booking?bookingID=" + bookingID + "&checkIn=" + checkIn + "&checkOut=" + checkOut);
    }
}
