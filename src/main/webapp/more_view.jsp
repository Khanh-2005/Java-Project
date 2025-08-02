<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false" %>
        <%@ page import="com.dao.JobDAO" %>
            <%@ page import="com.entity.Jobs" %>
                <%@ page import="com.DB.DBConnect" %>
                    <%@ page import="java.util.List" %>
                        <%@ page import="java.util.ArrayList" %>

                            <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
                                <!DOCTYPE html>
                                <html>

                                <head>
                                    <meta charset="UTF-8">
                                    <title>User: View Jobs by fill location and category</title>
                                    <%@ include file="allcomponent/allcss.jsp" %>
                                </head>

                                <body style="background-color: #f0f1f2;">
                                    <c:if test="${empty userobj }">
                                        <c:redirect url="login.jsp" />
                                    </c:if>

                                    <%@ include file="allcomponent/navbar.jsp" %>
                                        <div class="container">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <h5 class="text-center text-primary">All Jobs</h5>
                                                    <% String loc=request.getParameter("loc"); String
                                                        cat=request.getParameter("cat"); String msg="" ; JobDAO dao=new
                                                        JobDAO(DBConnect.getConn()); List<Jobs> list = null;


                                                        if (loc == null || cat == null || ("lo".equals(loc) &&
                                                        "ca".equals(cat))) {
                                                        list = new ArrayList<Jobs>();
                                                            msg = "Please select location or category";
                                                            } else if ("lo".equals(loc) || "ca".equals(cat)) {
                                                            list = dao.getJobsORLocationAndCate(cat, loc);
                                                            } else {
                                                            list = dao.getJobsANDLocationAndCate(cat, loc);
                                                            }

                                                            if (list.isEmpty()) {
                                                            %>
                                                            <h4 class="text-center text-danger">Job Not Available
                                                            </h4>
                                                            <% } if (list !=null) { for (Jobs job : list) { %>
                                                                <div class="card mt-2">
                                                                    <div class="card-body">
                                                                        <div class="text-center text-primary">
                                                                            <i class="far fa-clipboard fa-2x"></i>
                                                                        </div>

                                                                        <h6>
                                                                            <%=job.getTitle() %>
                                                                        </h6>
                                                                        <p>
                                                                            <%=job.getDescription() %>
                                                                        </p>
                                                                        <br>
                                                                        <div class="form-row">
                                                                            <div class="form-group col-md-3">
                                                                                <input type="text"
                                                                                    class="form-control form-control-sm"
                                                                                    value="Location:<%=job.getLocation() %>"
                                                                                    readonly>
                                                                            </div>
                                                                            <div class="form-group col-md-3">
                                                                                <input type="text"
                                                                                    class="form-control form-control-sm"
                                                                                    value="Category:<%=job.getCategory() %>"
                                                                                    readonly>
                                                                            </div>
                                                                        </div>

                                                                        <h6>Publish Date:
                                                                            <%= job.getPdate().toString() %>
                                                                        </h6>

                                                                        <div class="text-center">
                                                                            <a href="one_view.jsp?id=<%=job.getId() %>"
                                                                                class="btn btn-sm bg-success text-white">Apply
                                                                                Now</a>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <% } } else { %>
                                                                    <h4 class="text-center text-danger">
                                                                        <%=msg %>
                                                                    </h4>
                                                                    <% } %>



                                                </div>
                                            </div>

                                        </div>
                                </body>

                                </html>