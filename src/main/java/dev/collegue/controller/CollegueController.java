package dev.collegue.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.collegue.dto.CollegueDto;
import dev.collegue.entite.Collegue;
import dev.collegue.exception.CollegueNonTrouveException;
import dev.collegue.service.CollegueService;

/**
 * @author Antoine Renou
 *
 */
@RestController
@RequestMapping("collegues")
public class CollegueController {

	private CollegueService collegueService;

	/**
	 * Constructor
	 *
	 * @param collegueService
	 */
	public CollegueController(CollegueService collegueService) {
		super();
		this.collegueService = collegueService;
	}

	@GetMapping
	public List<String> getMatriculesByNom(@RequestParam String nom) {

		return collegueService.getMatriculesByNom(nom);
	}

	@GetMapping("{matricule}")
	public Collegue getCollegueByMatricule(@PathVariable String matricule) {

		return collegueService.getCollegueByMatricule(matricule);
	}

	@ExceptionHandler(CollegueNonTrouveException.class)
	public ResponseEntity<String> onCollegueNonTrouveException(CollegueNonTrouveException e) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	@PostMapping
	public Collegue postCollegue(@Valid @RequestBody CollegueDto newCollegue) {

		return collegueService.postCollegue(newCollegue);
	}

}
