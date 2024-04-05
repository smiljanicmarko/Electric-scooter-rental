package modul3.test.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import modul3.test.model.Rezervacija;
import modul3.test.service.RezervacijaService;
import modul3.test.support.RezervacijaDtoToRezervacija;
import modul3.test.support.RezervacijaToRezervacijaDto;
import modul3.test.web.dto.RezervacijaDTO;
import modul3.test.web.dto.VracanjeDTO;

@RestController
@RequestMapping(value = "/api/rezervacije", produces = MediaType.APPLICATION_JSON_VALUE)
public class RezervacijaController {

	@Autowired 
	private RezervacijaService rezervacijaService;
	@Autowired
	private RezervacijaToRezervacijaDto toDto;
	@Autowired
	private RezervacijaDtoToRezervacija toKlasa;
	
	
	//  @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	 // @PreAuthorize("hasRole('KORISNIK')")
	
	
	

	
	//GET ALL LISTA
	
		@GetMapping
		public ResponseEntity<List<RezervacijaDTO>> getAll() {

			List<Rezervacija> stranice = rezervacijaService.findAll();

		
			return new ResponseEntity<>(toDto.convert(stranice), HttpStatus.OK);

		}
	
		
		
		
			
		 @PreAuthorize("hasRole('KORISNIK')")
		@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<RezervacijaDTO> create(@Valid @RequestBody RezervacijaDTO dto){
	
			Rezervacija obj = toKlasa.convert(dto);
			Rezervacija saved = rezervacijaService.rezervisi(obj);
	
			if (saved !=null) {
				return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
			}else {
				return new ResponseEntity<RezervacijaDTO>(HttpStatus.BAD_REQUEST);
			}
			
			
		}
		
		
		
		
		
		 @PreAuthorize("hasRole('KORISNIK')")
		 @PutMapping(value = "/vrati",consumes = MediaType.APPLICATION_JSON_VALUE)
		    public ResponseEntity<RezervacijaDTO> update(@Valid @RequestBody VracanjeDTO dto){
	
		       
			 boolean uspeh = rezervacijaService.vratiTrotinet(dto);
	
			 if(uspeh) {
				 return new ResponseEntity<>(HttpStatus.OK);
			 }			 
	
		        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		    }
		
		

	 
	 
}