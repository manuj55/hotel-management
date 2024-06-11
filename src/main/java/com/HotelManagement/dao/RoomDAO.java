package com.HotelManagement.dao;

import com.HotelManagement.model.Room;
import com.HotelManagement.utils.DBContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;


public class RoomDAO extends DBContext {
    public List<Room> getAvailableRooms(String roomType, String roomView, Date checkIn, Date checkOut) {
        List<Room> rooms = new ArrayList<>();
		/*
		 * String query =
		 * "SELECT RoomNumber, 'Available' AS Status, RoomType, RoomView " +
		 * "FROM Room " + "WHERE RoomType = ? AND RoomView = ? " +
		 * "AND RoomNumber NOT IN ( " + "SELECT RoomNumber " + "FROM Room " +
		 * "WHERE RoomType = ? " + "AND RoomView = ? " +
		 * "AND (CheckIn <= ? AND CheckOut >= ?) " + ") " + "UNION " +
		 * "SELECT RoomNumber, Status, RoomType, RoomView " + "FROM AvailabilityRooms "
		 * + "WHERE RoomType = ? " + "AND RoomView = ? " + "AND RoomNumber NOT IN ( " +
		 * "SELECT r.RoomNumber " + "FROM Bookings b " +
		 * "JOIN Room r ON b.BookingID = r.BookingID " + "WHERE r.RoomType = ? " +
		 * "AND r.RoomView = ? " + "AND (b.CheckInDate <= ? AND b.CheckOutDate >= ?) " +
		 * ")";
		 */

        String query = "WITH AllSuperDeluxeSeaViewRooms AS ( " +
                "    SELECT RoomNumber, 'Available' AS Status, RoomType, RoomView, CheckIn, CheckOut " +
                "    FROM Room " +
                "    WHERE RoomType = ? " +
                "    AND RoomView = ? " +
                "    UNION " +
                "    SELECT RoomNumber, Status, RoomType, RoomView, CheckIn, CheckOut " +
                "    FROM AvailabilityRooms " +
                "    WHERE RoomType = ? " +
                "    AND RoomView = ? " +
                ") " +
                "SELECT RoomNumber, Status, RoomType, RoomView, CheckIn, CheckOut " +
                "FROM AllSuperDeluxeSeaViewRooms " +
                "WHERE RoomNumber NOT IN ( " +
                "    SELECT RoomNumber " +
                "    FROM Room " +
                "    WHERE RoomType = ? " +
                "    AND RoomView = ? " +
                "    AND ( " +
                "        (CheckIn <= ? AND CheckOut >= ?) " +
                "        OR " +
                "        (CheckIn <= ? AND CheckOut >= ?) " +
                "        OR " +
                "        (CheckIn >= ? AND CheckOut <= ?) " +
                "    ) " +
                "    UNION " +
                "    SELECT r.RoomNumber " +
                "    FROM Bookings b " +
                "    JOIN Room r ON b.BookingID = r.BookingID " +
                "    WHERE r.RoomType = ? " +
                "    AND r.RoomView = ? " +
                "    AND ( " +
                "        (b.CheckInDate <= ? AND b.CheckOutDate >= ?) " +
                "        OR " +
                "        (b.CheckInDate <= ? AND b.CheckOutDate >= ?) " +
                "        OR " +
                "        (b.CheckInDate >= ? AND b.CheckOutDate <= ?) " +
                "    ) " +
                ")";

        
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//
//            statement.setString(1, roomType);
//            statement.setString(2, roomView);
//            statement.setString(3, roomType);
//            statement.setString(4, roomView);
//            statement.setDate(5, checkIn);
//            statement.setDate(6, checkOut);
//            statement.setString(7, roomType);
//            statement.setString(8, roomView);
//            statement.setString(9, roomType);
//            statement.setString(10, roomView);
//            statement.setDate(11, checkIn);
//            statement.setDate(12, checkOut);
//            System.out.println(statement + "statement");
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                Room room = new Room();
//                room.setRoomNumber(rs.getInt("RoomNumber"));
//                room.setStatus(rs.getString("Status"));
//                room.setRoomType(rs.getString("RoomType"));
//                room.setRoomView(rs.getString("RoomView"));
//                System.out.println(rs.getInt("RoomNumber"));
//                System.out.println(rs.getString("Status"));
//                System.out.println(rs.getString("RoomType"));
//                rooms.add(room);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

        	// Set the roomType and roomView parameters
        	pstmt.setString(1, roomType);
        	pstmt.setString(2, roomView);
        	pstmt.setString(3, roomType);
        	pstmt.setString(4, roomView);
        	pstmt.setString(5, roomType);
        	pstmt.setString(6, roomView);
        	pstmt.setDate(7, checkIn);
        	pstmt.setDate(8, checkOut);
        	pstmt.setDate(9, checkIn);
        	pstmt.setDate(10, checkOut);
        	pstmt.setDate(11, checkIn);
        	pstmt.setDate(12, checkOut);
        	pstmt.setString(13, roomType);
        	pstmt.setString(14, roomView);
        	pstmt.setDate(15, checkIn);
        	pstmt.setDate(16, checkOut);
        	pstmt.setDate(17, checkIn);
        	pstmt.setDate(18, checkOut);
        	pstmt.setDate(19, checkIn);
        	pstmt.setDate(20, checkOut);

        	ResultSet rs = pstmt.executeQuery();

        	 while (rs.next()) {
               Room room = new Room();
               room.setRoomNumber(rs.getInt("RoomNumber"));
               room.setStatus(rs.getString("Status"));
               room.setRoomType(rs.getString("RoomType"));
               room.setRoomView(rs.getString("RoomView"));
               room.setCheckIn(rs.getDate("CheckIn"));
               room.setCheckOut(rs.getDate("CheckOut"));
               rooms.add(room);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return rooms;
    }

    public void bookRoom(int roomNumber, String roomType, String roomView, Date checkIn, Date checkOut) {
        String updateQuery = "UPDATE AvailabilityRooms SET Status = 'Booked', CheckIn = ?, CheckOut = ? " +
                             "WHERE RoomNumber = ? AND RoomType = ? AND RoomView = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setDate(1, checkIn);
            statement.setDate(2, checkOut);
            statement.setInt(3, roomNumber);
            statement.setString(4, roomType);
            statement.setString(5, roomView);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM Room";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Room room = new Room();
                room.setRoomNumber(resultSet.getInt("RoomNumber"));
                room.setRoomType(resultSet.getString("RoomType"));
                room.setRoomView(resultSet.getString("RoomView"));
                room.setStatus(resultSet.getString("Status"));
                rooms.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
