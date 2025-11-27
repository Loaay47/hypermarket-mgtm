package models;
public class Seller extends User{
	
	public Seller(String id, String username, String password, String name) {
		super(id, username, password, name, "seller");
	}
	
}
