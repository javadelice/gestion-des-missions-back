package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

    // Accès à la page "nature" qu'à ceux ayant le rôle d'administrateur
    @Secured("ROLE_ADMINISTRATEUR")
    @RequestMapping(method = RequestMethod.GET, path = "/nature")
    public List<Nature> getNature(@RequestParam Long id) {
        return natureRepo.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/natures")
    public List<Nature> getNatures() {
        return this.natureRepo.findAll();
    }

}
