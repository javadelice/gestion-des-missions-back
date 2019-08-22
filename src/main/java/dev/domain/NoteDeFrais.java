package dev.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class NoteDeFrais {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 
	 private LocalDate date;
	 
	 @Enumerated(EnumType.STRING)
	 private NdfNature nature;
	 
	 private Double montant;
	 
	 
	 @ManyToOne
	 @JoinColumn(name = "collegue_id")
	 private Collegue collegue;
	 
	 @ManyToOne
	 @JoinColumn(name = "mission_id")
	 private Mission mission;
	 
	public NoteDeFrais() {
		
	}

	public Collegue getCollegue() {
		return collegue;
	}

	public void setCollegue(Collegue collegue) {
		this.collegue = collegue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public NdfNature getNature() {
		return nature;
	}

	public void setNature(NdfNature nature) {
		this.nature = nature;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}
	 
	 
	 
	 
	 
	 
	 
	 
}
