package dev.collegue.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public String[] getMatriculesByNom(@RequestParam String nom) {

		return collegueService.getMatriculesByNom(nom);
	}

}
