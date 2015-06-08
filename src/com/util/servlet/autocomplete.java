package com.util.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import BO.Movies;
import BO.top5search;
import BO.movieDetails;
import DB.dbOperations;

//import com.google.gson.Gson;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
 

/**
 * Servlet implementation class autocomplete
 */
@WebServlet("/autocomplete")
public class autocomplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public autocomplete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parstring = request.getParameter("parstring");
		StringTokenizer st = new StringTokenizer(parstring);
		if(parstring.length()>0){
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
//        response.setHeader("Cache-control", "no-cache, no-store");
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Expires", "-1");
// 
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
//        response.setHeader("Access-Control-Max-Age", "86400");
// 
//        Gson gson = new Gson(); 
//        JsonObject myObj = new JsonObject();
//       
        //============================================
//        boolean check= false;
//        StringBuilder query = new StringBuilder();
//        query.append("Select id,title from movies where ");
//		query.append(" title like '%" + st.nextToken() + "%' ");
//		while (st.hasMoreTokens()) {
//			check=true;
//			query.append("AND title like '%" + st.nextToken() + "%' ");
//		}
//		if(check==false){
//			query.append(";");
//		}
//        
        int i=0;
        String[] st1 = parstring.split(" ");
        int length = st1.length;
        boolean check= false;
		StringBuilder query = new StringBuilder();
		System.out.println(st1[0]);
		String findthe = "the";
		if(length==1 && st1[0].equals(findthe)){
			query.append("Select id,title from movies where ");
			query.append(" title like '%" + st.nextToken() + "%'");
		}
		else{
		query.append("Select id,title from movies where MATCH(title) AGAINST('");
		while(length>1){
		query.append(" +" + st1[i] + " ");
		i++;length--;
		}
		if(length==1){
			query.append(st1[i]+"*' IN BOOLEAN MODE);");
		}
		}
        
        //====================================================
		
//		query.append("Select id,title from movies where ");
//		query.append(" title like '%" + parstring + "%';");
	//	query.append("OR levenshtein('"+parstring+"',title)<=3 ORDER BY `score` ASC;");
//		query.append("Select id,title,levenshtein('"+parstring+"',title) as 'score' from movies where ");
//		query.append(" title like '%" + parstring + "%'");
//		query.append("OR levenshtein('"+parstring+"',title)<=3 ORDER BY `score` ASC;");
		
		
		dbOperations dbop = new dbOperations();
		System.out.println(query.toString());
		
		List<Movies> listFromQuery = new ArrayList<Movies>();
		try {
			listFromQuery = dbop.getMoviesBySearchQueryajax(query.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<ul>");
		top5search search5 = new top5search();
		if(listFromQuery.size()>=1)
			out.println("<li><a href=SingleMovie.jsp?movieID="+listFromQuery.get(0).id +">"+listFromQuery.get(0).title+"</a></li>");
		if(listFromQuery.size()>=2)
			out.println("<li><a href=SingleMovie.jsp?movieID="+listFromQuery.get(1).id +">"+listFromQuery.get(1).title+"</a></li>");
		if(listFromQuery.size()>=3)
			out.println("<li><a href=SingleMovie.jsp?movieID="+listFromQuery.get(2).id +">"+listFromQuery.get(2).title+"</a></li>");
		if(listFromQuery.size()>=4)
			out.println("<li><a href=SingleMovie.jsp?movieID="+listFromQuery.get(3).id +">"+listFromQuery.get(4).title+"</a></li>");
		if(listFromQuery.size()>=5)
			out.println("<li><a href=SingleMovie.jsp?movieID="+listFromQuery.get(4).id +">"+listFromQuery.get(4).title+"</a></li>");
		if(listFromQuery.size()>=6)
			out.println("<li><a href=SingleMovie.jsp?movieID="+listFromQuery.get(5).id +">"+listFromQuery.get(5).title+"</a></li>");
		if(listFromQuery.size()>=7)
			out.println("<li><a href=SingleMovie.jsp?movieID="+listFromQuery.get(6).id +">"+listFromQuery.get(6).title+"</a></li>");
		if(listFromQuery.size()>=8)
			out.println("<li><a href=SingleMovie.jsp?movieID="+listFromQuery.get(7).id +">"+listFromQuery.get(7).title+"</a></li>");
		
		
		
		
//		out.println("<li>"+listFromQuery.get(1).title+"</li>");
//		out.println("<li>"+listFromQuery.get(2).title+"</li>");
//		out.println("<li>"+listFromQuery.get(3).title+"</li>");
//		out.println("<li>"+listFromQuery.get(4).title+"</li>");
//		out.println("</ul>");
        out.close();
		}
		}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parstring = request.getParameter("parstring");
		 
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
 
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
 
//        Gson gson = new Gson(); 
//        JsonObject myObj = new JsonObject();
        

		StringBuilder query = new StringBuilder();
		query.append("Select id,title from movies where ");
		query.append(" title like '%" + parstring + "%';");
	//	query.append("OR levenshtein('"+parstring+"',title)<=3 ORDER BY `score` ASC;");
//		query.append("Select id,title,levenshtein('"+parstring+"',title) as 'score' from movies where ");
//		query.append(" title like '%" + parstring + "%'");
//		query.append("OR levenshtein('"+parstring+"',title)<=3 ORDER BY `score` ASC;");
		
		
		dbOperations dbop = new dbOperations();
		System.out.println(query.toString());
		
		List<Movies> listFromQuery = new ArrayList<Movies>();
		try {
			listFromQuery = dbop.getMoviesBySearchQueryajax(query.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		top5search search5 = new top5search();
		if(listFromQuery.get(1).title.length()>0)
			search5.id1 = listFromQuery.get(0).title;
		if(listFromQuery.get(2).title.length()>0)
			search5.id2 = listFromQuery.get(1).title;
		if(listFromQuery.get(3).title.length()>0)
			search5.id3 = listFromQuery.get(2).title;
		if(listFromQuery.get(4).title.length()>0)
			search5.id4 = listFromQuery.get(3).title;
		if(listFromQuery.get(5).title.length()>0)
			search5.id5 = listFromQuery.get(4).title;
		
		
		out.println("<ul>");
		out.println("<li>"+search5.id1+"</li>");
		out.println("<li>"+search5.id2+"</li>");
		out.println("<li>"+search5.id3+"</li>");
		out.println("<li>"+search5.id4+"</li>");
		out.println("<li>"+search5.id5+"</li>");
		out.println("</ul>");
		
		
//		JsonElement search5json = gson.toJsonTree(search5);
//        if((search5.id1) != ""){
//            myObj.addProperty("success", false);
//        }
//        else {
//            myObj.addProperty("success", true);
//        }
//        myObj.add("search5", search5json);
        //out.println(myObj.toString());
 
        out.close();
        
	}

}
