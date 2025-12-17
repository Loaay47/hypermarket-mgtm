package managers;

import java.util.ArrayList;
import models.*;

public class AdminManager {

    private final AuthService auth;

    public AdminManager(AuthService auth) {
        this.auth = auth;
    }
    public User addEmployee(String id, String username, String password, String role) {
        User emp;

        switch (role) {
            case "admin" -> emp = new Admin(id, username, password);
            case "inventory" -> emp = new InventoryEmployee(id, username, password);
            case "marketing" -> emp = new MarketingEmployee(id, username, password);
            case "seller" -> emp = new Seller(id, username, password);
            default -> {
                return null;
            }
        }

        // TODO: a bug prevented compilation, no idea why
        // auth.registerUser(emp);
        return emp;
    }

    public void removeEmployee(String id) {
        if (auth.searchUser(id) != null) {
            auth.deleteUser(id);
        }
    }

    public void updateEmployee(User u) {
        auth.updateUser(u);
    }

    public User searchEmployeeById(String id) {
        return auth.searchUser(id);
    }

    public ArrayList<User> listEmployees() {
        return auth.listUsers();
    }
}
