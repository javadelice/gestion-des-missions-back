package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Mission;

public interface MissionRepo extends JpaRepository<Mission, Long>{

	Optional<Mission> findById(Long Id);
	
}
