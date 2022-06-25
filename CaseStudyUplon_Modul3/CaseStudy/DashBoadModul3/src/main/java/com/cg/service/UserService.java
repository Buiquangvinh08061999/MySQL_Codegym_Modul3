package com.cg.service;

import com.cg.dto.UserDTO;
import com.cg.model.User;
import com.cg.utils.DBConText;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService{



    //Kiểu hiện thị truyền thống nhưng không dùng nữa, ta đã dùng DTO để ánh xạ các thành Phố kiểu(INT) về kiểu(String)để hiển thị
    private static final String SELECT_ALL_USERA="SELECT * FROM users;";
    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(SELECT_ALL_USERA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getDate(10),
                        rs.getString(11)
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }


    /*Dùng DTO để chuyển lại kiểu dữ liệu cityId(Int) sang kiểu cityName(String) để Lấy ra được Hà Nội, Hải Phòng*/
    private static final String SELECT_ALL_USER = "SELECT u.id , u.username, u.password, u.fullname, u.phone,u.email, c.name , u.Role, u.updatedAt,u.createdAt, u.img " +
            " FROM users AS u  " +
            "JOIN cities AS c " +
            " ON u.cityId = c.id;";
    @Override
    public List<UserDTO> findAllUserDTO() {
        List<UserDTO> list = new ArrayList<>();
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(SELECT_ALL_USER);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(new UserDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getDate(10),
                        rs.getString(11)
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    /*Xóa user theo id*/
    private static final String DELETE_FROM_USER= "DELETE FROM users WHERE id = ?;";
    @Override
    public void deleteId(String id) {
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(DELETE_FROM_USER);
            ps.setString(1,id);

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /*Thêm mới user*/
    private static String INSERT_USER= "INSERT INTO users (username, password, fullname, phone, email, cityId, Role, createdAt, img) " +
            "VALUES (?,?,?,?,?,?,?, now(),?);";
    @Override
    public boolean create(User user) {
        boolean success = false;
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(INSERT_USER);

                ps.setString(1,user.getUsername());
                ps.setString(2,user.getPassword());
                ps.setString(3,user.getFullname());
                ps.setString(4,user.getPhone());
                ps.setString(5,user.getEmail());
                ps.setInt(6,user.getCityId());
                ps.setString(7,user.getRole());
                ps.setString(8,user.getImg());

            ps.execute();

            success = true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return success;
    }
    /*Bước 1 Tìm theo id để hiển thị các giá trị theo id đó vào phần ô edit */
    private static String SELECT_USER_BYID = "SELECT u.id, u.username, u.password, u.fullname, u.phone, u.email, u.cityId, u.Role, u.img " +
            "FROM users AS u " +
            "WHERE u.id = ?;";
    @Override
    public User findById(int id) {
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(SELECT_USER_BYID);

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9)
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
      return null;
    }
    /*Update lại các trường theo id */
    private static final String UPDATE_BYID_USER ="UPDATE users SET username=? , password=?, fullname=? , phone=?, email =?, cityId=?, Role=?, updatedAt = now(), img =? WHERE id = ?;";
    @Override
    public boolean update(User user) {
        boolean success = false;
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(UPDATE_BYID_USER);

            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getFullname());
            ps.setString(4,user.getPhone());
            ps.setString(5,user.getEmail());
            ps.setInt(6,user.getCityId());
            ps.setString(7,user.getRole());
            ps.setString(8,user.getImg());
            ps.setInt(9,user.getId());

            ps.execute();
            success = true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return success;
    }

    private static final String EXIST_EMAIL_USER=
            "SELECT COUNT(*) AS count " +
            "FROM users AS u " +
            "WHERE u.email = ?;";
    @Override
    public boolean existsByEmail(String email) {
        boolean exists = false;
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(EXIST_EMAIL_USER);

            ps.setString(1,email);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int count = rs.getInt("count");

                if (count > 0){
                    exists = true;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return exists;
    }


    private static final String EXIST_PHONE_USER =
            "SELECT COUNT(*) AS count " +
            "FROM users AS u " +
            "WHERE u.phone = ?;";
    @Override
    public boolean existsByPhone(String phone) {
        boolean exists = false;
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(EXIST_PHONE_USER);

            ps.setString(1,phone);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int count = rs.getInt("count");

                if (count > 0){
                    exists = true;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return exists;
    }



}
