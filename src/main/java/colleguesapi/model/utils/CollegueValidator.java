/**
 * 
 */
package colleguesapi.model.utils;

import colleguesapi.model.Collegue;

/**
 * @author Eloi
 *
 */
public class CollegueValidator {

	public static Boolean validerNom(Collegue collegueAValider) {
		return collegueAValider.getNom().length() >= 2;
	}

	public static Boolean validerPrenom(Collegue collegueAValider) {
		return collegueAValider.getPrenom().length() >= 2;

	}

	public static Boolean validerEmail(Collegue collegueAValider) {
		return (collegueAValider.getEmail().length() > 3 && collegueAValider.getEmail().contains("@"));
	}

	public static Boolean validerPhoto(Collegue collegueAValider) {
		return collegueAValider.getPhotoUrl().startsWith("http");

	}

	// TODO Vérifier que la date de naissance correspond à un age >= 18

	public static Boolean validerAll(Collegue collegueAValider) {
		return (validerNom(collegueAValider) && validerPrenom(collegueAValider) && validerEmail(collegueAValider) && validerPhoto(collegueAValider));
	}

}
