package package1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViderPanier
 */
@WebServlet("/ViderPanier")
public class ViderPanier extends HttpServlet {
	Connection connexion=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViderPanier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnregistrerCommande enr = new EnregistrerCommande();
		try {
			enr.deleteData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CommanderUnDisque c = new CommanderUnDisque();
		c.getSelectedDisquesList().clear();HttpSession session = request.getSession();
		Enumeration attributes = session.getAttributeNames();
		 while (attributes.hasMoreElements()) {
		 String name = (String) attributes.nextElement();
		 session.removeAttribute("name");
		 }
		 	
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/achat");
		    dispatcher.forward(request, response);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
