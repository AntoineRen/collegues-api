package dev.collegue.service;

import java.util.List;
import java.util.stream.Collectors;

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

	public List<String> getMatriculesByNom(String nom) {

		return collegueRepository.findByNom(nom).stream().map(c -> c.getMatricule()).collect(Collectors.toList());
	}

}
