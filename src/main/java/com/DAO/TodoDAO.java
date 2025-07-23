package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TodoDAO {

    private Connection conn;

    public TodoDAO(Connection conn) {
        super();
        this.conn = conn;
    }

    public boolean addTodo(String name, String todo, String status) {

        boolean f = false;
        try {
            String sql = "INSERT INTO todo(name, todo, status) VALUES(?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, todo);
            ps.setString(3, status);

            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }

        } catch (Exception e) {
            e.printStackTrace();

            return f;
        }
        return f;

    }
}
