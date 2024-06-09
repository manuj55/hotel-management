package com.HotelManagement.web;

import com.HotelManagement.dao.RoomDAO;
import com.HotelManagement.model.Room;

import com.HotelManagement.dao.CategoryDAO;
import com.HotelManagement.model.Category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.List;


@WebServlet("/available")
public class AvailableRoomsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	//added for testing need to remove after integration
    	HttpSession session = request.getSession();
    	session.setAttribute("customerID", 1);
    	session.setAttribute("email", "johndoe@example.com");
    	
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categories = categoryDAO.getAllCategories();
System.out.print(categories+"hit");
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/room-search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roomType = request.getParameter("roomType");
        String roomView = request.getParameter("roomView");
        String checkIn = request.getParameter("checkIn");
        String checkOut = request.getParameter("checkOut");

		/*
		 * Date checkInDate = Date.valueOf(checkIn); Date checkOutDate =
		 * Date.valueOf(checkOut);
		 */
        
        Date checkInDate = null;
        Date checkOutDate = null;

        try {
            if (checkIn != null && !checkIn.isEmpty()) {
                checkInDate = Date.valueOf(checkIn);
            }
            if (checkOut != null && !checkOut.isEmpty()) {
                checkOutDate = Date.valueOf(checkOut);
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Invalid date format. Please use YYYY-MM-DD.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        if (checkInDate == null || checkOutDate == null) {
            request.setAttribute("error", "Check-in and Check-out dates are required.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        RoomDAO roomDAO = new RoomDAO();
        List<Room> availableRooms = roomDAO.getAvailableRooms(roomType, roomView, checkInDate, checkOutDate);

        request.setAttribute("availableRooms", availableRooms);
        request.setAttribute("checkInDate", checkInDate);
        request.setAttribute("checkOutDate", checkOutDate);
        request.getRequestDispatcher("available-rooms.jsp").forward(request, response);
    }
}

