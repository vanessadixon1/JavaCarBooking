package com.amcSoftware.user.services;

import com.amcSoftware.user.User;
import com.amcSoftware.user.dao.UsersDao;
import com.amcSoftware.user.interfaces.ILocateUser;

import java.util.UUID;

public class LocateUser implements ILocateUser {

    private  UsersDao usersDao;

    public LocateUser(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public User getUser(UUID userId) {
        User user = null;

        User[] users = this.usersDao.getUsers();
        try {
            for(int i = 0; i < users.length; i++) {
                if(users[i].getId().equals(userId)) {
                    user = users[i];
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

}
