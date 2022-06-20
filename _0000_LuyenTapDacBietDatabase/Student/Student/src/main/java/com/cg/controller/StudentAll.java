//package com.cg.controller;
//
//import com.cg.dao.StudentDAO;
//import com.cg.model.Student;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "StudentAll", urlPatterns = "/users")
//public class StudentAll extends HttpServlet {
//
//    StudentDAO dao;
//
//    public void init() throws ServletException {
//        dao = new StudentDAO();
//    }
//
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//        if (action == null) {
//            action = "";
//        }
//        switch (action) {
//            case "list":
//                showStudent(request, response);
//                break;
//            case "delete":
//                deleteID(request, response);
//                break;
//            default:
//                break;
//        }
//    }
//
//    public void showStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Student> list = dao.findAllStudent();
//
//        request.setAttribute("listS", list);
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/showstudent.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    public void deleteID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String id = request.getParameter("sid");
//        //b2: truyen sid vao dao
//        dao.deleteID(id);
//
//        response.sendRedirect("users");
//    }
//
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//        if (action == null) {
//            action = "";
//        }
//        switch (action) {
//            case "add":
//                doAdd(request, response);
//                break;
//            default:
//                break;
//        }
//    }
//
//    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
//
//        String sname = request.getParameter("name");
//        int sgender = Integer.parseInt(request.getParameter("gender"));
//        String sdob = request.getParameter("dob");
//
//
//        dao.insertInTo(sname, sgender, sdob);
//        dispatcher.forward(request,response);
//        response.sendRedirect("users");
//    }
//
//}
