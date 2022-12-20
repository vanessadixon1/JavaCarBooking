package com.amcSoftware.car.services;

import com.amcSoftware.car.Car;
import com.amcSoftware.car.dao.CarDao;
import com.amcSoftware.car.interfaces.ILocateCar;

public class LocateCar implements ILocateCar {

    @Override
    public Car getCar(String regNumber) {
       Car car = null;
        try {
            for(int i = 0; i < CarDao.getCars().length; i++) {
                if(CarDao.getCars()[i].getRegNumber().equals(regNumber)) {
                    car = CarDao.getCars()[i];
                } else {

                }
            }
        } catch (Exception e) {

        }
        return car;
    }
}
