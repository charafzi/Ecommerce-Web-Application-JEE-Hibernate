package controleur;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.AchatService;
import service.Authentification;
import service.CategorieService;
import service.LigneAchatService;
import service.ProduitService;
import service.VerificationCarte;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import dto.AchatDTO;
import dto.CategorieDTO;
import dto.ClientDTO;
import dto.LigneAchatDTO;
import dto.ProduitDTO;

/**
 * Servlet implementation class CommandeControleur
 */
public class CommandeControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<ProduitDTO> produits;
	HashSet<CategorieDTO> categories;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommandeControleur() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		produits = new ArrayList<>(new ProduitService().retreive());
		categories = new HashSet<>(new CategorieService().retreive());
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		request.setAttribute("produits", produits);
		request.setAttribute("categories", categories);
		RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/produits.jsp");
		disp.forward(request, response);
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String action = request.getParameter("action");
		String supprimer = request.getParameter("supprimerLigne");
		String inrementer = request.getParameter("incrementer");
		String decrementer = request.getParameter("decrementer");
		HttpSession session = request.getSession(false);
		
		if(session == null)
		{
			doGet(request, response);
		}
		
		
		if(action != null)
		{
			System.out.println("---"+action);
			if(action.equals("login"))
			{	
				String password = request.getParameter("password");
		        String email = request.getParameter("email");

		        System.out.println("password " + password);
		        System.out.println("Email: " + email);
		        
		        ClientDTO c = this.loginClient(email, password);
		        if(c!=null)
		        {
		        	
		        	session = request.getSession(true);
		        	session.setAttribute("client", c);
		        	doGet(request, response);
		        }
		        else
		        {
		        	request.setAttribute("error", "Mot de passe ou Email incorrect !");
		        	RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/login.jsp");
		            disp.forward(request, response);
		        }
			}
			else if(action.equals("logout"))
			{
				session.setAttribute("client", null);
				doGet(request, response);
				
			}
			else if(action.equals("ajouter"))
			{	
				LigneAchatService las = new LigneAchatService();
				new  AchatService();
				AchatDTO achat = AchatService.createAchatDTO();
				
				
				int id = Integer.parseInt(request.getParameter("idprod"));
				
				
				
				boolean trouve = false;
				for(LigneAchatDTO lg:achat.getLgAchats())
				{
					if(lg.getProduit().getIdprod() == id)
					{
						trouve = true;
					}
				}
				if(trouve == false)
				{
					ProduitDTO p = null;
					for(ProduitDTO prod:produits)
					{
						if(prod.getIdprod() == id)
						{
							p = prod;
							break;
						}
					}
					LigneAchatDTO lignedto1 = las.getNouvelleLigne(1, p);
					
					if(lignedto1 == null)
					{
						request.setAttribute("error", "Quantité demandée est supérieur à la quantité du stock !");
					}
					else
					{
						AchatService.addLigneAchat(lignedto1);
						request.setAttribute("success", "Produit bien ajouté au panier !");
					}	
					
				}
				
				
				System.out.println("les ligne achats");
				for(LigneAchatDTO lg:achat.getLgAchats())
				{
					System.out.println(lg.getQuantite());
					System.out.println(lg.getSousTotal());
				}
				
				System.out.println("-->"+achat.getTotal());
				session.setAttribute("achat", achat);
				doGet(request, response);
			}
			else if(action.equals("panier"))
			{

				RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/panier.jsp");
				disp.forward(request, response);
				
			}
			else if(action.equals("produits"))
			{
				if(session == null)
				{
		        	RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/login.jsp");
		            disp.forward(request, response);
				}
				doGet(request, response);
			}
			else if(action.equals("vider"))
			{
				
				new AchatService();
				AchatService.initialiserAchat();
				session.setAttribute("achat", AchatService.getAchatDTO());
				
				
				RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/panier.jsp");
				disp.forward(request, response);
			}
			else if(action.equals("payer"))
			{
				ClientDTO client = (ClientDTO) session.getAttribute("client");

				// Vérifiez si l'objet client est présent dans la session
				if (client != null && client.getIdclient() != 0) 
				{
					AchatDTO achat = (AchatDTO) session.getAttribute("achat");
					String cardNumber = request.getParameter("cardNumber");
					String cardExpiration = request.getParameter("cardExpiration");
					String cardType = request.getParameter("cardType");
					System.out.println(cardNumber+" "+cardExpiration+" "+cardType);
					if(verifierCarte(cardNumber, cardExpiration, cardType) == 0)
					{
						request.setAttribute("error", "Informations de la carte sont erronés !");
			        	RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/panier.jsp");
			            disp.forward(request, response);
					}
					else
					{
						AchatService as = new AchatService();
						ProduitService ps = new ProduitService();
						boolean valide = true;
						//verifier la quantité la disponibilité de chaque quantité
						for(LigneAchatDTO lg:as.getAchatDTO().getLgAchats())
						{
							if(ps.verifierQuantite(lg.getProduit().getIdprod(), lg.getQuantite())==false)
							{
								valide = false;
								request.setAttribute("error", "Quantité demandé du produit '"+lg.getProduit().getNom()+"' est indisponible pour le moment.");
					        	RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/panier.jsp");
					            disp.forward(request, response);
					            
							}
						}
						
						if(valide)
						{
							as.getAchatDTO().setClient(client);
							if(as.save(achat))
							{
								//mise à jour l=de la quentité du stock
								for(LigneAchatDTO lg:as.getAchatDTO().getLgAchats())
								{
									lg.getProduit().setStock(lg.getProduit().getStock() - lg.getQuantite());
								}
								AchatService.initialiserAchat();
								session.setAttribute("achat", AchatService.getAchatDTO());	
								request.setAttribute("success", "Achat bien enregistré, Merci pour votre achat !");
							}
							else
							{
								request.setAttribute("error", "Erreur lor de l'enregistrement de l'achat !");
							}
							System.out.println("Achat bien enregistré !");
							
							
							doGet(request, response);
						}
						
						
						
					}  
				} 
				else 
				{
				    RequestDispatcher disp = request.getServletContext().getRequestDispatcher("/login.jsp");
				    disp.forward(request, response);
				}
				
				
			}
		}
		
		if(supprimer != null)
		{
			AchatDTO achat = AchatService.getAchatDTO();
			int idprod = Integer.parseInt(supprimer);
			
			for(LigneAchatDTO l:achat.getLgAchats())
			{
				if(l.getProduit().getIdprod() == idprod)
				{
					achat.getLgAchats().remove(l);
					break;
				}
				
			}
			
			RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/panier.jsp");
            disp.forward(request, response);
		}
		
		if(decrementer != null)
		{
			AchatDTO achat = AchatService.getAchatDTO();
			int idprod = Integer.parseInt(decrementer);
			for(LigneAchatDTO l:achat.getLgAchats())
			{
				if(l.getProduit().getIdprod() == idprod)
				{
					if(l.getQuantite()>1)
					{
						l.setQuantite(l.getQuantite()-1);
						l.setSousTotal(l.getProduit().getPrix()*l.getQuantite());
						break;
					}
				}
				
			}
			RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/panier.jsp");
            disp.forward(request, response);
		}
		if(inrementer != null)
		{
			AchatDTO achat = AchatService.getAchatDTO();
			int idprod = Integer.parseInt(inrementer);
			for(LigneAchatDTO l:achat.getLgAchats())
			{
				if(l.getProduit().getIdprod() == idprod)
				{
					if(l.getQuantite()<l.getProduit().getStock())
					{
						l.setQuantite(l.getQuantite()+1);
						l.setSousTotal(l.getProduit().getPrix()*l.getQuantite());
						break;
					}
				}
				
			}
			RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/panier.jsp");
            disp.forward(request, response);
		}
		
		
	}
	
	ClientDTO loginClient(String login,String password)
	{
		Authentification auth = new Authentification();
		return auth.loginClient(login, password);
	}
	
	int verifierCarte(String cardnumber, String dateexp,String cardtype)
	{
		return (new VerificationCarte().verifierCarte(cardnumber, dateexp, cardtype));
	}

}
