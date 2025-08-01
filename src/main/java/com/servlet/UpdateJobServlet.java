package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.JobDAO;
import com.entity.Jobs;

@WebServlet("/update")
public class UpdateJobServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            // Lấy dữ liệu từ form
            int id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String category = req.getParameter("category");
            String status = req.getParameter("status");
            String location = req.getParameter("location");

            // Tạo đối tượng job
            Jobs job = new Jobs();
            job.setId(id);
            job.setTitle(title);
            job.setDescription(description);
            job.setCategory(category);
            job.setStatus(status);
            job.setLocation(location);

            HttpSession session = req.getSession();

            // Gọi DAO để thêm vào DB
            JobDAO dao = new JobDAO(DBConnect.getConn());
            boolean f = dao.updateJobs(job);
            if (f) {
                session.setAttribute("succMsg", "Job updated successfully!");
            } else {
                session.setAttribute("succMsg", "Something went wrong, please try again.");
            }

            // Quay lại trang form
            resp.sendRedirect("view_job.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
