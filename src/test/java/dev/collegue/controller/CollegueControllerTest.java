package dev.collegue.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import dev.collegue.entite.Collegue;
import dev.collegue.service.CollegueService;

@WebMvcTest(CollegueController.class)
class CollegueControllerTest {

	@Autowired
	MockMvc mockMvc;

	String baseUrl = "/collegues/";

	@MockBean
	CollegueService collegueService;

	@Test
	void testGetMatriculesByNom() throws Exception {

		String paramNom = "test";
		String url = baseUrl + "?nom=" + paramNom;

		when(this.collegueService.getMatriculesByNom(paramNom))
				.thenReturn(Arrays.asList("testMatricule1", "testMatricule2"));

		mockMvc.perform(MockMvcRequestBuilders.get(url))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("testMatricule1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("testMatricule2"));
	}

	@Test
	void testGetCollegueByMatricule() throws Exception {

		String matricule = "test";
		String url = baseUrl + matricule;

		when(this.collegueService.getCollegueByMatricule(matricule))
				.thenReturn(new Collegue("matricule", null, null, null, null, null));

		mockMvc.perform(MockMvcRequestBuilders.get(url))
				.andExpect(MockMvcResultMatchers.jsonPath("$.matricule").value("matricule"));
	}

}
