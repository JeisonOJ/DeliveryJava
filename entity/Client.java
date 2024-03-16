package entity;

public class Client extends User{

    private String address;

    public Client(String username, String password, Role role, int id, String name, String address) {
        super(username, password, role, id, name);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
