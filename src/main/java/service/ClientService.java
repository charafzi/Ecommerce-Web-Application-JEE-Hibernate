package service;

import java.util.List;
import java.util.Optional;

import bo.Client;
import dao.ClientDAO;
import dto.ClientDTO;

public class ClientService implements ClientServiceInterface {

	@Override
	public List<ClientDTO> retreive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(ClientDTO clientdto) {
		ClientDAO dao=new ClientDAO();
		return dao.create(this.toClient(clientdto));
		
	}

	@Override
	public boolean update(ClientDTO clientdto, int id) {
		ClientDAO dao=new ClientDAO();

		Optional<Client> client=Optional.ofNullable(dao.findById(id));
		
		if (client.isPresent())
		 {
			Client C=this.toClient(clientdto);
			return dao.update(C);
		}
		return false;
		
	}

	@Override
	public boolean delete(int id) {
		ClientDAO dao=new ClientDAO();
		return dao.delete(dao.findById(id));
	
	}


	public Client toClient(ClientDTO clientdto) {
		Client client=new Client();
		client.setIdclient(clientdto.getIdclient());
		client.setNom(clientdto.getNom());
		client.setPrenom(clientdto.getPrenom());
		client.setLogin(clientdto.getLogin());
		client.setPassword(clientdto.getPassword());
		return client;
	}
	
	public ClientDTO fromClient(Client client) {
		ClientDTO clientdto=new ClientDTO();
		clientdto.setIdclient(client.getIdclient());
		clientdto.setNom(client.getNom());
		clientdto.setPrenom(client.getPrenom());
		clientdto.setLogin(client.getLogin());
		return clientdto;	
		
	}

	@Override
	public ClientDTO getClientDTO(int id) {
		ClientDTO c = null;
		ClientDAO dao = new ClientDAO();
		
		Optional<Client> client=Optional.ofNullable(dao.findById(id));
		if (client.isPresent())
			c = this.fromClient(client.get());
		return c;
	}


}
