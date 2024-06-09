package com.HotelManagement.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.HotelManagement.model.Category;
import com.HotelManagement.utils.DBContext;

public class CategoryDAO extends DBContext {

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT DISTINCT RoomType, RoomView, Price FROM Categories";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Category category = new Category();
//                System.out.println(resultSet.getString("RoomType"));
//                System.out.println(resultSet.getString("RoomView"));
//                System.out.println(resultSet.getInt("Price"));
                category.setRoomType(resultSet.getString("RoomType"));
                category.setRoomView(resultSet.getString("RoomView"));
                category.setPrice(resultSet.getInt("Price"));
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }
}
