package com.cg.controller;

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
        List<User> list = userService.findAll();

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

        String id =  request.getParameter("id");

        User user = userService.findById(Integer.parseInt(id));

        if(user != null){
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

        String usename = request.getParameter("username".trim());
        String password = request.getParameter("password").trim();
        String fullname = request.getParameter("fullname").trim();
        String phone = request.getParameter("phone").trim();
        String email = request.getParameter("email").trim();
        String city = request.getParameter("city").trim();
        String role = request.getParameter("role").trim();
        String img = request.getParameter("file").trim();

        User user = new User(usename, password, fullname, phone, email, city, role ,img);
        boolean success = userService.create(user);

        if (success) {
            request.setAttribute("success", true);
        } else {
            request.setAttribute("errors", true);
        }

        dispatcher.forward(request, response);


//        boolean isUsename = Validate.isUsernameValid(usename);
//        boolean isPassword = Validate.isPasswordValid(password);
//        boolean isFullName = Validate.isFullNameValid(fullname);
//        boolean isPhone = Validate.isPhoneValid(phone);
//        boolean isEmail = Validate.isEmailValid(email);


    }
    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/user/Edituser.jsp");

        String id = request.getParameter("id").trim();
        String usename = request.getParameter("username".trim());
        String password = request.getParameter("password").trim();
        String fullname = request.getParameter("fullname").trim();
        String phone = request.getParameter("phone").trim();
        String email = request.getParameter("email").trim();
        String city = request.getParameter("city").trim();
        String role = request.getParameter("role").trim();
        String img = request.getParameter("file").trim();

        User user = new User(Integer.parseInt(id) ,usename,password,fullname,phone,email,city,role,img);

        boolean success = userService.update(user);

        if (success) {
            request.setAttribute("success", true);
        } else {
            request.setAttribute("errors", true);
        }

        dispatcher.forward(request,response);
    }



}

