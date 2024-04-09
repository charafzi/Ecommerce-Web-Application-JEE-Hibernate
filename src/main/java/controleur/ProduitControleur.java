package controleur;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CategorieService;
import service.ProduitService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import dto.CategorieDTO;
import dto.ProduitDTO;

/**
 * Servlet implementation class ChercherControleur
 */
public class ProduitControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HashSet<CategorieDTO> categories;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProduitControleur() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		categories = new HashSet<>(new CategorieService().retreive());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String motcle = request.getParameter("motcle");
		
		System.out.println("mot cle ====== "+motcle);
		if(motcle.equals(""))
		{
			
		}
		else
		{
			ArrayList<ProduitDTO> produits;
			produits = new ArrayList<>(new ProduitService().parMotCle(motcle.toUpperCase()));
			
			RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/produits.jsp");
			request.setAttribute("produits", produits);
			request.setAttribute("categories", categories);
			disp.forward(request, response);
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String categorie = request.getParameter("categorie");
		
		if(categorie != null)
		{
			System.out.println("---"+categorie);
			ArrayList<ProduitDTO> produits;
			produits = new ArrayList<>(new ProduitService().retreive(Integer.parseInt(categorie)));
			
			RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/produits.jsp");
			request.setAttribute("produits", produits);
			request.setAttribute("categories", categories);
			disp.forward(request, response);
		}
	}

}
