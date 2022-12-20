package com.amcSoftware.user.dao;

import com.amcSoftware.user.User;
import com.amcSoftware.user.interfaces.IUsers;
import com.amcSoftware.utils.FileServices;

public class UsersDao implements IUsers {

    private static User[] users;

    static {
       users = new FileServices().getUsers();
    }

    @Override
    public User[] getUsers() {
        return users;
    }
}
