package com.example.banglaeshtourguide.dao;

import com.example.banglaeshtourguide.model.Spot;
import com.example.banglaeshtourguide.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpotDAO {

    public List<Spot> getSpotsByDistrictId(int districtId) {
        List<Spot> spots = new ArrayList<>();
        String sql = "SELECT spot_id, spot_name, district_id, photo_url, ticket_price, facilities FROM spots WHERE district_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, districtId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    spots.add(new Spot(
                            rs.getInt("spot_id"),
                            rs.getString("spot_name"),
                            rs.getInt("district_id"),
                            rs.getString("photo_url"),
                            rs.getBigDecimal("ticket_price"),
                            rs.getString("facilities")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching spots by district ID: " + e.getMessage());
            e.printStackTrace();
        }
        return spots;
    }

    // You can add methods like addSpot, updateSpot, deleteSpot
}
