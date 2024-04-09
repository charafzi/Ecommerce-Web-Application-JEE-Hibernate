package bo;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class LigneAchat implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idligne;
	@ManyToOne
	@JoinColumn(name="idProd")
	private Produit produit;
	private int quantite;
	private double sousTotal;
	@ManyToOne
	@JoinColumn(name="idachat")
	private Achat achat;
	
	public LigneAchat(){}
	
	

	public LigneAchat(int idligne, Produit produit, int quantite, double sousTotal, Achat achat) {
		super();
		this.idligne = idligne;
		this.produit = produit;
		this.quantite = quantite;
		this.sousTotal = sousTotal;
		this.achat = achat;
	}



	public int getIdligne() {
		return idligne;
	}

	public void setIdligne(int idligne) {
		this.idligne = idligne;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getSousTotal() {
		return sousTotal;
	}

	public void setSousTotal(double sousTotal) {
		this.sousTotal = sousTotal;
	}

	public Achat getAchat() {
		return achat;
	}

	public void setAchat(Achat achat) {
		this.achat = achat;
	}
	
	
	
	
	

}
