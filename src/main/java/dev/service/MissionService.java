package dev.service;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.Mission;
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

        // TODO création de mission qui chevauche une autre mission interdite

        return missionRepo.save(mission);
    }

}
