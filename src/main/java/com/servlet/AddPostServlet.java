package com.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.entity.Jobs;
import com.dao.JobDAO;

@WebServlet("/add_job")
public class AddPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            // Lấy dữ liệu từ form
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String category = req.getParameter("category");
            String status = req.getParameter("status");
            String location = req.getParameter("location");

            // Tạo đối tượng job
            Jobs job = new Jobs();
            job.setTitle(title);
            job.setDescription(description);
            job.setCategory(category);
            job.setStatus(status);
            job.setLocation(location);

            HttpSession session = req.getSession();

            // Gọi DAO để thêm vào DB
            JobDAO dao = new JobDAO(DBConnect.getConn());
            boolean f = dao.addJobs(job);
            if (f) {
                session.setAttribute("succMsg", "Job added successfully!");
            } else {
                session.setAttribute("succMsg", "Something went wrong, please try again.");
            }

            // Quay lại trang form
            resp.sendRedirect("add_job.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
