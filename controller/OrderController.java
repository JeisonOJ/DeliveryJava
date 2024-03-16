package controller;

import entity.*;

import javax.swing.*;
import java.util.ArrayList;

public class OrderController {

    private static int orderNumber = 0;
    private ArrayList<Order> orderList;
    private ArrayList<Product> productList;
    private AuthController auth;

    public OrderController(AuthController auth){
        this.auth = auth;
    }

    public void orderMenu(){
        if (auth.getUser().getRole() == Account.Role.CLIENT){
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
                        showOrders();
                        break;
                }

            } while (optionInt != 3);
        } else if (auth.getUser().getRole() == Account.Role.DELIVERYMAN) {

        }else{
            JOptionPane.showMessageDialog(null,"You aren't logged in");
        }
    }

    public void createOrder() {
        Order order = new Order();
        productList = addProducts();
        order.setProductsList(productList);
        order.setOrderNumber(orderNumber);
        order.setStatus(Order.Status.PENDING);
        order.setDate("03-16-2024");
        order.setClient((Client) auth.getUser());
        //missing delivery
        orderList.add(order);
    }

    public ArrayList<Product> addProducts(){
        ArrayList<Product> productList = new ArrayList<>();
        Product product1 = new Product(120,"Phone",300);
        Product product2 = new Product(121,"PC",2000);
        Product product3 = new Product(122,"Tablet",400);

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        return productList;
    }

    public void showOrders() {
        StringBuilder message = new StringBuilder();
        for (Order order : orderList) {
            message.append("Status: ").append(order.getStatus()).append(" Name: ").append(order.getClient().getName()).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }


}
