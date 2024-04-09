package controleur;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.Authentification;
import java.io.IOException;

import dto.ClientDTO;

/**
 * Servlet implementation class controlServ
 */

public class AuthControleur extends HttpServlet {
  
    public void init() throws ServletException { 
  
        
  
    } 
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthControleur() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Do something with the parameters (e.g., print them)
        System.out.println("password " + password);
        System.out.println("Email: " + email);
        
        ClientDTO c = this.loginClient(email, password);
        if(c!=null)
        {

        	HttpSession session = request.getSession();
            session.setAttribute("client", c);
        	RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/CommandeControleur");
            disp.forward(request, response);
        }
        else
        {
        	request.setAttribute("errorMessage", "Mot de passe ou Email incorrect!");
        	RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/login.jsp");
            disp.forward(request, response);
        }
        
        
  
		
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	ClientDTO loginClient(String login,String password)
	{
		Authentification auth = new Authentification();
		return auth.loginClient(login, password);
	}

}
