package service;


import dto.ClientDTO;

public interface AuthentificationInterface {
	ClientDTO loginClient(String login, String password);
	int loginAdmin(String login, String password);

}
