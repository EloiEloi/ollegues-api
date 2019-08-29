/**
 * 
 */
package colleguesapi.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import colleguesapi.model.Collegue;
import colleguesapi.model.exception.CollegueInvalideException;
import colleguesapi.model.exception.CollegueNonTrouveException;
import colleguesapi.model.service.CollegueService;

/**
 * @author Eloi
 *
 */
@RestController

public class CollegueControleur {

	@Autowired
	CollegueService c;

	@RequestMapping(path = "/collegues", method = RequestMethod.GET)
	public List<Collegue> findColleguesAll() {
		return c.rechercherTous();
	}

	@RequestMapping(path = "/collegues/nom/{nom}", method = RequestMethod.GET)
	public List<Collegue> findColleguesByName(@PathVariable String nom) throws CollegueNonTrouveException {
		return c.rechercherParNom(nom);
	}

	@RequestMapping(path = "/collegues/matricule/{matricule}", method = RequestMethod.GET)
	public Collegue findColleguesByMatricule(@PathVariable String matricule) throws CollegueNonTrouveException {
		return c.rechercherParMatricule(matricule);
	}

	@ExceptionHandler(CollegueNonTrouveException.class)
	public ResponseEntity<String> handleConflict(CollegueNonTrouveException exception) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		String bodyOfResponse = exception.getMessage();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyOfResponse);
	}

	@PostMapping("/collegues/creer")
	public Collegue creerCollegue(@RequestParam String nom, @RequestParam String prenom, @RequestParam String email, @RequestParam String photo) throws CollegueInvalideException {
		return c.ajouterUnCollegue(nom, prenom, email, photo);
	}

	@ExceptionHandler(CollegueInvalideException.class)
	public ResponseEntity<String> handleConflict(CollegueInvalideException exception) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		String bodyOfResponse = exception.getMessage();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyOfResponse);
	}

	@PostMapping("/collegues/modifier")
	public Collegue modifierEmail(@RequestParam String matricule, @RequestParam String email) throws CollegueNonTrouveException {
		return c.modifierEmail(matricule, email);
	}
}
