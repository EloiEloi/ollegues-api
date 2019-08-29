/**
 * 
 */
package colleguesapi.model;

import java.time.LocalDate;

import lombok.Data;

/**
 * @author Eloi
 *
 */

@Data
public class Collegue {

	String matricule;
	String nom;
	String prenom;
	String email;
	LocalDate dateDeNaissance;
	String photoUrl;

	/**
	 * Constructor
	 * 
	 */
	public Collegue() {

	}

}
