package mru.app;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.Collection;

/**
 * COMP 2503 Fall 2023 Assignment 4
 * 
 * This program must read a input stream and keeps track of the frequency at
 * which an avenger is mentioned either by name or alias or performer's last name. The program must use HashMaps
 * for keeping track of the Avenger Objects, and it must use TreeMaps
 * for storing the data. 
 * 
 * @author Maryam Elahi
 * @date Fall 2023
 */

public class A4 {

	public String[][] avengerRoster = { { "captainamerica", "rogers", "evans" }, { "ironman", "stark", "downey" },
			{ "blackwidow", "romanoff", "johansson" }, { "hulk", "banner", "ruffalo" },
			{ "blackpanther", "tchalla", "boseman" }, { "thor", "odinson", "hemsworth" },
			{ "hawkeye", "barton", "renner" }, { "warmachine", "rhodes", "cheadle" },
			{ "spiderman", "parker", "holland" }, { "wintersoldier", "barnes", "stan" } };

	private int topN = 4;
	private int totalWordCount = 0;
	private Scanner input = new Scanner(System.in);
	private HashMap<String, Avenger> avengerHashMap = new HashMap<>();
	private TreeMap<Avenger, String> alphabeticalTreeMap = new TreeMap<>();
	private TreeMap<Avenger, Integer> mentionTreeMap = new TreeMap<>(new AvengerMentionComparator());
	private TreeMap<Avenger, Integer> PopularAvengerTreeMap = new TreeMap<>();
	private TreeMap<Avenger, Integer> PopularPerformerTreeMap = new TreeMap<>();
	

	/* TODO:
	 * Create the necessary hashMap and treeMap objects to keep track of the Avenger objects 
	 * Remember that a hashtable does not keep any inherent ordering for its contents.
	 * But for this assignment we want to be able to create the sorted lists of avenger objects.
	 * Use TreeMap objects (which are binary search trees, and hence have an
	 * ordering) for creating the following orders: alphabetical, mention order, most popular avenger, and most popular performer
	 * The alphabetical order TreeMap must be constructed with the natural order of the Avenger objects.
	 * The other three orderings must be created by passing the corresponding Comparators to the 
	 * TreeMap constructor. 
	 */
	
	public static void main(String[] args) {
		A4 a4 = new A4();
		a4.run();
	}

	public void run() {
		readInput();
		createdOrderedTreeMaps();
		printResults();
	}

	private void createdOrderedTreeMaps() {
		/* TODO:
		 * Create an iterator over the key set in the HashMap that keeps track of the avengers
		 * Add avenger objects to the treeMaps with different orderings.
		 * 
		 ** Hint: 
		 * Note that the HashMap and the TreeMap classes do not implement
		 * the Iterable interface at the top level, but they have
		 * methods that return Iterable objects, such as keySet() and entrySet().
		 * For example, you can create an iterator object over 
		 * the 'key set' of the HashMap and use the next() method in a loop
		 * to get each word object. 
		 */	
		
		Iterator<Avenger> avengerIterator = avengerHashMap.iterator();
		
	}

	/**
	 * read the input stream and keep track how many times avengers are mentioned by
	 * alias or last name.
	 */
	private void readInput() {
		/*
		 * In a loop, while the scanner object has not reached end of stream, - read a
		 * word. - clean up the word - if the word is not empty, add the word count. -
		 * Check if the word is either an avenger alias or last name then - Create a new
		 * avenger object with the corresponding alias and last name. - if this avenger
		 * has already been mentioned, increase the corresponding frequency count for the object
		 * already in the hashMap. - if this avenger has not been mentioned before, add the
		 * newly created avenger to the hashMap, remember to set the frequency, and 
		 * to keep track of the mention order
		 */
		
		while(input.hasNext()) {
			String word = input.next();
			word = cleanWord(word);
		
			if(!word.isEmpty()) {
				totalWordCount++;
				updateHashMap(word);
			}
		}

	}
	
	private String cleanWord(String next) {
		String word;
		int index = next.indexOf('\'');
		if(index != -1) {
			word = next.substring(0, index).toLowerCase().trim().replaceAll("[^a-z]", "");
		} else {
			word = next.toLowerCase().trim().replaceAll("[^a-z]","");
		}
			
		return word;
		
	}
	
	private void updateHashMap(String word) {
		
		Avenger newAvenger = new Avenger();
		Avenger aveng = findAvenger(word);
		
		for (int i=0; i < avengerRoster.length; i++) {
			if (word.equals(avengerRoster[i][0])|| word.equals(avengerRoster[i][1]) || word.equals(avengerRoster[i][2])) {
				newAvenger.setHeroAlias(avengerRoster[i][0]);
				newAvenger.setHeroName(avengerRoster[i][1]);
				newAvenger.setPerformer(avengerRoster[i][2]);
			}
			
			if(aveng != null) {
				if (word.equals(avengerRoster[i][0]))
					aveng.setAliasFreq(aveng.getAliasFreq() + 1);
				else if (word.equals(avengerRoster[i][1]))
					aveng.setNameFreq(aveng.getNameFreq() + 1);
				else if (word.equals(avengerRoster[i][2]))
					aveng.setPerformerFreq(aveng.getPerformerFreq() + 1);
			}else {
				
				aveng = newAvenger;
				
				if (word.equals(avengerRoster[i][0]))
					aveng.setAliasFreq(1);
				else if (word.equals(avengerRoster[i][1]))
					aveng.setNameFreq(1);
				else if (word.equals(avengerRoster[1][2]))
					aveng.setPerformerFreq(1);
				
			
				aveng.setMentionOrder(avengerHashMap.size() + 1);
				//avengerHashMap.put(aveng.heroAlias, aveng);
			}
		}
	}
	
	private Avenger findAvenger(String word) {
		
		Iterator<Avenger> iterate = avengerHashMap.iterator();
		
		while(iterate.hasNext()) {
			Avenger foundAvenger = iterate.next();
			
			if(foundAvenger.getHeroName().equalsIgnoreCase(word) || foundAvenger.getHeroAlias().equalsIgnoreCase(word) || foundAvenger.getPerformer().equalsIgnoreCase(word))
				return foundAvenger;
		}
		return null;
	}

	/**
	 * print the results
	 */
	private void printResults() {
		/*
		 * Please first read the documentation for TreeMap to see how to 
		 * iterate over a TreeMap data structure in Java.
		 *  
		 * Hint for printing the required list of avenger objects:
		 * Note that the TreeMap class does not implement
		 * the Iterable interface at the top level, but it has
		 * methods that return Iterable objects.
		 * You must either create an iterator over the 'key set',
		 * or over the values 'collection' in the TreeMap.
		 * 
		 */
		
		
		System.out.println("Total number of words: " + totalWordCount);
		//System.out.println("Number of Avengers Mentioned: " + ??);
		System.out.println();

		System.out.println("All avengers in the order they appeared in the input stream:");
		// Todo: Print the list of avengers in the order they appeared in the input
		// Make sure you follow the formatting example in the sample output
		System.out.println();

		System.out.println("Top " + topN + " most popular avengers:");
		// Todo: Print the most popular avengers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output
		System.out.println();

		System.out.println("Top " + topN + " most popular performers:");
		// Todo: Print the most popular performer, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output
		System.out.println();

		System.out.println("All mentioned avengers in alphabetical order:");
		// Todo: Print the list of avengers in alphabetical order
		System.out.println();
	}
}
