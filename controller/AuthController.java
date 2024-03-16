package controller;

import entity.Client;
import entity.DeliveryMan;
import entity.User;

import javax.swing.*;
import java.util.ArrayList;

public class AuthController {

    private ArrayList<User> usersList;
    private boolean userLogged;

    public AuthController() {
        usersList = new ArrayList<>();
        userLogged =false;
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
                        String role = "DeliveryMan";
                        String username = JOptionPane.showInputDialog(null, "Enter your username(Account)");
                        String password = JOptionPane.showInputDialog(null, "Enter your password");
                        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your identification"));
                        String name = JOptionPane.showInputDialog(null, "Enter your name");
                        String vehicle = JOptionPane.showInputDialog(null, "Enter your vehicle");
                        if (username == null || password == null || name == null || vehicle == null) {
                            break;
                        }
                        User userDeliveryMan = new DeliveryMan(username, password, role, id, name, vehicle);
                        usersList.add(userDeliveryMan);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Invalid data\n" + exception);
                    }
                    break;
                case 2:
                    try {
                        String role = "Client";
                        String username = JOptionPane.showInputDialog(null, "Enter your username(Account)");
                        String password = JOptionPane.showInputDialog(null, "Enter your password");
                        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your identification"));
                        String name = JOptionPane.showInputDialog(null, "Enter your name");
                        String address = JOptionPane.showInputDialog(null, "Enter your address");
                        if (username == null || password == null || name == null || address == null) {
                            break;
                        }
                        User userClient = new Client(username, password, role, id, name, address);
                        usersList.add(userClient);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Invalid data\n" + exception);
                    }
                    break;
            }

        } while (optionInt != 3);

    }
    public void showUsers(){
        String message = "";
        for (User user: usersList){
            message += "Name: "+user.getName()+" Role: "+user.getRole()+"\n";
        }
        JOptionPane.showMessageDialog(null,message);
    }

    public void loginUser(){

        String username;
        String password;
        do {
            if (userLogged){
                JOptionPane.showMessageDialog(null,"You have already logged in");
                break;
            }
            username = JOptionPane.showInputDialog(null, "Enter your username(Account)");
            password = JOptionPane.showInputDialog(null, "Enter your password");
            if (username == null || password == null){
                break;
            }
            for (User user: usersList){
                if (user.getUsername().equalsIgnoreCase(username)){
                    if (user.getPassword().equalsIgnoreCase(password)){
                        userLogged = true;
                        JOptionPane.showMessageDialog(null,"Welcome: "+username);
                        break;
                    }
                }
            }
            if (!userLogged){
                JOptionPane.showMessageDialog(null,"Invalid credentials");
            }
        }while (!userLogged);
    }
    public void logOut(){
        userLogged = !userLogged;
        JOptionPane.showMessageDialog(null,"You have logged out successfully");
    }

}
