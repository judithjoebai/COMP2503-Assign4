package mru.app;

/**
 * This class represents the hero, their alias, name,
 * performer and their frequencies 
 * it has setters and getter for the avenger attributes 
 * and implements an comparable interface for custom sorting 
 * @author Team 7
 * 
 */
public class Avenger implements Comparable<Avenger> {
	
	private String heroAlias;
	private String heroName; 
	private String performer;

	private int nameFreq;
	private int aliasFreq;
	private int performerFreq;
	
	private int mentionComp;
	/**
	 * creating a method of avenger objects and 
	 * instantiating the avenger attribute fields
	 */
	public Avenger() {
		heroAlias = "";
		heroName = "";
		performer = "";
		
		nameFreq = 0;
		aliasFreq = 0;
		performerFreq = 0;
		
		mentionComp = 0;
	}

	/**
	 * @return heroAlias returns the hero's alias
	 */
	public String getHeroAlias() {
		return heroAlias;
	}

	/**
	 * Sets the hero's alias to given value 
	 * @param HA the hero's alias 
	 */
	public void setHeroAlias(String HA) {
		heroAlias = HA;
	}
	
	/**
	 * @return heroName returns the hero's name 
	 */
	public String getHeroName() {
		return heroName;
	}
	
	/**
	 * Sets the hero's name to given value 
	 * @param HN the hero's name
	 */
	public void setHeroName(String HN) {
		heroName = HN;
	}
	
	/**
	 * @return performer returns the hero performer 
	 */
	public String getPerformer() {
		return performer;
	}
	
	/**
	 * Sets the hero's performer to given value 
	 * @param P the performer
	 */
	public void setPerformer(String P) {
		performer = P;
	}
	
	/**
	 * @return nameFreq the frequency of the hero's name
	 */
	public int getNameFreq() {
		return nameFreq;
	}

	/**
	 * Sets the hero's name frequency to given value 
	 * @param NF the hero's name frequency 
	 */
	public void setNameFreq(int NF) {
		nameFreq = NF;
	}
	
	/**
	 * @return aliasFreq the frequency of the hero's alias
	 */
	public int getAliasFreq() {
		return aliasFreq;
	}

	/**
	 * Sets the hero's alias frequency to given value 
	 * @param AF the hero's alias frequency
	 */
	public void setAliasFreq(int AF) {
		aliasFreq = AF;
	}
	
	/**
	 * @return performerFreq the frequency of the hero's performer 
	 */
	public int getPerformerFreq() {
		return performerFreq;
	}

	/**
	 * Sets the hero's performer frequency to given value 
	 * @param PF the performer frequency
	 */
	public void setPerformerFreq(int PF) {
		performerFreq = PF;
	}
	
	/**
	 * @return mentionComp the index for ordering who was first
	 */
	public int getMentionOrder() {
		return mentionComp;
	}
	
	/**
	 * Sets the mention index for comparing
	 * @param MI the mention index
	 */
	public void setMentionOrder(int MI) {
		mentionComp = MI;
	}
	
	/**
	 * Updating the hero alias frequency
	 */
	public void addAliasFreq() {
		aliasFreq++;
	}
	
	/**
	 * Updating the hero name frequency
	 */
	public void addNameFreq() {
		nameFreq++;
	}
	
	/**
	 * Updating the hero performer frequency
	 */
	public void addPerformerFreq() {
		performerFreq++;
	}
	
	/**
	 * Compares 2 avenger objects in alphabetical order 
	 * based on their hero alias
	 * @param o the avenger object to compare 
	 * @return result 1, 0, or -1 as a result of comparison 
	 */
	@Override
	public int compareTo(Avenger o) {
		int result = 0;
		
		result = this.getHeroAlias().compareTo(o.getHeroAlias());
		
		return result;
	}
	
	/**
	 * Compares the avenger object with another object for equality 
	 * @param o the other object to compare with this avenger 
	 * @return true if the other object is an avenger with the same heroAlias
	 * returns false otherwise or if the object is null 
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		Avenger a = (Avenger) o;
		if (this.getHeroAlias().equals(a.getHeroAlias()))
			return true;
		else 
			return false;
	}
	
	/**
	 * This method created a formatted string representation of avengers
	 * @return format the hero's alias, name, performer and frequency
	 */
	@Override
	public String toString() {
		String format = heroAlias + " aka " + heroName
				+ " performed by " + performer
				+ " mentioned "
				+ "(n: " + nameFreq
				+ " + a: " + aliasFreq
				+ " + p: " + performerFreq
				+ ")" + " time(s)";
		
		return format;
	}
}
