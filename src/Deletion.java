

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BO.Customer;

/**
 * Servlet implementation class Deletion
 */
@WebServlet("/Deletion")
public class Deletion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deletion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String b=request.getParameter("user_id");
		String b1 = request.getParameter("password");
		boolean flag = DB.DBMethods.authentication(b,b1);
		if(flag==false){
		request.setAttribute("Error", "Login Failed");
		request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		else{
			request.setAttribute("Error", "Login Successful");
			request.getRequestDispatcher("Menu.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String b=request.getParameter("myradio");
		DB.DBMethods.DeleteCustomerEntry(Integer.parseInt(b));
		String message = "Deleted Successfully customer with id="+b;
		request.setAttribute("Error", message);
		request.getRequestDispatcher("Deletecustomer.jsp").forward(request, response);
	}

}
