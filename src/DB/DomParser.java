package DB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import BO.Collection;

public class DomParser {
	static ArrayList<Collection> documentList;
	static int documentId = 1;

	static LinkedHashMap<String,Integer> genreMap = new LinkedHashMap<String,Integer>();
	static ArrayList<String> genreList = new ArrayList<String>();
	static int genreId = 1;

	static LinkedHashMap<String,Integer> peopleMap = new LinkedHashMap<String,Integer>();
	static int peopleId = 1;
	
	static LinkedHashMap<String,ArrayList<Integer>> authorMap = new LinkedHashMap<String,ArrayList<Integer>>();
	static int authorId = 1;

	static LinkedHashMap<String,Integer> bookTitleMap = new LinkedHashMap<String,Integer>();
	static int bookTitleId = 1;

	static LinkedHashMap<String,Integer> publisherMap = new LinkedHashMap<String,Integer>();
	static int publisherId = 1;

	Document dom;
	public DomParser(){
		//create a list to hold the employee objects
		documentList = new ArrayList<Collection>();

	}
	public void runExample() {

		//parse the xml file and get the dom object
		parseXmlFile();

		//get each employee element and create a Employee object
		parseDocument();

		//Iterate through the list and print the data
		printData();

	}


	private void parseXmlFile(){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			dom = db.parse("C:/Users/suhas/Desktop/Course Work/Q3/WebApps/Project 3/dblp-data-big/dblp-data.xml");

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void parseDocument(){
		//get the root elememt
		Element docEle = dom.getDocumentElement();
		int count = 0;
		for(String a : genreList){
			NodeList nl = docEle.getElementsByTagName(a);
			if(nl != null && nl.getLength() > 0) {
				for(int i = 0 ; i <nl.getLength();i++) {
					Element el = (Element)nl.item(i);
					Collection c = getCollection(el);
					if(!genreMap.containsKey(a)){
						genreMap.put(a,genreId);
						genreId += 1;
					}
					//add it to list
					documentList.add(c);
					count++;
					System.out.println(count);
				}


			}
		}
	}
	/**
	 * I take an employee element and read the values in, create
	 * an Employee object and return it
	 * @param empEl
	 * @return
	 */
	private Collection getCollection(Element empEl) {
		ArrayList<Integer> editor_id = new ArrayList<Integer>();
		ArrayList<Integer> booktitle_id = new ArrayList<Integer>();
		ArrayList<Integer> publisher_id = new ArrayList<Integer>();
		String editorIds = "";
		String bookTitleIds = "";
		String publisherIds = "";		

		editor_id = getMultipleValues(empEl,"author",editor_id);
		editor_id = getMultipleValues(empEl,"editor",editor_id);
		if(editor_id.size()>0){
			Collections.sort(editor_id);
			editorIds = editor_id.toString();
		}


		String booktitle = getTextValue(empEl,"booktitle");
		if(booktitle!=null){
			if(!bookTitleMap.containsKey(booktitle)){
				bookTitleMap.put(booktitle, bookTitleId);
				booktitle_id.add(bookTitleId);
				bookTitleId += 1;
			}
			else{
				int id = bookTitleMap.get(booktitle);
				booktitle_id.add(id);
			}
		}
		if(booktitle_id.size()>0){
			Collections.sort(booktitle_id);
			bookTitleIds = booktitle_id.toString();
		}

		String publisher = getTextValue(empEl,"publisher");
		if(publisher!=null){
			if(!publisherMap.containsKey(publisher)){
				publisherMap.put(publisher, publisherId);
				publisher_id.add(publisherId);
				publisherId += 1;
			}
			else{
				int id = publisherMap.get(publisher);
				publisher_id.add(id);
			}
		}
		if(publisher_id.size()>0){
			Collections.sort(publisher_id);
			publisherIds = publisher_id.toString();
		}

		String title = getTextValue(empEl,"title");
		//System.out.println(title);

		String pages = getTextValue(empEl,"pages");
		String start = null;
		String end = null;
		if(pages!=null){
			String[] startEndPage = pages.split("-");
			start = startEndPage[0];
			if(startEndPage.length>1){
				end = startEndPage[1];
			}			
		}

		String year = getTextValue(empEl,"year");
		//System.out.println(year);

		String volume = getTextValue(empEl,"volume");
		//System.out.println(volume);

		String number = getTextValue(empEl,"number");

		String url = getTextValue(empEl,"url");
		//String url = empEl.getElementsByTagName("url").item(0).getTextContent();
		//System.out.println(url);

		String ee = getTextValue(empEl,"ee");

		String cdrom = getTextValue(empEl,"cdrom");

		ArrayList<String> cite = getListOfValue(empEl,"cite");
		String citeList = "";
		if(cite.size()>0){
			citeList = cite.toString();
		}

		String crossref = getTextValue(empEl,"crossref");

		String isbn = getTextValue(empEl,"isbn");

		String series = getTextValue(empEl,"series");


		//Create a new Employee with the value read from the xml nodes
		Collection e = new Collection(documentId, title, start, end, year, volume, number, url, 
				ee, cdrom, citeList, crossref, isbn, series, editorIds, 
				bookTitleIds, publisherIds);
		documentId += 1;
		return e;
	}
	/**
	 * I take a xml element and the tag name, look for the tag and get
	 * the text content
	 * i.e for <employee><name>John</name></employee> xml snippet if
	 * the Element points to employee node and tagName is name I will return John 
	 * @param ele
	 * @param tagName
	 * @return
	 */
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			for(int i=0; i<nl.getLength(); i++){
				Element el = (Element)nl.item(i);
				if (textVal == null){
					textVal = el.getFirstChild().getNodeValue();
				}
				else{
					textVal = textVal.concat(el.getFirstChild().getNodeValue());
				}
			}
		}
		return textVal;
	}

	private ArrayList<Integer> getMultipleValues(Element ele, String tagName, ArrayList<Integer> editor_id) {
		//String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			for(int i=0; i<nl.getLength(); i++){
				Element el = (Element)nl.item(i);
				if(!authorMap.containsKey(el.getFirstChild().getNodeValue())){
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(peopleId);
					list.add(documentId);
					authorMap.put(el.getFirstChild().getNodeValue(),list);
				}
				else{
					ArrayList<Integer> list = authorMap.get(el.getFirstChild().getNodeValue());
					list.add(documentId);
				}
				if(!peopleMap.containsKey(el.getFirstChild().getNodeValue())){
					peopleMap.put(el.getFirstChild().getNodeValue(),peopleId);					
					editor_id.add(peopleId);
					peopleId += 1;
				}
				else{
					int id = peopleMap.get(el.getFirstChild().getNodeValue());
					editor_id.add(id);
				}
			}
		}
		return editor_id;
	}

	private ArrayList<String> getListOfValue(Element ele, String tagName) {
		ArrayList<String> list = new ArrayList<String>();

		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			for(int i=0; i<nl.getLength(); i++){
				Element el = (Element)nl.item(i);				
				list.add(el.getFirstChild().getNodeValue());
			}
		}
		return list;
	}

	/**
	 * Calls getTextValue and returns a int value
	 * @param ele
	 * @param tagName
	 * @return
	 */
	private Integer getIntValue(Element ele, String tagName) {
		//in production application you would catch the exception
		String temp = getTextValue(ele,tagName);
		if(temp!=null){
			return Integer.parseInt(temp);
		}
		else{
			return null;
		}
	}

	/**
	 * Iterate through the list and print the
	 * content to console
	 */
	private void printData(){
		System.out.println("No of entries " + documentList.size() + ".");
	}

	public static String Nullify(String inp){
		if(inp != null){
			return inp;
		}
		else{
			return "";
		}
	}

	public static void makeGenreFile() throws IOException{
		FileWriter writer1 = new FileWriter("C:/Users/suhas/Desktop/Course Work/Q3/WebApps/Project 3/dblp-data-big/genre.txt");
		for (Map.Entry<String,Integer> b : genreMap.entrySet()) {
			writer1.write(b.getValue().toString());
			writer1.write(";");
			writer1.write(b.getKey());
			writer1.write('\n');
		}
		writer1.close();
	}

	public static void makePeopleFile() throws IOException{
		FileWriter writer2 = new FileWriter("C:/Users/suhas/Desktop/Course Work/Q3/WebApps/Project 3/dblp-data-big/people.txt");
		for (Map.Entry<String,Integer> entry : peopleMap.entrySet()) {
			writer2.write(entry.getValue().toString());
			writer2.write(";");
			writer2.write(entry.getKey());
			writer2.write('\n');
		}
		writer2.close();
	}

	public static void makeBookTitleFile() throws IOException{
		FileWriter writer2 = new FileWriter("C:/Users/suhas/Desktop/Course Work/Q3/WebApps/Project 3/dblp-data-big/bookTitle.txt");
		for (Map.Entry<String,Integer> entry : bookTitleMap.entrySet()) {
			writer2.write(entry.getValue().toString());
			writer2.write(";");
			writer2.write(entry.getKey());
			writer2.write('\n');
		}
		writer2.close();
	}

	public static void makePublisherFile() throws IOException{
		FileWriter writer2 = new FileWriter("C:/Users/suhas/Desktop/Course Work/Q3/WebApps/Project 3/dblp-data-big/publisher.txt");
		for (Map.Entry<String,Integer> entry : publisherMap.entrySet()) {
			writer2.write(entry.getValue().toString());
			writer2.write(";");
			writer2.write(entry.getKey());
			writer2.write('\n');
		}
		writer2.close();
	}

	public static void makeDocumentFile() throws IOException{
		FileWriter writer = new FileWriter("C:/Users/suhas/Desktop/Course Work/Q3/WebApps/Project 3/dblp-data-big/dblp_document.txt");
		System.out.println("reached");
		for (Collection entry : documentList) {
			writer.write(Integer.toString(entry.id));
			writer.write(";");
			writer.write(Nullify(entry.title));
			writer.write(";");
			writer.write(Nullify(entry.start));
			writer.write(";");
			writer.write(Nullify(entry.end));
			writer.write(";");
			writer.write(Nullify(entry.year));
			writer.write(";");
			writer.write(Nullify(entry.volume));
			writer.write(";");
			writer.write(Nullify(entry.number));
			writer.write(";");
			writer.write(Nullify(entry.url));
			writer.write(";");
			writer.write(Nullify(entry.ee));
			writer.write(";");
			writer.write(Nullify(entry.cdrom));
			writer.write(";");
			writer.write(Nullify(entry.cite));
			writer.write(";");
			writer.write(Nullify(entry.crossref));
			writer.write(";");
			writer.write(Nullify(entry.isbn));
			writer.write(";");
			writer.write(Nullify(entry.series));
			writer.write(";");
			writer.write(Nullify(entry.editor_id));
			writer.write(";");
			writer.write(Nullify(entry.booktitle_id));
			writer.write(";");
			writer.write(Nullify(entry.publisher_id));
			writer.write('\n');
		}
		writer.close();
	}
	
	public static void makeAuthorDocumentFile() throws IOException{
		FileWriter writer2 = new FileWriter("C:/Users/suhas/Desktop/Course Work/Q3/WebApps/Project 3/dblp-data-big/authorDocument.txt");
		for (Map.Entry<String,ArrayList<Integer>> entry : authorMap.entrySet()) {
			ArrayList<Integer> list = entry.getValue();
			int authID = list.get(0);
			for(int i=1; i<list.size(); i++){
				writer2.write(Integer.toString(authorId));
				writer2.write(";");
				writer2.write(Integer.toString(list.get(i)));
				writer2.write(";");
				writer2.write(Integer.toString(authID));
				writer2.write('\n');
				authorId += 1;
			}
		}
		writer2.close();
	}

	public static Connection getConnection() throws Exception {
		String driver = "org.gjt.mm.mysql.Driver";
		String url = "jdbc:mysql://localhost:3306/moviedb";
		String username = "root";
		String password = "Wednesday@1";

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	public static void main(String[] args) throws SQLException{
		//create an instance
		double start = System.currentTimeMillis();
		DomParser dpe = new DomParser();

		genreList.add("article");
		genreList.add("inproceedings");
		genreList.add("proceedings");
		genreList.add("book");
		genreList.add("incollection");
		genreList.add("phdthesis");
		genreList.add("mastersthesis");
		genreList.add("www");

		dpe.runExample();

		File xmlFile = new File("C:/Users/suhas/Desktop/Course Work/Q3/WebApps/Project 3/dblp-data-big/dblp-data.xml");
		try {
			InputStream fis = new FileInputStream(xmlFile);
			if (fis != null) {
				makeGenreFile();
				makePeopleFile();
				makeBookTitleFile();
				makePublisherFile();
				makeDocumentFile();		
				makeAuthorDocumentFile();
			}

			//FileInputStream fileInp = null;
			PreparedStatement pstmt = null;
			Connection conn = null;

			conn = getConnection();
			conn.setAutoCommit(false);
			
			String fileName = "C:/Users/suhas/Desktop/Course Work/Q3/WebApps/Project 3/dblp-data-big/genre.txt";
			//File file = new File(fileName);
			//fileInp = new FileInputStream(file);
			pstmt = conn.prepareStatement("LOAD DATA LOCAL INFILE ? INTO TABLE tbl_genres " +
					"COLUMNS TERMINATED BY ';' LINES TERMINATED BY '\n';");
			pstmt.setString(1, fileName);
			pstmt.executeUpdate();
			//conn.commit();
			
			fileName = "C:/Users/suhas/Desktop/Course Work/Q3/WebApps/Project 3/dblp-data-big/people.txt";
			//file = new File(fileName);
			//fileInp = new FileInputStream(file);
			pstmt = conn.prepareStatement("LOAD DATA LOCAL INFILE ? INTO TABLE tbl_people " +
					"COLUMNS TERMINATED BY ';' LINES TERMINATED BY '\n';");
			pstmt.setString(1, fileName);
			pstmt.executeUpdate();
			//conn.commit();
			
			fileName = "C:/Users/suhas/Desktop/Course Work/Q3/WebApps/Project 3/dblp-data-big/bookTitle.txt";
			//file = new File(fileName);
			//fileInp = new FileInputStream(file);
			pstmt = conn.prepareStatement("LOAD DATA LOCAL INFILE ? INTO TABLE tbl_booktitle " +
					"COLUMNS TERMINATED BY ';' LINES TERMINATED BY '\n';");
			pstmt.setString(1, fileName);
			pstmt.executeUpdate();
			//conn.commit();
			
			fileName = "C:/Users/suhas/Desktop/Course Work/Q3/WebApps/Project 3/dblp-data-big/publisher.txt";
			//file = new File(fileName);
			//fileInp = new FileInputStream(file);
			pstmt = conn.prepareStatement("LOAD DATA LOCAL INFILE ? INTO TABLE tbl_publisher " +
					"COLUMNS TERMINATED BY ';' LINES TERMINATED BY '\n';");
			pstmt.setString(1, fileName);
			pstmt.executeUpdate();
			//conn.commit();
			
			fileName = "C:/Users/suhas/Desktop/Course Work/Q3/WebApps/Project 3/dblp-data-big/dblp_document.txt";
			//file = new File(fileName);
			//fileInp = new FileInputStream(file);
			pstmt = conn.prepareStatement("LOAD DATA LOCAL INFILE ? INTO TABLE tbl_dblp_document " +
					"COLUMNS TERMINATED BY ';' LINES TERMINATED BY '\n';");
			pstmt.setString(1, fileName);
			pstmt.executeUpdate();
			//conn.commit();
			
			fileName = "C:/Users/suhas/Desktop/Course Work/Q3/WebApps/Project 3/dblp-data-big/authorDocument.txt";
			//file = new File(fileName);
			//fileInp = new FileInputStream(file);
			pstmt = conn.prepareStatement("LOAD DATA LOCAL INFILE ? INTO TABLE tbl_author_document_mapping " +
					"COLUMNS TERMINATED BY ';' LINES TERMINATED BY '\n';");
			pstmt.setString(1, fileName);
			pstmt.executeUpdate();
			conn.commit();
			
			pstmt.close();
			//fileInp.close();
			conn.close();
			
			double end = System.currentTimeMillis();
			System.out.println((end-start)/60000 + " mminutes." );
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
