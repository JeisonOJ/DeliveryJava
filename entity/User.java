package entity;

public abstract class User extends Account{
    private int id;
    private String name;

    public User(String username, String password, Role role, int id, String name) {
        super(username, password, role);
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
