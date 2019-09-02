package dev.domain;

import java.util.List;
import java.util.Objects;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Collegue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private String email;

    private String motDePasse;

    @OneToMany(mappedBy = "collegue", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<RoleCollegue> roles;

    @OneToMany(mappedBy = "collegue")
    @JsonIgnore
    private List<Mission> missions;

    @Enumerated(EnumType.STRING)
    private Departement departement;

//    @OneToMany(mappedBy = "collegue", cascade = CascadeType.PERSIST)
//    @JsonIgnore
//    private List<NoteDeFrais> notesDeFrais; 

    /*
     * @OneToMany(cascade=CascadeType.ALL)
     * 
     * @JoinTable(name="collegue_notesdefrais_mapping",joinColumns=@JoinColumn(name=
     * "collegue_id"),inverseJoinColumns=@JoinColumn(name="notedefrais_id")) private
     * List<NoteDeFrais> notesDeFrais;
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<RoleCollegue> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleCollegue> roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

//    public List<NoteDeFrais> getNotesDeFrais() {
//        return notesDeFrais;
//    }
//
//    public void setNotesDeFrais(List<NoteDeFrais> notesDeFrais) {
//        this.notesDeFrais = notesDeFrais;
//    }
//
//    public void addNotesDeFrais(NoteDeFrais noteDeFrais) {
//        this.notesDeFrais.add(noteDeFrais);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collegue collegue = (Collegue) o;
        return id.equals(collegue.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.email;
    }
}
