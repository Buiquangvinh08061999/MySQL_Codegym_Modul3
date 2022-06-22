<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard | Uplon - Responsive Bootstrap 4 Admin Dashboard</title>
    <%--1--%>
    <%@include file="/cpa/layout/head_1.jsp" %>
    <style>
        .success{
            margin-top: 36px;
            color: #35895c
        }
        .error{
            margin-top: 36px;
            color: #c64c5e
        }
    </style>
</head>

<body>


<div id="wrapper">

    <%--2--%>
    <%@include file="/cpa/layout/navbar_custom_top_2.jsp" %>


    <div class="left-side-menu">
        <%--3--%>
        <%@include file="/cpa/layout/hearder_left_3.jsp" %>
    </div>

    <div class="content-page">
        <%--4--%>
        <div class="content">
            <!-- Start Content-->
            <div class="container-fluid">
                <!-- start page title -->
                <div class="row">
                    <div class="col-12">
                        <div class="page-title-box">
                            <div class="page-title-right">
                                <ol class="breadcrumb m-0">
                                    <li class="breadcrumb-item"><a href="javascript: void(0);">Uplon</a></li>
                                    <li class="breadcrumb-item active">Dashboard</li>
                                </ol>
                            </div>
                            <h4 class="page-title">Welcome Create User</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-10">
                        <h1>Create Use</h1>
                    </div>
                    <div class="col-2">
                        <a href="/cpa/user">
                            <button type="button" class="btn btn-success">List user</button> </a>
                    </div>
                </div>

                <form method="post">
                    <fieldset class="row">

                        <div class="col-sm-6 mt-3">
                            <label>User Name <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="username">
                        </div>

                        <div class="col-sm-6 mt-3">
                            <label>Pass Word <span class="text-danger">*</span></label>
                            <div class="input-group mb-3">
                                <input type="password" class="form-control" id="ipnPassword" name="password" />
                                <div class="input-group-append">
                                    <button class="btn btn-outline-secondary" type="button" id="btnPassword">
                                        <span class="fas fa-eye"></span>
                                    </button>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6 mt-3">
                            <label>Full Name <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="fullname">
                        </div>

                        <div class="col-sm-6 mt-3">
                            <label>Phone <span class="text-danger">*</span></label>
                            <input type="tel" class="form-control" name="phone">
                        </div>
                        <div class="col-sm-6 mt-3">
                            <label>Email <span class="text-danger">*</span></label>
                            <input type="email" class="form-control" name="email" placeholder="txr@gmail.com">
                        </div>

                        <div class="col-sm-6 mt-3">
                            <label>City <span class="text-danger">*</span></label>
                            <select name="city"  class="form-control" var="">
                                <option value="HUẾ">HUẾ</option>
                                <option value="ĐÀ NẴNG">ĐÀ NẴNG</option>
                                <option value="QUẢNG TRỊ">QUẢNG TRỊ</option>
                                <option value="QUẢNG BÌNH">QUẢNG BÌNH</option>
                                <option value="NGHỆ AN">NGHỆ AN</option>
                                <option value="HÀ TĨNH">HÀ TĨNH</option>
                            </select>
                        </div>

                        <div class="col-sm-6 mt-3">
                            <label>Role<span class="text-danger">*</span></label>
                            <select name="role" id="role" class="form-control">
                                <option value="ADMIN">ADMIN</option>
                                <option value="USER">USER</option>
                            </select>
                        </div>
                        <div class="col-sm-6 mt-3">
                            <label for="resume">Image</label>
                            <input type="file" class="form-control-file" id="resume" name="file">
                        </div>

                        <div class="col-sm-3 mt-3">
                            <button type="submit" class="btn btn-success waves-effect waves-light"> Add user </button>
                        </div>

                    </fieldset>
                </form>
            </div>

            <div class="">
                <c:if test="${success == true}">
                    <ul class="success">
                        <li>Thêm mới thành công</li>
                    </ul>
                </c:if>
                <c:if test="${errors == true}">
                    <ul class="error">
                        <li>Thêm mới thất bại</li>
                    </ul>
                </c:if>
<%--                  <c:if test="${!errors.isEmpty()}">--%>
<%--                      <ul class="error">--%>
<%--                          <c:forEach items="${errors}" var="item">--%>
<%--                              <li>${item}</li>--%>
<%--                          </c:forEach>--%>
<%--                      </ul>--%>
<%--                  </c:if>--%>

            </div>
            </div>
        </div>
        <script>
            const ipnElement = document.querySelector('#ipnPassword')
            const btnElement = document.querySelector('#btnPassword')

            btnElement.addEventListener('click', function () {
                const currentType = ipnElement.getAttribute('type')

                ipnElement.setAttribute(
                    'type',
                    currentType === 'password' ? 'text' : 'password'
                )
            })
        </script>

    <!-- Footer Start -->
    <footer class="footer">
        <%--5--%>
        <%@include file="/cpa/layout/footer_5.jsp" %>
    </footer>

    <%@include file="/cpa/layout/script/scipt-6.jsp" %>

</body>

</html>