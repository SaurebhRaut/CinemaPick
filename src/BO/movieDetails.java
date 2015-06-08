package BO;
import java.util.*;

public class movieDetails {

	//movie details;
	public Integer id;
	public String title;
	public Integer year;
	public String director;
	public String banner_url;
	public String trailer_url;
	
	//pricing == not in the DB
	public int price;
	
	//genre list associated with each movie
	public List<genre> genreList = new ArrayList<genre>();
	
	//star list associated with each movie
	public List<Star> starList = new ArrayList<Star>();
	
	public movieDetails (Movies mov)
	{
		this.id = mov.id;
		this.title = mov.title;
		this.year = mov.year;
		this.director = mov.director;
		this.banner_url = mov.banner_url;
		this.trailer_url = mov.trailer_url;
		this.price = 15;
	}
	
	public movieDetails()
	{
		
	}
}
