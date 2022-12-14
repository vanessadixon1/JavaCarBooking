package com.amcSoftware.user.services;

import com.amcSoftware.carBooking.CarBookingDao;
import com.amcSoftware.user.dao.UsersDao;
import com.amcSoftware.user.User;

import java.util.UUID;

public class UserService {

    private UsersDao usersDao;
    private LocateUser locateUser;

    public UserService(UsersDao usersDao, LocateUser locateUser) {
        this.usersDao = usersDao;
        this.locateUser = locateUser;
    }

    public void getUsers() {
        try {
            for (User user: this.usersDao.getUsers()) {
                System.out.println(user);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getUserBookedCars(UUID userId) {
        User user = this.locateUser.getUser(userId);
        int count = 0;
        try {
            for(int i = 0; i < CarBookingDao.getCarBookings().size(); i++) {
                if((CarBookingDao.getCarBookings().get(i).getUsers().getId().equals(userId))) {
                    System.out.println(CarBookingDao.getCarBookings().get(i));
                    count++;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if(count == 0 ) {
            System.out.println("❌ user " + user + " has no cars booked");
        }

    }
}




