package entity;

public class DeliveryMan extends User{
    private String vehicle;

    public DeliveryMan(String username, String password, String role, int id, String name, String vehicle) {
        super(username, password, role, id, name);
        this.vehicle = vehicle;
    }

    @Override
    public void showOrders() {

    }
}
