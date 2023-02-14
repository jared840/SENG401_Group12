package entities;

//Supplier entity class
public class Supplier{

//private class variables
	private String firstName;
	private String lastName;
	private String description;
	private String username;
	private String password;
	

	/*
	Supplier Constructor
	Inputs: first name, last name, description, username, and password (all String types)
	Outputs: None - instantiates supplier object w/ inputted values
	*/
	public Supplier (String fname, String lname, String desc, String user, String pswd) {

	this.firstName=fname;
	this.lastName=lname;
	this.description=desc;
	this.username=user;
	this.password = pswd;

	}








}
