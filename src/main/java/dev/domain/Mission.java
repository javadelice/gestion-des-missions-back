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
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "id_nature")
    private Nature nature;

    private String villeDepart;

    private String villeArrivee;

    @Enumerated(EnumType.STRING)
    private Transport transport;

    @Enumerated(EnumType.STRING)
    private StatutMission statut;

    private int prime;

    @ManyToOne
    @JoinColumn(name = "id_collegue")
    private Collegue collegue;

    public Mission() {
        this.statut = StatutMission.INITIALE;
    }

    public Mission(LocalDate startDate, LocalDate endDate, Nature nature, String villeDepart, String villeArrivee, Transport transport,
            int prime, Collegue collegue) {
        this();
        this.startDate = startDate;
        this.endDate = endDate;
        this.nature = nature;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.transport = transport;
        this.prime = prime;
        this.collegue = collegue;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Nature getNature() {
        return nature;
    }

    public void setNature(Nature nature) {
        this.nature = nature;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
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

    public int getPrime() {
        return prime;
    }

    public void setPrime(int prime) {
        this.prime = prime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Mission [id=");
        builder.append(id);
        builder.append(", startDate=");
        builder.append(startDate);
        builder.append(", endDate=");
        builder.append(endDate);
        builder.append(", nature=");
        builder.append(nature);
        builder.append(", villeDepart=");
        builder.append(villeDepart);
        builder.append(", villeArrivee=");
        builder.append(villeArrivee);
        builder.append(", transport=");
        builder.append(transport);
        builder.append(", statut=");
        builder.append(statut);
        builder.append(", prime=");
        builder.append(prime);
        builder.append(", collegue=");
        builder.append(collegue);
        builder.append("]");
        return builder.toString();
    }

}
