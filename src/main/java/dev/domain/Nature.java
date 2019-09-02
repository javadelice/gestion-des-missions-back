package dev.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Nature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Enumerated(EnumType.STRING)
    private Choix isFacturee;

    @Enumerated(EnumType.STRING)
    private Choix hasPrime;

    private int tjm;

    private double pourcentagePrime;

    private int plafondFrais;

    @Enumerated(EnumType.STRING)
    private Choix depassPlafond;

    private LocalDate debutValidite;

    private LocalDate finValidite;


    @OneToMany(mappedBy = "nature", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Mission> listeMissions;

    public Nature() {
    }

    public Nature(String code, Choix isFacturee, Choix hasPrime, int tjm, double pourcentagePrime, int plafondFrais, Choix depassPlafond,
            LocalDate debutValidite, LocalDate finValidite, List<Mission> listeMissions) {
        this.code = code;
        this.isFacturee = isFacturee;
        this.hasPrime = hasPrime;
        this.tjm = tjm;
        this.pourcentagePrime = pourcentagePrime;
        this.plafondFrais = plafondFrais;
        this.depassPlafond = depassPlafond;
        this.debutValidite = debutValidite;
        this.finValidite = finValidite;
        this.listeMissions = listeMissions;
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

    public int getTjm() {
        return tjm;
    }

    public void setTjm(int tjm) {
        this.tjm = tjm;
    }

    public double getPourcentagePrime() {
        return pourcentagePrime;
    }

    public void setPourcentagePrime(double pourcentagePrime) {
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

    public List<Mission> getListeMissions() {
        return listeMissions;
    }

    public void setListeMissions(List<Mission> listeMissions) {
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
        builder.append(", tjm=");
        builder.append(tjm);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((debutValidite == null) ? 0 : debutValidite.hashCode());
		result = prime * result + ((depassPlafond == null) ? 0 : depassPlafond.hashCode());
		result = prime * result + ((finValidite == null) ? 0 : finValidite.hashCode());
		result = prime * result + ((hasPrime == null) ? 0 : hasPrime.hashCode());
		result = prime * result + ((isFacturee == null) ? 0 : isFacturee.hashCode());
		result = prime * result + plafondFrais;
		long temp;
		temp = Double.doubleToLongBits(pourcentagePrime);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + tjm;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nature other = (Nature) obj;
		if (hasPrime != other.hasPrime)
			return false;
		if (isFacturee != other.isFacturee)
			return false;
		if (plafondFrais != other.plafondFrais)
			return false;
		if (Double.doubleToLongBits(pourcentagePrime) != Double.doubleToLongBits(other.pourcentagePrime))
			return false;
		if (tjm != other.tjm)
			return false;
		return true;
	}

    
    
}
