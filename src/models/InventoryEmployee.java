package models;
public class InventoryEmployee extends User {
	
	public InventoryEmployee(String id, String username, String password) {
		super(id, username, password, "inventory");
	}
	
}
