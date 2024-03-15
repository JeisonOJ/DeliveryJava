package entity;

public class Client extends User{

    private String address;

    public Client(String username, String password, String role, int id, String name, String address) {
        super(username, password, role, id, name);
        this.address = address;
    }

    @Override
    public void showOrders() {

    }
}
