package dev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.exception.LigneDeFraisInvalideException;
import dev.exception.NoteDeFraisNonTrouveeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.repository.CollegueRepo;
import dev.repository.MissionRepo;
import dev.repository.NoteDeFraisCumulRepo;

@Service
public class LigneDeFrais {

    @Autowired
    NoteDeFraisCumulRepo noteDeFraisCumulRepo;

    @Autowired
    dev.repository.LigneDeFrais ligneDeFrais;

    @Autowired
    CollegueRepo collegueRepo;

    @Autowired
    MissionRepo missionRepo;

    public LigneDeFrais() {
    }

    public Optional<dev.domain.LigneDeFrais> findById(Long id) {

        return ligneDeFrais.findById(id);

    }

    public List<dev.domain.LigneDeFrais> findByNdfCumulId(Long id) {
        List<dev.domain.LigneDeFrais> list = new ArrayList<>();
        try {
            list = ligneDeFrais.findAll().stream().filter(ndf -> ndf.getNdfCumul().getId() == id)
                    .collect(Collectors.toList());
//
//		return noteDeFraisCumulRepo.findByMission(this.missionRepo.findById(id).get());
        } catch (NullPointerException e) {
            throw new NoteDeFraisNonTrouveeException("Note de frais non trouvée.");
        } finally {
        }

        return list;

    }

    public dev.domain.LigneDeFrais verifyLigneDeFrais(dev.domain.LigneDeFrais ldf) {
        //si la ligne de frais existe
        if (ldf != null) {

            //Verification de l'id de note de frais
            try {
                ligneDeFrais.findAll().stream().filter(ndf -> ndf.getNdfCumul().getId() == ldf.getNdfCumul().getId());

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
        }
        return ldf;
    }

//	public List<LigneDeFrais> findByMission(Long id) {
//
//		Optional<NoteDeFraisCumul> ndfC = noteDeFraisCumulRepo.findByMission(id);
//
//		if (ndfC.isPresent()) {
//
//			return ligneDeFrais.findAll().stream().filter(ndf -> ndf.getNdfCumul().getId() == id)
//					.collect(Collectors.toList());
//
//		} else {
//			return null;
//
//		}
//	}
/*
	public Optional<LigneDeFrais[]> findByCollegue(Collegue col) {

		return null;// LigneDeFrais.fin(Id);

	}
*/

}
