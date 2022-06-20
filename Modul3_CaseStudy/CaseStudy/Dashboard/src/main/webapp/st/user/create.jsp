<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dash Board</title>
    <%@include file="/st/layout/head/head.jsp" %>
    <link rel="stylesheet" href="/assets/css/create.css">
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
        <div class="right_col" role="main"><!-- top tiles -->
            <div class="row">
                <div class="col-lg-10">
                    <h1>Create user</h1>
                </div>
                <div class="col-lg-2 heder-right" >
                    <a href="/st/user/list.jsp">
                        <button type="button" class="btn btn-success">List of user</button>
                    </a>
                </div>

            </div>

            <div>
                <form method="post">
                    <div class="mb-3">
                        <label for="fullname" class="form-label">FullName</label>
                        <input type="text" class="form-control" id="fullname" name="fullName">
                    </div>
                    <div class="mb-3">
                        <label for="age" class="form-label">Age</label>
                        <input type="text" class="form-control" id="age" name="fullName">
                    </div>
                    <div class="mb-3">
                        <label for="phone" class="form-label">Phone</label>
                        <input type="text" class="form-control" id="phone" name="phone">
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Address</label>
                        <input type="text" class="form-control" id="address" name="address">
                    </div>
                    <div class="mb-3">
                        <label for="cityId" class="form-label">City</label>
                        <select class="form-control" name="cityId" id="cityId">
                            <option value="1">Hà Nội</option>
                            <option value="2">Hải Phòng</option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-success create">Create</button>
                </form>
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
