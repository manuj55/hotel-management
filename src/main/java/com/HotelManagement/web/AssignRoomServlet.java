package com.HotelManagement.web;

import com.HotelManagement.dao.RoomAssignmentDAO;
import com.HotelManagement.dao.EmployeeDAO;
import com.HotelManagement.dao.RoomDAO;
import com.HotelManagement.model.Employee;
import com.HotelManagement.model.Room;
import com.HotelManagement.model.RoomAssignment;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/assign-room")
public class AssignRoomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        RoomDAO roomDAO = new RoomDAO();
        RoomAssignmentDAO assignmentDAO = new RoomAssignmentDAO();

        List<Employee> employees = employeeDAO.getAllEmployees();
        List<Room> rooms = roomDAO.getAllRooms();
        List<RoomAssignment> assignments = assignmentDAO.getAllAssignments();

        request.setAttribute("employees", employees);
        request.setAttribute("rooms", rooms);
        request.setAttribute("assignments", assignments);

        request.getRequestDispatcher("/assign-room.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));

        RoomAssignment assignment = new RoomAssignment();
        assignment.setRoomNumber(roomNumber);
        assignment.setEmployeeID(employeeID);

        RoomAssignmentDAO dao = new RoomAssignmentDAO();
        dao.assignRoom(assignment);

        response.sendRedirect("assign-room");
    }
}
