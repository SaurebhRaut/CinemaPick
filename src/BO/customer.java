package BO;

public class customer {

	public int id;
	public String first_name;
	public String last_name;
	public String cc_id;
	public String address;
	public String email;
	
	public customer(int id, String fname, String lname)
	{
		this.id = id;
		this.first_name = fname;
		this.last_name = lname;
	}
	
	public customer()
	{
		
	}
}

