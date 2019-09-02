package dev.controller;

import java.util.List;

import dev.repository.LigneDeFrais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.service.NoteDeFraisCumulService;

@RestController
@CrossOrigin(allowCredentials = "true")
public class LigneDeFraisController {
	
	@Autowired
	private LigneDeFrais ndfRepo;
	
	@Autowired 
	private dev.service.LigneDeFrais ndfService;
	
	@Autowired 
	private NoteDeFraisCumulService ndfCumulService;

	public LigneDeFraisController(LigneDeFrais ndfRepo) {
        this.ndfRepo=ndfRepo;
    }
	
	
    
	@RequestMapping(method = RequestMethod.GET, path= "/notedefrais/{id}")
    public List<dev.domain.LigneDeFrais> getNotesDeFraisFromId (@PathVariable Long id){

    	return ndfService.findByNdfCumulId(id);
   
    }
	
	/** La méthode getNoteDeFrais
	 *  permet d'obtenir la liste des notes de frais
	 *  en relation avec la mission donnée en paramètre
	 * 
	 */
	
    @RequestMapping(method = RequestMethod.GET, path= "/notedefrais")//, params = "mission")
    public List<dev.domain.LigneDeFrais> getNoteDeFraisFromMission (@RequestParam Long mission){

    	//return ndfService.findByNdfCumulId(ndfCumulService.findByMission(id).get().getId());
   
    	return ndfService.findByNdfCumulId(ndfCumulService.findByMission(mission).getId());
    }
    
    /** La méthode creerNdf
	 *  permet d'ajouter la note de frais
	 *  donné en paramètre
	 * 
	 */
    
    @PostMapping(path= "/lignedefrais")
    public dev.domain.LigneDeFrais CreerNdf(@RequestBody dev.domain.LigneDeFrais ligneDeFrais) {
    	
    	return ndfRepo.save(ndfService.verifyLigneDeFrais(ligneDeFrais));
    	//imp cas où paramètres incorrects	
    }
    
    /** La méthode patchNdf
	 *  permet de mettre à jour la note de frais
	 *  correspondant à l'id donné en paramètre
	 */
    
    @PatchMapping(path= "/lignedefrais")
    public dev.domain.LigneDeFrais patchNdf(@RequestBody dev.domain.LigneDeFrais ligneDeFrais) {
    	
		return ndfRepo.save(ndfService.verifyLigneDeFrais(ligneDeFrais));
    	
    }
    
    @DeleteMapping(path = "/lignedefrais")
    public void deleteMission(@RequestParam Long id) {
        this.ndfRepo.deleteById(id);
    }

}
