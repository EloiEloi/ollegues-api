/**
 * 
 */
package colleguesapi.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import colleguesapi.model.Collegue;
import colleguesapi.model.exception.CollegueInvalideException;
import colleguesapi.model.exception.CollegueNonTrouveException;
import colleguesapi.model.utils.CollegueValidator;
import colleguesapi.repository.CollegueRepository;

/**
 * @author Eloi
 *
 */
@Service
public class CollegueService {

	@Autowired
	private CollegueRepository collegueRepository;

	public List<Collegue> rechercherParNom(String nomRecherche) throws CollegueNonTrouveException {

		List<Collegue> colleguesTrouves;

		if (nomRecherche != "") {
			colleguesTrouves = collegueRepository.findAll();
		} else {
			throw new CollegueNonTrouveException("Le nom ne peut être vide");
		}

		if (colleguesTrouves != null) {
			return colleguesTrouves;
		} else {
			throw new CollegueNonTrouveException("Le nom n'existe pas");
		}

	}

	public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException {

		Collegue colleguesTrouve;

		// colleguesTrouve =
		// collegueRepository.findByMatricule(matriculeRecherche).orElseThrow(() -> new
		// CollegueNonTrouveException("Le matricule ne
		// peut être vide"));

		if (matriculeRecherche != "") {
			colleguesTrouve = collegueRepository.findByMatricule(matriculeRecherche);
		} else {
			throw new CollegueNonTrouveException("Le matricule ne peut être vide");
		}

		if (colleguesTrouve != null) {
			return colleguesTrouve;
		} else {
			throw new CollegueNonTrouveException("Le matricule n'existe pas");
		}

	}

	//
	// public List<Collegue> rechercherTous() {
	//
	// return new ArrayList<>(data.values());
	// }

	@Transactional
	public Collegue ajouterUnCollegue(String nom, String prenom, String email, String photo)
			throws CollegueInvalideException {

		Collegue collegueAAjouter = new Collegue();
		collegueAAjouter.setEmail(email);
		collegueAAjouter.setNom(nom);
		collegueAAjouter.setPrenom(prenom);
		collegueAAjouter.setPhotoUrl(photo);

		if (!CollegueValidator.validerAll(collegueAAjouter)) {
			throw new CollegueInvalideException("impossible de creer le collegue");
		}

		// générer un matricule pour ce collègue
		String matricule = UUID.randomUUID().toString();
		collegueAAjouter.setMatricule(matricule);

		// Sauvegarder le collègue
		collegueRepository.save(collegueAAjouter);
		// data.put(collegueAAjouter.getMatricule(), collegueAAjouter);

		return collegueAAjouter;

	}

	public Collegue modifierEmail(String matricule, String email) throws CollegueNonTrouveException {

		Collegue collegueEmailAModifier = rechercherParMatricule(matricule);

		if (!CollegueValidator.validerEmail(collegueEmailAModifier)) {
			throw new CollegueNonTrouveException("Le matricule n'existe pas");
		}
		collegueEmailAModifier.setEmail(email);
		// data.put(collegueEmailAModifier.getMatricule(), collegueEmailAModifier);

		return collegueEmailAModifier;
	}

	public Collegue modifierPhotoUrl(String matricule, String photoUrl) throws CollegueNonTrouveException {

		Collegue colleguePhotoAModifier = rechercherParMatricule(matricule);

		if (!CollegueValidator.validerEmail(colleguePhotoAModifier)) {
			throw new CollegueNonTrouveException("Le matricule n'existe pas");
		}

		colleguePhotoAModifier.setPhotoUrl(photoUrl);

		// data.put(colleguePhotoAModifier.getMatricule(), colleguePhotoAModifier);

		return colleguePhotoAModifier;
	}

}
