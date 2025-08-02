<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false" %>
        <%@ page import="com.dao.JobDAO" %>
            <%@ page import="com.entity.Jobs" %>
                <%@ page import="com.DB.DBConnect" %>
                    <%@ page import="java.util.List" %>

                        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
                            <!DOCTYPE html>
                            <html>

                            <head>
                                <meta charset="UTF-8">
                                <title>User: View Jobs</title>
                                <%@ include file="allcomponent/allcss.jsp" %>
                            </head>

                            <body style="background-color: #f0f1f2;">
                                <!-- <c:if test="${empty userobj }">
                                    <c:redirect url="login.jsp" />
                                </c:if> -->

                                <%@ include file="allcomponent/navbar.jsp" %>

                                    <div class="container">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h5 class="text-center text-primary">All Jobs</h5>

                                                <c:if test="${not empty succMsg}">
                                                    <h4 class="text-center text-danger">${succMsg}</h4>
                                                    <c:remove var="succMsg" />
                                                </c:if>

                                                <div class="card">
                                                    <div class="card-body">
                                                        <form class="form-inline" action="more_view.jsp" method="get">
                                                            <div class="form-group col-md-5 mt-1">
                                                                <h5>Location</h5>
                                                            </div>

                                                            <div class="form-group col-md-4 mt-1">
                                                                <h5>Category</h5>
                                                            </div>

                                                            <div class="form-group col-md-5">
                                                                <select name="loc" class="custom-select"
                                                                    id="inlinedFormCustomSelectPref">
                                                                    <option selected value="lo">Choose...</option>
                                                                    <option value="DaNang">Da Nang</option>
                                                                    <option value="HaNoi">Ha Noi</option>
                                                                    <option value="HoChiMinh">TP Ho Chi Minh</option>
                                                                    <option value="Hue">Hue</option>
                                                                    <option value="QuangNgai">Quang Ngai</option>
                                                                    <option value="HaiPhong">Hai Phong</option>
                                                                    <option value="CanTho">Can Tho</option>
                                                                    <option value="HungYen">Hung Yen</option>
                                                                    <option value="BacNinh">Bac Ninh</option>
                                                                    <option value="KienGiang">Kien Giang</option>
                                                                </select>
                                                            </div>

                                                            <div class="form-group col-md-5">
                                                                <select name="cat" class="custom-select"
                                                                    id="inlinedFormCustomSelectPref">
                                                                    <option selected value="ca">Choose...</option>
                                                                    <option value="IT">Information Technology</option>
                                                                    <option value="HR">Human Resource</option>
                                                                    <option value="PM">Project Management</option>
                                                                    <option value="DS">Designer</option>
                                                                    <option value="DM">Digital Marketing</option>
                                                                    <option value="AI">Artificial Intelligence</option>
                                                                    <option value="BA">Business Analysis</option>
                                                                    <option value="CS">Customer Service</option>
                                                                </select>
                                                            </div>
                                                            <button class="btn btn-success">Submit</button>
                                                        </form>
                                                    </div>
                                                </div>

                                                <% JobDAO dao=new JobDAO(DBConnect.getConn()); List<Jobs> list =
                                                    dao.getAllJobsForUser();
                                                    for(Jobs job : list){
                                                    %>
                                                    <div class="card mt-2">
                                                        <div class="card-body">
                                                            <div class="text-center text-primary">
                                                                <i class="far fa-clipboard fa-2x"></i>
                                                            </div>

                                                            <h6>
                                                                <%=job.getTitle() %>
                                                            </h6>

                                                            <% if (job.getDescription().length()> 0 &&
                                                                job.getDescription().length() < 120) { %>
                                                                    <p>
                                                                        <%=job.getDescription() %>.
                                                                    </p>
                                                                    <% } else { %>
                                                                        <p>
                                                                            <%=job.getDescription().substring(0, 120) %>
                                                                                ...
                                                                        </p>
                                                                        <% } %>

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

                                                                            <h6>Publish Date:<%=
                                                                                    job.getPdate().toString() %>
                                                                            </h6>

                                                                            <div class="text-center">
                                                                                <a href="one_view.jsp?id=<%=job.getId() %>"
                                                                                    class="btn btn-sm bg-success text-white">View
                                                                                    more</a>

                                                                            </div>
                                                        </div>
                                                    </div>

                                                    <% } %>

                                            </div>

                                        </div>
                                    </div>
                            </body>

                            </html>