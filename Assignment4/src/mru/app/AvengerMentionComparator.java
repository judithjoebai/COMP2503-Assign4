package mru.app;

import java.util.Comparator;


public class AvengerMentionComparator implements Comparator<Avenger> {

	/**
	 * compares the index of when they were mentioned in the BST
	 * 
	 * @param o1 the first avenger object to compare 
	 * @param o2 the second avenger object to compare 
	 */
	@Override
	public int compare(Avenger o1, Avenger o2) {
		return Integer.compare(o1.getMentionOrder(), o2.getMentionOrder());
	}

}

