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
    private final IdGenerator idGenerator;

    public AuthService(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
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

    public User registerUser(String username, String password, String role) {
        String id;
        switch (role) {
            case "inventory" -> id = idGenerator.nextInventoryId();
            case "marketing" -> id = idGenerator.nextMarketingId();
            case "seller" -> id = idGenerator.nextSellerId();
            default -> {
                return null;
            }
        }

        User u;
        switch (role) {
            case "inventory" -> u = new InventoryEmployee(id, username, password);
            case "marketing" -> u = new MarketingEmployee(id, username, password);
            case "seller" -> u = new Seller(id, username, password);
            default -> {
                return null;
            }
        }

        users.add(u);
        saveUsersToFile();
        return u;
    }

    public User registerSellar(String username, String password) {
        String id = idGenerator.nextSellerId();
        User u = new Seller(id, username, password);

        users.add(u);
        saveUsersToFile();
        return u;
    }

    public User searchUser(String id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public String getCurrentRole() {
        return getCurrentUser().getRole();
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
            if (u.getId().equals(id) && !(u instanceof Admin)) {
                users.remove(u);
                saveUsersToFile();
            }
        }
    }

    private void loadUsersFromFile() {

        try {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();

                users.add(new Admin("A000", "loaay", "1234"));
                users.add(new Admin("A001", "abdo", "1321"));

                saveUsersToFile();
                return;
            }
            if (file.length() == 0) {
                users.add(new Admin("A000", "loaay", "1234"));
                users.add(new Admin("A001", "abdo", "1321"));

                saveUsersToFile();
                return;
            }
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length < 4) continue;

                    String id = parts[0], username = parts[1], password = parts[2], role = parts[3];

                    User u;
                    switch (role) {
                        case "inventory" -> u = new InventoryEmployee(id, username, password);
                        case "marketing" -> u = new MarketingEmployee(id, username, password);
                        case "seller" -> u = new Seller(id, username, password);
                        case "admin" -> u = new Admin(id, username, password);
                        default -> {
                            continue;
                        }
                    }
                    users.add(u);
                }
            }

        } catch (IOException e) {
            System.err.println("Error accessing users file: " + e.getMessage());
        }
    }

    private void saveUsersToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (User u : users) {
                bw.write(
                        u.getId() + "," +
                                u.getUsername() + "," +
                                u.getPassword() + "," +
                                u.getRole());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing in file.");
        }
    }
}
