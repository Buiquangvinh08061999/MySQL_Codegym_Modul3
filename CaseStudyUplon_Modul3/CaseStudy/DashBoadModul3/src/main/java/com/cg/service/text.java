package com.cg.service;

import com.cg.model.User;

public class text {
    public static void main(String[] args) {
        UserService userService = new UserService();
        User u = userService.findById(47);
        System.out.println(u);
    }


}
