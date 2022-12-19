package com.amcSoftware.services;


import com.amcSoftware.Car;
import com.amcSoftware.CarBooking;
import com.amcSoftware.User;
import com.amcSoftware.dao.CarBookingDao;
import com.amcSoftware.dao.CarDao;
import com.amcSoftware.dao.UsersDao;

import java.util.UUID;

//@Bean or Component = gives us an instance of this class
public class CarService {

    private UsersDao usersDao;
    private CarDao carDao;
    private CarBookingDao carBookingDao;

    //Autowired = means go find an instance for me and inject it for me so I can assign it to the field to be used throughout the code
    public CarService(UsersDao usersDao, CarDao carDao, CarBookingDao carBookingDao) {
        this.usersDao = usersDao;
        this.carDao = carDao;
        this.carBookingDao = carBookingDao;
    }

    private Car locateCar(String regNumber) {
        Car car = null;
        try {
            for(int i = 0; i < carDao.getCars().length; i++) {
                if(carDao.getCars()[i].getRegNumber().equals(regNumber)) {
                    car = carDao.getCars()[i];
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return car;
    }

    private User locateUser(UUID userId) {
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

    public void bookCar(String regNumber, UUID userId) {
        try {
            Car car = locateCar(regNumber);
            User user = locateUser(userId);
            UUID bookingRef = UUID.randomUUID();
            CarBooking carBooking = new CarBooking(bookingRef, user, car, false);
            if(carBookingDao.getCarBookings()[0] == null) {
                carBookingDao.getCarBookings()[0] = carBooking;
                System.out.println("🎉 Successfully booked car with reg number " + car.getRegNumber() + " for user " +
                        car + "\nBooking ref: " + bookingRef);
            }else {
                System.out.println("There is already a booking in place. Only 1 booking is allowed");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAvailableCars() {
        try {
            int availableCars = 0;

            for(int i = 0; i < carDao.getCars().length; i++) {
                if((carBookingDao.getCarBookings()[0] == null) ||
                        (carBookingDao.getCarBookings()[0].getCar() != carDao.getCars()[i])) {
                    System.out.println(carDao.getCars()[i]);
                    availableCars++;
                }
            }

            if(availableCars == 0) {
                System.out.println("❌ no cars are available for renting");
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }

    public void getAvailableElectricCars() {
        try {
            int electricCars = 0;

            for(int i = 0; i < carDao.getCars().length; i++) {
                if((carBookingDao.getCarBookings()[0] == null) && (carDao.getCars()[i].isElectric()) ) {
                        System.out.println(carDao.getCars()[i]);
                        electricCars++;
                }else if((carDao.getCars()[i].isElectric()) &&
                            (carDao.getCars()[i] != carBookingDao.getCarBookings()[0].getCar()) ) {
                    System.out.println(carDao.getCars()[i]);
                    electricCars++;
                }
            }

            if(electricCars == 0) {
                System.out.println("❌ No electric cars available for renting");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getUsers() {
        try {
            User[] users = this.usersDao.getUsers();
            for(int i = 0; i < users.length; i++) {
                System.out.println(users[i]);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void getAllBookings() {
        try {
            int booked = 0;

            for(int i = 0; i < carDao.getCars().length; i++) {
                if((carBookingDao.getCarBookings()[0] != null) && (carDao.getCars()[i].getRegNumber() == carBookingDao.getCarBookings()[0].getCar().getRegNumber())) {
                    System.out.println(carBookingDao.getCarBookings()[0]);
                    booked++;
                }
            }

            if(booked == 0) {
                System.out.println("No bookings available 😕");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getUserBookedCars(UUID userId) {
        User user = locateUser(userId);
        int count = 0;
        try {
            for(int i = 0; i < carBookingDao.getCarBookings().length; i++) {
                if((carBookingDao.getCarBookings()[i] != null) &&
                        (carBookingDao.getCarBookings()[i].getUsers().getId().equals(userId))) {
                    System.out.println(carBookingDao.getCarBookings()[i]);
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
