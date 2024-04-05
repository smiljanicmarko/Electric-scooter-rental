package modul3.test.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class TrotinetDTO {

	 	private Long id;
	 	@Size(max = 20)
	    private String sifra;
	    @Min(0)
	    @Max(100)
	    private Integer baterija=100;
	    	 
	    private Integer brzina;
	    	   
	    private Boolean iznajmljen = false;	    
	   
	    private Long adresaId;
	    private String adresa;
	    private Integer broj;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getSifra() {
			return sifra;
		}
		public void setSifra(String sifra) {
			this.sifra = sifra;
		}
		public Integer getBaterija() {
			return baterija;
		}
		public void setBaterija(Integer baterija) {
			this.baterija = baterija;
		}
		public Integer getBrzina() {
			return brzina;
		}
		public void setBrzina(Integer brzina) {
			this.brzina = brzina;
		}
		public Boolean getIznajmljen() {
			return iznajmljen;
		}
		public void setIznajmljen(Boolean iznajmljen) {
			this.iznajmljen = iznajmljen;
		}
		public Long getAdresaId() {
			return adresaId;
		}
		public void setAdresaId(Long adresaId) {
			this.adresaId = adresaId;
		}
		public String getAdresa() {
			return adresa;
		}
		public void setAdresa(String adresa) {
			this.adresa = adresa;
		}
		public Integer getBroj() {
			return broj;
		}
		public void setBroj(Integer broj) {
			this.broj = broj;
		}
    
	    
	    
   
}
