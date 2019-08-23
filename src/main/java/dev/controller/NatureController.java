package dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Nature;
import dev.repository.NatureRepo;

@CrossOrigin(allowCredentials = "true")
@RestController
public class NatureController {
	
	@Autowired
    private NatureRepo natureRepo;

    @RequestMapping(method = RequestMethod.GET, path = "/nature")
    public List<Nature> getNature(@RequestParam Long id) {
        return natureRepo.findAll();
    }

    /*
    @RequestMapping(method = RequestMethod.DELETE, path = "/nature")
    public void deleteMission(@RequestParam Long id) {
        this.missionRepo.deleteById(id);
    }
    */

}
