package com.amcSoftware.car.dao;


import com.amcSoftware.car.Brand;
import com.amcSoftware.car.Car;

import java.math.BigDecimal;

public class CarDao {
    private static final Car[] cars;

    static {
        cars = new Car[] {
                new Car("1234", BigDecimal.valueOf(150.59), Brand.JAGUAR,false),
                new Car("8537", BigDecimal.valueOf(59.99), Brand.HONDA,false),
                new Car("8789", BigDecimal.valueOf(59.99), Brand.HONDA,true),
        };
    }

    public static Car[] getCars() {
        return cars;
    }

}
