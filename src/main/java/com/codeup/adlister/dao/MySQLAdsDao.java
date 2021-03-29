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
    public Ad individualAd(Long id) {
        String singleAd = "SELECT * FROM ads WHERE user_id = ? ";
        String searchTermWithWildcards = "%" + id + "%";
        try {
            PreparedStatement stmt = connection.prepareStatement(singleAd);
            stmt.setString(1, searchTermWithWildcards);

            ResultSet rs = stmt.executeQuery();
            return extractAd(rs);
        } catch (SQLException throwables) {
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

    @Override
    public List<Ad> search(String term){
        String sql = "SELECT * FROM ads WHERE title  LIKE ? ";
        String searchTermWithWildcards = "%" + term + "%";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, searchTermWithWildcards);

            ResultSet rs = stmt.executeQuery();
            return generateAds(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Ad> getAdsByUser(long id) {
        List<Ad> adsByUser = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ads WHERE user_id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                adsByUser.add(new Ad(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("title"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ads by a user from the database", e);
        }
        return adsByUser;
    }
    public void updateProfile(String username, String userEmail, String password , long id) {
        String queryInsert = "UPDATE users as u SET u.username = ?, u.email = ?, u.password = ? WHERE id = ?";
        try {
            System.out.println(username + "=" + password + " " + userEmail);
            PreparedStatement stmt = connection.prepareStatement(queryInsert);
            stmt.setString(1, username);
            stmt.setString(2, userEmail);
            stmt.setString(3, password);
            stmt.setLong(4, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error", e);
        }
    }
    private List<Ad> generateAds(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()){
            ads.add(new Ad(
                    rs.getLong("id"),
                    rs.getLong("user_id"),
                    rs.getString("title"),
                    rs.getString("description")
            ));
        }
        return ads;
    }
}
