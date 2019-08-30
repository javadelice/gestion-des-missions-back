package dev.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import dev.domain.NoteDeFraisCumul;

import dev.repository.NoteDeFraisCumulRepo;

@Service
public class NoteDeFraisCumulService {
	
	@Autowired
	NoteDeFraisCumulRepo noteDeFraisCumulRepo;


	
	public Optional<NoteDeFraisCumul> findById(Long id){
		
		return noteDeFraisCumulRepo.findById(id);
	
	}
	
	public NoteDeFraisCumul findByMission(Long idMission){
		
		return this.noteDeFraisCumulRepo.findAll().stream().filter(ndf -> ndf.getMission().getId() == idMission).collect(Collectors.toList()).get(0);
//		
//		return noteDeFraisCumulRepo.findByMission(this.missionRepo.findById(id).get());

	
	}
	
}
