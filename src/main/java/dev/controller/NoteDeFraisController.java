package dev.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.CollegueVM;
import dev.domain.Mission;
import dev.domain.NoteDeFrais;
import dev.domain.StatutMission;
import dev.repository.CollegueRepo;
import dev.repository.NoteDeFraisRepo;
import dev.service.NoteDeFraisCumulService;
import dev.service.NoteDeFraisService;

@RestController
@CrossOrigin(allowCredentials = "true")
public class NoteDeFraisController {
	
	@Autowired
	private NoteDeFraisRepo ndfRepo;
	
	@Autowired 
	private NoteDeFraisService ndfService;
	
	@Autowired 
	private NoteDeFraisCumulService ndfCumulService;

	public NoteDeFraisController(NoteDeFraisRepo ndfRepo) {
        this.ndfRepo=ndfRepo;
    }
	
	
    
	@RequestMapping(method = RequestMethod.GET, path= "/notedefrais/{id}")
    public List<NoteDeFrais> getNotesDeFraisFromId (@PathVariable Long id){

    	return ndfService.findByNdfCumulId(id);
   
    }
	
	/** La méthode getNoteDeFrais
	 *  permet d'obtenir la liste des notes de frais
	 *  en relation avec la mission donnée en paramètre
	 * 
	 */
	
    @RequestMapping(method = RequestMethod.GET, path= "/notedefrais")//, params = "mission")
    public List<NoteDeFrais> getNoteDeFraisFromMission (@RequestParam Long mission){

    	//return ndfService.findByNdfCumulId(ndfCumulService.findByMission(id).get().getId());
   
    	return ndfService.findByNdfCumulId(ndfCumulService.findByMission(mission).getId());
    }
    
    /** La méthode creerNdf
	 *  permet d'ajouter la note de frais
	 *  donné en paramètre
	 * 
	 */
    
    @PostMapping(path= "/lignedefrais")
    public NoteDeFrais CreerNdf(@RequestBody NoteDeFrais noteDeFrais) {
    	
    	return ndfRepo.save(ndfService.verifyLigneDeFrais(noteDeFrais));
    	//imp cas où paramètres incorrects	
    }
    
    /** La méthode patchNdf
	 *  permet de mettre à jour la note de frais
	 *  correspondant à l'id donné en paramètre
	 */
    
    @PatchMapping(path= "/lignedefrais")
    public NoteDeFrais patchNdf(@RequestBody NoteDeFrais noteDeFrais) {
    	
		return ndfRepo.save(ndfService.verifyLigneDeFrais(noteDeFrais));
    	
    }
    
    @DeleteMapping(path = "/lignedefrais")
    public void deleteMission(@RequestParam Long id) {
        this.ndfRepo.deleteById(id);
    }

}
