package com.HotelManagement.web;

import com.HotelManagement.model.Room;
import com.HotelManagement.dao.RoomDAO;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.sql.Date;

@WebServlet("/available-rooms-test")
public class RoomAvailabilityServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RoomDAO roomDAO;

    @Override
    public void init() throws ServletException {
        roomDAO = new RoomDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    
     throws ServletException, IOException {
        String roomType = request.getParameter("roomType");
        String roomView = request.getParameter("roomView");
        String checkIn = request.getParameter("checkInDate");
        String checkOut = request.getParameter("checkOutDate");
        
        Date checkInDate = Date.valueOf(checkIn);
        Date checkOutDate = Date.valueOf(checkOut);

        List<Room> availableRooms = roomDAO.getAvailableRooms(roomType, roomView, checkInDate, checkOutDate);
        request.setAttribute("availableRooms", availableRooms);
        
        request.getRequestDispatcher("/available-rooms.jsp").forward(request, response);
    }
}
