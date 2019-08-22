package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.NoteDeFrais;

public interface NoteDeFraisRepo extends JpaRepository<NoteDeFrais, Integer>{
	
	Optional<NoteDeFrais> findByMission(Long id);
	Optional<NoteDeFrais> findByCollegue(Long id);
	Optional<NoteDeFrais> findById(Long id);
	
}
