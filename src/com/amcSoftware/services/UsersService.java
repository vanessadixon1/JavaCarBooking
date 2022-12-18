package com.amcSoftware.services;

import com.amcSoftware.User;
import com.amcSoftware.dao.IUsers;

import java.io.*;
import java.util.Scanner;
import java.util.UUID;

public class UsersService implements IUsers {

    public String data =  """
            7e4b9220-a47a-45a7-a33b-7182ee0dc30e, Leila
            0236e9db-8c46-45a1-8fef-718d12e271f3, Bond
            43bf7ab5-1f20-4693-a4f0-7319a7926d66, Ali
            1fda7774-b948-42fa-ad35-7eb1a7248e35, Samira
            """;

    private static User[] users = new User[4];

    public File createFile(String pathname){
        File file = null;
        try {
            file = new File(pathname);
            if(!file.exists()) {
                file.createNewFile();
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return file;
    }

    public void writeToUsersFile(File file)  {
        try(FileWriter fileWriter = new FileWriter(file);
            PrintWriter writer = new PrintWriter(fileWriter)) {
            writer.append(data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readFileAddToDao(File pathname) {
        try {
            Scanner scanner = new Scanner(pathname);
            while(scanner.hasNext()) {
                for(int i = 0; i < users.length; i++) {
                    String[] userInfo = scanner.nextLine().split(", ");
                    UUID id = UUID.fromString(userInfo[0].trim());
                    String name = userInfo[1].trim();
                    users[i] = new User(id, name);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User[] getUsers() {
        return users;
    }
}
