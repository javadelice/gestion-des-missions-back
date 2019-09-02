package dev.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.NoteDeFraisCumul;
import dev.repository.MissionRepo;
import dev.repository.NoteDeFraisCumulRepo;
import dev.repository.NoteDeFraisRepo;

@Service
public class NoteDeFraisCumulService {

    @Autowired
    private NoteDeFraisCumulRepo noteDeFraisCumulRepo;

    @Autowired
    private NoteDeFraisRepo ndfRepo;

    @Autowired
    MissionRepo missionRepo;

    public Optional<NoteDeFraisCumul> findById(Long id) {

        return noteDeFraisCumulRepo.findById(id);

    }

    public NoteDeFraisCumul findByMission(Long idMission) {
        NoteDeFraisCumul ndfCumul = new NoteDeFraisCumul();
        try {
            ndfCumul = this.noteDeFraisCumulRepo.findAll().stream()
                    // .filter(ndf -> ndf.getMission().getId() == idMission ? Stream.of(ndf):
                    // Stream.empty())
                    .filter(ndf -> (ndf.getMission().getId() == idMission))

                    .collect(Collectors.toList()).get(0);
//		
//		return noteDeFraisCumulRepo.findByMission(this.missionRepo.findById(id).get());
        } catch (NullPointerException e) {
            // Creation d'une note de frais
            this.missionRepo.findById(idMission).get().setNdfCumul(new NoteDeFraisCumul());
            // recurse on this method
            this.findByMission(idMission);
        } finally {
            return ndfCumul;
        }
    }

    public NoteDeFraisCumul createNdfCumul(NoteDeFraisCumul ndfCumul) {
        return this.noteDeFraisCumulRepo.save(ndfCumul);
    }

}
