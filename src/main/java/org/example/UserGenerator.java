package org.example;

import java.util.Random;

public class UserGenerator {
    public static User getDefault() {
        String[] login = new String[]{"575@google.com", "575@yandex.ru", "575@mail.com", "575@gmail.com"};
        String[] password = new String[]{"?:;№_!)", "HHHHHHH", "123456", "123456789"};
        String[] firstName = new String[]{"Masha", "Kate", "q", "1"};
        int randomLogin = new Random().nextInt(login.length);
        int randomPassword = new Random().nextInt(password.length);
        int randomFirstName = new Random().nextInt(firstName.length);
        return new User(login[randomLogin], password[randomPassword], firstName[randomFirstName]);
    }
}
