package dev.service;

import dev.domain.*;
import dev.repository.CollegueRepo;
import dev.repository.MissionRepo;
import dev.security.JWTAuthenticationSuccessHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class TraitementDeNuitService {

    private static final Logger LOG = LoggerFactory.getLogger(TraitementDeNuitService.class);

    @Autowired
    private MissionRepo missionRepo;
    @Autowired
    private CollegueRepo collegueRepo;
    @Autowired
    public JavaMailSender emailSender;

    @Scheduled(cron = "0 0 0 * * ?")
    public void traitementDeNuit() {
        LOG.info("Début du traitement de nuit");

        List<Mission> missionsInitiales = missionRepo.findByStatut(StatutMission.INITIALE);

        List<Collegue> collegues = collegueRepo.findAll();

        missionsInitiales.forEach(mission -> {
            LOG.info(mission.toString());
            collegues.stream().filter(collegue ->
                collegue.getRoles().stream().anyMatch(roleCollegue -> roleCollegue.getRole().equals(Role.ROLE_MANAGER))
                    && collegue.getDepartement().equals(mission.getCollegue().getDepartement())
                    && !collegue.equals(mission.getCollegue())
            ).forEach(
                    collegue -> {
                        LOG.info("Envoi d'un mail à " + collegue.toString());
                        SimpleMailMessage message = new SimpleMailMessage();
                        message.setTo(collegue.getEmail());
                        message.setSubject("mission en attente de validation");
                        message.setText("La mission de " + mission.getCollegue().getPrenom() + " "
                                + mission.getCollegue().getNom() + " du " + mission.getStartDate() + " au " + mission.getEndDate()
                                + " est passée en attente de validation.\n"
                                + "Vous pouvez vous rendre sur l'application de gestion de missions pour la consulter.");
                        emailSender.send(message);
                    }
            );
            mission.setStatut(StatutMission.EN_ATTENTE_VALIDATION);
            missionRepo.save(mission);
        });

        List<Mission> missionsACalculer = missionRepo.findByPrimeACalculer(true);

        missionsACalculer.stream().filter(mission -> mission.getEndDate().isBefore(LocalDate.now()) && mission.getStatut().equals(StatutMission.VALIDEE))
        .forEach(mission -> {
            LOG.info("Calcul de la prime de la mission " + mission.toString());
            mission.setPrime(
                    (int) Math.ceil(Period.between(mission.getStartDate(), mission.getEndDate()).getDays()
                            * mission.getNature().getTjm()
                            * mission.getNature().getPourcentagePrime()/100
                            //TODO : Enlever les dépassements de frais de la prime
                            )
            );

            mission.setPrimeACalculer(false);

            missionRepo.save(mission);
        });

        LOG.info("Fin du traitement de nuit");
    }


}
