package dev.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.Mission;
import dev.domain.NoteDeFraisCumul;
import dev.exception.MissionNonTrouveException;
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
        Mission mission = missionRepo.findById(idMission)
                .orElseThrow(() -> new MissionNonTrouveException("Mission introuvable"));

        if (mission.getNdfCumul() == null) {
            mission.setNdfCumul(new NoteDeFraisCumul(new ArrayList<>(), mission));
        }
        noteDeFraisCumulRepo.save(mission.getNdfCumul());
        missionRepo.save(mission);
        return mission.getNdfCumul();
    }

    public NoteDeFraisCumul createNdfCumul(NoteDeFraisCumul ndfCumul) {
        return this.noteDeFraisCumulRepo.save(ndfCumul);
    }

}
