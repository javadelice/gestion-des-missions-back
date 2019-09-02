package dev.repository;

import java.util.Optional;

import dev.domain.LigneDeFrais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneDeFrais extends JpaRepository<dev.domain.LigneDeFrais, Long>{


	Optional<dev.domain.LigneDeFrais> findById(Long id);
}
