package mru.app;

import java.util.Comparator;

public class PerformerComparator implements Comparator <Avenger>{

	/**
	 * compares two avenger objects on their performer freq, 
	 * hero name length and hero alias 
	 * 
	 * @param o1 the first avenger to compare
	 * @param o2 the second avenger to compare 
	 * @return a negative int if the first avenger is less than second, 
	 * zero if they are equal, a positive integer first is greater than the second obj
	 */
	@Override
	public int compare(Avenger o1, Avenger o2) {
		//compares performer frequency
		if(o1.getPerformerFreq() > o2.getPerformerFreq()) {
			return -1;
		}
		if(o1.getPerformerFreq() < o2.getPerformerFreq()) {
			return 1;
		}
		else {
			//compares hero name length
			if(o1.getHeroName().length() > o2.getHeroName().length()) {
				return 1;
			}
			if(o1.getHeroName().length() < o2.getHeroName().length()) {
				return -1;
			}
			else
				//comparing alias alphabetically
				return o1.getHeroAlias().compareTo(o2.getHeroAlias());
		}
	}
}

