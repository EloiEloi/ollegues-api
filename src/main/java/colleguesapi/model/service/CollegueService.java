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

/**
 * @author Eloi
 *
 */
@Service
public class CollegueService {

	private Map<String, Collegue> data = new HashMap<>();

	public CollegueService() {

		Collegue collegue1 = new Collegue();
		collegue1.setMatricule(UUID.randomUUID().toString());
		collegue1.setEmail("myemail@email.com");
		collegue1.setNom("papin");
		collegue1.setPrenom("Jean-Pierre");
		collegue1.setPhotoUrl("http://3.bp.blogspot.com/-Zq_KnsmFVvs/UDlIza0CsXI/AAAAAAAAbuc/upXcWKFI4-0/s1600/Jean-Pierre+PAPIN+Panini+Olympique+de+Marseille+1990.png");

		Collegue collegue2 = new Collegue();
		collegue2.setMatricule(UUID.randomUUID().toString());
		collegue2.setEmail("myemail@email.com");
		collegue2.setNom("waddle");
		collegue2.setPrenom("chris");
		collegue2.setPhotoUrl("http://www.lefond2lair.fr/wp-content/uploads/2016/02/article-2271866-174A53AC000005DC-594_964x729-600x400.jpg");

		Collegue collegue3 = new Collegue();
		collegue3.setMatricule(UUID.randomUUID().toString());
		collegue3.setEmail("myemail@email.com");
		collegue3.setNom("platini");
		collegue3.setPrenom("michel");
		collegue3.setPhotoUrl("https://www.hitech-sport.com/wp-content/uploads/2018/11/michel-platini.jpg");

		data.put(collegue1.getMatricule(), collegue1);
		data.put(collegue2.getMatricule(), collegue2);
		data.put(collegue3.getMatricule(), collegue3);

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

	public Collegue ajouterUnCollegue(Collegue collegueAAjouter) throws CollegueInvalideException {

		// Vérifier que le nom et les prenoms ont chacun au moins 2 caractères
		if (collegueAAjouter.getNom().length() < 2 && collegueAAjouter.getNom().length() < 2) {
			throw new CollegueInvalideException("Le nom et le prenom doivent comporter au moins 2 caracteres");
		}

		// Vérifier que l'email a au moins 3 caractères et contient `@`
		if (collegueAAjouter.getEmail().length() < 3 && !collegueAAjouter.getEmail().contains("@")) {
			throw new CollegueInvalideException("");
		}

		// Vérifier que la photoUrl commence bien par `http`
		if (!collegueAAjouter.getPhotoUrl().startsWith("http")) {
			throw new CollegueInvalideException("l'url de la photo n'est pas valide");
		}

		// TODO Vérifier que la date de naissance correspond à un age >= 18

		// générer un matricule pour ce collègue
		String matricule = UUID.randomUUID().toString();
		collegueAAjouter.setMatricule(matricule);

		// Sauvegarder le collègue
		data.put(collegueAAjouter.getMatricule(), collegueAAjouter);

		return collegueAAjouter;

	}

	public Collegue modifierEmail(String matricule, String email) {

		// TODO retourner une exception `CollegueNonTrouveException`
		// si le matricule ne correspond à aucun collègue

		// TODO Vérifier que l'email a au moins 3 caractères et contient `@`
		// TODO Si la règle ci-dessus n'est pas valide, générer une exception :
		// `CollegueInvalideException`. avec un message approprié.

		// TODO Modifier le collègue

		return null;
	}

	public Collegue modifierPhotoUrl(String matricule, String photoUrl) {

		// TODO retourner une exception `CollegueNonTrouveException`
		// si le matricule ne correspond à aucun collègue

		// TODO Vérifier que la photoUrl commence bien par `http`
		// TODO Si la règle ci-dessus n'est pas valide, générer une exception :
		// `CollegueInvalideException`. avec un message approprié.

		// TODO Modifier le collègue

		return null;
	}

}
