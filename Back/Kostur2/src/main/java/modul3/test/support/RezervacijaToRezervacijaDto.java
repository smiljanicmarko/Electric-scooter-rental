package modul3.test.support;


import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import modul3.test.model.Rezervacija;
import modul3.test.web.dto.RezervacijaDTO;

@Component
public class RezervacijaToRezervacijaDto implements Converter<Rezervacija, RezervacijaDTO> {

    @Override
    public RezervacijaDTO convert(Rezervacija e) {
    	RezervacijaDTO dto = new RezervacijaDTO();
       
    	dto.setEmail(e.getEmail());
    	dto.setId(e.getId());
    	dto.setSifra(e.getTrotinet().getSifra());
    	dto.setTrotinetId(e.getTrotinet().getId());
    	dto.setVremeIznajmljivanja(e.getVremeIznajmljivanja());
    	dto.setVremeVracanja(e.getVremeVracanja());
    	
        return dto;
    }

    public List<RezervacijaDTO> convert(List<Rezervacija> lista){
        List<RezervacijaDTO> listaDTO = new ArrayList<>();

        for(Rezervacija e : lista) {
            listaDTO.add(convert(e));
        }

        return listaDTO;
    }

}



