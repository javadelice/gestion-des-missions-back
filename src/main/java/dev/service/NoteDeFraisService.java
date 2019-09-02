package dev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.domain.NdfDTO;
import dev.domain.NoteDeFraisCumul;
import dev.exception.LigneDeFraisInvalideException;
import dev.exception.NoteDeFraisNonTrouveeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.NoteDeFrais;
import dev.repository.CollegueRepo;
import dev.repository.MissionRepo;
import dev.repository.NoteDeFraisCumulRepo;
import dev.repository.NoteDeFraisRepo;

@Service
public class NoteDeFraisService {

    @Autowired
    NoteDeFraisCumulRepo noteDeFraisCumulRepo;

    @Autowired
    NoteDeFraisRepo noteDeFraisRepo;

    @Autowired
    CollegueRepo collegueRepo;

    @Autowired
    MissionRepo missionRepo;

    @Autowired
    NoteDeFraisCumulService noteDeFraisCumulService;

    public NoteDeFraisService() {
    }

    public Optional<NoteDeFrais> findById(Long id) {

        return noteDeFraisRepo.findById(id);

    }

    public List<NoteDeFrais> findByNdfCumulId(Long id) {
        List<NoteDeFrais> list = new ArrayList<>();
        try {
            list = noteDeFraisRepo.findAll().stream().filter(ndf -> ndf.getNdfCumul().getId() == id)
                    .collect(Collectors.toList());
//
//		return noteDeFraisCumulRepo.findByMission(this.missionRepo.findById(id).get());
        } catch (NullPointerException e) {
            throw new NoteDeFraisNonTrouveeException("Note de frais non trouvée.");
        } finally {
        }

        return list;

    }

    public NoteDeFrais verifyLigneDeFrais(NoteDeFrais ldf) {
        //si la ligne de frais existe
        if (ldf == null) {
            throw new NoteDeFraisNonTrouveeException("La note de frais associée n'existe pas dans la base.");
        }

        //Verification de l'id de note de frais
        try {
            noteDeFraisRepo.findAll().stream().filter(ndf -> ndf.getNdfCumul().getId() == ldf.getNdfCumul().getId());

        } catch (NullPointerException e) {
            throw new NoteDeFraisNonTrouveeException("La note de frais associée n'existe pas dans la base.");
        } finally {
        }

        //Verification d'un montant strictment positif
        if (ldf.getMontant() <= 0) {
            throw new LigneDeFraisInvalideException("Le montant doit être strictement supérieur à 0.");
        }

        //Vérification de la date : doit être comprise dans la période de la mission
        if (ldf.getDate().isAfter(ldf.getNdfCumul().getMission().getEndDate())
                || ldf.getDate().isBefore(ldf.getNdfCumul().getMission().getStartDate())) {
            throw new LigneDeFraisInvalideException("La date doit être comprise dans la période de la mission");
        }

        NoteDeFraisCumul noteDeFraisCumul = noteDeFraisCumulRepo.findById(ldf.getNdfCumul().getId()).get();

        if (noteDeFraisCumul.getNotesDeFrais().stream().anyMatch(noteDeFraisATester ->
                noteDeFraisATester.getDate().equals(ldf.getDate())
                && noteDeFraisATester.getNature() == ldf.getNature())) {
            throw new LigneDeFraisInvalideException("Une ligne identique existe déjà.");
        }
        ldf.getNdfCumul().getMission().setPrimeACalculer(true);
        missionRepo.save(ldf.getNdfCumul().getMission());
        return ldf;
    }

    public NoteDeFrais createLigneDeFrais(NdfDTO ndfDTO){
        NoteDeFraisCumul noteDeFraisCumul = this.noteDeFraisCumulService.findByMission(ndfDTO.getIdMission());
        if (ndfDTO.getDate().isBefore(noteDeFraisCumul.getMission().getStartDate())
                || ndfDTO.getDate().isAfter(noteDeFraisCumul.getMission().getEndDate())) {
            System.out.println(ndfDTO.getDate().toString() + noteDeFraisCumul.getMission().getEndDate() + noteDeFraisCumul.getMission().getStartDate());
            throw new LigneDeFraisInvalideException("Ligne de frais invalide ! La date doit être comprise dans les dates de la mission.");
        }
        if (ndfDTO.getMontant() <= 0) {
            throw new LigneDeFraisInvalideException("Ligne de frais invalide ! Le montant doit être supérieur à 0");
        }

        NoteDeFrais noteDeFrais = new NoteDeFrais(ndfDTO.getDate(), ndfDTO.getMontant(), ndfDTO.getNature(), noteDeFraisCumul);

        if (noteDeFrais.getNdfCumul().getNotesDeFrais().stream().anyMatch(noteDeFraisATester ->
                        noteDeFraisATester.getDate().equals(noteDeFrais.getDate())
                    && noteDeFraisATester.getNature() == noteDeFrais.getNature()
                )) {
            throw new LigneDeFraisInvalideException("Une ligne identique existe déjà.");
        }

        noteDeFraisCumul.addNotesDeFrais(noteDeFrais);
        noteDeFraisCumulRepo.save(noteDeFraisCumul);

        noteDeFrais.getNdfCumul().getMission().setPrimeACalculer(true);
        missionRepo.save(noteDeFrais.getNdfCumul().getMission());

        noteDeFraisRepo.save(noteDeFrais);
        return noteDeFrais;
    }
//	public List<NoteDeFrais> findByMission(Long id) {
//
//		Optional<NoteDeFraisCumul> ndfC = noteDeFraisCumulRepo.findByMission(id);
//
//		if (ndfC.isPresent()) {
//
//			return noteDeFraisRepo.findAll().stream().filter(ndf -> ndf.getNdfCumul().getId() == id)
//					.collect(Collectors.toList());
//
//		} else {
//			return null;
//
//		}
//	}
/*
	public Optional<NoteDeFrais[]> findByCollegue(Collegue col) {

		return null;// NoteDeFraisRepo.fin(Id);

	}
*/

}
