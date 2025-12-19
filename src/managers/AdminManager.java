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
        auth.registerUser(username,password,role);
        return emp;
    }

    public boolean deleteEmployee(String id) {
        if (auth.searchUser(id) != null) {
            auth.deleteUser(id);
            return true;
        }
        return false;
    }

    public boolean updateEmployee(String employeeId, String newUsername, String newPassword, String newRole) {
        User user = auth.searchUser(employeeId);
        if (user == null) {
            return false;
        }

        if (user.getRole().equals("Admin")) {
            newRole = user.getRole();
        }

        user.setUsername(newUsername);
        user.setPassword(newPassword);
        user.setRole(newRole);

        return auth.updateUser(user);
    }

    public User searchEmployeeById(String id) {
        return auth.searchUser(id);
    }

    public ArrayList<User> listEmployees() {
        return auth.listUsers();
    }
}
