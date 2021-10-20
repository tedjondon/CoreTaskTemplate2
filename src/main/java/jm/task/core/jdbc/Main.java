package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl test = new UserServiceImpl();
        //1. создание таблицы
        test.createUsersTable();

        //2. добавление 4х user-ов
        User user1 = new User("Anton", "Bobrov", (byte) 30);
        test.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("User " + user1.getName() + " добавлен в базу");

        User user2 = new User("Andrey", "Bobrovskiy", (byte) 31);
        test.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("User " + user2.getName() + " добавлен в базу");

        User user3 = new User("Alex", "Bobov", (byte) 32);
        test.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("User " + user3.getName() + " добавлен в базу");

        User user4 = new User("Afanasiy", "Bobriatskiy", (byte) 33);
        test.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println("User " + user4.getName() + " добавлен в базу");

        //3. получение user-ов и вывод в консоль
        List<User> users = test.getAllUsers();
        for (User user: users) {
            System.out.println(user);
        }

        //4. Очистка таблицы
        test.cleanUsersTable();

        //5. Удаление таблицы
        test.dropUsersTable();
    }
}
