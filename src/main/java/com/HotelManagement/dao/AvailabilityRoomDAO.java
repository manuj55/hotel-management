package com.HotelManagement.dao;

import com.HotelManagement.model.AvailabilityRoom;
import com.HotelManagement.utils.DBContext;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AvailabilityRoomDAO extends DBContext {

    public void updateAvailabilityRoom(AvailabilityRoom availabilityRoom) {
        String sql = "UPDATE AvailabilityRooms SET BookingID = ?, Status = ?, CheckIn = ?, CheckOut = ? WHERE RoomNumber = ? AND RoomType = ? AND RoomView = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, availabilityRoom.getBookingId());
            statement.setString(2, availabilityRoom.getStatus());
            statement.setDate(3, availabilityRoom.getCheckIn());
            statement.setDate(4, availabilityRoom.getCheckOut());
            statement.setInt(5, availabilityRoom.getRoomNumber());
            statement.setString(6, availabilityRoom.getRoomType());
            statement.setString(7, availabilityRoom.getRoomView());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
