package modul3.test.service;

import java.util.List;

import modul3.test.model.Rezervacija;
import modul3.test.web.dto.VracanjeDTO;

public interface RezervacijaService {
	
	
  Rezervacija findOneById(Long id);
	
  List<Rezervacija> findAll();

  Rezervacija save(Rezervacija rez);  

  Rezervacija delete(Long id);

  Rezervacija rezervisi (Rezervacija rez);
  
  boolean vratiTrotinet (VracanjeDTO dto);
  
  List<Rezervacija> findByTrotinetId (Long id);
  

}
