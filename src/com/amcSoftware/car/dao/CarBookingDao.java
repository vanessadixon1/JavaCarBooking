package com.amcSoftware.car.dao;


import com.amcSoftware.car.CarBooking;

public class CarBookingDao {
    private static CarBooking[] carBookings;
    private static int carsBooked = 4;

    static {
        carBookings = new CarBooking[carsBooked];
    }

    public static CarBooking[] getCarBookings() {
        return carBookings;
    }

    public static int getCarsBooked() {
        return carsBooked;
    }

    public static void setCarsBooked(int carsBooked) {
        CarBookingDao.carsBooked = carsBooked;
    }
}

