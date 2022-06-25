package com.cg.service;

import com.cg.dto.ProductDTO;
import com.cg.model.Product;
import com.cg.utils.DBConText;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {

    private static final String SELECT_ALL_PRODUCT = "SELECT * FROM product;";
    @Override
    public List<Product> findAll() {
        List<Product> list  = new ArrayList<>();
        try{
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(SELECT_ALL_PRODUCT);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getBigDecimal(3),
                        rs.getInt(4),
                        rs.getDate(5),
                        rs.getDate(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10)
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    private static final String SELECT_ALL_PRODUCTS=
            "SELECT p.id, p.title ,p.price, p.quantity, p.updateAT, p.createAT, c.name, p.size, p.color, p.img " +
                    "FROM product AS p " +
                    "JOIN category AS c " +
                    "ON p.idCategory = c.id;";
    @Override
    public List<ProductDTO> findAllProductDTO() {
        List<ProductDTO> list  = new ArrayList<>();
        try{
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(SELECT_ALL_PRODUCTS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new ProductDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getBigDecimal(3),
                        rs.getInt(4),
                        rs.getDate(5),
                        rs.getDate(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10)
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
       return list;
    }


    /*Xóa user theo id*/
    private static final String DELETE_FROM_PRODUCT= "DELETE FROM product WHERE id = ?;";
    @Override
    public void deleteId(String id) {
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(DELETE_FROM_PRODUCT);

            ps.setString(1,id);
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
//    private static final String INSERT_PRODUCT ="" +
//            "INSERT INTO `product` (`title`, `price`, `quantity`, `createAT`, `idCategory`, `size`, `color`,`img`) " +
//            "VALUES (?, ?, ?, now(), ?, ?, ?, ?);";

    private static final String SP_INSERT_PRODUCT = "CALL sp_insert_product(?, ?, ?, ?, ?, ?, ?, ?,?);";
    @Override
    public boolean create(Product product) {
        boolean success = false;
        try {
            Connection conn = DBConText.getConnection();
            CallableStatement  ps = conn.prepareCall(SP_INSERT_PRODUCT);

                ps.setString(1,product.getTitle());
                ps.setBigDecimal(2,product.getPrice());
                ps.setInt(3,product.getQuantity());
                ps.setInt(4,product.getIdCategory());
                ps.setInt(5,product.getSize());
                ps.setString(6,product.getColor());
                ps.setString(7,product.getImg());
                ps.registerOutParameter(8, Types.BOOLEAN);
                ps.registerOutParameter(9,Types.VARCHAR);

            ps.execute();
            success = true;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public Product findById(int userId) {
        return null;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public boolean existsByPhone(String phone) {
        return false;
    }

}