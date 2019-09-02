package dev.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Nature;
import dev.repository.NatureRepo;
import dev.service.NatureService;

@CrossOrigin(allowCredentials = "true")
@RestController
public class NatureController {

    @Autowired
    private NatureRepo natureRepo;

    @Autowired
    private NatureService natureService;

    @RequestMapping(method = RequestMethod.GET, path = "/nature")
    public List<Nature> getNature() {
        return natureRepo.findAll().stream()
                .filter(natureAFiltrer -> (natureAFiltrer.getFinValidite() == null
                        || !LocalDate.now().isAfter(natureAFiltrer.getFinValidite())))
                .collect(Collectors.toList());
    }

    @Secured("ROLE_ADMINISTRATEUR")
    @RequestMapping(method = RequestMethod.POST, path = "/nature")
    public Nature createNature(@RequestBody Nature nature) {
        nature.setDebutValidite(LocalDate.now());
        return this.natureService.createNature(nature);
    }

    @Secured("ROLE_ADMINISTRATEUR")
    @RequestMapping(method = RequestMethod.DELETE, path = "/nature")
    public Nature deleteNature(@RequestParam Long id) {
        return natureService.deleteNature(id);
    }

    @Secured("ROLE_ADMINISTRATEUR")
    @RequestMapping(method = RequestMethod.PATCH, path = "/nature")
    public Nature deleteNature(@RequestBody Nature nature) {
        return natureService.updateNature(nature);
    }
}
