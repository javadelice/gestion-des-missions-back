package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Nature;
import dev.repository.NatureRepo;

@CrossOrigin(allowCredentials = "true")
@RestController
public class NatureController {

    @Autowired
    private NatureRepo natureRepo;

    @RequestMapping(method = RequestMethod.GET, path = "/natures")
    public List<Nature> getNatures() {
        return this.natureRepo.findAll();
    }

}
