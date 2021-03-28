package package1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class CommanderUnDisque
 */
@WebServlet("/CommanderUnDisque")
public class CommanderUnDisque extends HttpServlet {
    List<ListDisques> selectedDisquesList=new ArrayList<ListDisques>();
	private static final long serialVersionUID = 1L;
       
    public List<ListDisques> getSelectedDisquesList() {
		return selectedDisquesList;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public CommanderUnDisque() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				HttpSession session=request.getSession();
				String nom = null;
				int nbreProduit = 0;
				Cookie[] cookies = request.getCookies(); 
				nom = Identification.chercheNom(cookies);
				response.setContentType("text/html"); 
				PrintWriter out = response.getWriter(); out.println("<html>");
				out.println("<body>");
				out.println("<head>");
				out.println("<title> votre commande </title>"); out.println("</head>");
				out.println("<body bgcolor=\"white\">");
				out.println("<h3>" + "Bonjour "+ nom + " voici votre commande" + "</h3>");
			    ListDisques disque = new ListDisques(request.getParameter("code"),request.getParameter("prix"));
			    selectedDisquesList.add(disque);
			    int i=0;
			    while (i < selectedDisquesList.size()) {
		            out.println(selectedDisquesList.get(i).getCode() + " " + selectedDisquesList.get(i).getPrix() + "<br>");
	            i++;
		        }
			    session.setAttribute("code",request.getParameter("code"));
			    session.setAttribute("prix",request.getParameter("prix"));
				out.println("<A HREF=achat> Vous pouvez commandez un autre disque </A><br> "); 
				out.println("<A HREF=enregistre> Vous pouvez enregistrer votre commande </A><br> ");
				out.println("</body>");
				out.println("</html>");
		
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
