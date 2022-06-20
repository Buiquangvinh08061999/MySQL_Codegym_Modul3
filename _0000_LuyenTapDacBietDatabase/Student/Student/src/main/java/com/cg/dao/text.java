package com.cg.dao;

import com.cg.model.Student;

import java.util.List;

public class text {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        List<Student> list = dao.findAllStudent();
//
//        for (Student item : list){
//            System.out.println(item);
//        }

        Student sa = dao.getStudentId("7");

        System.out.println(sa);



    }

}
