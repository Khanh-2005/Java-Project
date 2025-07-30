package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.entity.Jobs;

public class JobDAO {

    private Connection conn;

    public JobDAO(Connection conn) {
        super();
        this.conn = conn;
    }

    public boolean addJobs(Jobs job) {
        boolean f = false;
        try {
            String sql = "insert into jobs(title, description, category, `status`, location, pdate) values(?,?,?,?,?,now())";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, job.getTitle());
            ps.setString(2, job.getDescription());
            ps.setString(3, job.getCategory());
            ps.setString(4, job.getStatus());
            ps.setString(5, job.getLocation());
            ps.setString(6, job.getPdate());

            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

}
