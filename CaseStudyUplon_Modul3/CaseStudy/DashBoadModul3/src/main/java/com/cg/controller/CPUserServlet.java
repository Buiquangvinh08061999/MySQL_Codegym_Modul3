package com.cg.controller;

import com.cg.dto.UserDTO;
import com.cg.model.User;
import com.cg.service.IUserService;
import com.cg.service.UserService;
import com.cg.utils.Validate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name ="CPUserServlet" , urlPatterns = "/cpa/user")
public class CPUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    IUserService userService;

    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html/charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "list":
                showListPage(request, response);
                break;
            case "delete":
                deleteID(request, response);
                break;
            case "create":
                showCreate(request, response);
                break;
            case "edit":
                showEdit(request, response);
                break;
            default:
                showListPage(request, response);
                break;
        }
    }

    private void showListPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/user/listuser.jsp");

        List<UserDTO> list = userService.findAllUserDTO();

        request.setAttribute("listS", list);

        dispatcher.forward(request, response);
    }

    private void deleteID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("sid");
        userService.deleteId(id);

        response.sendRedirect("/cpa/user");
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/user/createuser.jsp");

        dispatcher.forward(request, response);
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/user/Edituser.jsp");

        String id = request.getParameter("id");

        User user = userService.findById(Integer.parseInt(id));

        if (user != null) {
            request.setAttribute("user", user);
        }

        dispatcher.forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html/charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                doCreate(request, response);
                break;
            case "edit":
                doUpdate(request, response);
                break;
        }
    }


    private void doCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/user/createuser.jsp");

        String usename = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        int cityId = Integer.parseInt(request.getParameter("cityId"));
        String role = request.getParameter("role");
        String img = request.getParameter("file");


        List<String> errors = new ArrayList<>();
        boolean isUsername = Validate.isUsernameValid(usename);
        boolean isPassword = Validate.isPasswordValid(password);
        boolean isFullName = Validate.isFullNameValid(fullname);
        boolean isPhone = Validate.isPhoneValid(phone);
        boolean isEmail = Validate.isEmailValid(email);
        boolean isRole = Validate.isRoleValid(role);

        if (usename.equals("")) {
            errors.add("Tên Username không được để trống");
        }
        if (password.equals("")) {
            errors.add("Password không được để trống");
        }
        if (fullname.equals("")) {
            errors.add("Password không được để trống");
        }
        if (phone.equals("")) {
            errors.add("Phone không được để trống");
        }
        if (email.equals("")) {
            errors.add("Email không được để trống");
        }
        if (img.equals("")) {
            errors.add("Image không được để trống");
        }
        if (!isUsername) {
            errors.add("Nhập Username sai định dạng(Không bao gồm dấu cách, kí tự đặc biệt)");
        }
        if (!isPassword) {
            errors.add("Password sai định dạng(VD: Ab1@ tối thiếu 4 kí tự trở lên)");
        }
        if (!isFullName) {
            errors.add("Nhập Fullname sai định dạng(Tên phải viết hoa chữ cái đầu và không dấu)");
        }
        if (!isPhone) {
            errors.add("Nhập Phone sai định dạng(không bao gồm dấu cách,chữ,kí tự đặc biệt, Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
        }
        if (!isEmail) {
            errors.add("Nhập Email sai định dạng (vd: buiquangvinh@gmail.com)");
        }

        if (!isRole) {
            errors.add("Nhập Role sai định dạng (vd: ADMIN | USER)");
        }
        boolean success = false;
        boolean exitsEmail = userService.existsByEmail(email);
        boolean exitsPhone = userService.existsByPhone(phone);
        if (exitsEmail) {
            errors.add("Email đã tồn tại vui lòng nhập email khác");
        } else if (exitsPhone) {
            errors.add("Phone đã tồn tại vui lòng nhập phone khác");
        } else {

            if (errors.size() == 0) {
                User user = new User(usename, password, fullname, phone, email, cityId, role, img);
                success = userService.create(user);
            }

        }
        if (success) {
            request.setAttribute("success", true);
        } else {
            errors.add("Thêm mới thất bại");
        }


        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
        }

        dispatcher.forward(request, response);

    }


    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/user/Edituser.jsp");

        String id = request.getParameter("id");
        String usename = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        int cityId = Integer.parseInt(request.getParameter("cityId"));
        String role = request.getParameter("role");
        String img = request.getParameter("file");

        List<String> errors = new ArrayList<>();
        boolean isUsername = Validate.isUsernameValid(usename);
        boolean isPassword = Validate.isPasswordValid(password);
        boolean isFullName = Validate.isFullNameValid(fullname);
        boolean isPhone = Validate.isPhoneValid(phone);
        boolean isEmail = Validate.isEmailValid(email);
        boolean isRole = Validate.isRoleValid(role);

        if (usename.equals("")) {
            errors.add("Tên Username không được để trống");
        }
        if (password.equals("")) {
            errors.add("Password không được để trống");
        }
        if (fullname.equals("")) {
            errors.add("Password không được để trống");
        }
        if (phone.equals("")) {
            errors.add("Phone không được để trống");
        }
        if (email.equals("")) {
            errors.add("Email không được để trống");
        }
        if (img.equals("")) {
            errors.add("Image không được để trống");
        }
        if (!isUsername) {
            errors.add("Nhập Username sai định dạng(Không bao gồm dấu cách, kí tự đặc biệt)");
        }
        if (!isPassword) {
            errors.add("Password sai định dạng(VD: Ab1@ tối thiếu 4 kí tự trở lên)");
        }
        if (!isFullName) {
            errors.add("Nhập Fullname sai định dạng(Tên phải viết hoa chữ cái đầu và không dấu)");
        }
        if (!isPhone) {
            errors.add("Nhập Phone sai định dạng(không bao gồm dấu cách,chữ,kí tự đặc biệt, Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
        }
        if (!isEmail) {
            errors.add("Nhập Email sai định dạng (vd: buiquangvinh@gmail.com)");
        }

        if (!isRole) {
            errors.add("Nhập Role sai định dạng (vd: ADMIN | USER)");
        }

        boolean success = false;
        if (errors.size() == 0) {

            User user = new User(Integer.parseInt(id), usename, password, fullname, phone, email, cityId, role, img);
            success = userService.update(user);
        }

        if (success) {
            request.setAttribute("success", true);
        } else {
            errors.add("Cập nhật thật bại");
        }

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
        }

        dispatcher.forward(request, response);
    }


}

