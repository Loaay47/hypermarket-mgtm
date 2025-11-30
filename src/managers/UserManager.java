package managers;

import java.io.*;
import models.*;
import java.util.*;

public class UserManager {
    private final File path = new File("D:\\VSCode\\HyperMarketManagement\\data");
    private final File data = new File(path, "users.dat");
    private  ArrayList<User> users = new ArrayList<>();

    public UserManager() {
        loadUsers();
    }

    public void addUser(User user) {
        users.add(user);
    }
    public void deleteUser(String id) {
        users.removeIf(user -> user.getId().equals(id));
        saveUsers();
    }
    public void updateUser(String id, String newUsername, String newPass, String newName) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                u.setUsername(newUsername);
                u.setPassword(newPass);
                u.setName(newName);
                saveUsers();
                break;
            }
        }
    }
    public User search(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }
    public void listUsers() {
        System.out.println("\nAll Users: ");

        System.out.println("ID - Username - Role");
        System.out.println("---------------------------");
            
        for (User u : users) {
            System.out.println(u.getId() + " - " + u.getUsername() + " - " + u.getRole());
        }
    }
    public User login(String username, String passsword) {
        for (User u : users){
            if (u.getUsername().equals(username) && u.getPassword().equals(passsword))
                return u;
        }
        return null;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private void saveUsers() {
        try { 
            if(!data.exists()) data.mkdirs();

            try (FileWriter writer = new FileWriter(data)) {
                for (User user : users) {
                    String line = String.join(",", user.getId(), user.getUsername(), user.getPassword(), user.getName(), user.getRole());
                    writer.write(line + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings({"CallToPrintStackTrace", "unused"})
    private void loadUsers() {
        users = new ArrayList<>();
        if (!data.exists()) return;

        try (Scanner sc = new Scanner(data)) {
            while (sc.hasNextLine()) {
            String[] parts = sc.nextLine().split(",");
                if (parts.length == 5) {
                    String id = parts[0];
                    String username = parts[1];
                    String password = parts[2];
                    String name = parts[3];
                    String role = parts[4];
                    
                    User user;
                    user = switch (role.trim().toLowerCase()) {
                    case "admin" -> new Admin(id, username, password, name);
                    case "marketing" -> new MarketingEmployee(id, username, password, name);
                    case "inventory" -> new InventoryEmployee(id, username, password, name);
                    case "sales" -> new Seller(id, username, password, name);
                    default -> null;
                };
                    if (user != null) users.add(user);
            }
       }     
    } catch (IOException e) {
        e.printStackTrace();
        }
    }
}
