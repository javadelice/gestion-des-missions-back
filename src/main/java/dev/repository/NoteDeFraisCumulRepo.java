package dev.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Mission;
import dev.domain.NoteDeFrais;
import dev.domain.NoteDeFraisCumul;

public interface NoteDeFraisCumulRepo extends JpaRepository<NoteDeFraisCumul, Integer>{
	

		Optional<NoteDeFraisCumul> findByMission(Mission mission);
		
		Optional<NoteDeFraisCumul> findById(Long id);

		
	
}
