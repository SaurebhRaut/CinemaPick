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
	
	public customer(int id, String fname, String lname, String cid, String add, String email)
	{
		this.id = id;
		this.first_name = fname;
		this.last_name = lname;
		this.cc_id = cid;
		this.address = add;
		this.email = email;
	}
}

