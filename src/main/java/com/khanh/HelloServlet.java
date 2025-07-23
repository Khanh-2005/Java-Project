package com.khanh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.TodoDAO;
import com.DB.DBConnector;

public class HelloServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username = request.getParameter("username");
        String todo = request.getParameter("todo");
        String status = request.getParameter("status");

        TodoDAO todoDAO = new TodoDAO(DBConnector.getConn());
        boolean f = todoDAO.addTodo(username, todo, status);

        if (f) {
            System.out.println("Todo added successfully");

        } else {
            System.out.println("Failed to add todo");

        }
        response.sendRedirect("index.jsp");
    }

    // @Override
    // public void init() throws ServletException {
    // System.out.println("Init Servlet");
    // }

    // @Override
    // public void destroy() {
    // System.out.println("Destroy Servlet");
    // }

    // @Override
    // protected void service(HttpServletRequest request, HttpServletResponse
    // response)
    // throws ServletException, IOException {
    // super.service(request, response);
    // }

    // @Override
    // protected void doGet(HttpServletRequest request, HttpServletResponse
    // response)
    // throws ServletException, IOException {
    // response.setContentType("text/html"); // Thêm dòng này
    // PrintWriter writer = response.getWriter();
    // writer.println("<h2>Welcome to my first web app</h2>");
    // writer.close();
    // }

}
