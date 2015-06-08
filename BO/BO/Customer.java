package BO;

public class Customer {
	public int id; // int primary key AUTO_INCREMENT,
	public String first_name; // varchar(50) not null,
	public String last_name; // varchar(50) not null,
	public String cc_id; // varchar(20) not null, #referencing creditcards.id
	public String address; // varchar(200) not null,
	public String email; // varchar(50) not null,
	public String password; // varchar(20) not null,
	
	public Customer(int id, String First_name, String Last_name, String cc_id, String address,String email,String password){
		this.id=id;
		this.first_name = First_name;
		this.last_name = Last_name;
		this.cc_id = cc_id;
		this.address = address;
		this.email = email;
		this.password = password;
	}
	
	public Customer(){
		
	}
}
