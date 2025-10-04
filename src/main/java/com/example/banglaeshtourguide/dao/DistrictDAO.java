package com.example.banglaeshtourguide.dao;
import com.example.banglaeshtourguide.model.District;
import com.example.banglaeshtourguide.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DistrictDAO {

    public List<District> getAllDistricts() {
        ArrayList<District> districts = new ArrayList<>();
        String sql = "SELECT district_id, district_name FROM districts ORDER BY district_name";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                districts.add(new District(
                        rs.getInt("district_id"),
                        rs.getString("district_name")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching districts: " + e.getMessage());
            e.printStackTrace();
        }
        return districts;
    }

    public District getDistrictById(int id) {
        String sql = "SELECT district_id, district_name FROM districts WHERE district_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new District(rs.getInt("district_id"), rs.getString("district_name"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching district by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // You can add more methods like addDistrict, updateDistrict, deleteDistrict
}