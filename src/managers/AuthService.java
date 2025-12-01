package managers;

import models.User;
import java.io.*;
import java.util.*;
import models.Admin;
import models.InventoryEmployee;
import models.MarketingEmployee;
import models.Seller;

public class AuthService {
    private final ArrayList<User> users = new ArrayList<>();
    private User currentUser = null;
    private final File file = new File("data/users.txt");

    public AuthService() {
        loadUsersFromFile();
    }
    public boolean login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                currentUser = u;
                return true;
            }
        }
        return false;
    }
    public void logout() {
        currentUser = null;
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public void registerUser(User u) {
        users.add(u);
        saveUsersToFile();
    }

    public User searchUser(String id){ 
        for (User u : users) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<User> listUsers() {
        return new ArrayList<>(users);
    }

    public void updateUser(User updated) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(updated.getId())) {
                users.set(i, updated);
                saveUsersToFile();
                return;
            }
        }
    }

    public void deleteUser(String id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                users.remove(u);
                saveUsersToFile();
            }
        }
    }
    
    private void loadUsersFromFile() {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                String id = parts[0], username = parts[1], password = parts[2], role = parts[3];

                User u;

                switch (role) {
                    case "admin" -> u = new Admin(id, username, password);
                    case "inventory" -> u = new InventoryEmployee(id, username, password);
                    case "marketing" -> u = new MarketingEmployee(id, username, password);
                    case "seller" -> u = new Seller(id, username, password);
                    default -> {
                        continue;
                    }
                }
                users.add(u);
            }
        } catch (IOException e){
            System.out.println("Error reading from file.");
        }
    }
    
    private void saveUsersToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
          for (User u : users) {
                bw.write(
                    u.getId() + "," +
                    u.getUsername() + "," +
                    u.getPassword() + "," +
                    u.getRole()
                );
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writng in file.");
        }
    }
}
