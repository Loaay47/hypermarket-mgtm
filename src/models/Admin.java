package models;
public class Admin extends User{

	public Admin(String id, String username, String password, String name) {
		super(id, username, password, name, "admin");
	}
	
}

