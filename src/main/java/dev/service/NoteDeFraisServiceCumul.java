package dev.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import dev.domain.NoteDeFrais;
import dev.domain.NoteDeFraisCumul;
import dev.repository.NoteDeFraisCumulRepo;

public class NoteDeFraisServiceCumul {
	
	@Autowired
	NoteDeFraisCumulRepo noteDeFraisCumulRepo;

	
	public Optional<NoteDeFraisCumul> findById(Long long1){
		
		return noteDeFraisCumulRepo.findById(long1);
	
	}
	
	public Optional<NoteDeFraisCumul> findByMission(Long Id){
		
		return noteDeFraisCumulRepo.findByMission(Id);

	
	}
	
}
