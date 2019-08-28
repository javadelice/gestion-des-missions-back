package dev.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.Nature;
import dev.exception.NatureInvalideException;
import dev.repository.NatureRepo;

@Service
public class NatureService {

	@Autowired
    private NatureRepo natureRepo;

    public NatureService() {

    }
    
    public Nature createNature(Nature nature) {
        if (nature.getFinValidite().isBefore(LocalDate.now())) {
            throw new NatureInvalideException("La date de fin de validité ne peut pas être antérieur à celle du début de validité.");
        }
        
        if (nature.getPourcentagePrime() > 10 || nature.getPourcentagePrime() < 0) {
        	throw new NatureInvalideException("Valeur de pourcentage invalide (le pourcentage doit être inférieur à 10).");
        }
        
        if (nature.getPlafondFrais() < 0) {
        	throw new NatureInvalideException("Valeur de plafond invalide (le plafond doit être strictement positif).");
        }
        
        List<Nature> listeNature = getNature();
        for (Nature uneNature : listeNature) {
            if (uneNature.getCode().equals(nature.getCode())) {
            	throw new NatureInvalideException("Code invalide (un code de nature doit être unique).");
            }
            if(uneNature.equals(nature)) {
            	throw new NatureInvalideException("Nature avec attributs identiques déjà existante!");
            }
        }
        
		return natureRepo.save(nature);
}
    public List<Nature> getNature() {
        return natureRepo.findAll().stream()
                .filter(nature -> LocalDate.now().isBefore(nature.getFinValidite()))
                .collect(Collectors.toList());
    }
}
