package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.NoteDeFrais;

public interface NoteDeFraisRepo extends JpaRepository<NoteDeFrais, Integer>{

	Optional<NoteDeFrais> findById(Long id);
}
