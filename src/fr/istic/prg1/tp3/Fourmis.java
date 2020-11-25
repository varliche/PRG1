package fr.istic.prg1.tp3;

/**
 * @author VARLOTEAUX Maxime, VARY Adrien
 */

public class Fourmis {

	public static String next(String ui) {
		int cpt=1;
		int a = 0;
		String s = "";
		while( ui.length() > a ) {
			while( ui.length() -1 > a && ui.charAt(a) == ui.charAt(a+1) ) {
				++cpt;
				++a;
			}
			s += Integer.toString(cpt) + ui.charAt(a);
			//La toString() permet de créer une représentation String d'un objet en utilisant le contenu de l'objet.
			cpt = 1 ;
			++a;
		}
		return s;
	}

}
