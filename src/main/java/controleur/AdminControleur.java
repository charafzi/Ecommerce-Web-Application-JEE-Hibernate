package controleur;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.Authentification;
import service.CategorieService;
import service.ProduitService;

import java.io.IOException;
import java.util.ArrayList;

import org.hibernate.mapping.List;

import dto.CategorieDTO;
import dto.ClientDTO;
import dto.ProduitDTO;

/**
 * Servlet implementation class AdminControleur
 */
public class AdminControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminControleur() {
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
		String action = request.getParameter("action");
		
		
		if(action != null)
		{
			System.out.println("---"+action);
			if(action.equals("produit"))
			{	
				String pname = request.getParameter("pname");
		        String pdesc = request.getParameter("pdesc");
		        double pprice = Double.parseDouble(request.getParameter("pprice".trim()));
		        int pqte = Integer.parseInt(request.getParameter("pqte").trim());
		        int catsid = Integer.parseInt(request.getParameter("catsid").trim());
		        String pic = request.getParameter("ppic");

		        ProduitDTO p = new ProduitDTO();
		        p.setNom(pname);
		        p.setDesignation(pdesc);
		        p.setPrix(pprice);
		        p.setStock(pqte);
		        p.setCategorie(new CategorieService().getCategorieById(catsid));
		        p.setImageurl("resources/images/"+pic);
		        
		        
		        if(new ProduitService().create(p))
		        {
		        	request.setAttribute("success", "Produit bien enregistré !");
		        }
		        else
		        {
		        	request.setAttribute("error", "Erreur lors d'enregistrement !");
		        }
		        
	        	RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/admin.jsp");
	            disp.forward(request, response);
			}
			else if(action.equals("categorie"))
			{
				String ctitle = request.getParameter("ctitle");
				
				CategorieDTO c = new CategorieDTO();
				c.setNom(ctitle);
				
				if(new CategorieService().save(c))
		        {
		        	request.setAttribute("success", "Catégorie bien enregistrée !");
		        }
		        else
		        {
		        	request.setAttribute("error", "Erreur lors d'enregistrement !");
		        }
		        
	        	RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/admin.jsp");
	            disp.forward(request, response);	
			}
			else if(action.equals("suppCat"))
			{
				CategorieService cs = new CategorieService();
				int idcat = Integer.parseInt(request.getParameter("categorie"));
				if(cs.getProducts(idcat).size() != 0)
					request.setAttribute("error", "Impossible de supprimer la catégorie !");
				else
				{
					ArrayList<CategorieDTO> categories = new ArrayList<>(cs.retreive());
					CategorieDTO c = cs.getCategorieById(idcat);
					if(cs.delete(c))
						request.setAttribute("success", "Catégorie bien supprimé !");
					else
						request.setAttribute("error", "Erreur lors de la suppression !");
				}
				RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/admin.jsp");
	            disp.forward(request, response);
				
			}
			else if(action.equals("suppProd"))
			{
				ProduitService ps = new ProduitService();
				int id = Integer.parseInt(request.getParameter("produit"));
				
				ProduitDTO p = ps.getProductById(id);
				System.out.println(p.getIdprod()+"  "+p.getNom());
				
				if(ps.delete(p))
					request.setAttribute("success", "Produit bien supprimé !");
				else
					request.setAttribute("error", "Erreur lors de la suppression !");
				RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/admin.jsp");
	            disp.forward(request, response);
				
				
			}
			else if(action.equals("login"))
			{
				String password = request.getParameter("password");
		        String email = request.getParameter("email");

		        System.out.println("password " + password);
		        System.out.println("Email: " + email);
		        
		        int existe = this.loginAdmin(email, password);
		        if(existe == 1)
		        {
		        	HttpSession session = request.getSession(true);
		        	session.setAttribute("admin", email);
		        	RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/admin.jsp");
		            disp.forward(request, response);
		        }
		        else
		        {
		        	request.setAttribute("errorMessage", "Mot de passe ou Email incorrect!");
		        	RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/loginAdmin.jsp");
		            disp.forward(request, response);
		        }
				
			}
		}
	}
	int loginAdmin(String login,String password)
	{
		Authentification auth = new Authentification();
		return auth.loginAdmin(login, password);
	}

}
