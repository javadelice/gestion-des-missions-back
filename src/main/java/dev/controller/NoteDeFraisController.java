package dev.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.CollegueVM;
import dev.domain.Mission;
import dev.domain.NoteDeFrais;
import dev.repository.CollegueRepo;
import dev.repository.NoteDeFraisRepo;
import dev.service.NoteDeFraisService;

@RestController
@RequestMapping("/notedefrais")
public class NoteDeFraisController {
	
	@Autowired
	private NoteDeFraisRepo ndfRepo;
	
	@Autowired 
	private NoteDeFraisService ndfService;

	public NoteDeFraisController(NoteDeFraisRepo ndfRepo) {
        this.ndfRepo=ndfRepo;
    }
	
	/** La méthode getNoteDeFrais
	 *  permet d'obtenir la liste des notes de frais
	 *  en relation avec la mission donnée en paramètre
	 * 
	 */
    
	@RequestMapping(method = RequestMethod.GET, params= "{Id}")
    public Optional<NoteDeFrais> getNoteDeFraisFromId (@RequestParam Long Id){

    	return ndfService.findById(Id);
   
    }
	
    @RequestMapping(method = RequestMethod.GET, params= "mission")
    public Optional<NoteDeFrais> getNoteDeFraisFromMission (@RequestParam Mission mission){

    	return ndfService.findById(mission.getId());
   
    }
    
    /** La méthode creerNdf
	 *  permet d'ajouter la note de frais
	 *  donné en paramètre
	 * 
	 */
    
    @PostMapping//("/notedefrais")
    public NoteDeFrais CreerNdf(@RequestParam NoteDeFrais noteDeFrais) {
    	
    	return ndfRepo.save(noteDeFrais);
    	//imp cas où paramètres incorrects	
    }
    
    /** La méthode patchNdf
	 *  permet de mettre à jour la note de frais
	 *  correspondant à l'id donné en paramètre
	 */
    
    @PatchMapping//("/notedefrais")
    public NoteDeFrais patchNdf(@RequestParam NoteDeFrais noteDeFrais) {
    	
    	
		return ndfRepo.save(noteDeFrais);
    	
    }
}
