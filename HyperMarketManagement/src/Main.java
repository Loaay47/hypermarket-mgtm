import users.*;
import modules.*;
public class Main {
    public static void main(String[] args) {
        UserManager um = new UserManager();

        um.addUser(new Admin("1", "Adam admin", "adam123", "Adam Tate"));
        um.addUser(new MarketingEmployee("2", "John marketer", "john123", "John Carter"));
        um.addUser(new InventoryEmployee("3", "Nour inventory", "nour123", "Nour Ehab"));
        um.addUser(new SalesEmployee("4", "Max saler", "123max", "Max Jumper"));

        User loggedIn = um.login("Nour inventory", "nour123");
        if (loggedIn != null) {
            System.out.println("Login success!");
        }
        // display users
        um.listUsers();
    
        User search = um.search("marketer");
        if (search != null) {
            System.out.println("\nSearch Result: " + search.getName() + " - " + search.getRole());
        }

        um.updateUser("2", "marketingPro", "newpass", "Marketing Boss");

        um.deleteUser("3");

        System.out.println("\nAfter Update & Deletion:");
        um.listUsers();
    }
}
