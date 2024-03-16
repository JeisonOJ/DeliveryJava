import controller.AuthController;
import controller.OrderController;

public class Main {
    public static void main(String[] args) {
        AuthController auth = new AuthController();
        OrderController orderController = new OrderController(auth);
        auth.createAnAccount();
        auth.loginUser();
        if (auth.getUser()!=null){
            orderController.setAuth(auth);
            orderController.orderMenu();
        }
        auth.showUsers();
        auth.logOut();
        auth.loginUser();
        if (auth.getUser()!=null){
            orderController.setAuth(auth);
            orderController.orderMenu();
        }

    }
}
