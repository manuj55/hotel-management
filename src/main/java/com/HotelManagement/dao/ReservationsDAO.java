package com.HotelManagement.dao;
import com.HotelManagement.model.Reservations;
import com.HotelManagement.utils.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservationsDAO extends DBContext {

    public void addReservation(Reservations reservation) {
        try {
            String sql = "INSERT INTO reservations (CustomerID, CategoryID, CheckInDate, CheckOutDate) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, reservation.getCustomerID());
                statement.setInt(2, reservation.getCategoryID());
                statement.setDate(3, (Date) reservation.getCheckInDate());
                statement.setDate(4, (Date) reservation.getCheckOutDate());
                System.out.println("hit here");
                System.out.println(statement);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Additional CRUD operations can be added here
}
