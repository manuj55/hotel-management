package com.HotelManagement.web;

import com.HotelManagement.dao.ReservationsDAO;

import java.util.Enumeration;

import com.HotelManagement.dao.CategoryDAO;
import com.HotelManagement.model.Reservations;
import com.HotelManagement.model.Category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/reservations")
public class ReservationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CategoryDAO categoryDAO;
    private ReservationsDAO reservationsDAO;

    @Override
    public void init() throws ServletException {
    	categoryDAO = new CategoryDAO();
        reservationsDAO = new ReservationsDAO();
    }
    
    private void printRequestDetails(HttpServletRequest request) {
        System.out.println("---- HttpServletRequest Details ----");

        // Print request method
        System.out.println("Method: " + request.getMethod());

        // Print request URI
        System.out.println("Request URI: " + request.getRequestURI());

        // Print request protocol
        System.out.println("Protocol: " + request.getProtocol());

        // Print remote address
        System.out.println("Remote Addr: " + request.getRemoteAddr());

        // Print headers
        System.out.println("---- Headers ----");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + request.getHeader(headerName));
        }

        // Print parameters
        System.out.println("---- Parameters ----");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            System.out.println(paramName + ": " + request.getParameter(paramName));
        }

        // Print attributes
        System.out.println("---- Attributes ----");
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            System.out.println(attributeName + ": " + request.getAttribute(attributeName));
        }

        System.out.println("---- End of HttpServletRequest Details ----");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categories = categoryDAO.getAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("reservation-form.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	printRequestDetails(request);
        int customerID = Integer.parseInt(request.getParameter("customer_id"));
        int categoryID = Integer.parseInt(request.getParameter("category_id"));
        Date checkInDate = Date.valueOf(request.getParameter("check_in_date"));
        Date checkOutDate = Date.valueOf(request.getParameter("check_out_date"));

        Reservations reservation = new Reservations();
        reservation.setCustomerID(customerID);
        reservation.setCategoryID(categoryID);
        reservation.setCheckInDate(checkInDate);
        reservation.setCheckOutDate(checkOutDate);

        reservationsDAO.addReservation(reservation);
        response.sendRedirect("reservation-success.jsp");
    }
}
