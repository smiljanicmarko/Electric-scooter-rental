package modul3.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modul3.test.model.Adresa;

@Repository
public interface AdresaRepository extends JpaRepository<Adresa, Long> {
	
	Adresa findOneById(Long id);

   

}
