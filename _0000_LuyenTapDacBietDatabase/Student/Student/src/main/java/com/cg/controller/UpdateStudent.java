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

@WebServlet(name = "UpdateStudent", urlPatterns = "/update")
public class UpdateStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("sid");
        StudentDAO dao = new StudentDAO();
        Student student = dao.getStudentId(id);

        if (student != null) {
            request.setAttribute("st", student);
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("/update.jsp");
        dispatcher.forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sid = request.getParameter("id");
        String sname = request.getParameter("name");
        int sgender = Integer.parseInt(request.getParameter("gender"));
        String sdob = request.getParameter("dob");

        StudentDAO dao = new StudentDAO();
        dao.UpdateStudentId(sid, sname, sgender, sdob);


        response.sendRedirect("load");


    }


}
