package bo;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Produit implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProd;
	private String nom;
	private String designation;
	private double prix;
	private int stock;
	@Column(name = "IMAGEURL")
	private String imageurl;
	@ManyToOne
	@JoinColumn(name = "idcat")
	private Categorie categorie;	
	
	public Produit()
	{
		
	}

	
	
	public Produit(int idProd, String nom, String designation, double prix) {
		super();
		this.idProd = idProd;
		this.nom = nom;
		this.designation = designation;
		this.prix = prix;
	}



	public int getIdProd() {
		return idProd;
	}

	public void setIdProd(int idProd) {
		this.idProd = idProd;
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



	public int getStock() {
		return stock;
	}



	public void setStock(int stock) {
		this.stock = stock;
	}



	public Categorie getCategorie() {
		return categorie;
	}



	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}



	public String getImageurl() {
		return imageurl;
	}



	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	
	
	
	

}
