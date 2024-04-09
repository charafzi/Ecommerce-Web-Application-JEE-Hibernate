package dto;

import java.util.ArrayList;
import java.util.List;

import bo.Produit;
import service.CategorieService;
import service.ProduitService;


public class CategorieDTO {
	private int idcat;
	private String nom;
	List<ProduitDTO> produits;
	
	public CategorieDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdcat() {
		return idcat;
	}
	public void setIdcat(int idcat) {
		this.idcat = idcat;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<ProduitDTO> getProduits() {
		if(this.produits==null)
		{
			this.produits = new ProduitService().retreive(this.idcat);
		}
		return this.produits;
	}
	public void setProduits(List<ProduitDTO> prods) {
		this.produits = prods;
	}
	
	

}
