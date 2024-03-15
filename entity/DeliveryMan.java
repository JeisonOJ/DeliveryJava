package entity;

public class DeliveryMan extends User{
    private double salary;

    public DeliveryMan(String username, String password, String role, int id, String name, double salary) {
        super(username, password, role, id, name);
        this.salary = salary;
    }

    @Override
    public void showOrders() {

    }
}
