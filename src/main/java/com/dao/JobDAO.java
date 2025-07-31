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

    // Thêm công việc mới
    public boolean addJobs(Jobs job) {
        boolean f = false;
        try {
            // Không thêm cột Id vì MySQL tự AUTO_INCREMENT
            String sql = "INSERT INTO jobs(title, description, category, `status`, location, pdate) "
                    + "VALUES (?, ?, ?, ?, ?, NOW())";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, job.getTitle());
            ps.setString(2, job.getDescription());
            ps.setString(3, job.getCategory());
            ps.setString(4, job.getStatus());
            ps.setString(5, job.getLocation());

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
