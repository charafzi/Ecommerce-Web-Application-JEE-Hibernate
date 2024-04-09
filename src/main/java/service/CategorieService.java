package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bo.Categorie;
import bo.Client;
import bo.Produit;
import dao.CategorieDAO;
import dao.ClientDAO;
import dao.ProduitDAO;
import dto.CategorieDTO;
import dto.ProduitDTO;

public class CategorieService implements CategorieServiceInterface{
	
	
	
	@Override
	public List<CategorieDTO> retreive() {
		ArrayList<CategorieDTO> catdto = new ArrayList<>();
		
		List<Categorie> categories  = new CategorieDAO().retrieve();
		
		for(Categorie c:categories)
		{
			CategorieDTO categoriedao = new CategorieDTO();
			categoriedao = this.fromCategorie(c);
			catdto.add(categoriedao);
		}
		
		return catdto;
	}

	@Override
	public CategorieDTO getCategorieById(int id) {
		
		CategorieDAO dao=new CategorieDAO();
		
		Optional<Categorie> cat=Optional.ofNullable(dao.getCategorieById(id));
		if (cat.isPresent()) return this.fromCategorie(cat.get());
		return null;
	}
	
	public List<ProduitDTO> getProducts(int id)
	{
		
		List<ProduitDTO> prods = new ProduitService().retreive(id);
		return prods;
	}
	
	CategorieDTO fromCategorie(Categorie c)
	{
		
		CategorieDTO catdto = new CategorieDTO();
		catdto.setIdcat(c.getIdcat());
		catdto.setNom(c.getNom());
		
		/*List<ProduitDTO> prods = new ArrayList<ProduitDTO>();
		ProduitService ps = new ProduitService();
		
		for(Produit p:c.getProduits())
		{
			prods.add(ps.fromProduit(p));
		}
		catdto.setProduits(prods);*/
		return catdto;
	}
	
	Categorie toCategorie(CategorieDTO catdto)
	{
		
		Categorie c =new Categorie();
		c.setIdcat(catdto.getIdcat());
		c.setNom(catdto.getNom());
		/*
		List<Produit> prods = new ArrayList<Produit>();
		ProduitService ps = new ProduitService();
		
		for(ProduitDTO p:catdto.getProduits())
		{
			prods.add(ps.toProduit(p));
		}
		c.setProduits(prods);*/
		return c;
	}

	public static void main(String[] args) {
		
		System.out.println();
		CategorieDTO cat =  new CategorieService().getCategorieById(8);
		
		//List<ProduitDTO> prods = new CategorieService().getProducts(8);
		//cat.setProduits(prods);
		
		for(ProduitDTO p:cat.getProduits() )

		{
			System.out.println(p.getDesignation());
		}
		
		
	}

	@Override
	public boolean save(CategorieDTO c) {
		return new CategorieDAO().create(this.toCategorie(c));
	}

	@Override
	public boolean delete(CategorieDTO c) {
		// TODO Auto-generated method stub
		return new CategorieDAO().delete(this.toCategorie(c));
	}
	
	

}
