package modul3.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modul3.test.model.Rezervacija;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {
	
	Rezervacija findOneById(Long id);
	
	List<Rezervacija>findAll ();
	
	List<Rezervacija> findByTrotinetId (Long id);

}
