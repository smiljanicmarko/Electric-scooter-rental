package modul3.test.service;

import java.util.List;

import org.springframework.data.domain.Page;

import modul3.test.model.Trotinet;

public interface TrotinetService {

    Trotinet findOneById(Long id);
	
    List<Trotinet> findAll();

    Trotinet save(Trotinet t);

    Trotinet update(Trotinet t);

    Trotinet delete(Long id);

    Page<Trotinet> pretraga(Long adresaId, Integer baterijaOd, Integer baterijaDo, int pageNo);
    

    Trotinet napuni (Long id);
	
	
//    Page<Film> findAll(Integer pageNo);
//    Page<Film> find(String naziv, Long zanrId, Integer trajanjeOd, Integer trajanjeDo, int page);
//    List<Film> findByZanrId(Long zanrId);
//    List<Linija> nadjiPoId (List<Long>ids);
}
