package dev.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.NoteDeFraisCumul;
import dev.repository.NoteDeFraisCumulRepo;
import dev.repository.NoteDeFraisRepo;

@Service
public class NoteDeFraisCumulService {

    @Autowired
    private NoteDeFraisCumulRepo noteDeFraisCumulRepo;

    @Autowired
    private NoteDeFraisRepo ndfRepo;

    public Optional<NoteDeFraisCumul> findById(Long id) {

        return noteDeFraisCumulRepo.findById(id);

    }

    public NoteDeFraisCumul findByMission(Long idMission) {

        List<NoteDeFraisCumul> ndfCumul = this.noteDeFraisCumulRepo.findAll().stream()
                .filter(ndf -> ndf.getMission().getId() == idMission)
                .collect(Collectors.toList());

        if (ndfCumul.isEmpty()) {
            return null;
        }

        return ndfCumul.get(0);

    }

    public NoteDeFraisCumul createNdfCumul(NoteDeFraisCumul ndfCumul) {
        return this.noteDeFraisCumulRepo.save(ndfCumul);
    }

}
