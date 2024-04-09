package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import bo.Achat;
import bo.LigneAchat;
import dao.AchatDAO;
import dto.AchatDTO;
import dto.LigneAchatDTO;

public class AchatService {
	
	private static AchatDTO achat;
	
	public static AchatDTO createAchatDTO() {
		if (achat==null) achat=new AchatDTO();
		return achat ;
	}
	
	public static void initialiserAchat() {
		achat=null;
	}
	
	
	public static AchatDTO getAchatDTO() {
		
		return achat;
		
	}
	public static void addLigneAchat(LigneAchatDTO ligne) {
		
		
		achat.getLgAchats().add(ligne);
		float total=0;
		
		for(LigneAchatDTO l:achat.getLgAchats()) 
		{
			total+=l.getSousTotal();
		}
		achat.setTotal(total);
		
	}
	
	public boolean save(AchatDTO achatdto) {
		return (new AchatDAO().create(this.toAchat(achatdto)));
		
	}
	public 	Achat toAchat(AchatDTO achatdto) {
		Achat achat=new Achat();
		achat.setDate(LocalDate.now());
		
		achat.setClient(new ClientService().toClient(achatdto.getClient()));
		List<LigneAchat> lignes = new ArrayList<>();
		
		LigneAchatService las= new LigneAchatService();
		for(LigneAchatDTO ligne:achatdto.getLgAchats())
		{
			lignes.add(las.toLigneAchat(ligne));
		}
		
		achat.setLgAchats(lignes);
		achat.setTotal(achatdto.getTotal());
		return achat;
	}
	
	
	
	

}
