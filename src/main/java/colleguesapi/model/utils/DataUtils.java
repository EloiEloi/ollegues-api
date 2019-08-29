/**
 * 
 */
package colleguesapi.model.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import colleguesapi.model.Collegue;

/**
 * @author Eloi
 *
 */
public class DataUtils {

	/**
	 * Constructor
	 * 
	 */

	public static Map<String, Collegue> initData() {

		Map<String, Collegue> data = new HashMap<>();

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

		return data;

	}

}
