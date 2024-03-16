package controller;

import entity.*;

import javax.swing.*;
import java.util.ArrayList;

public class OrderController {

    private static int orderNumber = 0;
    private final ArrayList<Order> orderList = new ArrayList<>();
    private final ArrayList<Product> productList = new ArrayList<>();
    private AuthController auth;

    public OrderController(AuthController auth) {
        this.auth = auth;
    }

    public void setAuth(AuthController auth) {
        this.auth = auth;
    }

    public void orderMenu() {
        if (auth.getUser().getRole() == Account.Role.CLIENT) {
            String option;
            int optionInt = 0;
            do {
                try {
                    option = JOptionPane.showInputDialog(null, """
                            ...::CLIENT MENU::...
                            1. Place an order.
                            2. Show orders.
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
                        createOrder();
                        break;
                    case 2:
                        showOrdersClient();
                        break;
                }

            } while (optionInt != 3);
        } else if (auth.getUser().getRole() == Account.Role.DELIVERYMAN) {
            String option;
            int optionInt = 0;
            do {
                try {
                    option = JOptionPane.showInputDialog(null, """
                            ...::DELIVERYMAN MENU::...
                            1. Show all orders.
                            2. Show my assigned orders.
                            3. Show pending orders and accept new orders.
                            4. Complete an order(Change status to delivered).
                            5. Exit.
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
                        showAllOrders();
                        break;
                    case 2:
                        showOrdersAssignedToDeliveryMan();
                        break;
                    case 3:
                        acceptNewOrders();
                        break;
                    case 4:
                        completeAnOrder();
                        break;
                }

            } while (optionInt != 5);
        } else {
            JOptionPane.showMessageDialog(null, "You aren't logged in");
        }
    }

    public void createOrder() {
        Order order = new Order();
        addProducts();
        order.setProductsList(productList);
        order.setOrderNumber(orderNumber);
        orderNumber++;
        order.setStatus(Order.Status.PENDING);
        order.setDate("03-16-2024");
        order.setClient((Client) auth.getUser());
        orderList.add(order);
    }

    public void addProducts() {
        Product product1 = new Product(120, "Phone", 300);
        Product product2 = new Product(121, "PC", 2000);
        Product product3 = new Product(122, "Tablet", 400);

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
    }

    public void showOrdersClient() {
        StringBuilder message = new StringBuilder();
        for (Order order : orderList) {
            if (order.getDeliveryMan() != null) {
                message.append("Order number: ").append(order.getOrderNumber()).append("\nStatus: ").append(order.getStatus()).append("\nName: ").append(order.getClient().getName())
                        .append("\nDeliveryMan: ").append(order.getDeliveryMan().getName()).append("\n");
            }
            if (order.getClient().getUsername().equalsIgnoreCase(auth.getUser().getUsername())) {
                message.append("Order number: ").append(order.getOrderNumber()).append("\nStatus: ").append(order.getStatus()).append("\nName: ").append(order.getClient().getName()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    public String showAllOrders() {
        StringBuilder message = new StringBuilder();
        for (Order order : orderList) {
            message.append("Order number: ").append(order.getOrderNumber()).append("\nStatus: ").append(order.getStatus()).append("\nName: ").append(order.getClient().getName()).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString());
        return message.toString();
    }

    public void showOrdersAssignedToDeliveryMan() {
        StringBuilder message = new StringBuilder();
        for (Order order : orderList) {
            if (order.getDeliveryMan() == null) {
                continue;
            }
            if (order.getDeliveryMan().getUsername().equalsIgnoreCase(auth.getUser().getUsername())) {
                message.append("Deliveryman: ").append(auth.getUser().getUsername()).append("\nOrder number: ").append(order.getOrderNumber()).append("\nStatus: ").append(order.getStatus()).append("\nName: ").append(order.getClient().getName()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    public String showPendingOrders() {
        StringBuilder message = new StringBuilder();
        for (Order order : orderList) {
            if (order.getStatus() == Order.Status.PENDING) {
                message.append("Deliveryman: ").append(auth.getUser().getUsername()).append("\nOrder number: ").append(order.getOrderNumber()).append("\nStatus: ").append(order.getStatus()).append("\nName: ").append(order.getClient().getName()).append("\n");
            }
        }
        return message.toString();
    }

    public void acceptNewOrders() {
        String message = showAllOrders();
        boolean founded = false;
        message += """
                ...::ACCEPT NEW ORDERS::...
                Please enter the order number to accept the order
                """;
        do {
            try {
                String orderNumberString = JOptionPane.showInputDialog(null, message);
                if (orderNumberString == null) {
                    break;
                }
                int orderNumberInt = Integer.parseInt(orderNumberString);

                Order orderFounded = searchOrderByOrderNumber(orderNumberInt);
                if (orderFounded != null) {
                    orderFounded.setStatus(Order.Status.ACCEPTED);
                    orderFounded.setDeliveryMan((DeliveryMan) auth.getUser());
                    JOptionPane.showMessageDialog(null, "Order number: " + orderFounded.getOrderNumber() + " was accepted.");
                    founded = true;
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Order number incorrect\n" + exception);
            }
        } while (!founded);
    }

    public Order searchOrderByOrderNumber(int orderNumber) {
        if (!orderList.isEmpty()) {
            for (Order order : orderList) {
                if (order.getOrderNumber() == orderNumber) {
                    return order;
                }
            }
        }
        return null;
    }

    public void completeAnOrder() {
        String message = showAllOrders();
        boolean founded = false;
        message += """
                ...::COMPLETE AN ORDER::...
                Please enter the order number to accept the order
                """;
        do {
            try {
                String orderNumberString = JOptionPane.showInputDialog(null, message);
                if (orderNumberString == null) {
                    break;
                }
                int orderNumberInt = Integer.parseInt(orderNumberString);

                Order orderFounded = searchOrderByOrderNumber(orderNumberInt);
                if (orderFounded != null) {
                    orderFounded.setStatus(Order.Status.DELIVERED);
                    JOptionPane.showMessageDialog(null, "Order number: " + orderFounded.getOrderNumber() + " was delivered.");
                    founded = true;
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Order number incorrect\n" + exception);
            }
        } while (!founded);

    }
}
