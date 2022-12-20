package com.amcSoftware.car.services;

import com.amcSoftware.car.Car;
import com.amcSoftware.car.CarBooking;
import com.amcSoftware.car.dao.CarBookingDao;
import com.amcSoftware.car.dao.CarDao;
import com.amcSoftware.user.services.LocateUser;
import com.amcSoftware.user.User;

import java.util.UUID;

public class CarService {


    private LocateCar locateCar;
    private LocateUser locateUser;


    public CarService(LocateCar locateCar,LocateUser locateUser ) {
        this.locateCar = locateCar;
        this.locateUser = locateUser;

    }

    public void bookCar(String regNumber, UUID userId) {
        try {
            Car car = this.locateCar.getCar(regNumber);
            User user = this.locateUser.getUser(userId);
            UUID bookingRef = UUID.randomUUID();
            CarBooking carBooking = new CarBooking(bookingRef, user, car, false);
            if(CarBookingDao.getCarBookings()[0] == null) {
                CarBookingDao.getCarBookings()[0] = carBooking;
                System.out.println("🎉 Successfully booked car with reg number " + car.getRegNumber() + " for user " +
                        car + "\nBooking ref: " + bookingRef);
            }else {
                System.out.println("There is already a booking in place. Only 1 booking is allowed");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllBookings() {
        try {
            int booked = 0;

            for(int i = 0; i < CarDao.getCars().length; i++) {
                if((CarBookingDao.getCarBookings()[0] != null) && (CarDao.getCars()[i].getRegNumber() == CarBookingDao.getCarBookings()[0].getCar().getRegNumber())) {
                    System.out.println(CarBookingDao.getCarBookings()[0]);
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

    public void getAvailableCars() {
        try {
            int availableCars = 0;

            for(int i = 0; i < CarDao.getCars().length; i++) {
                if((CarBookingDao.getCarBookings()[0] == null) ||
                        (CarBookingDao.getCarBookings()[0].getCar() != CarDao.getCars()[i])) {
                    System.out.println(CarDao.getCars()[i]);
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

            for(int i = 0; i < CarDao.getCars().length; i++) {
                if((CarBookingDao.getCarBookings()[0] == null) && (CarDao.getCars()[i].isElectric()) ) {
                    System.out.println(CarDao.getCars()[i]);
                    electricCars++;
                }else if((CarDao.getCars()[i].isElectric()) &&
                        (CarDao.getCars()[i] != CarBookingDao.getCarBookings()[0].getCar()) ) {
                    System.out.println(CarDao.getCars()[i]);
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

}
