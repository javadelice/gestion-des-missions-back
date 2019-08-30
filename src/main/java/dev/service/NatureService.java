package dev.service;

import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

import dev.exception.NatureIntrouvableException;
import dev.exception.NatureUtiliseeException;
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
        return natureRepo.findAll();
    }

    public Nature deleteNature(Long idNature) {
        Optional<Nature> optionalNature = natureRepo.findById(idNature);
        Nature nature = optionalNature.orElseThrow(NatureIntrouvableException::new);
        if (nature.getListeMissions().isEmpty()) {
            natureRepo.delete(nature);
            return null;
        }
        if (nature.getFinValidite() != null && !nature.getFinValidite().isAfter(LocalDate.now())) {
            throw new NatureIntrouvableException();
        }
        if(nature.getListeMissions().stream().anyMatch(mission -> LocalDate.now().isBefore(mission.getEndDate()))) {
            throw new NatureUtiliseeException();
        }

        nature.setFinValidite(LocalDate.now());
        natureRepo.save(nature);
        return nature;
    }
}
