import controller.AuthController;

public class Main {
    public static void main(String[] args) {
        AuthController auth = new AuthController();
        auth.createAnAccount();
        auth.showUsers();
        auth.loginUser();
    }
}
