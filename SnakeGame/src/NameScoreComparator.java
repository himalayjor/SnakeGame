
import java.util.Comparator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class NameScoreComparator implements Comparator<NameScore> {

	@Override
	public int compare(NameScore o1, NameScore o2) {
		return (o2.score - o1.score);
	}
	
}
