package fr.istic.prg1.tp3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InsertionPair {
	
	private static final int SIZE_MAX =10;
	private int size;
	private Pair[] array = new Pair[SIZE_MAX];
	
	public InsertionPair() {}

	/**
	 * @author VARLOTEAUX Maxime, VARY Adrien
	 * @return Copy of the filled part of the table
	 * @return Array size containing the values
	 */
	public Pair[] toArray() {
		Pair[] tab = new Pair[size];
		for(int i = 0 ; i < size ; i++ ) {
			tab[i] = array[i];//on copie la partie remplie du tableau dans le nouveau 
		}
		return tab;
	}

	/**
	 * @author VARLOTEAUX Maxime, VARY Adrien
	 * Function to add a value in the array when it is not found.
	 * @param Integer Value
	 * @return true ou false
	 */
	public boolean insert(Pair p) {
		int i = 0;
		if(this.size==SIZE_MAX) {
			return false;
		}
		while(i<this.size && p.compareTo(this.array[i]) != -1 && !this.array[i].equals(p)) {
			++i;
		}
		if(p.equals(this.array[i])) {
			return false;
		}else {
			++this.size;
			for(int j = this.size-2; j > i-1; j--) {
				this.array[j+1] = this.array[j];
			}
			this.array[i] = p;
		}
		return true;
	}

	/**
	 * Function which allows to insert peers in the table and stops at -1
	 * @author VARLOTEAUX Maxime, VARY Adrien
	 * @param scanner
	 */
	public void createArray(Scanner scanner) {
		while(scanner.hasNextInt()) {
			int sc = scanner.nextInt();
			int sc2;
			try {
				sc2 = scanner.nextInt();
			}catch(java.util.NoSuchElementException e) {
				sc2 = -1;
			}
			Pair unP = null;
			if(sc != -1 && sc2 != -1 ) {
				unP = new Pair(sc,sc2);
				insert(unP);
			}
			else {
				scanner.close();
				return;
			}
		}
	}
	
	/**
     * Display array's values of current object
     * @author VARLOTEAUX Maxime, VARY Adrien
     * @return Pair table
     */
	public String toString() {
    	String chaine = "Tableau = [";
    	for(int i =0 ; i<size;i++) {
    		chaine = chaine + array[i]+ ", " ;
    	}
    	return chaine+ "]";
    }
	
	/*public static void main(String[] args) throws FileNotFoundException {
		Pair pair10 = new Pair(1, 2);
		Pair pair11 = new Pair(2, 3);
		Pair pair12 = new Pair(11, 22);
		Pair pair13 = new Pair(33, 1);
		Pair[] realArray = { pair10, pair11, pair12, pair13 };
		File file = new File("fichier2.txt");
		Scanner scanner = new Scanner(file);
		InsertionPair p = new InsertionPair();
		p.createArray(scanner);
		System.out.println(p.toArray());
		System.out.println(realArray);
		System.out.println(p.toArray().equals(realArray));
	}*/
}
