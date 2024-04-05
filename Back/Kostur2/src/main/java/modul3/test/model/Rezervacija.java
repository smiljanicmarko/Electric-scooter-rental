
package modul3.test.model;

import java.time.LocalDateTime;
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
public class Rezervacija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDateTime vremeIznajmljivanja;

	private LocalDateTime vremeVracanja;
	
	@Column(nullable = false)
	private String email;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Trotinet trotinet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getVremeIznajmljivanja() {
		return vremeIznajmljivanja;
	}

	public void setVremeIznajmljivanja(LocalDateTime vremeIznajmljivanja) {
		this.vremeIznajmljivanja = vremeIznajmljivanja;
	}

	public LocalDateTime getVremeVracanja() {
		return vremeVracanja;
	}

	public void setVremeVracanja(LocalDateTime vremeVracanja) {
		this.vremeVracanja = vremeVracanja;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Trotinet getTrotinet() {
		return trotinet;
	}

	public void setTrotinet(Trotinet trotinet) {
		this.trotinet = trotinet;
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
		Rezervacija other = (Rezervacija) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Rezervacija [id=" + id + ", vremeIznajmljivanja=" + vremeIznajmljivanja + ", vremeVracanja="
				+ vremeVracanja + ", email=" + email + ", trotinet=" + trotinet + "]";
	}
	
	
}
