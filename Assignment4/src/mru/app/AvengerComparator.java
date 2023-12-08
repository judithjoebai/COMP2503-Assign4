package mru.app;

import java.util.Comparator;

public class AvengerComparator implements Comparator <Avenger>{

	/**
	 * compares 2 avenger objects based on the total of their name, alias and performer frequencies
	 * also based on alphabetical order 
	 * 
	 * @param o1 the first avenger to compare
	 * @param o2 the second avenger to compare 
	 * @return a negative int if the first avenger is less than second, 
	 * zero if they are equal, a positive integer first is greater than the second obj
	 */
	@Override
	public int compare(Avenger o1, Avenger o2) {
		
		//adding total frequencies for each object of comparison 
		int total1 = o1.getNameFreq() + o1.getAliasFreq() + o1.getPerformerFreq();
		int total2 = o2.getNameFreq() + o2.getAliasFreq() + o2.getPerformerFreq();
		String p1 = o1.getPerformer();
		String p2 = o2.getPerformer();
		
		//compares total frequencies of 2 objects 
		if(total1 > total2) {
			return -1;
		}
		else if (total1 < total2) {
			return 1;
		}
		
		//performer name comparison 
		else {
			if(p1 == null && p2 == null) {
				return 0;
			}
			else if (p1 == null) {
				return -1;
			}
			else if (p2 == null) {
				return 1;
			}
			else {
				return p1.compareTo(p2);
			}
		}
	}	
}
