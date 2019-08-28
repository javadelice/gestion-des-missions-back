package dev.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.Collegue;
import dev.domain.NdfNature;
import dev.domain.NoteDeFrais;
import dev.domain.NoteDeFraisCumul;
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

	public NoteDeFraisService() {
	}

	public Optional<NoteDeFrais> findById(Long id) {

		return noteDeFraisRepo.findById(id);

	}

	public List<NoteDeFrais> findByNdfCumulId(Long id) {

		return noteDeFraisRepo.findAll().stream().filter(ndf -> ndf.getNdfCumul().getId() == id)
				.collect(Collectors.toList());

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
