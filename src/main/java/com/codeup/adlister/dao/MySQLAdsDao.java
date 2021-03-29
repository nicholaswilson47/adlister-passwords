package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }
    //update profile
    @Override
    public void updateProfile(String username, String password, String email, long id) {

    }
    @Override
    public List<Ad> deleteAd(long id) {

        String sql = "DELETE FROM ads WHERE id LIKE ?";
        String deletead = "%" + id + "%";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, deletead);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Ad> updateAd(long id,String title,String description) {

        String sql = "UPDATE  ads SET title=?  ,description=? WHERE id LIKE ?";
        String adId = "%" + id + "%";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(3, adId);
            statement.setString(1,title);
            statement.setString(2,description);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated < 0) {
                System.out.println("Your ad was successfully updated!");
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }
    @Override
    public Ad byAdID(long id) {
        String singleAd = "SELECT * FROM ads WHERE ads.id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(singleAd);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return extractAd(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
