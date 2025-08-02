package com.dao;

import java.sql.Connection;
import com.entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    private Connection conn;

    public UserDAO(Connection conn) {
        super();
        this.conn = conn;
    }

    // Add a new user
    public boolean addUser(User user) {
        boolean f = false;
        try {

            String sql = "INSERT INTO users(name, qualification, email, password, role) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getQualification());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, "user");

            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    // User login
    public User login(String em, String psw) {
        User user = null;

        try {
            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, em);
            ps.setString(2, psw);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setQualification(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setRole(rs.getString(6));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;

    }

}
