package dev;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import dev.domain.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.repository.CollegueRepo;
import dev.repository.MissionRepo;
import dev.repository.NatureRepo;
import dev.repository.NoteDeFraisCumulRepo;
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
    private NoteDeFraisCumulRepo ndfCumulRepo;
    private dev.repository.LigneDeFrais ndfRepo;

    public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo, PasswordEncoder passwordEncoder,
            CollegueRepo collegueRepo, NatureRepo natureRepo, MissionRepo missionRepo, NoteDeFraisCumulRepo ndfCumulRepo,
            dev.repository.LigneDeFrais ligneDeFrais) {

        this.appVersion = appVersion;
        this.versionRepo = versionRepo;
        this.passwordEncoder = passwordEncoder;
        this.collegueRepo = collegueRepo;

        this.natureRepo = natureRepo;

        this.missionRepo = missionRepo;
        this.ndfCumulRepo = ndfCumulRepo;
        this.ndfRepo = ligneDeFrais;

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

        Collegue col4 = new Collegue();
        col4.setNom("User2");
        col4.setPrenom("DEV");
        col4.setEmail("user2@dev.fr");
        col4.setMotDePasse(passwordEncoder.encode("superpass"));
        col4.setRoles(Arrays.asList(new RoleCollegue(col2, Role.ROLE_UTILISATEUR)));
        this.collegueRepo.save(col4);

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
        n1.setTjm(750);
        n1.setPourcentagePrime(3.5);
        n1.setPlafondFrais(150);
        n1.setDepassPlafond(Choix.OUI);
        n1.setDebutValidite(LocalDate.now());
        n1.setFinValidite(null);
        this.natureRepo.save(n1);

        Nature n2 = new Nature();
        n2.setCode("Expertise technique");
        n2.setIsFacturee(Choix.OUI);
        n2.setHasPrime(Choix.OUI);
        n2.setTjm(1000);
        n2.setPourcentagePrime(4);
        n2.setPlafondFrais(150);
        n2.setDepassPlafond(Choix.OUI);
        n2.setDebutValidite(LocalDate.now());
        n2.setFinValidite(null);
        this.natureRepo.save(n2);

        Mission m1 = new Mission(LocalDate.now().minusDays(9), LocalDate.now().minusDays(7), n1, "Nantes", "Lyon", Transport.AVION, 100, col1);
        m1.setStatut(StatutMission.VALIDEE);
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

        Mission m20 = new Mission(LocalDate.of(2019, Month.JULY, 15), LocalDate.of(2019, Month.JULY, 19), n2, "Rennes", "Paris", Transport.AVION, 0,
                col1);
        m20.setStatut(StatutMission.VALIDEE);
        this.missionRepo.saveAndFlush(m20);

        dev.domain.LigneDeFrais ligneDeFrais = new dev.domain.LigneDeFrais();
        ligneDeFrais.setDate(LocalDate.of(2019, Month.AUGUST, 15));
        ligneDeFrais.setMontant(78.35);
        ligneDeFrais.setNature(NdfNature.AVION);

        this.ndfRepo.save(ligneDeFrais);

        dev.domain.LigneDeFrais ligneDeFrais2 = new dev.domain.LigneDeFrais();
        ligneDeFrais2.setDate(LocalDate.of(2018, Month.MAY, 20));
        ligneDeFrais2.setMontant(25.20);
        ligneDeFrais2.setNature(NdfNature.TRAIN);

        this.ndfRepo.save(ligneDeFrais2);

        NoteDeFraisCumul ndfCumul1 = new NoteDeFraisCumul();
        ndfCumul1.setMission(m3);
        ndfCumul1.addNotesDeFrais(ligneDeFrais);

        this.ndfCumulRepo.save(ndfCumul1);

        NoteDeFraisCumul ndfCumul2 = new NoteDeFraisCumul();
        ndfCumul2.setMission(m1);
        ndfCumul2.addNotesDeFrais(ligneDeFrais2);
        this.ndfCumulRepo.save(ndfCumul2);

        // à améliorer

        ligneDeFrais.setNdfCumul(ndfCumul2);
        this.ndfRepo.save(ligneDeFrais);
        ligneDeFrais2.setNdfCumul(ndfCumul1);
        this.ndfRepo.save(ligneDeFrais2);
        m3.setNdfCumul(ndfCumul1);
        this.missionRepo.save(m3);
        m1.setNdfCumul(ndfCumul2);
        this.missionRepo.save(m1);

        dev.domain.LigneDeFrais ligneDeFrais3 = new dev.domain.LigneDeFrais(LocalDate.of(2018, Month.MARCH, 20), 25.20, NdfNature.HOTEL, ndfCumul2, col2);
        this.ndfRepo.save(ligneDeFrais3);

        dev.domain.LigneDeFrais ligneDeFrais4 = new dev.domain.LigneDeFrais(LocalDate.of(2018, Month.JUNE, 25), 34.51, NdfNature.CARBURANT, ndfCumul1, col3);
        this.ndfRepo.save(ligneDeFrais4);

        dev.domain.LigneDeFrais ligneDeFrais5 = new dev.domain.LigneDeFrais(LocalDate.of(2018, Month.APRIL, 11), 34.51, NdfNature.DEJEUNER, ndfCumul1, col3);
        this.ndfRepo.save(ligneDeFrais5);

    }

}
