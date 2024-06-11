package com.HotelManagement.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.HotelManagement.utils.DBContext;

public class CustomerDAO extends DBContext {
	  public int getTotalCustomers() {
	        int total = 0;
	        String query = "SELECT COUNT(*) AS total FROM Customers";
	        try (PreparedStatement stmt = connection.prepareStatement(query);
	             ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                total = rs.getInt("total");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        System.out.println("Total Customers: " + total); 
	        return total;
	    }
}
