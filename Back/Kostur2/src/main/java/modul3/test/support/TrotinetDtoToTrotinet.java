package modul3.test.support;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import modul3.test.model.Adresa;
import modul3.test.model.Trotinet;
import modul3.test.service.AdresaService;
import modul3.test.service.TrotinetService;
import modul3.test.web.dto.TrotinetDTO;

@Component
public class TrotinetDtoToTrotinet implements Converter<TrotinetDTO, Trotinet> {

    @Autowired
    private TrotinetService trotinetService;

    @Autowired
    private AdresaService adresaService;

    @Override
    public Trotinet convert(TrotinetDTO dto) {

    	Trotinet e;

        if(dto.getId() == null) {
            e = new Trotinet();
        }else {
            e = trotinetService.findOneById(dto.getId());
        }

        if(e != null) {
          
           e.setBaterija(dto.getBaterija());
           e.setBrzina(dto.getBrzina());
           e.setId(dto.getId());
           e.setIznajmljen(dto.getIznajmljen());
           e.setSifra(dto.getSifra());
        	
           Adresa adresa = adresaService.findOneById(dto.getAdresaId());
           if (adresa!=null) {
        	   e.setAdresa(adresa);
           }
           
           
        }

        return e;
    }
}