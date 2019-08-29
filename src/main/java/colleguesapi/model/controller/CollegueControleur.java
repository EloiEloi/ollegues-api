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
@RequestMapping("/collegues")
public class CollegueControleur {

	@Autowired
	CollegueService c;

	@RequestMapping(path = "/nom/{nom}", method = RequestMethod.GET)
	public List<Collegue> findColleguesByName(@PathVariable String nom) throws CollegueNonTrouveException {
		return c.rechercherParNom(nom);
	}

	@RequestMapping(path = "/matricule/{matricule}", method = RequestMethod.GET)
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

	@PostMapping("/creer")
	public Collegue creerCollegue(@RequestParam String nom, @RequestParam String prenom, @RequestParam String email, @RequestParam String photo) throws CollegueInvalideException {
		Collegue collegueAAjouter = new Collegue();

		collegueAAjouter.setEmail(email);
		collegueAAjouter.setNom(nom);
		collegueAAjouter.setPrenom(prenom);
		collegueAAjouter.setPhotoUrl(photo);

		return c.ajouterUnCollegue(collegueAAjouter);
	}

	@ExceptionHandler(CollegueInvalideException.class)
	public ResponseEntity<String> handleConflict(CollegueInvalideException exception) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		String bodyOfResponse = exception.getMessage();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyOfResponse);
	}
}
