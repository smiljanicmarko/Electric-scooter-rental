package modul3.test.support;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import modul3.test.model.Rezervacija;
import modul3.test.model.Trotinet;
import modul3.test.service.AdresaService;
import modul3.test.service.RezervacijaService;
import modul3.test.service.TrotinetService;
import modul3.test.web.dto.RezervacijaDTO;

@Component
public class RezervacijaDtoToRezervacija implements Converter<RezervacijaDTO, Rezervacija> {

    @Autowired
    private TrotinetService trotinetService;

    @Autowired
    private RezervacijaService rezService;
    @Autowired
    private AdresaService adresaService;

    @Override
    public Rezervacija convert(RezervacijaDTO dto) {

    	Rezervacija e;

        if(dto.getId() == null) {
            e = new Rezervacija();
        }else {
            e = rezService.findOneById(dto.getId());
        }

        if(e != null) {
          
         e.setEmail(dto.getEmail());
         e.setId(dto.getId());
         e.setVremeIznajmljivanja(dto.getVremeIznajmljivanja());
         e.setVremeVracanja(dto.getVremeVracanja());
         
         Trotinet trotinet = trotinetService.findOneById(dto.getTrotinetId());
         if (trotinet !=null) {
        	 e.setTrotinet(trotinet);
         }
           
           
        }

        return e;
    }
}