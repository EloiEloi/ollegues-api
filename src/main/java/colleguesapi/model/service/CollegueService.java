/**
 * 
 */
package colleguesapi.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import colleguesapi.model.Collegue;
import colleguesapi.model.exception.CollegueInvalideException;
import colleguesapi.model.exception.CollegueNonTrouveException;
import colleguesapi.model.utils.CollegueValidator;
import colleguesapi.model.utils.DataUtils;

/**
 * @author Eloi
 *
 */
@Service
public class CollegueService {

	private Map<String, Collegue> data = new HashMap<>();

	public CollegueService() {
		data = DataUtils.initData();
	}

	public List<Collegue> rechercherParNom(String nomRecherche) throws CollegueNonTrouveException {

		List<Collegue> colleguesTrouves = new ArrayList<Collegue>();

		if (nomRecherche != "") {
			colleguesTrouves = this.data.values().stream().filter(collegue -> collegue.getNom().equals(nomRecherche)).collect(Collectors.toList());
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

		if (matriculeRecherche != "") {
			colleguesTrouve = data.get(matriculeRecherche);
		} else {
			throw new CollegueNonTrouveException("Le matricule ne peut être vide");
		}

		if (colleguesTrouve != null) {
			return colleguesTrouve;
		} else {
			throw new CollegueNonTrouveException("Le matricule n'existe pas");
		}

	}

	public List<Collegue> rechercherTous() {

		return new ArrayList<>(data.values());

	}

	public Collegue ajouterUnCollegue(String nom, String prenom, String email, String photo) throws CollegueInvalideException {

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
		data.put(collegueAAjouter.getMatricule(), collegueAAjouter);

		return collegueAAjouter;

	}

	public Collegue modifierEmail(String matricule, String email) throws CollegueNonTrouveException {

		Collegue collegueEmailAModifier = rechercherParMatricule(matricule);

		if (!CollegueValidator.validerEmail(collegueEmailAModifier)) {
			throw new CollegueNonTrouveException("Le matricule n'existe pas");
		}
		collegueEmailAModifier.setEmail(email);
		data.put(collegueEmailAModifier.getMatricule(), collegueEmailAModifier);

		return collegueEmailAModifier;
	}

	public Collegue modifierPhotoUrl(String matricule, String photoUrl) throws CollegueNonTrouveException {

		Collegue colleguePhotoAModifier = rechercherParMatricule(matricule);

		if (!CollegueValidator.validerEmail(colleguePhotoAModifier)) {
			throw new CollegueNonTrouveException("Le matricule n'existe pas");
		}

		colleguePhotoAModifier.setPhotoUrl(photoUrl);

		data.put(colleguePhotoAModifier.getMatricule(), colleguePhotoAModifier);

		return colleguePhotoAModifier;
	}

}
