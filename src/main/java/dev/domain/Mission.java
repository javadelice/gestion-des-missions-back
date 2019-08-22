package dev.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mission {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	private LocalDate dateDebut;
	private LocalDate dateFin;
	
	@Enumerated(EnumType.STRING)
	private MissionNature nature;
	
	private String villeDépart;
	private String villeArrivée;
	
	@Enumerated(EnumType.STRING)
	private Transport transport;
	
	@Enumerated(EnumType.STRING)
	private StatutMission statut;

	
	
	public Mission() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public MissionNature getNature() {
		return nature;
	}

	public void setNature(MissionNature conseil) {
		this.nature = conseil;
	}

	public String getVilleDépart() {
		return villeDépart;
	}

	public void setVilleDépart(String villeDépart) {
		this.villeDépart = villeDépart;
	}

	public String getVilleArrivée() {
		return villeArrivée;
	}

	public void setVilleArrivée(String villeArrivée) {
		this.villeArrivée = villeArrivée;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public StatutMission getStatut() {
		return statut;
	}

	public void setStatut(StatutMission statut) {
		this.statut = statut;
	}

	
	
}
