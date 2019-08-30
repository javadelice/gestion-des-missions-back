package dev.service;

import java.util.List;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

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
        
        List<Nature> listeNature = natureRepo.findAll().stream()
                .filter(natureAFiltrer -> natureAFiltrer.getFinValidite() == null
                        || !LocalDate.now().isAfter(natureAFiltrer.getFinValidite())).collect(Collectors.toList());

        for (Nature uneNature : listeNature) {
            if (uneNature.getCode().equals(nature.getCode())) {
            	throw new NatureInvalideException("Ce code de nature existe déjà.");
            }
            if(uneNature.equals(nature)) {
            	throw new NatureInvalideException("Une nature avec des attributs identiques existe déjà.");
            }
        }

        nature.setDebutValidite(LocalDate.now());
        nature.setFinValidite(null);

		return natureRepo.save(nature);
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

    public Nature updateNature(Nature nature) {
        Optional<Nature> optionalNature = natureRepo.findById(nature.getId());
        Nature oldNature = optionalNature.orElseThrow(NatureIntrouvableException::new);

        if (oldNature.getFinValidite() != null && LocalDate.now().isAfter(oldNature.getFinValidite())) {
            throw new NatureIntrouvableException();
        }


        if (nature.getPourcentagePrime() > 10 || nature.getPourcentagePrime() < 0) {
            throw new NatureInvalideException("Valeur de pourcentage invalide (le pourcentage doit être inférieur à 10).");
        }

        if (nature.getPlafondFrais() < 0) {
            throw new NatureInvalideException("Valeur de plafond invalide (le plafond doit être strictement positif).");
        }

        List<Nature> listeNaturesActives = natureRepo.findAll().stream()
                .filter(natureAFiltrer -> (natureAFiltrer.getFinValidite() == null
                        || !LocalDate.now().isAfter(natureAFiltrer.getFinValidite()))
                        && natureAFiltrer.getId() != nature.getId()).collect(Collectors.toList());

        for (Nature natureActive : listeNaturesActives) {
            if (natureActive.getCode().equals(nature.getCode())) {
                throw new NatureInvalideException("Ce code de nature existe déjà.");
            }
            if(natureActive.equals(nature)) {
                throw new NatureInvalideException("Une nature avec des attributs identiques existe déjà.");
            }
        }


        if (oldNature.getListeMissions().isEmpty()) {
            nature.setFinValidite(null);
            nature.setDebutValidite(LocalDate.now());
            natureRepo.save(nature);
            return nature;
        }

        oldNature.setFinValidite(LocalDate.now().minusDays(1));
        natureRepo.save(oldNature);
        
        nature.setId(null);
        nature.setFinValidite(null);
        nature.setDebutValidite(LocalDate.now());
        natureRepo.save(nature);
        return nature;



    }
}
