package com.amcSoftware.dao;

import com.amcSoftware.User;

import java.util.UUID;

public class UserDao {

    private static User[] userDao;

    static {
        userDao = new User[] {
                new User(UUID.fromString("9ac51d2b-aaaf-4bf2-834a-e02964e10fc3"), "Casie"),
                new User(UUID.fromString("C21d126a-3608-4980-9f9c-aa179f5cebc3"), "David")
        };
    }

    public static User[] getUserDao() {
        return userDao;
    }
}
