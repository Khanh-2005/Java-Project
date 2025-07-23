<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.sql.Connection" %>
        <%@ page import="com.DB.DBConnector" %>
            <%@ page import="com.entity.Entity" %>
                <%@ page import="com.DAO.TodoDAO" %>
                    <%@ page import="java.util.List" %>

                        <!DOCTYPE html>
                        <html>

                        <head>
                            <meta charset="UTF-8">
                            <title>Welcome my todo app</title>
                            <%@ include file="component/all_css.jsp" %>
                        </head>

                        <body>
                            <%@ include file="component/navbar.jsp" %>

                                <h1 class="text-center text-success">TODO APP</h1>

                                <% String sucMsg=(String) session.getAttribute("sucMsg"); if (sucMsg !=null) { %>
                                    <div class="alert alert-success" role="alert">
                                        <%=sucMsg%>
                                    </div>
                                    <% session.removeAttribute("sucMsg"); } %>

                                        <% String failedMsg=(String) session.getAttribute("failedMsg"); if (failedMsg
                                            !=null) { %>
                                            <div class="alert alert-danger" role="alert">
                                                <%=failedMsg%>
                                            </div>
                                            <% session.removeAttribute("failedMsg"); } %>

                                                <div class="container">
                                                    <table class="table table-striped" border="1px">
                                                        <thead class="bg-success text-white">
                                                            <tr>
                                                                <th scope="col">ID</th>
                                                                <th scope="col">Name</th>
                                                                <th scope="col">Todo</th>
                                                                <th scope="col">Status</th>
                                                                <th scope="col">Action</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <% TodoDAO todoDAO=new TodoDAO(DBConnector.getConn());
                                                                List<Entity> todo = todoDAO.getTodo();
                                                                for (Entity en : todo) {
                                                                %>
                                                                <tr>
                                                                    <th scope="row">
                                                                        <%=en.getId()%>
                                                                    </th>
                                                                    <td>
                                                                        <%=en.getName()%>
                                                                    </td>
                                                                    <td>
                                                                        <%=en.getTodo()%>
                                                                    </td>
                                                                    <td>
                                                                        <%=en.getStatus()%>
                                                                    </td>
                                                                    <td>
                                                                        <a href=" edit.jsp?id=<%= en.getId() %>"
                                                                            class="btn btn-sm btn-success">Edit</a>
                                                                        <a href=""
                                                                            class="btn btn-sm btn-danger">Delete</a>
                                                                    </td>
                                                                </tr>
                                                                <% } %>
                                                        </tbody>
                                                    </table>
                                                </div>
                        </body>

                        </html>