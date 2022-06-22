//package com.cg.dao;
//
//import com.cg.model.User;
//import com.cg.utils.DBConText;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDao {
//
//    private static final String SELECT_ALL_USER = "SELECT * FROM users;";
//
//    public List<User> findAllUser(){
//        List<User> list = new ArrayList<>();
//        try {
//            Connection conn = DBConText.getConnection();
//            PreparedStatement ps = conn.prepareCall(SELECT_ALL_USER);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                list.add(new User(rs.getInt(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getString(4),
//                        rs.getString(5),
//                        rs.getString(6),
//                        rs.getString(7),
//                        rs.getString(8),
//                        rs.getDate(9),
//                        rs.getDate(10)
//                ));
//            }
//        }catch (SQLException e){
//
//        }
//        return list;
//    }
//}
