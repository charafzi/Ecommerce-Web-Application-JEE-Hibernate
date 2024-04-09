package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bo.Client;
import bo.Produit;
import dao.ClientDAO;
import dao.HibernateUtil;
import dao.ProduitDAO;
import dto.ProduitDTO;

public class ProduitService implements ProduitServiceInterface {

	@Override
	public List<ProduitDTO> retreive() {
		List<ProduitDTO> produitsdto = new ArrayList<>();
		ProduitService ps = new ProduitService();
		
		List<Produit> produits = new ProduitDAO().retrieve();
		for(Produit p : produits)
		{
			produitsdto.add(ps.fromProduit(p));
		}
		return produitsdto;	
	}

	@Override
	public boolean create(ProduitDTO dtop) {
		return (new ProduitDAO().create(this.toProduit(dtop)));
	}

	@Override
	public boolean update(ProduitDTO dtop) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(ProduitDTO dtop) {
		return (new ProduitDAO().delete(this.toProduit(dtop)));
	}
	
	@Override
	public ProduitDTO getProductById(int id) {
		ProduitDAO dao=new ProduitDAO();
		Optional<Produit> produit=Optional.ofNullable(dao.getProductById(id));
		if (produit.isPresent()) return this.fromProduit(produit.get());
		return null;
	}
	
	public ProduitDTO fromProduit(Produit p)
	{
		ProduitDTO produitdto = new ProduitDTO();
		produitdto.setIdprod(p.getIdProd());
		produitdto.setCategorie(new CategorieService().fromCategorie(p.getCategorie()));
		produitdto.setDesignation(p.getDesignation());
		produitdto.setNom(p.getNom());
		produitdto.setPrix(p.getPrix());
		produitdto.setStock(p.getStock());
		produitdto.setImageurl(p.getImageurl());
		return produitdto;
	}
	
	public Produit toProduit(ProduitDTO produitdto)
	{
		Produit p = new Produit();
		p.setIdProd(produitdto.getIdprod());
		p.setNom(produitdto.getNom());
		p.setCategorie(new CategorieService().toCategorie(produitdto.getCategorie()));
		p.setDesignation(produitdto.getDesignation());
		p.setPrix(produitdto.getPrix());
		p.setStock(produitdto.getStock());
		p.setImageurl(produitdto.getImageurl());

		return p;
	}
	
	
	
	public static void main(String[] args) {
		List<ProduitDTO> produitsdto = new ArrayList<>();
		
		produitsdto = new ProduitService().retreive();
		
		for(ProduitDTO p:produitsdto)
		{
			System.out.println(p.getNom());
		}
		
		System.out.println("--->"+new ProduitService().getProductById(21).getDesignation());
	}

	@Override
	public List<ProduitDTO> retreive(int idcat) {
		List<ProduitDTO> produitsdto = new ArrayList<>();
		ProduitService ps = new ProduitService();
		
		List<Produit> produits = new ProduitDAO().retrieve(idcat);
		for(Produit p : produits)
		{
			produitsdto.add(ps.fromProduit(p));
		}
		return produitsdto;	
	}

	@Override
	public List<ProduitDTO> parMotCle(String motcle) {
		List<ProduitDTO> produitsdto = new ArrayList<>();
		ProduitService ps = new ProduitService();
		
		List<Produit> produits = new ProduitDAO().retrieve(motcle);
		for(Produit p : produits)
		{
			produitsdto.add(ps.fromProduit(p));
		}
		return produitsdto;	
	}

	@Override
	public boolean verifierQuantite(int id, int qte) {
		return (new ProduitDAO().verifierQuantite(id, qte));
	}

	

}
