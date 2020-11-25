package fr.istic.prg1.tp3;

/**
 * @author MickaÃ«l Foursov <foursov@univ-rennes1.fr>
 * @version 5.0
 * @since 2015-11-10
 * Classe représentant des doublets non modifiables
 */

public class Pair implements Comparable<Pair> {

	private int x1;
	private int x2;
	
	/**
	 * Constructor of the Pair class which initializes the variables
	 * @author VARLOTEAUX Maxime, VARY Adrien
	 * @param i
	 * @param j
	 */
	public Pair(int i, int j) {
		this.x1=i;
		this.x2=j;
	}

	/**
	 * Getter of attribute x1
	 * @author VARLOTEAUX Maxime, VARY Adrien
	 * @return x1
	 */
	public int getX1() {
		return x1;
	}

	/**
	 * Getter of attribute x2
	 * @author VARLOTEAUX Maxime, VARY Adrien
	 * @return x2
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * Function which makes it possible to compare two objects of the Pair type and which returns -1 if the current object 
	 * is less than the object passed in parameter, 0 if they are identical (have the same values) and 1 if the current object 
	 * is greater than the object passed in parameter.
	 * @author VARLOTEAUX Maxime, VARY Adrien
	 * @return -1, 0 ou 1
	 */
	@Override
	public int compareTo(Pair d) {
		//Si la première valeur est inférieure, cela sera forcément inférieure
		if ( (this.x1 < d.getX1()) || (this.x1 == d.getX1() && this.x2 < d.getX2()) ) {
			return -1;
		} else {
			if (this.x1 == d.getX1() && this.x2 == d.getX2()) {
				return 0;
			} else {
				return 1;
			}
		}
	}
	
	/**
	 * Function that allows you to clone the current object by creating another object
	 * @author VARLOTEAUX Maxime, VARY Adrien
	 * @return un objet Pair avec les valeur x1 et x2
	 */
	@Override
	public Pair clone() {
		Pair p = new Pair(this.getX1(),this.getX2());
	    return p;
	}

	/**
	 * Function used to display the doublet of the current object
	 * @author VARLOTEAUX Maxime, VARY Adrien
	 * @return Pair 
	 */
	@Override
	public String toString() {
		String ch = "(" + getX1() + "," + getX2()+")";
		return ch;
	}

	/**
	 * Function which allows to check that the current object is equal to the object set in parameter
	 * @author VARLOTEAUX Maxime, VARY Adrien
	 * @return false, true
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Pair)) {
			return false;
		}
		Pair pa = (Pair) obj;
		if (this.getX1() != pa.getX1()) {
			return false;
		}
		if(this.getX2() != pa.getX2()) {
			return false;
		}
		return true;
	}
}