/**
 * 
 */
package colleguesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import colleguesapi.model.Collegue;

/**
 * @author Eloi
 *
 */
public interface CollegueRepository extends JpaRepository<Collegue, String> {

	List<Collegue> findByNom(String nom);

	// Collegue findByMatricule(String nom);
	// Optional<Collegue> findByMatricule(String nom);

}
