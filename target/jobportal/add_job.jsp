<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Admin:Add Job</title>
        <%@ include file="allcomponent/allcss.jsp" %>
    </head>

    <body style="background-color: #f0f1f2;">
        <%@ include file="allcomponent/navbar.jsp" %>

            <div class="container p-2">
                <div class="col-md-10 offset-md-1">
                    <div class="card">
                        <div class="card-body">
                            <div class="text-center text-success">
                                <i class="fas fa-users-friends fa-3x"></i>
                                <!-- <c:if test="${not empty succMsg}">
                                <div class="alert alert-success" role="alert">${succMsg}</div>
                                <c: remove var="succMsg"/>
                            </c:if> -->
                                <h5>Add Job</h5>
                            </div>

                            <form action="add_job" method="post">
                                <div class="form-group">
                                    <label>Enter Job Title</label>
                                    <input type="text" required class="form-control" name="title">
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label>Location</label>
                                        <select name="Location" class="custom-select" id="inlinedFormCustomSelectPref">
                                            <option selected>Choose...</option>
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

                                    <div class="form-group col-md-4">
                                        <label>Category</label>
                                        <select name="Location" class="custom-select" id="inlinedFormCustomSelectPref">
                                            <option selected>Choose...</option>
                                            <option value="IT">Information Technology</option>
                                            <option value="HR>">Human Resource</option>
                                            <option value="PM">Project Management</option>
                                            <option value="DS">Designer</option>
                                            <option value="DM">Digital Marketing</option>
                                            <option value="AI">Artificial Intelligence</option>
                                            <option value="BA">Business Analysis</option>
                                            <option value="CS">Customer Service</option>
                                        </select>
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label>Status</label>
                                        <select class="form-control" name="status">
                                            <option value="Active">Active</option>
                                            <option value="Inactive">Inactive</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label>Job Description</label>
                                    <textarea class="form-control" required rows="6" cols="" name="desc"></textarea>
                                </div>
                                <button class="btn btn-success">Publish Job</button>"
                            </form>
                        </div>
                    </div>
                </div>
            </div>
    </body>

    </html>