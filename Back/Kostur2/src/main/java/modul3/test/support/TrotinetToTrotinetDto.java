package modul3.test.support;


import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import modul3.test.model.Trotinet;
import modul3.test.web.dto.TrotinetDTO;

@Component
public class TrotinetToTrotinetDto implements Converter<Trotinet, TrotinetDTO> {

    @Override
    public TrotinetDTO convert(Trotinet e) {
    	TrotinetDTO dto = new TrotinetDTO();
       
    	dto.setAdresa(e.getAdresa().getUlica());
    	dto.setAdresaId(e.getAdresa().getId());
    	dto.setBaterija(e.getBaterija());
    	dto.setBroj(e.getAdresa().getBroj());
    	dto.setBrzina(e.getBrzina());
    	dto.setId(e.getId());
    	dto.setIznajmljen(e.getIznajmljen());
    	dto.setSifra(e.getSifra());
    	
        return dto;
    }

    public List<TrotinetDTO> convert(List<Trotinet> lista){
        List<TrotinetDTO> listaDTO = new ArrayList<>();

        for(Trotinet e : lista) {
            listaDTO.add(convert(e));
        }

        return listaDTO;
    }

}



