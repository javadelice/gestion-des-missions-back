package dev;

import dev.domain.Collegue;
import dev.domain.Mission;
import dev.domain.MissionNature;
import dev.domain.StatutMission;
import dev.domain.NdfNature;
import dev.domain.NoteDeFrais;
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.domain.Transport;
import dev.domain.Version;
import dev.repository.CollegueRepo;
import dev.repository.MissionRepo;
import dev.repository.NoteDeFraisRepo;
import dev.repository.VersionRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

/**
 * Code de démarrage de l'application.
 * Insertion de jeux de données.
 */
@Component
public class StartupListener {

    private String appVersion;
    private VersionRepo versionRepo;
    private PasswordEncoder passwordEncoder;
    private CollegueRepo collegueRepo;
    private MissionRepo missionRepo;
    private NoteDeFraisRepo ndfRepo;

    public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo, PasswordEncoder passwordEncoder, 
    		CollegueRepo collegueRepo, MissionRepo missionRepo, NoteDeFraisRepo ndfRepo) {
        this.appVersion = appVersion;
        this.versionRepo = versionRepo;
        this.passwordEncoder = passwordEncoder;
        this.collegueRepo = collegueRepo;
        this.missionRepo = missionRepo;
        this.ndfRepo = ndfRepo;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onStart() {
        this.versionRepo.save(new Version(appVersion));

        // Création de deux utilisateurs

        Collegue col1 = new Collegue();
        col1.setNom("Admin");
        col1.setPrenom("DEV");
        col1.setEmail("admin@dev.fr");
        col1.setMotDePasse(passwordEncoder.encode("superpass"));
        col1.setRoles(Arrays.asList(new RoleCollegue(col1, Role.ROLE_ADMINISTRATEUR), new RoleCollegue(col1, Role.ROLE_UTILISATEUR)));
        this.collegueRepo.save(col1);

        Collegue col2 = new Collegue();
        col2.setNom("User");
        col2.setPrenom("DEV");
        col2.setEmail("user@dev.fr");
        col2.setMotDePasse(passwordEncoder.encode("superpass"));
        col2.setRoles(Arrays.asList(new RoleCollegue(col2, Role.ROLE_UTILISATEUR)));
        this.collegueRepo.save(col2);
        
        
        Mission mission1= new Mission();
        mission1.setDateDebut(LocalDate.of(2018, Month.JANUARY, 02));
        mission1.setDateFin(LocalDate.of(2018, Month.JUNE, 30));
        mission1.setNature(MissionNature.CONSEIL);
        mission1.setStatut(StatutMission.VALIDEE);        
        mission1.setVilleDépart("Nantes");
        mission1.setVilleArrivée("Lyon");
        mission1.setTransport(Transport.TRAIN);
        this.missionRepo.save(mission1);
        
        Mission mission2= new Mission();
        mission2.setDateDebut(LocalDate.of(2018, Month.APRIL, 02));
        mission2.setDateFin(LocalDate.of(2019, Month.NOVEMBER, 30));
        mission2.setNature(MissionNature.EXPERTISE_TECHNIQUE);
        mission2.setStatut(StatutMission.EN_ATTENTE_VALIDATION);
        mission2.setVilleDépart("Paris");
        mission2.setVilleArrivée("Brest");
        mission2.setTransport(Transport.AVION);
        this.missionRepo.save(mission2);
        
        
        NoteDeFrais noteDeFrais = new NoteDeFrais();
        noteDeFrais.setCollegue(col1);
        noteDeFrais.setDate(LocalDate.of(2019, Month.AUGUST, 15));
        noteDeFrais.setMission(mission1);
        noteDeFrais.setMontant(78.35);
        noteDeFrais.setNature(NdfNature.TRAIN);
        this.ndfRepo.save(noteDeFrais);
        
        NoteDeFrais noteDeFrais2 = new NoteDeFrais();
        noteDeFrais2.setCollegue(col1);
        noteDeFrais2.setDate(LocalDate.of(2018, Month.MAY, 20));
        noteDeFrais2.setMission(mission2);
        noteDeFrais2.setMontant(25.20);
        noteDeFrais2.setNature(NdfNature.TRAIN);
        this.ndfRepo.save(noteDeFrais2);
        
    }

}
