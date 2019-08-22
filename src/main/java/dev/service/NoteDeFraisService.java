package dev.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.NoteDeFrais;
import dev.repository.CollegueRepo;
import dev.repository.MissionRepo;
import dev.repository.NoteDeFraisRepo;

@Service
public class NoteDeFraisService {
	
	@Autowired
	NoteDeFraisRepo noteDeFraisRepo;
	
	@Autowired
	CollegueRepo collegueRepo;
	
	@Autowired
	MissionRepo missionRepo;

	public NoteDeFraisService() {}
	
	public Optional<NoteDeFrais> findById(Long id){
		
		return noteDeFraisRepo.findById(id);
	
	}
	
	public Optional<NoteDeFrais[]> findByMission(Long id){
		
			return noteDeFraisRepo.findByMission(id);

		
	}
	
	public Optional<NoteDeFrais> findByCollegue(Long id){

			return noteDeFraisRepo.findByCollegue(id);

		
	}
}
