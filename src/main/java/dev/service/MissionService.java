package dev.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.Mission;
import dev.domain.StatutMission;
import dev.domain.Transport;
import dev.exception.MissionInvalideException;
import dev.repository.MissionRepo;

@Service
public class MissionService {

    @Autowired
    private MissionRepo missionRepo;

    public MissionService() {

    }

    public Mission createMission(Mission mission) {
        if (mission.getStartDate().isBefore(LocalDate.now().plusDays(1))) {
            throw new MissionInvalideException("Date de début de mission invalide (une mission ne peut débuter ni aujourd'hui, ni dans le passé).");
        }

        if (mission.getTransport().equals(Transport.AVION) && mission.getStartDate().isBefore(LocalDate.now().plusDays(8))) {
            throw new MissionInvalideException(
                    "Date de début de mission invalide (avec un transport en AVION, une anticipation de 7 jours calendaires est exigée).");
        }

        if (!mission.getEndDate().isAfter(mission.getStartDate())) {
            throw new MissionInvalideException("Date de fin de mission invalide (une mission ne peut se terminer avant sa date de début).");
        }

        if (mission.getStartDate().getDayOfWeek().equals(DayOfWeek.SATURDAY) || mission.getStartDate().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            throw new MissionInvalideException(
                    "Date de début de mission invalide (il est interdit de créer une mission qui commence Samedi ou Dimanche).");
        }

        if (mission.getEndDate().getDayOfWeek().equals(DayOfWeek.SATURDAY) || mission.getEndDate().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            throw new MissionInvalideException(
                    "Date de fin de mission invalide (il est interdit de créer une mission qui se termine Samedi ou Dimanche).");
        }

        List<Mission> listeMissions = getMissions(mission.getCollegue().getId());
        for (Mission uneMission : listeMissions) {
            if (uneMission.getId() == mission.getId()) {
                continue;
            }
            if (mission.getStartDate().isBefore(uneMission.getStartDate()) && mission.getEndDate().isAfter(uneMission.getStartDate().minusDays(1))) {
                throw new MissionInvalideException("Les dates saisies chevauchent sur une autre mission (mission du " + uneMission.getStartDate()
                        + " au " + uneMission.getEndDate() + ").");
            }
            if (mission.getStartDate().isAfter(uneMission.getStartDate().minusDays(1))
                    && mission.getStartDate().isBefore(uneMission.getEndDate().plusDays(1))) {
                throw new MissionInvalideException("Les dates saisies chevauchent sur une autre mission (mission du " + uneMission.getStartDate()
                        + " au " + uneMission.getEndDate() + ").");
            }
        }

        return missionRepo.save(mission);
    }

    public List<Mission> getMissions(Long id) {
        return missionRepo.findAll().stream()
                .filter(mission -> id == mission.getCollegue().getId())
                .collect(Collectors.toList());
    }

    public List<Mission> getMissionsAValider() {
        return this.missionRepo.findAll().stream()
                .filter(mission -> mission.getStatut().equals(StatutMission.EN_ATTENTE_VALIDATION))
                .collect(Collectors.toList());
    }

    public Mission validerMission(boolean isValidated, Mission mission) {
        if (isValidated == true) {
            mission.setStatut(StatutMission.VALIDEE);
        }

        if (isValidated == false) {
            mission.setStatut(StatutMission.REJETEE);
        }

        return this.missionRepo.save(mission);
    }

}
