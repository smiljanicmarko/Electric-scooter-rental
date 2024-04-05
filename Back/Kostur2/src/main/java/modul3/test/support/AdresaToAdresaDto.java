package modul3.test.support;


import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import modul3.test.model.Adresa;
import modul3.test.web.dto.AdresaDTO;

@Component
public class AdresaToAdresaDto implements Converter<Adresa, AdresaDTO> {

    @Override
    public AdresaDTO convert(Adresa e) {
    	AdresaDTO dto = new AdresaDTO();
       
    	dto.setBroj(e.getBroj());
    	dto.setId(e.getId());
    	dto.setUlica(e.getUlica());
    	
        return dto;
    }

    public List<AdresaDTO> convert(List<Adresa> lista){
        List<AdresaDTO> listaDTO = new ArrayList<>();

        for(Adresa e : lista) {
            listaDTO.add(convert(e));
        }

        return listaDTO;
    }

}



