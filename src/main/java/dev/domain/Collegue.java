package dev.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Collegue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private String email;

    @JsonIgnore
    private String motDePasse;

    @OneToMany(mappedBy = "collegue", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<RoleCollegue> roles;

    @OneToMany(mappedBy = "collegue")
    @JsonIgnore
    private List<Mission> missions;

    @Enumerated(EnumType.STRING)
    private Departement departement;

//    @OneToMany(mappedBy = "collegue", cascade = CascadeType.PERSIST)
//    @JsonIgnore
//    private List<LigneDeFrais> notesDeFrais;

    /*
     * @OneToMany(cascade=CascadeType.ALL)
     * 
     * @JoinTable(name="collegue_notesdefrais_mapping",joinColumns=@JoinColumn(name=
     * "collegue_id"),inverseJoinColumns=@JoinColumn(name="notedefrais_id")) private
     * List<LigneDeFrais> notesDeFrais;
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

//    public List<LigneDeFrais> getNotesDeFrais() {
//        return notesDeFrais;
//    }
//
//    public void setNotesDeFrais(List<LigneDeFrais> notesDeFrais) {
//        this.notesDeFrais = notesDeFrais;
//    }
//
//    public void addNotesDeFrais(LigneDeFrais noteDeFrais) {
//        this.notesDeFrais.add(noteDeFrais);
//    }


}
