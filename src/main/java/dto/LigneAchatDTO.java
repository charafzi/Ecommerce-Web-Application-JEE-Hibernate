package dto;


public class LigneAchatDTO {
	private ProduitDTO produit;
	private int quantite;
	private double sousTotal;
	private AchatDTO achat;
	
	public LigneAchatDTO()
	{
		
	}
	
	

	public LigneAchatDTO(ProduitDTO produit, int quantite, double sousTotal, AchatDTO achat) {
		super();
		this.produit = produit;
		this.quantite = quantite;
		this.sousTotal = sousTotal;
		this.achat = achat;
	}



	public ProduitDTO getProduit() {
		return produit;
	}

	public void setProduit(ProduitDTO produit) {
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

	public AchatDTO getAchat() {
		return achat;
	}

	public void setAchat(AchatDTO achat) {
		this.achat = achat;
	}
	
	

}
