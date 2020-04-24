package dev.collegue.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.collegue.entite.Collegue;

/**
 * @author Antoine Renou
 *
 */
@Repository
public interface CollegueRepository extends JpaRepository<Collegue, Integer> {

	List<Collegue> findByNom(String nom);

	Optional<Collegue> getOneByMatricule(String matricule);

}
