package dto;

import bo.Categorie;

public class ProduitDTO {
	private int idprod;
	private String nom;
	private String designation;
	private double prix;
	private int stock;
	private String imageurl;
	private CategorieDTO categorie;
	
	public ProduitDTO() {}
	
	
	
	

	public ProduitDTO(int idprod, String nom, String designation, double prix, CategorieDTO categorie, int stock) {
		super();
		this.idprod = idprod;
		this.nom = nom;
		this.designation = designation;
		this.prix = prix;
		this.categorie = categorie;
		this.stock = stock;
	}





	public int getIdprod() {
		return idprod;
	}




	public void setIdprod(int idprod) {
		this.idprod = idprod;
	}




	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public CategorieDTO getCategorie() {
		return categorie;
	}

	public void setCategorie(CategorieDTO categorie) {
		this.categorie = categorie;
	}



	public int getStock() {
		return stock;
	}



	public void setStock(int stock) {
		this.stock = stock;
	}





	public String getImageurl() {
		return imageurl;
	}





	public void setImageurl(String imageUrl) {
		this.imageurl = imageUrl;
	}
	
	
	
	
}
