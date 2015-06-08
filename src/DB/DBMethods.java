package DB;
import BO.movieDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import BO.movieDetails;

public class DBMethods {
	public static ArrayList<movieDetails> movielist = new ArrayList<movieDetails>();
	
	public static void StoreList(ArrayList<movieDetails> movies){
		movielist = movies;
	}
	
	public static ArrayList<movieDetails> RetriveList(){
		return movielist;
	}
	
	public static int ReturnSize(){
		int temp = movielist.size();
		return movielist.size();
	}
	
	public static void DelString(){
		movielist.clear();
	}
	
	public static movieDetails getMovieDetails(int id){
		movieDetails dispMovie = new movieDetails();
		
		return dispMovie;
	}
	
	public static ArrayList<movieDetails> Sort(int type, int order){

	    if(type==2){
	    	if(order==0)
	    	{
	    	Collections.sort(movielist, new Comparator<movieDetails>() {
	    	    @Override
	    	    public int compare(movieDetails o1, movieDetails o2) {
	    	        return o1.year.compareTo(o2.year);
	    	    }});
	    	}
	    	else
	    	{
	    		Collections.sort(movielist, Collections.reverseOrder( new Comparator<movieDetails>() {
		    	    @Override
		    	    public int compare(movieDetails o1, movieDetails o2) {
		    	        return o1.year.compareTo(o2.year);
		    	    }}));
	    	}
	    }	
	    else if(type==1){
	    	if(order == 0)
	    	{
	    	Collections.sort(movielist, new Comparator<movieDetails>() {
	    	    @Override
	    	    public int compare(movieDetails o1, movieDetails o2) {
	    	        return o1.title.compareTo(o2.title);
	    	    }});
	    	}
	    	else
	    	{
	    		Collections.sort(movielist, Collections.reverseOrder( new Comparator<movieDetails>() {
		    	    @Override
		    	    public int compare(movieDetails o1, movieDetails o2) {
		    	        return o1.title.compareTo(o2.title);
		    	    }}));
	    	}
	    }	
	    
	    return movielist;
	}

}
