package dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Mission;
import dev.repository.MissionRepo;

@CrossOrigin(allowCredentials = "true")
@RestController
public class MissionController {

    @Autowired
    private MissionRepo missionRepo;

    @RequestMapping(method = RequestMethod.GET, path = "/missions")
    public List<Mission> getMissions(@RequestParam Long id) {
        return missionRepo.findAll().stream()
                .filter(mission -> id == mission.getCollegue().getId())
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/missions")
    public void deleteMission(@RequestParam Long id) {
        this.missionRepo.deleteById(id);
    }

}