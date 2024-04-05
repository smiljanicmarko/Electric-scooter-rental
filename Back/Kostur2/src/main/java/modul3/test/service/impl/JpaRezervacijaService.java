package modul3.test.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modul3.test.model.Adresa;
import modul3.test.model.Rezervacija;
import modul3.test.model.Trotinet;
import modul3.test.repository.AdresaRepository;
import modul3.test.repository.RezervacijaRepository;
import modul3.test.repository.TrotinetRepository;
import modul3.test.service.RezervacijaService;
import modul3.test.web.dto.VracanjeDTO;
@Service
public class JpaRezervacijaService implements RezervacijaService{
	
	@Autowired
	private RezervacijaRepository r;

	@Autowired 
	private TrotinetRepository trotRepository;
	@Autowired
	private AdresaRepository adresaRepository;
	
	@Override
	public Rezervacija findOneById(Long id) {
		// TODO Auto-generated method stub
		return r.findOneById(id);
	}

	@Override
	public List<Rezervacija> findAll() {
		// TODO Auto-generated method stub
		return r.findAll();
	}

	@Override
	public Rezervacija save(Rezervacija rez) {
		// TODO Auto-generated method stub
		return r.save(rez);
	}

	@Override
	public Rezervacija delete(Long id) {
		Rezervacija rez = r.findOneById(id);
		if (rez!= null) {
			r.deleteById(id);
		}
		return rez;
	}

	@Override
	public Rezervacija rezervisi(Rezervacija rez) {
		
		Trotinet trotinet = rez.getTrotinet();
		String email = rez.getEmail();
		if (trotinet == null || email.isBlank()) {
			return null;
		}
		trotinet.setIznajmljen(true);
		trotRepository.save(trotinet);
		
		Rezervacija nova = r.save(rez);
		
		return nova;
		
		
		
		
	}

	@Override
	public boolean vratiTrotinet(VracanjeDTO dto) {
		
		Trotinet trotinet = trotRepository.findOneById(dto.getTrotinetId());
		
		Adresa adresa= adresaRepository.findOneById(dto.getAdresaId());
		
		String email = dto.getEmail();
		
		Integer baterija = dto.getBaterija();
		
		List<Rezervacija> rezervacije = r.findByTrotinetId(dto.getTrotinetId());
		Rezervacija rez = null;
		
		for (Rezervacija rezervacija : rezervacije) {
			if (rezervacija.getVremeVracanja()==null) {
				rez = rezervacija;
			}
		}
		
		if (trotinet == null || adresa == null || email == null || baterija == null || rez == null) {
			return false;
		}
		
		if (!email.equals(rez.getEmail())) {
			throw new IllegalArgumentException("Email se ne poklapa sa emailom osobe koja je iznajmila trotinet!");
		}
		
		
		trotinet.setBaterija(baterija);
		trotinet.setIznajmljen(false);
		trotinet.setAdresa(adresa);
		trotRepository.save(trotinet);
		
		
		rez.setVremeVracanja(LocalDateTime.now());		
		r.save(rez);
		
		
		
		
		
		return true;
	}

	@Override
	public List<Rezervacija> findByTrotinetId(Long id) {
		// TODO Auto-generated method stub
		return r.findByTrotinetId(id);
	}

	
	
	
}
