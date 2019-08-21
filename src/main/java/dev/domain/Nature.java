package dev.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    private Choix isFacturee;

    @Enumerated(EnumType.STRING)
    private Choix hasPrime;

    private int TJM;

    private int pourcentagePrime;

    private int plafondFrais;

    @Enumerated(EnumType.STRING)
    private Choix depassPlafond;

    private LocalDate debutValidite;

    private LocalDate finValidite;

    // TODO @OneToMany
    private List<Nature> listeMissions;

    public Nature() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Choix getIsFacturee() {
        return isFacturee;
    }

    public void setIsFacturee(Choix isFacturee) {
        this.isFacturee = isFacturee;
    }

    public Choix getHasPrime() {
        return hasPrime;
    }

    public void setHasPrime(Choix hasPrime) {
        this.hasPrime = hasPrime;
    }

    public int getTJM() {
        return TJM;
    }

    public void setTJM(int tJM) {
        TJM = tJM;
    }

    public int getPourcentagePrime() {
        return pourcentagePrime;
    }

    public void setPourcentagePrime(int pourcentagePrime) {
        this.pourcentagePrime = pourcentagePrime;
    }

    public int getPlafondFrais() {
        return plafondFrais;
    }

    public void setPlafondFrais(int plafondFrais) {
        this.plafondFrais = plafondFrais;
    }

    public Choix getDepassPlafond() {
        return depassPlafond;
    }

    public void setDepassPlafond(Choix depassPlafond) {
        this.depassPlafond = depassPlafond;
    }

    public LocalDate getDebutValidite() {
        return debutValidite;
    }

    public void setDebutValidite(LocalDate debutValidite) {
        this.debutValidite = debutValidite;
    }

    public LocalDate getFinValidite() {
        return finValidite;
    }

    public void setFinValidite(LocalDate finValidite) {
        this.finValidite = finValidite;
    }

    public List<Nature> getListeMissions() {
        return listeMissions;
    }

    public void setListeMissions(List<Nature> listeMissions) {
        this.listeMissions = listeMissions;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Nature [id=");
        builder.append(id);
        builder.append(", code=");
        builder.append(code);
        builder.append(", isFacturee=");
        builder.append(isFacturee);
        builder.append(", hasPrime=");
        builder.append(hasPrime);
        builder.append(", TJM=");
        builder.append(TJM);
        builder.append(", pourcentagePrime=");
        builder.append(pourcentagePrime);
        builder.append(", plafondFrais=");
        builder.append(plafondFrais);
        builder.append(", depassPlafond=");
        builder.append(depassPlafond);
        builder.append(", debutValidite=");
        builder.append(debutValidite);
        builder.append(", finValidite=");
        builder.append(finValidite);
        builder.append(", listeMissions=");
        builder.append(listeMissions);
        builder.append("]");
        return builder.toString();
    }

}
