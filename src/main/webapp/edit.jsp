<%@ page import="com.DAO.TodoDAO" %>
    <%@ page import="com.DB.DBConnector" %>
        <%@ page import="com.entity.Entity" %>

            <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="UTF-8">
                    <title>Welcome my todo app</title>
                    <%@ include file="component/all_css.jsp" %>
                </head>

                <body style="background-color: #f0f1f2;">
                    <%@ include file="component/navbar.jsp" %>
                        <div class="container">
                            <div class="row p-5">
                                <div class="col-md-6 offset-md-3">
                                    <div class="card">
                                        <div class="card-body">
                                            <h2 class="text-center text-success">Update Todo</h2>

                                            <% int id=Integer.parseInt(request.getParameter("id")); TodoDAO todoDAO=new
                                                TodoDAO(DBConnector.getConn()); Entity en=todoDAO.getTodoById(id); %>
                                                <form action="update" method="post">
                                                    <input type="hidden" name="id" value="<%=en.getId() %>">
                                                    <div class="form-group">
                                                        <label for="exampleInputEmail1">Name</label>
                                                        <input type="text" class="form-control" id="exampleInputEmail1"
                                                            aria-describedby="emailHelp" name="username"
                                                            value="<%=en.getName() %>">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="exampleInputEmail1">TODO</label>
                                                        <input type="text" class="form-control" id="exampleInputEmail1"
                                                            aria-describedby="emailHelp" name="todo"
                                                            value="<%=en.getTodo() %> ">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="inputState">Status</label>
                                                        <select id="inputState" class="form-control" name="status">
                                                            <% if ("Pending".equals(en.getStatus())) { %>
                                                                <option value="Pending">Pending</option>
                                                                <option value="In Progress">In Progress</option>
                                                                <option value="Complete">Complete</option>
                                                                <% } else if ("In Progress".equals(en.getStatus())) { %>
                                                                    <option value="In Progress">In Progress</option>
                                                                    <option value="Pending">Pending</option>
                                                                    <option value="Complete">Complete</option>
                                                                    <% } else { %>
                                                                        <option value="Complete">Complete</option>
                                                                        <option value="Pending">Pending</option>
                                                                        <option value="In Progress">In Progress</option>
                                                                        <% } %>
                                                        </select>
                                                    </div>

                                                    <div class="text-center">
                                                        <button type="submit" class="btn btn-primary">Update</button>
                                                    </div>

                                                </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                </body>

                </html>