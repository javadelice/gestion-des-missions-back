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
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 private LocalDate date;
	 
	 @Enumerated(EnumType.STRING)
	 private NdfNature nature;
	 
	 private Double montant;
	 
	 @ManyToOne
	 @JoinColumn(name = "id_ndfCumul")
	 private NoteDeFraisCumul ndfCumul;
	 

	public NoteDeFrais() {
		
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
	 
}