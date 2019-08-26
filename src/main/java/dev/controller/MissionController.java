package dev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Mission;
import dev.domain.StatutMission;
import dev.repository.MissionRepo;
import dev.service.MissionService;

@CrossOrigin(allowCredentials = "true")
@RestController
public class MissionController {

    @Autowired
    private MissionRepo missionRepo;

    @Autowired
    private MissionService missionService;

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
        this.missionRepo.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/missions")
    public Mission createMission(@RequestBody Mission mission) {
        mission.setStatut(StatutMission.INITIALE);
        return this.missionService.createMission(mission);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/missions")
    public Mission modifyMission(@RequestBody Mission mission) {
        mission.setStatut(StatutMission.INITIALE);
        return this.missionService.createMission(mission);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/valider")
    public List<Mission> getMissionsAValider() {
        return this.missionService.getMissionsAValider();
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/valider")
    public Mission validerMission(@RequestParam boolean isValidated, @RequestBody Mission mission) {
        return this.missionService.validerMission(isValidated, mission);
    }

}
