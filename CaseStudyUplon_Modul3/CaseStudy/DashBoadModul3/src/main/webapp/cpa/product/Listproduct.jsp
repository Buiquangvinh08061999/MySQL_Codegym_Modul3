<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard | Uplon - Responsive Bootstrap 4 Admin Dashboard</title>
    <%--1--%>
    <%@include file="/cpa/layout/head_1.jsp" %>
    <style>
        table{
            text-align: center;
        }

        table tbody tr td.pd-top{
            padding-top: 30px;
        }
        table tbody tr th.pd-top{
            padding-top: 30px;
        }
        table tbody tr td.pd-top-action{
            padding-top: 20px;
        }

        .table thead{
            background: #1bb99a;
            color: white;
        }
        /*.table tbody{*/
        /*    font-family: sans-serif*/
        /*}*/
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
                            <h4 class="page-title">Welcome List Product</h4>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="col-10">
                        <h1>List of product</h1>
                    </div>
                    <div class="col-2">
                        <a href="/cpa/product?action=create">
                            <button type="button" class="btn btn-success"> Create Product</button>
                        </a>
                    </div>

                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Title</th>
                            <th>Img</th>
                            <th>Size</th>
                            <th>Color</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>idCategory</th>
                            <th>UpdatedAt</th>
                            <th>CreatedAt</th>
                            <th colspan="2">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listP}" var="item" >
                            <tr>
                                <th class="pd-top">${item.id}</th>
                                <th class="pd-top">${item.title}</th>
                                <td class=""><img src="/assetsa/images/products/${item.img}" width="60px" height="60px" > </td>
                                <td class="pd-top">${item.size}</td>
                                <td class="pd-top">${item.color}</td>
                                <td class="pd-top">${item.price}</td>
                                <td class="pd-top">${item.quantity}</td>
                                <td class="pd-top">${item.idCategoryName}</td>
                                <td class="pd-top">${item.updatedAt}</td>
                                <td class="pd-top">${item.createdAt}</td>

                                <td class="pd-top-action">
                                    <a href="#"><button type="button" class="btn btn-outline-success btn-rounded waves-effect waves-light">Edit</button></a>
                                </td>
                                <td class="pd-top-action">
                                    <a href="#" onclick="showMess(${item.id})"><button type="button" class="btn btn-outline-danger btn-rounded waves-effect waves-light">Delete</button></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <script>
        function showMess(id){
            var option = confirm("Bạn có chắc muốn xóa ID số " + id + " này không?")
            if(option === true){
                window.location.href ='product?action=delete&sid=' + id;
            }
        }
    </script>



    <!-- Footer Start -->
    <footer class="footer">
        <%--5--%>
        <%@include file="/cpa/layout/footer_5.jsp" %>
    </footer>

    <%@include file="/cpa/layout/script/scipt-6.jsp" %>

</body>

</html>