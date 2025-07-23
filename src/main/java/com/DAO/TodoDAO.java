package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Entity;

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
        }
        return f;

    }

    public List<Entity> getTodo() {
        List<Entity> list = new ArrayList<Entity>();
        Entity en = null;
        try {
            String sql = "select * from todo";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                en = new Entity();
                en.setId(rs.getInt(1));
                en.setName(rs.getString(2));
                en.setTodo(rs.getString(3));
                en.setStatus(rs.getString(4));

                list.add(en);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

}
