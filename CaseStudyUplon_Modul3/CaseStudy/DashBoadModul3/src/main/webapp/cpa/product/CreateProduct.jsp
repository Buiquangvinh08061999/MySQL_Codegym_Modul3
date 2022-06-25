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
        .errors{
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
                            <h4 class="page-title">Welcome Create Product</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-10">
                        <h1>Create Product</h1>
                    </div>
                    <div class="col-2">
                        <a href="/cpa/product">
                            <button type="button" class="btn btn-success">List Product</button> </a>
                    </div>
                </div>

                <form method="post">

                    <fieldset class="row">

                        <div class="col-sm-6 mt-3">
                            <label>Title <span class="text-danger">*</span></label>
                            <select name="title"  class="form-control" id="title">
                                <option value="Giày Thể Thao">Giày Thể Thao</option>
                                <option value="Giày Lười">Giày Lười</option>
                                <option value="Giày Sneakers">Giày Sneakers</option>
                            </select>
                        </div>

                        <div class="col-sm-6 mt-3">
                            <label>Size<span class="text-danger">*</span></label>
                            <select name="size"  class="form-control" id="size">
                                <option value="38">38</option>
                                <option value="39">39</option>
                                <option value="40">40</option>
                                <option value="41">41</option>
                                <option value="42">42</option>
                                <option value="43">43</option>
                            </select>
                        </div>


                        <div class="col-sm-6 mt-3">
                            <label>Color <span class="text-danger">*</span></label>
                            <select name="color"  class="form-control" id="color">
                                <option value="RED">RED</option>
                                <option value="BLUE">BLUE</option>
                                <option value="YELLOW">YELLOW</option>
                                <option value="ORANGE">ORANGE</option>
                                <option value="BLACK">BLACK</option>
                                <option value="WHITE">WHITE</option>
                            </select>
                        </div>

                        <div class="col-sm-6 mt-3">
                            <label>Price <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="price">
                        </div>
                        <div class="col-sm-6 mt-3">
                            <label>Quantity <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="quantity" required >
                        </div>

                        <div class="col-sm-6 mt-3">
                            <label for="idCategory">IdCategory <span class="text-danger">*</span></label>
                            <select name="idCategory"  class="form-control"  id="idCategory">
                                <option value="1">Hugo Boos Lisbon</option>
                                <option value="2">Emporio Armani</option>
                                <option value="3">U.S. Polo Assn Cổ điển</option>
                                <option value="4">Lacoste Wild Card</option>
                                <option value="5">Calvin Klein PVN396</option>
                                <option value="6">Lacoste Evara 419</option>
                                <option value="7">Sneakers Lacoste L004</option>
                                <option value="8">Vans Old Skool 36</option>
                                <option value="9">U.S Polo Assn.</option>
                            </select>
                        </div>

                        <div class="col-sm-12 mt-3">
                            <label for="resume">Image</label>
                            <input type="file" class="form-control-file" id="resume" name="file">
                        </div>

                        <div class="col-sm-3 mt-3">
                            <button type="submit" class="btn btn-success waves-effect waves-light"> Add Product </button>
                        </div>


                    </fieldset>
                </form>

            </div>
        </div>
          <c:if test="${requestScope['errors'] != null}">
              <c:forEach items="${requestScope['errors']}" var="item">
                  <ul class="errors">
                      <li>${item}</li>
                  </ul>
              </c:forEach>
          </c:if>
        <c:if test="${requestScope['success'] != null}">
            <div class="">
                <c:if test="${requestScope['success'] == true}">
                    <ul class="success">
                        <li>Thêm mới thành công</li>
                    </ul>
                </c:if>
            </div>
        </c:if>

    </div>
</div>

<!-- Footer Start -->
<footer class="footer">
    <%@include file="/cpa/layout/footer_5.jsp" %>
</footer>

<%@include file="/cpa/layout/script/scipt-6.jsp" %>

</body>

</html>