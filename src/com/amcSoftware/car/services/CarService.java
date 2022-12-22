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
            CarBookingDao.getCarBookings().add(carBooking);
            System.out.println("🎉 Successfully booked car with reg number " + car.getRegNumber() + " for user " +
                    car + "\nBooking ref: " + bookingRef);
            CarDao.getCars().remove(carBooking.getCar());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllBookings() {
        try {
            for(int i = 0; i < CarBookingDao.getCarBookings().size(); i++) {
                System.out.println(CarBookingDao.getCarBookings().get(i));
            }
            if(CarBookingDao.getCarBookings().size() == 0) {
                System.out.println("No bookings available 😕");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAvailableCars() {
        try {
            int availableCars = 0;

            for(int i = 0; i < CarDao.getCars().size(); i++) {
                System.out.println(CarDao.getCars().get(i));
                availableCars++;
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

            for(int i = 0; i < CarDao.getCars().size(); i++) {
                 if((CarDao.getCars().get(i).isElectric()) &&
                        !(CarBookingDao.getCarBookings().contains(CarDao.getCars().get(i)))) {
                    System.out.println(CarDao.getCars().get(i));
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
