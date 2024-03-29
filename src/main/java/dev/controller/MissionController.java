package dev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Choix;
import dev.domain.Mission;
import dev.domain.StatutMission;
import dev.repository.MissionRepo;
import dev.service.MissionService;
import dev.service.TraitementDeNuitService;

@CrossOrigin(allowCredentials = "true")
@RestController
public class MissionController {

    @Autowired
    private MissionRepo missionRepo;

    @Autowired
    private MissionService missionService;

    @Autowired
    private TraitementDeNuitService traitementDeNuitService;

    @RequestMapping(method = RequestMethod.GET, path = "/missions")
    public List<Mission> getMissions(@RequestParam Long id) {

        return this.missionService.getMissions(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/mission")
    public Optional<Mission> getMission(@RequestParam Long idMission) {
        return this.missionRepo.findById(idMission);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/missions")
    public void deleteMission(@RequestParam Long id) {
        Mission mission = this.missionRepo.findById(id).get();
        if (mission.getStatut().equals(StatutMission.INITIALE) || mission.getStatut().equals(StatutMission.REJETEE)) {
            this.missionRepo.deleteById(id);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/missions")
    public Mission createMission(@RequestBody Mission mission) {
        mission.setStatut(StatutMission.INITIALE);
        if (mission.getNature().getHasPrime().equals(Choix.OUI)) {
            mission.setPrimeACalculer(true);
        } else {
            mission.setPrimeACalculer(false);
        }
        return this.missionService.createMission(mission);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/missions")
    public Mission modifyMission(@RequestBody Mission mission) {
        if (mission.getStatut().equals(StatutMission.INITIALE) || mission.getStatut().equals(StatutMission.REJETEE)) {
            mission.setStatut(StatutMission.INITIALE);
            return this.missionService.createMission(mission);
        }
        return null;

    }

    @Secured({ "ROLE_MANAGER" })
    @RequestMapping(method = RequestMethod.GET, path = "/valider")
    public List<Mission> getMissionsAValider(@RequestParam Long idManager) {
        return this.missionService.getMissionsAValider(idManager);
    }

    @Secured("ROLE_MANAGER")
    @RequestMapping(method = RequestMethod.PATCH, path = "/valider")
    public Mission validerMission(@RequestParam boolean isValidated, @RequestBody Mission mission) {
        return this.missionService.validerMission(isValidated, mission);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/update")
    public void forcerTraitementNuit() {
        traitementDeNuitService.traitementDeNuit();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/missionsechues")
    public List<Mission> getMissionsEchues(@RequestParam Long idCollegue) {
        return this.missionService.getMissionsEchues(idCollegue);
    }

}
