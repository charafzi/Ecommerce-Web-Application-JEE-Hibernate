package controleur;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ClientService;

import java.io.IOException;

import dto.ClientDTO;

/**
 * Servlet implementation class SignUpServlet
 */
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if(action != null)
		{
			if(action.equals("login"))
			{
				RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/login.jsp");
	            disp.forward(request, response);
			}
			
			ClientDTO newcli = new ClientDTO();
			String password = request.getParameter("password");
	        String email = request.getParameter("email");
	        String prenom = request.getParameter("prenom");
	        String nom = request.getParameter("nom");
	        
			newcli.setLogin(email);
			newcli.setPassword(password);
			newcli.setNom(nom);
			newcli.setPrenom(prenom);
			
			if( new ClientService().create(newcli))
			{
				request.setAttribute("message", "Vous pouvez se connecter !");
	        	
			}
			else
			{
				request.setAttribute("message", "Veuillez réssayer !");
	        	
			}
			
			RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/signup.jsp");
            disp.forward(request, response);
		}
	}

}
