package dev.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.NoteDeFrais;
import dev.domain.NoteDeFraisCumul;
import dev.repository.CollegueRepo;
import dev.repository.MissionRepo;
import dev.repository.NoteDeFraisCumulRepo;
import dev.repository.NoteDeFraisRepo;

@Service
public class NoteDeFraisService {
	
	@Autowired
	NoteDeFraisCumulRepo NoteDeFraisCumulRepo;
	
	@Autowired
	NoteDeFraisRepo NoteDeFraisRepo;
	
	
	@Autowired
	CollegueRepo collegueRepo;
	
	@Autowired
	MissionRepo missionRepo;

	public NoteDeFraisService() {}
	
	public Optional<NoteDeFrais> findById(Long long1){
		
		return NoteDeFraisRepo.findById(long1);
	
	}
	
	public Optional<NoteDeFraisCumul> findByMission(Long Id){
		
		return NoteDeFraisCumulRepo.findByMission(Id);

	
	}
	

}
