package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Mission;
import dev.domain.NoteDeFraisCumul;

public interface NoteDeFraisCumulRepo extends JpaRepository<NoteDeFraisCumul, Integer>{
	

		Optional<NoteDeFraisCumul> findByMission(Mission mission);
		
		Optional<NoteDeFraisCumul> findById(Long id);

		
	
}
