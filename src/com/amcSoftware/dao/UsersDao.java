package com.amcSoftware.dao;

import com.amcSoftware.User;
import com.amcSoftware.services.UsersService;

public class UsersDao implements IUsers {

    private static User[] users;

     static {
       users = new UsersService().getUsers();
    }

    @Override
    public User[] getUsers() {
        return users;
    }
}
