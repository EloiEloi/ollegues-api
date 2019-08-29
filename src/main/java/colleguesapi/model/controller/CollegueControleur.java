/**
 * 
 */
package colleguesapi.model.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import colleguesapi.model.Collegue;
import colleguesapi.model.service.CollegueService;

/**
 * @author Eloi
 *
 */
@RestController
@RequestMapping("/collegues")
public class CollegueControleur {

	CollegueService c = new CollegueService();

	@GetMapping
	@ResponseBody
	public List<Collegue> findCollegues(@RequestParam String nom) {

		return c.rechercherParNom(nom);

	}

}