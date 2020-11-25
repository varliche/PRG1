package fr.istic.prg1.tp3;

/**
 * @author VARLOTEAUX Maxime, VARY Adrien
 */

import java.util.Scanner;

public class InsertionInteger {
	private static final int SIZE_MAX = 10;
	/**
	 * Number of integers in t, 0 <= size <= SIZE_MAX
	 */
	private int tailleReelleTab;
	private int[] array = new int[SIZE_MAX];
	
	/*
	 * Construteur de classe par défaut 
	*/
	
	public InsertionInteger() {}
	
	/**
	 * @return copy of the filled part of the table
	 * @author VARLOTEAUX Maxime, VARY Adrien
	 */
	
	public int[] toArray() {
		int t[] = new int[this.tailleReelleTab];
		for(int i = 0 ; i < this.tailleReelleTab ; i++ ) {
			t[i] = this.array[i];//on copie la partie remplie du tableau dans le nouveau 
		}
		return t;
		
	}

	public boolean insert(int value) {
		int i = 0;//variable i qui sera utilisée dans le while
		if(this.tailleReelleTab == SIZE_MAX) {
			//Si le tableau est déjà à sa taille max (plein) on retourne false
			return false;
		}
		while(i < this.tailleReelleTab && this.array[i] < value) {
			++i;
		}
		if(value == this.array[i]) {
			return false;
		}
		else {
			++this.tailleReelleTab;
			//System.out.println("Taille = " + this.tailleReelleTab + "	i = " + i + "	value = " + value);
			for( int j = this.tailleReelleTab -1 ; j > i ; --j) {
				array[j]= array[j-1];
			}
			//System.out.println("array[i] = "+this.array[i]+"	value ="+value);
			this.array[i]=value;
		}
		return true;
	}
	
	public void createArray(Scanner scanner) {
		while( scanner.hasNextInt() ) {
			int sc = scanner.nextInt();
			if(sc != -1) {
				this.insert(sc);
				//on met this pour "parler" d'une fonction appartenant à la classe dans laquelle on écrit
			}
			else {
				scanner.close();
				return;
			}
		}
	}
	
	@Override
	public String toString() {
		String chaine = "Tableau = [";
		for( int i = 0; i < tailleReelleTab ; i++ ) {
			chaine = chaine + String.valueOf(array[i]) + " - ";
		}
		return chaine + " ]";
	}
}
