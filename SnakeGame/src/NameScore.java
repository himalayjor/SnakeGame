
import java.io.Serializable;
import java.util.Comparator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class NameScore implements Serializable {
	
	public int score;
	public String Name;

	public NameScore(int score, String Name) {
		this.score = score;
		this.Name = Name;
	}

}
