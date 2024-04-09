package service;

import java.util.List;

import dto.CategorieDTO;

public interface CategorieServiceInterface {
	 List<CategorieDTO> retreive();
	 CategorieDTO getCategorieById(int id);
	 boolean save(CategorieDTO c);
	 boolean delete(CategorieDTO c);

}
