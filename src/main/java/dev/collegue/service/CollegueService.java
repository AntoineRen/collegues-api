package dev.collegue.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.collegue.entite.Collegue;
import dev.collegue.exception.CollegueNonTrouveException;
import dev.collegue.repository.CollegueRepository;

/**
 * @author Antoine Renou
 *
 */
@Service
public class CollegueService {

	private CollegueRepository collegueRepository;

	/**
	 * Constructor
	 *
	 * @param collegueRepository
	 */
	public CollegueService(CollegueRepository collegueRepository) {
		super();
		this.collegueRepository = collegueRepository;
	}

	public List<String> getMatriculesByNom(String nom) {

		return collegueRepository.findByNom(nom).stream().map(c -> c.getMatricule()).collect(Collectors.toList());
	}

	public Collegue getCollegueByMatricule(String matricule) {

		Optional<Collegue> collegue = collegueRepository.getOneByMatricule(matricule);

		if (collegue.isPresent()) {
			return collegue.get();
		} else {
			throw new CollegueNonTrouveException("Collegue non trouvé");
		}
	}

}
