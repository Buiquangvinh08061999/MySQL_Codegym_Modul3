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
            errors.add("T??n Username kh??ng ????????c ?????? tr????ng");
        }
        if (password.equals("")) {
            errors.add("Password kh??ng ????????c ?????? tr????ng");
        }
        if (fullname.equals("")) {
            errors.add("Password kh??ng ????????c ?????? tr????ng");
        }
        if (phone.equals("")) {
            errors.add("Phone kh??ng ????????c ?????? tr????ng");
        }
        if (email.equals("")) {
            errors.add("Email kh??ng ????????c ?????? tr????ng");
        }
        if (img.equals("")) {
            errors.add("Image kh??ng ????????c ?????? tr????ng");
        }
        if (!isUsername) {
            errors.add("Nh???p Username sai ??i??nh da??ng(Kh??ng bao g???m d???u c??ch, k?? t??? ?????c bi???t)");
        }
        if (!isPassword) {
            errors.add("Password sai ??i??nh da??ng(VD: Ab1@ t????i thi????u 4 ki?? t???? tr???? l??n)");
        }
        if (!isFullName) {
            errors.add("Nh???p Fullname sai ??i??nh da??ng(T??n ph???i vi???t hoa ch??? c??i ?????u v?? kh??ng d???u)");
        }
        if (!isPhone) {
            errors.add("Nh????p Phone sai ??i??nh da??ng(kh??ng bao g???m d???u c??ch,ch????,k?? t??? ?????c bi???t, S??? ??i???n tho???i bao g???m 10 s??? v?? b???t ?????u l?? s??? 0)");
        }
        if (!isEmail) {
            errors.add("Nh???p Email sai ??i??nh da??ng (vd: buiquangvinh@gmail.com)");
        }

        if (!isRole) {
            errors.add("Nh???p Role sai ??i??nh da??ng (vd: ADMIN | USER)");
        }
        boolean success = false;
        boolean exitsEmail = userService.existsByEmail(email);
        boolean exitsPhone = userService.existsByPhone(phone);
        if (exitsEmail) {
            errors.add("Email ??a?? t????n ta??i vui lo??ng nh????p email kha??c");
        } else if (exitsPhone) {
            errors.add("Phone ??a?? t????n ta??i vui lo??ng nh????p phone kha??c");
        } else {

            if (errors.size() == 0) {
                User user = new User(usename, password, fullname, phone, email, cityId, role, img);
                success = userService.create(user);
            }

        }
        if (success) {
            request.setAttribute("success", true);
        } else {
            errors.add("Th??m m????i th????t ba??i");
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
            errors.add("T??n Username kh??ng ????????c ?????? tr????ng");
        }
        if (password.equals("")) {
            errors.add("Password kh??ng ????????c ?????? tr????ng");
        }
        if (fullname.equals("")) {
            errors.add("Password kh??ng ????????c ?????? tr????ng");
        }
        if (phone.equals("")) {
            errors.add("Phone kh??ng ????????c ?????? tr????ng");
        }
        if (email.equals("")) {
            errors.add("Email kh??ng ????????c ?????? tr????ng");
        }
        if (img.equals("")) {
            errors.add("Image kh??ng ????????c ?????? tr????ng");
        }
        if (!isUsername) {
            errors.add("Nh???p Username sai ??i??nh da??ng(Kh??ng bao g???m d???u c??ch, k?? t??? ?????c bi???t)");
        }
        if (!isPassword) {
            errors.add("Password sai ??i??nh da??ng(VD: Ab1@ t????i thi????u 4 ki?? t???? tr???? l??n)");
        }
        if (!isFullName) {
            errors.add("Nh???p Fullname sai ??i??nh da??ng(T??n ph???i vi???t hoa ch??? c??i ?????u v?? kh??ng d???u)");
        }
        if (!isPhone) {
            errors.add("Nh????p Phone sai ??i??nh da??ng(kh??ng bao g???m d???u c??ch,ch????,k?? t??? ?????c bi???t, S??? ??i???n tho???i bao g???m 10 s??? v?? b???t ?????u l?? s??? 0)");
        }
        if (!isEmail) {
            errors.add("Nh???p Email sai ??i??nh da??ng (vd: buiquangvinh@gmail.com)");
        }

        if (!isRole) {
            errors.add("Nh???p Role sai ??i??nh da??ng (vd: ADMIN | USER)");
        }

        boolean success = false;
        if (errors.size() == 0) {

            User user = new User(Integer.parseInt(id), usename, password, fullname, phone, email, cityId, role, img);
            success = userService.update(user);
        }

        if (success) {
            request.setAttribute("success", true);
        } else {
            errors.add("C????p nh????t th????t ba??i");
        }

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
        }

        dispatcher.forward(request, response);
    }


}

