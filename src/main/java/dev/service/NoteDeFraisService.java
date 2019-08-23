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
	
	public Optional<NoteDeFrais> findById(Long long1){
		
		return noteDeFraisRepo.findById(long1);
	
	}
	
	public Optional<NoteDeFrais> findByMission(Long Id){
		
		if (this.missionRepo.findById(Id) != null) {
			return noteDeFraisRepo.findByMission(Id);
		
		}else return null;
		
	}
	
	public Optional<NoteDeFrais> findByCollegue(Long Id){
		
		if (this.collegueRepo.findById(Id) != null) {
			return noteDeFraisRepo.findByCollegue(Id);
		
		}else return null;
		
	}
}
