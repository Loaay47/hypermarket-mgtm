package models;
public class InventoryEmployee extends User {
	
	public InventoryEmployee(String id, String username, String password, String name) {
		super(id, username, password, name, "inventory");
	}
	
}
