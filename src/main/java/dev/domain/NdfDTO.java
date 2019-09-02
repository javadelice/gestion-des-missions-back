package dev.domain;

import java.time.LocalDate;

public class NdfDTO {

    private Long id;

    private LocalDate date;


    private NdfNature nature;

    private Double montant;


    private Long idMission;

    public NdfDTO() {

    }

    public NdfDTO(LocalDate date, Double montant, NdfNature nature, Long idMission) {
        super();

        this.date = date;
        this.nature = nature;
        this.montant = montant;
        this.idMission = idMission;
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

    public Long getIdMission() {
        return idMission;
    }

    public void setIdMission(Long idMission) {
        this.idMission = idMission;
    }

    //	public Collegue getCollegue() {
//		return collegue;
//	}
//
//	public void setCollegue(Collegue collegue) {
//		this.collegue = collegue;
//	}
}
