<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dash Board </title>
    <%@include file="/st/layout/head/head.jsp" %>
    <link rel="stylesheet" href="/assets/css/list.css">
</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">

        <div class="col-md-3 left_col">
            <%@include file="/st/layout/body/head_left.jsp" %>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
            <%@include file="/st/layout/body/head_top.jsp" %>
        </div>

        <!-- page content -->
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-lg-10">
                    <h1>List of user</h1>
                </div>
                <div class="col-lg-2 heder-right">
                    <a href="/st/user/create.jsp">
                        <button type="button" class="btn btn-success"> Create User</button> </a>
                </div>
                <div>
                    <table class="table table-hover table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Full Name</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>City</th>
                            <th colspan="2" style="text-align: center">Action</th>
                        </tr>
                        </thead>
                    </table>

                </div>

            </div>
        </div>
    </div>


    <!-- footer content -->
    <footer>
        <%@include file="/st/layout/footer/footer.jsp" %>
    </footer>
</div>
</div>

<%@include file="/st/layout/script/script.jsp" %>

</body>
</html>
