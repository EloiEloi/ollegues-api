/**
 * 
 */
package colleguesapi.model.service;

import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import colleguesapi.model.Collegue;

/**
 * @author Eloi
 *
 */

@RunWith(SpringRunner.class)
public class CollegueRepositoryTest {

	@Autowired
	private CollegueRepository collegueRepository;

	@Test
	public void testFindByNom() {
		Collegue col = new Collegue();

		collegueRepository.save(col);

		col.setMatricule(UUID.randomUUID().toString());
		col.setEmail("myemail@email.com");
		col.setNom("blabla");
		col.setPrenom("Jean");
		col.setPhotoUrl("http://vdvdvdvdvdvdv.png");

		List<Collegue> listeCollegues = collegueRepository.findByNom("blabla");
		Assert.assertEquals(1, listeCollegues.size());
	}

	@Test
	public void testFindByNomCasNonTrouve() {

	}

}
