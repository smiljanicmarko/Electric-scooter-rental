package modul3.test.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import modul3.test.model.Trotinet;
import modul3.test.repository.TrotinetRepository;
import modul3.test.service.TrotinetService;

@Service
public class JpaTrotinetService implements TrotinetService {

	@Autowired
	private TrotinetRepository r;

	@Override
	public Trotinet findOneById(Long id) {
		// TODO Auto-generated method stub
		return r.findOneById(id);
	}

	@Override
	public List<Trotinet> findAll() {
		// TODO Auto-generated method stub
		return r.findAll();
	}

	@Override
	public Trotinet save(Trotinet t) {
		// TODO Auto-generated method stub
		return r.save(t);
	}

	@Override
	public Trotinet update(Trotinet t) {
		// TODO Auto-generated method stub
		return r.save(t);
	}

	@Override
	public Trotinet delete(Long id) {
		Trotinet trotinet = r.findOneById(id);
		if (trotinet != null) {
			r.deleteById(id);
		}
		
		return trotinet;
		
	}

	@Override
	public Page<Trotinet> pretraga(Long adresaId, Integer baterijaOd, Integer baterijaDo, int pageNo) {
		// TODO Auto-generated method stub
		return r.pretraga(adresaId, baterijaOd, baterijaDo, PageRequest.of(pageNo, 3));
	}

	@Override
	public Trotinet napuni(Long id) {
		Trotinet trotinet = r.findOneById(id);
		if (trotinet == null) {
			return null;
		}
		
		trotinet.setBaterija(100);
		r.save(trotinet);
		
		return trotinet;
	}
   
}
