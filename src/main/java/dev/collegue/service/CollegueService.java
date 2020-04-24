package dev.collegue.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import dev.collegue.dto.CollegueDto;
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

	@Transactional
	public Collegue postCollegue(@Valid CollegueDto newCollegue) {

		StringBuilder email = new StringBuilder();
		email.append(newCollegue.getNom().toLowerCase()).append(".").append(newCollegue.getPrenoms().toLowerCase())
				.append("@email.com");

		Collegue collegue = new Collegue(UUID.randomUUID().toString(), newCollegue.getNom(), newCollegue.getPrenoms(),
				email.toString(), newCollegue.getDateDeNaissance(), newCollegue.getPhotoUrl());

		collegueRepository.save(collegue);

		return collegue;
	}

}
