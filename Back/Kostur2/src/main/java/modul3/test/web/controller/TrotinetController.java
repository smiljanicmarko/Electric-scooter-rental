package modul3.test.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import modul3.test.model.Trotinet;
import modul3.test.service.TrotinetService;
import modul3.test.support.TrotinetDtoToTrotinet;
import modul3.test.support.TrotinetToTrotinetDto;
import modul3.test.web.dto.TrotinetDTO;

@RestController
@RequestMapping(value = "/api/trotineti", produces = MediaType.APPLICATION_JSON_VALUE)
public class TrotinetController {

	@Autowired 
	private TrotinetService trotinetService;
	@Autowired
	private TrotinetToTrotinetDto toDto;
	@Autowired
	private TrotinetDtoToTrotinet toKlasa;
	
	
	
	
	@GetMapping
	public ResponseEntity<List<TrotinetDTO>> getAll(			
			@RequestParam(required=false) Long adresaId,
			@RequestParam(required=false) Integer baterijaOd,
			@RequestParam(required=false) Integer baterijaDo,
			@RequestParam(defaultValue="0") int pageNo) {

		Page<Trotinet> stranice = trotinetService.pretraga(adresaId, baterijaOd, baterijaDo, pageNo);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Total-Pages", stranice.getTotalPages() + "");

		return new ResponseEntity<>(toDto.convert(stranice.getContent()), responseHeaders, HttpStatus.OK);

	}

	

	
	
	//GET BY ID
	  @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity <TrotinetDTO> get(@PathVariable Long id) {
		Trotinet obj = trotinetService.findOneById(id);

		if(obj != null) {
			return new ResponseEntity<>(toDto.convert(obj), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}




	//CREATE
    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TrotinetDTO> create(@Valid @RequestBody TrotinetDTO dto){

		Trotinet obj = toKlasa.convert(dto);
		Trotinet saved = trotinetService.save(obj);

		return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
	}


	//DELETE
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Trotinet obrisan = trotinetService.delete(id);

		if(obrisan != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//UPDATE
     @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	 @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<TrotinetDTO> update(@PathVariable Long id, @Valid @RequestBody TrotinetDTO dto){

	        if(!id.equals(dto.getId())) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Trotinet linija = toKlasa.convert(dto);
	        Trotinet saved = trotinetService.update(linija);

	        return new ResponseEntity<>(toDto.convert(saved),HttpStatus.OK);
	    }
	 
	 
	 @PreAuthorize("hasRole('ADMIN')")
	 @PutMapping(value = "/{id}/napuni")
	    public ResponseEntity<TrotinetDTO> update(@PathVariable Long id){

	       Trotinet novi = trotinetService.napuni(id);
	       
	       if (novi!=null) {
	    	   return new ResponseEntity<>(toDto.convert(novi),HttpStatus.OK);
	       }else {
	    	   return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	       }

	       
	    }

	 
	 
}
