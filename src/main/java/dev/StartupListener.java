package dev;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.domain.Choix;
import dev.domain.Collegue;
import dev.domain.Departement;
import dev.domain.Mission;
import dev.domain.Nature;
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.domain.StatutMission;
import dev.domain.Transport;
import dev.domain.Version;
import dev.repository.CollegueRepo;
import dev.repository.MissionRepo;
import dev.repository.NatureRepo;
import dev.repository.VersionRepo;

/**
 * Code de démarrage de l'application. Insertion de jeux de données.
 */
@Component
public class StartupListener {

    private String appVersion;
    private VersionRepo versionRepo;
    private PasswordEncoder passwordEncoder;
    private CollegueRepo collegueRepo;
    private NatureRepo natureRepo;
    private MissionRepo missionRepo;

    public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo, PasswordEncoder passwordEncoder,
            CollegueRepo collegueRepo, NatureRepo natureRepo, MissionRepo missionRepo) {
        this.appVersion = appVersion;
        this.versionRepo = versionRepo;
        this.passwordEncoder = passwordEncoder;
        this.collegueRepo = collegueRepo;
        this.natureRepo = natureRepo;
        this.missionRepo = missionRepo;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onStart() {
        this.versionRepo.save(new Version(appVersion));

        // Création de trois utilisateurs

        Collegue col1 = new Collegue();
        col1.setNom("Admin");
        col1.setPrenom("DEV");
        col1.setEmail("admin@dev.fr");
        col1.setMotDePasse(passwordEncoder.encode("superpass"));
        col1.setRoles(Arrays.asList(new RoleCollegue(col1, Role.ROLE_ADMINISTRATEUR), new RoleCollegue(col1, Role.ROLE_UTILISATEUR)));
        col1.setDepartement(Departement.D1);
        this.collegueRepo.save(col1);

        Collegue col2 = new Collegue();
        col2.setNom("User");
        col2.setPrenom("DEV");
        col2.setEmail("user@dev.fr");
        col2.setMotDePasse(passwordEncoder.encode("superpass"));
        col2.setRoles(Arrays.asList(new RoleCollegue(col2, Role.ROLE_UTILISATEUR)));
        col2.setDepartement(Departement.D2);
        this.collegueRepo.save(col2);

        Collegue col3 = new Collegue();
        col3.setNom("Manager");
        col3.setPrenom("DEV");
        col3.setEmail("manager@dev.fr");
        col3.setMotDePasse(passwordEncoder.encode("superpass"));
        col3.setRoles(Arrays.asList(new RoleCollegue(col3, Role.ROLE_MANAGER), new RoleCollegue(col3, Role.ROLE_UTILISATEUR)));
        col3.setDepartement(Departement.D2);
        this.collegueRepo.save(col3);

        Nature n1 = new Nature();
        n1.setCode("Conseil");
        n1.setIsFacturee(Choix.OUI);
        n1.setHasPrime(Choix.OUI);
        n1.setTJM(750);
        n1.setPourcentagePrime(3.5);
        n1.setPlafondFrais(150);
        n1.setDepassPlafond(Choix.OUI);
        n1.setDebutValidite(LocalDate.now());
        this.natureRepo.save(n1);

        Nature n2 = new Nature();
        n2.setCode("Expertise technique");
        n2.setIsFacturee(Choix.OUI);
        n2.setHasPrime(Choix.OUI);
        n2.setTJM(1000);
        n2.setPourcentagePrime(4);
        n2.setPlafondFrais(150);
        n2.setDepassPlafond(Choix.OUI);
        n2.setDebutValidite(LocalDate.now());
        this.natureRepo.save(n2);

        Mission m1 = new Mission(LocalDate.now(), LocalDate.now().plusDays(7), n1, "Nantes", "Lyon", Transport.AVION, 100, col1);
        this.missionRepo.saveAndFlush(m1);

        Mission m2 = new Mission(LocalDate.now(), LocalDate.now().plusDays(10), n2, "Nantes", "Rennes", Transport.COVOITURAGE, 150, col2);
        this.missionRepo.saveAndFlush(m2);

        Mission m3 = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(13), n2, "Nantes", "Rennes", Transport.COVOITURAGE, 150,
                col1);
        m3.setStatut(StatutMission.VALIDEE);
        this.missionRepo.saveAndFlush(m3);

        Mission m4 = new Mission(LocalDate.now().plusDays(14), LocalDate.now().plusDays(18), n1, "Nantes", "Bordeaux", Transport.TRAIN, 150,
                col1);
        m4.setStatut(StatutMission.EN_ATTENTE_VALIDATION);
        this.missionRepo.saveAndFlush(m4);

        Mission m5 = new Mission(LocalDate.now().plusDays(14), LocalDate.now().plusDays(18), n1, "Nantes", "Lille", Transport.VOITURE_DE_SERVICE, 150,
                col2);
        m5.setStatut(StatutMission.EN_ATTENTE_VALIDATION);
        this.missionRepo.saveAndFlush(m5);

        Mission m6 = new Mission(LocalDate.now().plusDays(20), LocalDate.now().plusDays(21), n1, "Nantes", "Vannes", Transport.AVION, 150,
                col2);
        m6.setStatut(StatutMission.EN_ATTENTE_VALIDATION);
        this.missionRepo.saveAndFlush(m6);

        Mission m7 = new Mission(LocalDate.now().plusDays(14), LocalDate.now().plusDays(18), n1, "Nantes", "Lille", Transport.TRAIN, 150,
                col3);
        m7.setStatut(StatutMission.EN_ATTENTE_VALIDATION);
        this.missionRepo.saveAndFlush(m7);

    }

}
