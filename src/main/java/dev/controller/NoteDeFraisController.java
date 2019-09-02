package dev.controller;

import java.util.ArrayList;
import java.util.List;

import dev.domain.NdfDTO;
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

import dev.domain.NoteDeFrais;
import dev.domain.NoteDeFraisCumul;
import dev.exception.LigneDeFraisInvalideException;
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
        this.ndfRepo = ndfRepo;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/notedefrais/{id}")
    public List<NoteDeFrais> getNotesDeFraisFromId(@PathVariable Long id) {

        return ndfService.findByNdfCumulId(id);

    }

    /**
     * La méthode getNoteDeFrais permet d'obtenir la liste des notes de frais en
     * relation avec la mission donnée en paramètre
     * 
     */

    @RequestMapping(method = RequestMethod.GET, path = "/notedefrais") // , params = "mission")
    public List<NoteDeFrais> getNoteDeFraisFromMission(@RequestParam Long idMission) {

        // return
        // ndfService.findByNdfCumulId(ndfCumulService.findByMission(id).get().getId());

        if (ndfCumulService.findByMission(idMission) == null) {
            return new ArrayList<NoteDeFrais>();
        }
        return ndfService.findByNdfCumulId(ndfCumulService.findByMission(idMission).getId());
    }

    /**
     * La méthode creerNdf permet d'ajouter la note de frais donné en paramètre
     * 
     */

    @PostMapping(path = "/lignedefrais")
    public NoteDeFrais creerNdf(@RequestBody NdfDTO noteDeFraisDTO) {

        NoteDeFraisCumul noteDeFraisCumul = this.ndfCumulService.findByMission(noteDeFraisDTO.getIdMission());
        if (noteDeFraisDTO.getDate().isBefore(noteDeFraisCumul.getMission().getStartDate())
                || noteDeFraisDTO.getDate().isAfter(noteDeFraisCumul.getMission().getEndDate())) {
            throw new LigneDeFraisInvalideException("Ligne de frais invalide ! La date doit être comprise dans les dates de la mission.");
        }
        if (noteDeFraisDTO.getMontant() <= 0) {
            throw new LigneDeFraisInvalideException("Ligne de frais invalide ! Le montant doit être supérieur à 0");
        }

        NoteDeFrais noteDeFrais = new NoteDeFrais(noteDeFraisDTO.getDate(), noteDeFraisDTO.getMontant(), noteDeFraisDTO.getNature(), noteDeFraisCumul);
        ndfRepo.save(noteDeFrais);

        return noteDeFrais;

        /**
         * La méthode creerNdf permet d'ajouter la note de frais donné en paramètre
         * 
         */

    }

    /**
     * La méthode patchNdf permet de mettre à jour la note de frais correspondant à
     * l'id donné en paramètre
     */

    @PatchMapping(path = "/lignedefrais")
    public NoteDeFrais patchNdf(@RequestBody NoteDeFrais noteDeFrais) {
        return ndfRepo.save(ndfService.verifyLigneDeFrais(noteDeFrais));

    }

    @DeleteMapping(path = "/lignedefrais")
    public void deleteMission(@RequestParam Long id) {
        this.ndfRepo.deleteById(id);
    }

    @PostMapping(path = "/notedefrais")
    public NoteDeFraisCumul createNdfCumul(@RequestBody NoteDeFraisCumul ndfCumul) {
        return this.ndfCumulService.createNdfCumul(ndfCumul);
    }

}
