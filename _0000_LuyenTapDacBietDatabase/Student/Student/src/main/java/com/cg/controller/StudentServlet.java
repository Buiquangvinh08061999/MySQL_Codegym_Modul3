package com.cg.controller;

import com.cg.dao.StudentDAO;
import com.cg.model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="StudentServlet",urlPatterns = "/load")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StudentDAO dao = new StudentDAO();
        List<Student> list = dao.findAllStudent();

        request.setAttribute("listS",list);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/showstudent.jsp");
        dispatcher.forward(request,response);
    }


}











