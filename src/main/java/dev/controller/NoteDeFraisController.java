package dev.controller;

import java.util.List;

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
    public List<NoteDeFrais> getNoteDeFraisFromMission(@RequestParam Long mission) {

        // return
        // ndfService.findByNdfCumulId(ndfCumulService.findByMission(id).get().getId());

        if (ndfCumulService.findByMission(mission) == null) {
            return null;
        }
        return ndfService.findByNdfCumulId(ndfCumulService.findByMission(mission).getId());
    }

    /**
     * La méthode creerNdf permet d'ajouter la note de frais donné en paramètre
     * 
     */

    @PostMapping(path = "/lignedefrais")
    public NoteDeFrais CreerNdf(@RequestBody NoteDeFrais noteDeFrais) {

        return ndfRepo.save(noteDeFrais);
        // imp cas où paramètres incorrects
    }

    /**
     * La méthode patchNdf permet de mettre à jour la note de frais correspondant à
     * l'id donné en paramètre
     */

    @PatchMapping(path = "/lignedefrais")
    public NoteDeFrais patchNdf(@RequestBody NoteDeFrais noteDeFrais) {

        return ndfRepo.save(noteDeFrais);

    }

    @DeleteMapping(path = "/lignedefrais")
    public void deleteMission(@RequestParam Long id) {
        this.ndfRepo.deleteById(id);
    }

}
