package dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AchatDTO {
	private LocalDate date;
	private double total;
	private List<LigneAchatDTO> lgAchats = new ArrayList<>();
;
	private ClientDTO client;
	
	public AchatDTO()
	{
		
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getTotal() {
		double total = 0;
		
		for(LigneAchatDTO l:this.lgAchats)
		{
			total+= l.getSousTotal();
		}
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<LigneAchatDTO> getLgAchats() {
		return lgAchats;
	}

	public void setLgAchats(List<LigneAchatDTO> lgAchats) {
		this.lgAchats = lgAchats;
	}

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}
	
	
}


