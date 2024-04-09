package service;

import bo.LigneAchat;
import dto.LigneAchatDTO;
import dto.ProduitDTO;

public class LigneAchatService {
	
	
	LigneAchat toLigneAchat(LigneAchatDTO lg)
	{
		LigneAchat ligne = new LigneAchat();
		ligne.setProduit(new ProduitService().toProduit(lg.getProduit()));
		ligne.setQuantite(lg.getQuantite());
		ligne.setSousTotal(lg.getSousTotal());
		return ligne;
	}
	
	public LigneAchatDTO getNouvelleLigne(int q,ProduitDTO p) {
		LigneAchatDTO ligne = null;
		if(q<=p.getStock()) 
		{
			ligne=new LigneAchatDTO();
			ligne.setQuantite(q);
			ligne.setSousTotal(q*p.getPrix());
			ligne.setProduit(p);
		
		}
		return ligne;	
	}
	


}
