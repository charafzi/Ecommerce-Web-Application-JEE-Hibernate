package service;

import java.util.List;

import dto.LigneAchatDTO;
import dto.ProduitDTO;

public interface ProduitServiceInterface {
	 List<ProduitDTO> retreive();
	 List<ProduitDTO> retreive(int idcat);
	 boolean create(ProduitDTO dtop);
	 boolean update(ProduitDTO dtop);
	 boolean delete(ProduitDTO dtop);
	 ProduitDTO getProductById(int id);
	 List<ProduitDTO> parMotCle(String motcle);
	 boolean verifierQuantite(int id, int qte);
	 

}
