package dev.repository;


import java.util.List;
import java.util.Optional;

import dev.domain.StatutMission;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Mission;


public interface MissionRepo extends JpaRepository<Mission, Long>{

	Optional<Mission> findById(Long Id);
	List<Mission> findByStatut(StatutMission statutMission);
	List<Mission> findByPrimeACalculer(Boolean primeACalculer);

}
