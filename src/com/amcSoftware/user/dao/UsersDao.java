package com.amcSoftware.user.dao;

import com.amcSoftware.user.User;
import com.amcSoftware.user.interfaces.IUsers;
import com.amcSoftware.utils.FileServices;

import java.util.List;

public class UsersDao implements IUsers {

    private static List<User> users;

    static {
       users = new FileServices().getUsers();
    }


    @Override
    public List<User> getUsers() {
        return users;
    }
}
