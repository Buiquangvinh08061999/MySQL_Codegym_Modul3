package com.cg.servlet;

import com.cg.model.Custommer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustommerServlet", urlPatterns = "/customer")
public class CustommerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/index.jsp");

        List<Custommer> custommerList = new ArrayList<>();
        custommerList.add(new Custommer("Vinh", "08/06/1999", "Huế", "https://static.vecteezy.com/packs/media/components/global/search-explore-nav/img/vectors/term-bg-1-666de2d941529c25aa511dc18d727160.jpg"));
        custommerList.add(new Custommer("Vinh", "08/06/1999", "Huế", "https://static.vecteezy.com/packs/media/components/global/search-explore-nav/img/vectors/term-bg-1-666de2d941529c25aa511dc18d727160.jpg"));
        custommerList.add(new Custommer("Vinh", "08/06/1999", "Huế", "https://static.vecteezy.com/packs/media/components/global/search-explore-nav/img/vectors/term-bg-1-666de2d941529c25aa511dc18d727160.jpg"));

        req.setAttribute("custommerList", custommerList);
        dispatcher.forward(req, resp);
    }
}
