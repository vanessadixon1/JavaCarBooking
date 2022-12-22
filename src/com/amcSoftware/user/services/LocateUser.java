package com.amcSoftware.user.services;

import com.amcSoftware.user.User;
import com.amcSoftware.user.dao.UsersDao;
import com.amcSoftware.user.interfaces.ILocateUser;

import java.util.List;
import java.util.UUID;

public class LocateUser implements ILocateUser {

    private  UsersDao usersDao;

    public LocateUser(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public User getUser(UUID userId) {
        User user = null;

        List<User> users = this.usersDao.getUsers();
        try {
            for(int i = 0; i < users.size(); i++) {
                if(users.get(i).getId().equals(userId)) {
                    user = users.get(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

}
