package BO;

public class creditcards {
	
	public String id;
	public String first_name;
	public String last_name;
	public String expiration;
	
	public creditcards()
	{
		
	}
	
	public creditcards(String id, String fname, String lname, String expiration)
	{
		this.id = id;
		this.first_name = fname;
		this.last_name = lname;
		this.expiration = expiration;
	}
}
