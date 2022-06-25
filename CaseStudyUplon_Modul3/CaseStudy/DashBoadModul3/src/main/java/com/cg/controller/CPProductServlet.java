package com.cg.controller;

import com.cg.dto.ProductDTO;
import com.cg.model.Product;
import com.cg.service.IProductService;
import com.cg.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name ="CPProductServlet" , urlPatterns = "/cpa/product")
public class CPProductServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;

    IProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
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
        switch (action){
            case "list":
                showListProduct(request, response);
                break;
            case "delete":
                showDeleteId(request, response);
                break;
            case "create":
                showCreateProduct(request, response);
            default:
                showListProduct(request, response);
                break;

        }
    }
    private void showListProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/product/Listproduct.jsp");

        List<ProductDTO> list = productService.findAllProductDTO();
        request.setAttribute("listP",list);

        dispatcher.forward(request, response);
    }

    private void showDeleteId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("sid");
        productService.deleteId(id);

        response.sendRedirect("/cpa/product");

    }
    private void showCreateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/product/CreateProduct.jsp");

        dispatcher.forward(request,response);

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
                doCreateProduct(request, response);
                break;
            default:
                doCreateProduct(request, response);
                break;
        }
    }

    private void doCreateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cpa/product/CreateProduct.jsp");

        String title = request.getParameter("title");
        String price = request.getParameter("price");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int idCategory = Integer.parseInt(request.getParameter("idCategory"));
        int size = Integer.parseInt(request.getParameter("size"));
        String color = request.getParameter("color");
        String img = request.getParameter("file");

        List<String> errors = new ArrayList<>();

        if(title.equals("")){
            errors.add("Title không được để trống");
        }
        if(price.equals("")){
            errors.add("Tiền không được để trống");
        }
        if(color.equals("")){
            errors.add("Color không được để trống");
        }
        if(img.equals("")){
            errors.add("Imgage không được để trống");
        }
        if(idCategory < 1  || idCategory > 9){
            errors.add("Nhập sai dữ liệu hệ thống");
        }


        boolean success = false;
        if(errors.size() == 0){
            Product product = new Product(title,BigDecimal.valueOf(Long.parseLong(price)),quantity,idCategory,size,color,img);
            success = productService.create(product);
        }

        if (success) {
            request.setAttribute("success", true);
        } else {
            errors.add("Thêm mới thất bại");
        }

        if(errors.size() > 0){
            request.setAttribute("errors",errors);
        }


        dispatcher.forward(request,response);
    }
}










































