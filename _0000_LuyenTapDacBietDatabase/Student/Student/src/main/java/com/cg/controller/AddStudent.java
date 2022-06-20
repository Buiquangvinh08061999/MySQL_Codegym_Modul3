package com.cg.controller;

import com.cg.dao.StudentDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="AddStudent",urlPatterns = "/add")
public class AddStudent extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String sname = request.getParameter("name");
            int sgender = Integer.parseInt(request.getParameter("gender"));
            String sdob = request.getParameter("dob");

            StudentDAO dao = new StudentDAO();

            dao.insertInTo(sname,sgender,sdob);

            response.sendRedirect("load");

    }
}
