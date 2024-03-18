package controller;

import entity.Account;
import entity.Client;
import entity.DeliveryMan;
import entity.User;

import javax.swing.*;
import java.beans.JavaBean;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthController {

    private ArrayList<User> usersList;
    private boolean userLogged;
    private User user;

    public AuthController() {
        usersList = new ArrayList<>();
        userLogged = false;
    }

    public boolean isUserLogged() {
        return userLogged;
    }

    public void setUserLogged(boolean userLogged) {
        this.userLogged = userLogged;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void createAnAccount() {
        String option;
        int optionInt = 0;
        do {
            try {
                option = JOptionPane.showInputDialog(null, """
                        ...::REGISTER AN USER::...
                        1. Register as DeliveryMan.
                        2. Register as Client.
                        3. Exit.
                        """);
                if (option == null) {
                    break;
                }
                optionInt = Integer.parseInt(option);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Option incorrect\n" + exception);
            }

            switch (optionInt) {
                case 1:
                    try {
                        Account.Role role = Account.Role.DELIVERYMAN;
                        String username = JOptionPane.showInputDialog(null, "Enter your username(Account)");
                        String password = JOptionPane.showInputDialog(null, "Enter your password");
                        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your identification"));
                        String name = JOptionPane.showInputDialog(null, "Enter your name");
                        String vehicle = JOptionPane.showInputDialog(null, "Enter your vehicle");
                        if (username == null || password == null || name == null || vehicle == null) {
                            break;
                        }
                        if (validatePassword(password)) {
                            User userDeliveryMan = new DeliveryMan(username, password, role, id, name, vehicle);
                            addUserList(userDeliveryMan);
                        } else {
                            JOptionPane.showMessageDialog(null, """
                                    The password must meet the following criteria:
                                    contain both uppercase and lowercase letters, include numbers, and include special characters.
                                    """);
                        }
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Invalid data\n" + exception);
                    }
                    break;
                case 2:
                    try {
                        Account.Role role = Account.Role.CLIENT;
                        String username = JOptionPane.showInputDialog(null, "Enter your username(Account)");
                        String password = JOptionPane.showInputDialog(null, "Enter your password");
                        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your identification"));
                        String name = JOptionPane.showInputDialog(null, "Enter your name");
                        String address = JOptionPane.showInputDialog(null, "Enter your address");
                        if (username == null || password == null || name == null || address == null) {
                            break;
                        }
                        if (validatePassword(password)) {
                            User userClient = new Client(username, password, role, id, name, address);
                            addUserList(userClient);
                        }

                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Invalid data\n" + exception);
                    }
                    break;
            }

        } while (optionInt != 3);

    }

    public void showUsers() {
        StringBuilder message = new StringBuilder();
        for (User user : usersList) {
            message.append("Name: ").append(user.getName()).append(" Role: ").append(user.getRole()).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    public User searchUserByUserName(String username) {
        if (!usersList.isEmpty()) {
            for (User user : usersList) {
                if (user.getUsername().equalsIgnoreCase(username)) {
                    return user;
                }
            }
        }
        return null;
    }

    public void addUserList(User user) {
        if (searchUserByUserName(user.getUsername()) == null) {
            usersList.add(user);
            JOptionPane.showMessageDialog(null, "User registered successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "User already exist in database");
        }
    }

    public boolean validatePassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{4,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();

    }

    public void loginUser() {

        String username;
        String password;
        do {
            if (userLogged) {
                JOptionPane.showMessageDialog(null, "You have already logged in");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Load user...");
            }
            username = JOptionPane.showInputDialog(null, "Enter your username(Account)");
            password = JOptionPane.showInputDialog(null, "Enter your password");
            if (username == null || password == null) {
                break;
            }
            for (User user : usersList) {
                if (user.getUsername().equalsIgnoreCase(username)) {
                    if (user.getPassword().equalsIgnoreCase(password)) {
                        userLogged = true;
                        this.user = user;
                        JOptionPane.showMessageDialog(null, "Welcome: " + username);
                        break;
                    }
                }
            }
            if (!userLogged) {
                JOptionPane.showMessageDialog(null, "Invalid credentials");
            }
        } while (!userLogged);
    }

    public void logOut() {
        userLogged = !userLogged;
        user = null;
        JOptionPane.showMessageDialog(null, "You have logged out successfully");
    }

}
