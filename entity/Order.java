package entity;

import com.sun.jdi.PrimitiveValue;

import java.util.ArrayList;

public class Order {
    private int orderNumber;
    private ArrayList<Product> productList;

    public enum Status {
        PENDING, ACCEPTED, DELIVERED
    }

    private Status status;
    private String date;
    private double orderPrice;
    private Client client;
    private DeliveryMan deliveryMan;

    public Order() {
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ArrayList<Product> getProductsList() {
        return productList;
    }

    public void setProductsList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public DeliveryMan getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }
}
