package dev.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class NoteDeFraisCumul {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "ndfCumul")
    @JsonIgnore
    private List<NoteDeFrais> notesDeFrais;

    @OneToOne
    @JoinColumn(name = "mission_id")
    @JsonIgnore
    private Mission mission;

    public NoteDeFraisCumul() {
        super();
        this.notesDeFrais = new ArrayList<NoteDeFrais>();

    }

    public NoteDeFraisCumul(List<NoteDeFrais> notesDeFrais, Mission mission) {
        super();

        this.notesDeFrais = notesDeFrais;
        this.mission = mission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<NoteDeFrais> getNotesDeFrais() {
        return notesDeFrais;
    }

    public void setNotesDeFrais(List<NoteDeFrais> notesDeFrais) {
        this.notesDeFrais = notesDeFrais;
    }

    public void addNotesDeFrais(NoteDeFrais noteDeFrais) {
        this.notesDeFrais.add(noteDeFrais);
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        mission.setNdfCumul(this); // à améliorer
        this.mission = mission;
    }

}
