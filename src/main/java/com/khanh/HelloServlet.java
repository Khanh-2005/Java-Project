package com.khanh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.TodoDAO;
import com.DB.DBConnector;
import com.entity.Entity;

public class HelloServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username = request.getParameter("username");
        String todo = request.getParameter("todo");
        String status = request.getParameter("status");

        Entity entity = new Entity();
        entity.setName(username);
        entity.setTodo(todo);
        entity.setStatus(status);

        TodoDAO todoDAO = new TodoDAO(DBConnector.getConn());
        boolean f = todoDAO.addTodo(entity.getName(), entity.getTodo(), entity.getStatus()); // Sửa lại dòng này

        HttpSession session = request.getSession();
        if (f) {
            session.setAttribute("sucMsg", "Todo added successfully"); // Sửa lại tên thuộc tính
            response.sendRedirect("index.jsp");
        } else {
            session.setAttribute("failedMsg", "Failed to add todo");
            response.sendRedirect("add_todo.jsp");
        }
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
