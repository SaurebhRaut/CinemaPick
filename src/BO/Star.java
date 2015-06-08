package BO;

import java.util.ArrayList;
import java.util.List;

public class Star {
	public long id;
	public String first_name;
	public String last_name;
	public String dob;
	public String photo_url;
	public List<String> movies;
	
	public Star(long id, String fname, String lname, String dob, String phoUrl)
	{
		this.id = id;
		this.first_name = fname;
		this.last_name = lname;
		this.dob = dob;
		this.photo_url = phoUrl;
	}
	
	public Star(int id, String fname, String lname)
	{
		this.id = id;
		this.first_name = fname;
		this.last_name = lname;
		this.movies = new ArrayList<String>();
	}
	
	public Star()
	{
		this.movies = new ArrayList<String>();
	}
}
