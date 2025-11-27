package models;
public class MarketingEmployee extends User {
	
	public MarketingEmployee(String id, String username, String password, String name) {
		super(id, username, password, name, "marketing");
	}
}
