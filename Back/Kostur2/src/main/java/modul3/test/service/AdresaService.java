package modul3.test.service;

import java.util.List;

import modul3.test.model.Adresa;

public interface AdresaService {
	
	
  Adresa findOneById(Long id);
	
  List<Adresa> findAll();

  Adresa save(Adresa a);


}
