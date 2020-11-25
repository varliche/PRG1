package fr.istic.prg1.tp4;

/**
 * @author MickaÃ«l Foursov <foursov@univ-rennes1.fr>
 * VARY Adrien & VARLOTEAUX Maxime
 * @version 4.0
 * @since 2015-06-15
 */

public class SmallSet {

    /**
     * taille de l'ensemble
     */
    private static final int setSize = 256;

    /**
     * Ensemble est représenté comme un tableau de booléens 
     * (par défaut initialisé à false)
     * Si on à l'ensemble S={0,3,7,254} par exemple
     * on aura les valeurs true à l'indice 0, 3, 7, 254
     */
    private boolean[] tab = new boolean[setSize];

    public SmallSet() {
    	for (int i = 0; i < setSize; ++i) {
    		tab[i] = false;
    	}
    }

    public SmallSet(boolean[] t) {
    	for (int i = 0; i < setSize; ++i) {
    		tab[i] = t[i];
    	}
    }

    /**
     * @return nombre de valeurs appartenant à  l'ensemble
     */
    public int size() {
    	int compteur = 0;
    	for (int i=0;i<this.setSize; i++) {
    		if(!tab[i]) {
    			compteur++;
    		}
    	}
    	return compteur;
    }

    /**
     * @param x
     *            valeur Ã  tester
     * @return true, si l'entier x appartient à  l'ensemble, false sinon
     */
    public boolean contains(int x) {
    	return tab[x];
    }

    /**
     * @return true, si lâ€™ensemble est vide, false sinon
     */
    public boolean isEmpty() {
    	for(int i=0; i< this.setSize ; ++i) {
    		if(!tab[i]) {
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * Ajoute x à l'ensemble (sans effet si x déjà  présent)
     *
     * @param x
     *            valeur Ã  ajouter
     * @pre 0 <= x <= 255
     */
    public void add(int x) {
    	if(!contains(x)) {
    		tab[x] = true;//même s'il est déjà présent, la valeur restera à true
    	}
    }

    /**
     * Retire x de lâ€™ensemble (sans effet si x nâ€™est pas prÃ©sent)
     *
     * @param x
     *            valeur Ã  supprimer
     * @pre 0 <= x <= 255
     */
    public void remove(int x) {
    	if(contains(x)) {
    		tab[x] = false;//si la valeur est déjà absente elle restera à false
    	}
    }

    /**
     * Ajoute à  lâ€™ensemble les valeurs deb, deb+1, deb+2, ..., fin.
     *
     * @param begin
     *            dÃ©but de lâ€™intervalle
     * @param end
     *            fin de lâ€™intervalle
     * @pre 0 <= begin <= end <= 255
     */
    public void addInterval(int deb, int fin) {
    	if(deb >= 0 && fin <= 255) {
    		for(int i=deb;i<=fin;i++) {
        		this.add(i);
        	}
    	}
    }

    /**
     * Retire de lâ€™ensemble les valeurs deb, deb+1, deb+2, ..., fin.
     *
     * @param begin
     *            dÃ©but de lâ€™intervalle
     * @param end
     *            fin de lâ€™intervalle
     * @pre 0 <= begin <= end <= 255
     */
    public void removeInterval(int deb, int fin) {
    	for(int i=deb; i<= fin ; ++i) {
    		if(contains(i)) {
    			tab[i]=false;
    		}
    	}
    }

    /**
     * this devient l'union de this et set2
     * 
     * @param set2
     *            deuxiÃ¨me ensemble
     */
    public void union(SmallSet set2) {
    	for(int i=0 ; i<setSize; ++i) {
	    	  this.tab[i]= set2.tab[i] || this.tab[i];
	    }
    }

    /**
     * this devient l'intersection de this et set2
     * 
     * @param set2
     *            deuxiÃ¨me ensemble
     */
    public void intersection(SmallSet set2) {
    	for(int i=0; i<setSize; ++i) {
    		this.tab[i]= set2.tab[i] && this.tab[i];
    	}
    }

    /**
     * this devient la diffÃ©rence de this et set2
     * 
     * @param set2
     *            deuxiÃ¨me ensemble
     */
    public void difference(SmallSet set2) {
    	for(int i=0 ; i<setSize; ++i) {
	    	  this.tab[i]= set2.tab[i] && !this.tab[i];
	    }
    }

    /**
     * this devient la diffÃ©rence symÃ©trique de this et set2
     * 
     * @param set2
     *            deuxiÃ¨me ensemble
     */
    public void symmetricDifference(SmallSet set2) {
    	for(int i=0 ; i<setSize; ++i) {
	    	  this.tab[i]= (this.tab[i] ||set2.tab[i]) && !(this.tab[i]&& set2.tab[i]);
	    }
    }

    /**
     * this devient le complÃ©ment de this.
     */
    public void complement() {
    	for(int i=0 ; i<setSize; ++i) {
	    	  tab[i]=!tab[i];
	    }
    }

    /**
     * this devient l'ensemble vide.
     */
    public void clear() {
    	for(int i=0; i<this.setSize;++i) {
    		tab[i]=false;
    	}
    }

    /**
     * @param set2
     *            second ensemble
     * @return true, si this est inclus dans set2, false sinon
     */
    public boolean isIncludedIn(SmallSet set2) {
    	boolean included=true;
    	for(int i=0 ; i<setSize; ++i) {
    		if( set2.tab[i] && !this.tab[i] ) {
	    		included=false;
	    	}
    	}
	return included;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof SmallSet)) {
	    return false;
	}
	SmallSet set2 = (SmallSet)obj;// il reste le cas quand obj est un SmallSet
	return false;
    }

    /**
     * @return copie de this
     */
    public SmallSet clone() {
	return new SmallSet(tab);
    }

    @Override
    public String toString() {
	String s;
	s = "elements presents : ";
	for (int i = 0; i < setSize; ++i) {
	    if (tab[i]) {
		s = s + i + " ";
	    }
	}
	return s;
    }
}