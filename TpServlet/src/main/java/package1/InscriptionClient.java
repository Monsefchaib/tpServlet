package package1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InscriptionClient
 */
@WebServlet("/InscriptionClient")
public class InscriptionClient extends HttpServlet {
	String nomRecu=null, motPasseRecu=null; String nomCookie=null, motPasseCookie=null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		nomRecu = request.getParameter("nom");
		motPasseRecu = request.getParameter("motdepasse");
		try {
			Cookie[] cookies = request.getCookies();
			nomCookie = Arrays.stream(cookies).filter(t -> "nom".equals(t.getName())).findFirst().get().getValue();
			motPasseCookie = Arrays.stream(cookies).filter(t -> "motdepasse".equals(t.getName())).findFirst().get()
					.getValue();
		} catch (Exception e) {
		}
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		System.out.println("main");
		System.out.println(nomRecu);
		System.out.println(motPasseRecu);
		System.out.println(nomCookie);
		System.out.println(motPasseCookie);
		
		if (nomCookie==null && nomRecu==null){
		// Cas 1 : cas où il n'y a ni de cookies ni de parametres 
		System.out.println("Case1");
		out.println("<html>");
		out.println("<body>");
		out.println("<head>");
		out.println("<title> inscription d'un client </title>");
		out.println("</head>");
		out.println("<body bgcolor='white' >");
		out.println( nomRecu +" | "+ motPasseRecu +" | "+ nomCookie +" | "+ motPasseCookie ); 
		out.println("<h3>" + "Bonjour, vous devez vous inscrire " + "</h3>");
		out.println("<h3>" + "Attention mettre nom et le mot de passe avec plus de 3 caracteres" + "</h3>");
		out.print(" <form action='InscriptionClient' method='GET' > "); out.println("nom");
		out.println("<input type='text' size='20' name='nom' >"); out.println("<br>");
		out.println("mot de passe");
		out.println("<input type='password' size='20' name='motdepasse'> <br>"); 
		out.println("<input type='submit' value='inscription'>"); 
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	
		System.out.println(nomRecu);
		System.out.println(motPasseRecu);
		System.out.println(nomCookie);
		System.out.println(motPasseCookie);
		

	
		} else if (nomCookie==null && nomRecu!=null){
			System.out.println("Case2");
			Cookie c1 = new Cookie("nom", nomRecu);
			Cookie c2 = new Cookie("motdepasse", motPasseRecu);
			response.addCookie(c1);
			response.addCookie(c2);
			Cookie c[]=request.getCookies();  
			response.setContentType("text/html"); 
			out.println("<html>");
			out.println("<body>");
			out.println("<head>");
			out.println("<title> case2 </title>");
			out.println("</head>");
			out.println("<body bgcolor='white' >");
			out.println( nomRecu +" | "+ motPasseRecu +" | "+ nomCookie +" | "+ motPasseCookie ); 
			out.print(" <form action='InscriptionClient' method='GET' > ");
			out.println("Cookies Saved");
			out.println("<input type='submit' value='next'>"); 
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
			System.out.println(nomRecu);
			System.out.println(motPasseRecu);
			System.out.println(nomCookie);
			System.out.println(motPasseCookie);

			

			
			

		}
		
		else if (identique(nomRecu,nomCookie) && identique(motPasseRecu,motPasseCookie))
		{
			System.out.println("Case4");

			   RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/achat");
			    dispatcher.forward(request, response);}

		else {
			System.out.println("Case3");

			response.setContentType("text/html");
			out.println("<html>");
			out.println("<body>");
			out.println("<head>");
			out.println("<title> Connexion du client </title>");
			out.println("</head>");
			out.println("<body bgcolor='white' >");
			out.println( nomRecu +" | "+ motPasseRecu +" | "+ nomCookie +" | "+ motPasseCookie ); 
			out.println("<h3>" + "Bonjour, vous devez vous connecter " + "</h3>");
			out.print(" <form action='InscriptionClient' method='GET' > "); out.println("nom");
			out.println("<input type='text' size='20' name='nom' >"); out.println("<br>");
			out.println("mot de passe");
			out.println("<input type='password' size='20' name='motdepasse'> <br>"); 
			out.println("<input type='submit' value='seconnecter'>"); 
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
			
		
			
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	
	boolean identique (String recu, String cookie) {
		return ((recu != null) && (recu.length() >3) && (cookie != null) && (recu.equals(cookie) ));
		}

}
