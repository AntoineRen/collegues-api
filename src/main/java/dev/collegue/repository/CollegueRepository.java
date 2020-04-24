package dev.collegue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.collegue.entite.Collegue;

/**
 * @author Antoine Renou
 *
 */
public interface CollegueRepository extends JpaRepository<Collegue, Integer> {

	List<Collegue> findByNom(String nom);

}
