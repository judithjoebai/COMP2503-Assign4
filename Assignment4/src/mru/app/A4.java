package mru.app;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.FileNotFoundException;
import java.util.Comparator;

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
	private HashMap<Avenger, String> avengerHashMap = new HashMap<>();
	private TreeMap<Avenger, String> alphabeticalTreeMap = new TreeMap<>(Comparator.naturalOrder());
	private TreeMap<Avenger, String> mentionTreeMap = new TreeMap<>(new AvengerMentionComparator());
	private TreeMap<Avenger, String> popularAvengerTreeMap = new TreeMap<>(new AvengerComparator());
	private TreeMap<Avenger, String> popularPerformerTreeMap = new TreeMap<>(new PerformerComparator());
	
	
	/**
	 * starting point to run the program 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) {
		A4 a4 = new A4();
		a4.run();
	}

	/**
	 * runs the program by calling a method to read input, 
	 * calling a method of the created alternative order of the binary search trees
	 * and calling a method that prints the output 
	 * @throws FileNotFoundException 
	 */
	public void run() {
		readInput();
		createdOrderedTreeMaps();
		printResults();
	}

	private void createdOrderedTreeMaps() {	
		
		Iterator<Map.Entry<Avenger, String>> avengerIterator = avengerHashMap.entrySet().iterator();
		
		while (avengerIterator.hasNext()) {
			Entry<Avenger, String> entry = avengerIterator.next();
			Avenger avenger = entry.getKey();
			
			alphabeticalTreeMap.put(avenger, avenger.getHeroAlias());
			mentionTreeMap.put(avenger, avenger.getHeroAlias());
			popularAvengerTreeMap.put(avenger, avenger.getHeroAlias());
			popularPerformerTreeMap.put(avenger, avenger.getHeroAlias());
			
		}
		
	}

	/**
	 * read the in stream and keep track how many times avengers are mentioned by
	 * alias or last name.
	 * @throws FileNotFoundException 
	 */
	private void readInput() {
		
		
		while(input.hasNext()) {
			//fix back to input
			String word = input.next();
			word = cleanWord(word);
		
			if(!word.isEmpty()) {
				totalWordCount++;
				updateHashMap(word);
			}
		}

	}
	
	/**
	 * takes a word and cuts off any unnecessary add-ons
	 * @param next
	 * @return word
	 */
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
	
	/**
	 * Reads the given parameter and checks if the word matches with the given array to then either
	 * add the name into the list or add a frequency
	 * @param word
	 */
	private void updateHashMap(String word) {
		
		Avenger newAvenger = new Avenger();
		Avenger existingAvenger = findAvenger(word);
		
		for (int i=0; i < avengerRoster.length; i++) {
			if (word.equals(avengerRoster[i][0])|| word.equals(avengerRoster[i][1]) || word.equals(avengerRoster[i][2])) {
				newAvenger.setHeroAlias(avengerRoster[i][0]);
				newAvenger.setHeroName(avengerRoster[i][1]);
				newAvenger.setPerformer(avengerRoster[i][2]);
			
			
			 
			
				if(existingAvenger != null) {
					if (word.equals(avengerRoster[i][0]))
						existingAvenger.setAliasFreq(existingAvenger.getAliasFreq() + 1);
					else if (word.equals(avengerRoster[i][1])) 
						existingAvenger.setNameFreq(existingAvenger.getNameFreq() + 1);
					else if (word.equals(avengerRoster[i][2])) 
						existingAvenger.setPerformerFreq(existingAvenger.getPerformerFreq() + 1);
					}else {
					
					existingAvenger = newAvenger;
					
					if (word.equals(avengerRoster[i][0]))
						existingAvenger.setAliasFreq(1);
					else if (word.equals(avengerRoster[i][1]))
						existingAvenger.setNameFreq(1);
					else if (word.equals(avengerRoster[i][2]))
						existingAvenger.setPerformerFreq(1);
					
				
					existingAvenger.setMentionOrder(avengerHashMap.size() + 1);
					avengerHashMap.put(existingAvenger, existingAvenger.getHeroAlias());
					
				}
			}
	
		}
	}
	
	/**
	 * iterates through the list and matches the word with pre-existing avenger otherwise returns null
	 * @param word
	 * @return foundAvenger
	 */
	private Avenger findAvenger(String word) {
	    for (Map.Entry<Avenger, String> entry : avengerHashMap.entrySet()) {
	        Avenger foundAvenger = entry.getKey();

	        if (foundAvenger.getHeroName().equalsIgnoreCase(word) ||
	            foundAvenger.getHeroAlias().equalsIgnoreCase(word) ||
	            foundAvenger.getPerformer().equalsIgnoreCase(word)) {
	            return foundAvenger;
	        }
	    }
	    return null;
	}

	/**
	 * print the results
	 */
	private void printResults() {
		
		System.out.println("Total number of words: " + totalWordCount);
		System.out.println("Number of Avengers Mentioned: " +  alphabeticalTreeMap.size());
		System.out.println();

		System.out.println("All avengers in the order they appeared in the input stream:");
		// Prints the list of avengers in the order they appeared in the input
		printTopNameNoP(mentionTreeMap);
		System.out.println();

		System.out.println("Top " + topN + " most popular avengers:");
		// Prints the most popular avengers
		printTopName(popularAvengerTreeMap);
		System.out.println();

		System.out.println("Top " + topN + " most popular performers:");
		// Prints the most popular performer
		printTopName(popularPerformerTreeMap);
		System.out.println();

		System.out.println("All mentioned avengers in alphabetical order:");
		// Prints the list of avengers in alphabetical order
		printTopNameNoP(alphabeticalTreeMap);
		System.out.println();
	}
	
	/**
	 * Takes a list and uses the iterator to print the top until the loop is broken
	 * @param list
	 */
	private void printTopName(TreeMap<Avenger, String> list) {
		
		Iterator<Entry<Avenger, String>> iterate = list.entrySet().iterator();
		int count = 0;
		
		while (iterate.hasNext() && count < topN) {
			Map.Entry<Avenger, String> entry = iterate.next();
			System.out.println(entry.getKey());
			
			count++;
		}
		
	}
	
	/**
	 * Takes a list and uses the iterator to print the entries of the tree map
	 * @param list
	 */
	private void printTopNameNoP(TreeMap<Avenger, String> list) {
		
		
		for(Map.Entry<Avenger, String> entry: list.entrySet()) {
			System.out.println(entry.getKey());
		}
		
	}
}
