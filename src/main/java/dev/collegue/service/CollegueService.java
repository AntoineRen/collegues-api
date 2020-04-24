package dev.collegue.service;

import org.springframework.stereotype.Service;

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

	public String[] getMatriculesByNom(String nom) {

		return (String[]) collegueRepository.findByNom(nom).stream().map(c -> c.getMatricule()).toArray();
	}

}
