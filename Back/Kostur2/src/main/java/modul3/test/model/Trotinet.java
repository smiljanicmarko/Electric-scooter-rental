package modul3.test.model;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Trotinet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String sifra;
    
    private Integer baterija;
    
    @Column (nullable = false)
    private Integer brzina;
    
    @Column (nullable = false)
    private Boolean iznajmljen;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Adresa adresa;

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

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trotinet other = (Trotinet) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Trotinet [id=" + id + ", sifra=" + sifra + ", baterija=" + baterija + ", brzina=" + brzina
				+ ", iznajmljen=" + iznajmljen + ", adresa=" + adresa + "]";
	}

   
    
    

//    @OneToMany(mappedBy = "adresa", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<Korisnik> korisnici = new ArrayList<>();

    
    
    
}
