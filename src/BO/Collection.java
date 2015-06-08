package BO;

import java.util.ArrayList;

public class Collection {
	public int id;
	public String title;
	public String start;
	public String end;
	public String year;
	public String volume;
	public String number;
	public String url;
	public String ee;
	public String cdrom;
	public String cite;
	public String crossref;
	public String isbn;
	public String series;
	public String editor_id;
	public String booktitle_id;
	//public Integer genre_id;
	public String publisher_id;

	public Collection(){

	}

	public Collection(int id, String title, String start, String end, String year, String volume, String number, String url, 
			String ee, String cdrom, String cite, String crossref, String isbn, String series, String editor_id, 
			String booktitle_id, String publisher_id) {
		this.id = id;
		this.title = title;
		this.start = start;
		this.end  = end;
		this.year  = year;
		this.volume = volume;
		this.number = number;
		this.url = url;
		this.ee = ee;
		this.cdrom = cdrom;
		this.cite  = cite;
		this.crossref = crossref;
		this.isbn = isbn;
		this.series = series;
		this.editor_id = editor_id;
		this.booktitle_id = booktitle_id;
		//this.genre_id = genre_id;
		this.publisher_id  = publisher_id;
	}
	//    public int getAge() {
		//            return age;
		//    }
	//    public void setAge(int age) {
		//            this.age = age;
	//    }
	//    public int getId() {
	//            return id;
	//    }
	//    public void setId(int id) {
	//            this.id = id;
	//    }
	//    public String getName() {
	//            return name;
	//    }
	//    public void setName(String name) {
	//            this.name = name;
	//    }
	//    public String getType() {
	//            return type;
	//    }
	//    public void setType(String type) {
	//            this.type = type;
	//    }       
	//   
	//   
	//    public String toString() {
	//            StringBuffer sb = new StringBuffer();
	//            sb.append("Employee Details - ");
	//            sb.append("Name:" + getName());
	//            sb.append(", ");
	//            sb.append("Type:" + getType());
	//            sb.append(", ");
	//            sb.append("Id:" + getId());
	//            sb.append(", ");
	//            sb.append("Age:" + getAge());
	//            sb.append(".");
	//           
	//            return sb.toString();
	//    }
} 