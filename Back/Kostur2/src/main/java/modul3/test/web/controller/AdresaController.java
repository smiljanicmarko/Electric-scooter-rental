package modul3.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import modul3.test.model.Adresa;
import modul3.test.service.AdresaService;
import modul3.test.support.AdresaToAdresaDto;
import modul3.test.web.dto.AdresaDTO;

@RestController
@RequestMapping(value = "/api/adrese", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdresaController {

	@Autowired 
	private AdresaService adresaService;
	@Autowired
	private AdresaToAdresaDto toDto;

	
	
	
	
		@GetMapping
		public ResponseEntity<List<AdresaDTO>> getAll() {

			List<Adresa> stranice = adresaService.findAll();

		
			return new ResponseEntity<>(toDto.convert(stranice), HttpStatus.OK);

		}
	
	

	 
	 
}