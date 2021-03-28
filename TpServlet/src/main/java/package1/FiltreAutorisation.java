package package1;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FiltreAutorisation
 */
@WebFilter("/")
public class FiltreAutorisation implements Filter {
	private FilterConfig filterConfig = null;

	
    public FiltreAutorisation() {
        // TODO Auto-generated constructor stub
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Filter");
		String nom = null;
		HttpServletRequest hrequest = (HttpServletRequest) request; 
		HttpServletResponse hresponse = (HttpServletResponse) response;
		Cookie[] cookies = hrequest.getCookies();

//		if (cookies != null) {
//		 for (Cookie cookie : cookies) {
//		   if (cookie.getName().equals("nomCookie")) {
//		    nom=cookie.getValue();
//		    }
//		  }
//		}
//		
//	if(nom == "NULL"){
//		
//		RequestDispatcher dispatcherr = filterConfig.getServletContext().getRequestDispatcher("/");
//		    dispatcherr.forward(request, response);
//			
//		}else {
//			chain.doFilter(request, response);
//		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
