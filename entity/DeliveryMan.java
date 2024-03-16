package entity;

public class DeliveryMan extends User{
    private String vehicle;


    public DeliveryMan(String username, String password, Role role, int id, String name, String vehicle) {
        super(username, password, role, id, name);
        this.vehicle = vehicle;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

}
