package bo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Achat implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int idachat;
	@Column(name = "dateAchat")
	private LocalDate date;
	private double total;
	@OneToMany(mappedBy = "achat",fetch = FetchType.LAZY)
	private List<LigneAchat> lgAchats;
	@ManyToOne
	@JoinColumn(name = "idclient")
	private Client client;

	
	
	public Achat() 
	{

	}


	public Achat(int idachat, LocalDate date, double total, List<LigneAchat> lgAchats) {
		super();
		this.idachat = idachat;
		this.date = date;
		this.total = total;
		this.lgAchats = lgAchats;
	}





	public int getIdachat() {
		return idachat;
	}



	public void setIdachat(int idachat) {
		this.idachat = idachat;
	}



	public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}



	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}



	public List<LigneAchat> getLgAchats() {
		return lgAchats;
	}



	public void setLgAchats(List<LigneAchat> lgAchats) {
		this.lgAchats = lgAchats;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}
		
	

}
