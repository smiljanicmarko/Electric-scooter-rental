package modul3.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modul3.test.model.Adresa;
import modul3.test.repository.AdresaRepository;
import modul3.test.service.AdresaService;
@Service
public class JpaAdresaService implements AdresaService {

	@Autowired
	private AdresaRepository r;

	@Override
	public Adresa findOneById(Long id) {
		// TODO Auto-generated method stub
		return r.findOneById(id);
	}

	@Override
	public List<Adresa> findAll() {
		// TODO Auto-generated method stub
		return r.findAll();
	}

	@Override
	public Adresa save(Adresa a) {
		// TODO Auto-generated method stub
		return r.save(a);
	}
	
}
