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

		if (newCollegue.getEmail() == null) {
			email.append(newCollegue.getNom().toLowerCase()).append(".").append(newCollegue.getPrenoms().toLowerCase())
					.append("@email.com");
		} else {
			email.append(newCollegue.getEmail());
		}

		Collegue collegue = new Collegue(UUID.randomUUID().toString(), newCollegue.getNom(), newCollegue.getPrenoms(),
				email.toString(), newCollegue.getDateDeNaissance(), newCollegue.getPhotoUrl());

		collegueRepository.save(collegue);

		return collegue;
	}

	@Transactional
	public Collegue updateCollegue(CollegueDto updateCollegue) {

		Optional<Collegue> searchCollegue = collegueRepository.getOneByMatricule(updateCollegue.getMatricule());

		if (searchCollegue.isPresent()) {

			Collegue colleguePresent = searchCollegue.get();

			if (updateCollegue.getNom() != null)
				colleguePresent.setNom(updateCollegue.getNom());

			if (updateCollegue.getPrenoms() != null)
				colleguePresent.setPrenoms(updateCollegue.getPrenoms());

			if (updateCollegue.getDateDeNaissance() != null)
				colleguePresent.setDateDeNaissance(updateCollegue.getDateDeNaissance());

			if (updateCollegue.getEmail() != null)
				colleguePresent.setEmail(updateCollegue.getEmail());

			if (updateCollegue.getPhotoUrl() != null)
				colleguePresent.setPhotoUrl(updateCollegue.getPhotoUrl());

			collegueRepository.save(colleguePresent);

			return colleguePresent;

		} else {
			throw new CollegueNonTrouveException("Modification impossible, collegue non trouvé");
		}

	}

}
