package com.cg.dao;

import com.cg.model.Student;
import com.cg.utils.DBConText;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentDAO {


    private static final String SELECT_ALL_STUDENT = "SELECT * FROM student;";

    public List<Student> findAllStudent() {
        List<Student> list = new ArrayList<>();
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(SELECT_ALL_STUDENT);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Student(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDate(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    private static final String DELETE_ID_STUDENT = "DELETE FROM student WHERE StudentId = ?;";

    public void deleteID(String id) {
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(DELETE_ID_STUDENT);

            ps.setString(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static final String INSERT_INTO_STUDENT = "insert into student (name,gender,dob) values (?,?,?);";

    public void insertInTo(String name, int gender, String dob) {
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(INSERT_INTO_STUDENT);

            ps.setString(1, name);
            ps.setInt(2, gender);
            ps.setString(3, dob);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static final String SELECT_ID_STUDENT ="SELECT * FROM student WHERE studentId = ?;";

    public Student getStudentId(String id) {
        try {
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(SELECT_ID_STUDENT);

            ps.setString(1,id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Student(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDate(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String UPDATE_ID_STUDENT = "UPDATE student SET name=?, gender=?, dob =? WHERE StudentId = ?;";

    public void UpdateStudentId(String id, String name , int gender , String dob){
        try{
            Connection conn = DBConText.getConnection();
            PreparedStatement ps = conn.prepareCall(UPDATE_ID_STUDENT);
            ps.setString(1,name);
            ps.setInt(2,gender);
            ps.setString(3,dob);
            ps.setString(4,id);

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
