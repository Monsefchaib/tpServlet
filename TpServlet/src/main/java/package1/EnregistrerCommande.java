package package1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class EnregistrerCommande
 */
@WebServlet("/EnregistrerCommande")
public class EnregistrerCommande extends HttpServlet {
	
	Connection connexion=null;
	static Statement stmt=null;
	PreparedStatement pstmt=null;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnregistrerCommande() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String nom = null;
		 int nbreProduit = 0;
		 Cookie[] cookies = request.getCookies();
		 boolean connu = false;
		 nom = Identification.chercheNom(cookies);
		 OuvreBase(); 
		 AjouteNomBase(nom);
		 response.setContentType("text/html"); 
		 PrintWriter out = response.getWriter(); 
		 out.println("<html>");
		 out.println("<body>");
		 out.println("<head>");
		 out.println("<title> votre commande </title>"); 
		 out.println("</head>");
		 out.println("<body bgcolor=\"white\">");
		 out.println("<h3>" + "Bonjour " + nom + " voici ta nouvelle commande" + "</h3>"); 
		 HttpSession session = request.getSession();
		 Enumeration names = session.getAttributeNames();
		 while (names.hasMoreElements()) {
		 nbreProduit++;
		 String name = (String) names.nextElement();
		 String value = session.getAttribute(name).toString();
		 out.println(name + " = " + value + "<br>");
		 }
		 AjouteCommandeBase(nom,session);
		 out.println("<h3>" + "et voici " + nom + " ta commande complete" + "</h3>");
		 try {
			MontreCommandeBase(nom, out);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 out.println("<A HREF=vider> Vous pouvez commandez un autre disque </A><br> "); 
		 out.println("</body>");
		 out.println("</html>");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		 out.println("<a HREF=vider> Vous pouvez commandez un autre disque </a><br> "); 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	protected void OuvreBase() { try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connexion = DriverManager.getConnection("jdbc:mysql://localhost:8889/magasin","root","root"); connexion.setAutoCommit(true);
		stmt = connexion.createStatement();
		}
		catch (Exception E) {
		log(" -------- probeme " + E.getClass().getName() );
		E.printStackTrace(); }
		}
	
	protected void fermeBase() {
		try { stmt.close();
		connexion.close(); }
		catch (Exception E) {
		log(" -------- probeme " + E.getClass().getName() ); 
		E.printStackTrace();
		
		}
		}
	
	protected void AjouteNomBase(String nom) { try {
		ResultSet rset = null;
		pstmt= connexion.prepareStatement("select numero from personnel where nom=?");
		pstmt.setString(1,nom);
		rset=pstmt.executeQuery();
		if (!rset.next())
		stmt.executeUpdate("INSERT INTO personnel VALUES (NULL,'" + nom + "' )" ); }
		catch (Exception E) {
		log(" - probeme " + E.getClass().getName() ); E.printStackTrace();
		} }
	
	protected void AjouteCommandeBase(String nom, HttpSession session ) {
		 String value = session.getAttribute("code").toString();
		 System.out.println(value);	 
		 String query = "Insert into commande Values (NULL, '" + value + "', 1)";
		 try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	protected void MontreCommandeBase(String nom, PrintWriter out) throws SQLException {
		try {
			out.println("<h3>Les Produits Presents dans la Base : </h3><br>");
			ResultSet rset = null;
			pstmt= connexion.prepareStatement("select * from commande ");
			rset=pstmt.executeQuery();
			while (rset.next()) {
			out.println(rset.getString("article"));
			out.println("<br>");
			}
			
		}
			catch (Exception E) {
			log(" - probeme " + E.getClass().getName() ); E.printStackTrace();
			}
		
	}
	
	public static void deleteData() throws SQLException {
		String query = "Delete from commande";
		 try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



}
}
	

