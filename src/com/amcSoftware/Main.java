package com.amcSoftware;

import com.amcSoftware.car.services.CarService;
import com.amcSoftware.car.services.LocateCar;
import com.amcSoftware.user.dao.UsersDao;
import com.amcSoftware.utils.FileServices;
import com.amcSoftware.user.services.LocateUser;
import com.amcSoftware.user.services.UserService;

import java.io.File;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        FileServices users = new FileServices();
        String pathname = "src/com/amcSoftware/utils/users.csv";
        File file = users.createFile(pathname);
        users.writeToUsersFile(file);
        users.readFileAddToDao(file);

        UsersDao usersDao = new UsersDao();

        LocateUser locateUser = new LocateUser(usersDao);
        LocateCar locateCar = new LocateCar();

        CarService carService = new CarService(locateCar, locateUser);

        UserService userService = new UserService(usersDao, locateUser);

        Scanner scanner = new Scanner(System.in);

        boolean isExit = false;

        menu();
        while(!isExit) {
            int value = Integer.parseInt(scanner.nextLine());
            switch (value) {
                case 1:
                    carService.getAvailableCars();
                    if(carService.carsWithNoBookings().size() == 0) {
                        menu();
                        System.out.println();
                        break;
                    }
                    System.out.println("➡️ select car reg number");
                    String regNumber = scanner.nextLine();
                    userService.getUsers();
                    System.out.println("➡️ select user id");
                    String userId = scanner.nextLine();
                    String s = userId.replace("-", "");
                    UUID uuid = new UUID(
                            new BigInteger(s.substring(0,16), 16).longValue(),
                            new BigInteger(s.substring(16),16).longValue());
                    carService.bookCar(regNumber,uuid);
                    menu();
                    break;
                case 2:
                    userService.getUsers();
                    System.out.println("➡️ select user id");
                    String input = scanner.nextLine();
                    s = input.replace("-", "");
                    uuid = new UUID(
                            new BigInteger(s.substring(0,16), 16).longValue(),
                            new BigInteger(s.substring(16),16).longValue());

                    userService.getUserBookedCars(uuid);
                    System.out.println("\n");
                    menu();
                    break;
                case 3:
                    carService.getAllBookings();
                    System.out.println("\n");
                    menu();
                    break;
                case 4:
                    carService.getAvailableCars();
                    System.out.println("\n");
                    menu();
                    break;
                case 5:
                    carService.getAvailableElectricCars();
                    System.out.println("\n");
                    menu();
                    break;
                case 6:
                    userService.getUsers();
                    System.out.println("\n");
                    menu();
                    break;
                case 7:
                    isExit = true;
                    break;
                default:
                    System.out.println(value + " not a valid option\n\n");
                    menu();
            }
        }
    }

    private static void menu() {
        System.out.println
                ("1️⃣ - Book Car\n" +
                "2️⃣ - View All User Booked Cars\n" +
                "3️⃣ - View All Bookings\n" +
                "4️⃣ - View Available Cars\n" +
                "5️⃣ - View Available Electric Cars\n" +
                "6️⃣ - View all users\n" +
                "7️⃣ - Exit");
    }
}
