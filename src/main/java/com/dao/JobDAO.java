package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;
import com.entity.Jobs;

public class JobDAO {

    private Connection conn;

    public JobDAO(Connection conn) {
        super();
        this.conn = conn;
    }

    // Add a new job
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

    // Read jobs by DESC
    public List<Jobs> getAllJobs() {
        List<Jobs> list = new ArrayList<Jobs>();
        Jobs job = null;

        try {
            String sql = "SELECT * FROM jobs ORDER BY id DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                job = new Jobs();
                job.setId(rs.getInt(1));
                job.setTitle(rs.getString(2));
                job.setDescription(rs.getString(3));
                job.setCategory(rs.getString(4));
                job.setStatus(rs.getString(5));
                job.setLocation(rs.getString(6));
                job.setPdate(rs.getDate(7) + "");
                list.add(job);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Read job by id
    public Jobs getJobById(int id) {
        Jobs job = null;

        try {
            String sql = "SELECT * FROM jobs WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                job = new Jobs();
                job.setId(rs.getInt(1));
                job.setTitle(rs.getString(2));
                job.setDescription(rs.getString(3));
                job.setCategory(rs.getString(4));
                job.setStatus(rs.getString(5));
                job.setLocation(rs.getString(6));
                job.setPdate(rs.getDate(7) + "");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return job;
    }

    // Update job
    public boolean updateJobs(Jobs job) {
        boolean f = false;
        try {

            String sql = "UPDATE jobs SET title=?, description=?, category=?, `status`=?, location=?, pdate=NOW() WHERE id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, job.getTitle());
            ps.setString(2, job.getDescription());
            ps.setString(3, job.getCategory());
            ps.setString(4, job.getStatus());
            ps.setString(5, job.getLocation());
            ps.setInt(6, job.getId());

            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    // Delete job
    public boolean deleteJobs(int id) {
        boolean f = false;
        try {
            String sql = "DELETE FROM jobs WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

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
