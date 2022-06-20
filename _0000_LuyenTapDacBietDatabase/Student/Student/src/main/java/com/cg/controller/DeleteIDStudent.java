package com.cg.controller;

import com.cg.dao.StudentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="DeleteIDStudent",urlPatterns = "/delete")
public class DeleteIDStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //b1:get sid from jsp
        String id = request.getParameter("sid");
        //b2: truyen sid vao dao
        StudentDAO dao = new StudentDAO();
        dao.deleteID(id);

        response.sendRedirect("load");


    }
}


















