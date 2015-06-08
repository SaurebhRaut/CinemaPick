package BO;

public class Movies
{
	public int id;
	public String title;
	public int year;
	public String director;
	public String banner_url;
	public String trailer_url;
	
	public Movies() {
		
	}
	
	public Movies(int id, String title, int year, String director, String banner_url, String trailer_url)
	{
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.banner_url = banner_url;
		this.trailer_url = trailer_url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((banner_url == null) ? 0 : banner_url.hashCode());
		result = prime * result
				+ ((director == null) ? 0 : director.hashCode());
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result
				+ ((trailer_url == null) ? 0 : trailer_url.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movies other = (Movies) obj;
		if (banner_url == null) {
			if (other.banner_url != null)
				return false;
		} else if (!banner_url.equals(other.banner_url))
			return false;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (trailer_url == null) {
			if (other.trailer_url != null)
				return false;
		} else if (!trailer_url.equals(other.trailer_url))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	
}