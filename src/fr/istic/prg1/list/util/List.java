package fr.istic.prg1.list.util;

/**
 * @author Adrien VARY, Maxime VARLOTEAUX
 * @since 13 novembre 2020
 * L3 Miage Groupe 2E
 */

import fr.istic.prg1.list_util.Iterator;
import fr.istic.prg1.list_util.SuperT;

public class List<T extends SuperT> {
	// liste en double chainage par references

	private class Element {
		// element de List<Item> : (Item, Element, Element)
		public T value;
		public Element left, right;

		public Element() {
			value = null;
			left = null;
			right = null;
		}
	} // class Element

	public class ListIterator implements Iterator<T> {
		private Element current;

		private ListIterator() {
			current = flag.right;
		}

		@Override
		public void goForward() {
			current = current.right;
		}

		@Override
		public void goBackward() {
			current = current.left;
		}

		@Override
		public void restart() {
			current = flag.right;
		}

		@Override
	    public boolean isOnFlag() { 
			if(current == flag) {
				return true;
			}
			return false; 
		}

		@Override
		public void remove() {
			try {
				assert current != flag : "\n\n\nimpossible de retirer le drapeau\n\n\n";
			} catch (AssertionError e) {
				e.printStackTrace();
				System.exit(0);
			}
		}

		@Override		 
		public T getValue() { 
			return current.value; 
		}

		@Override
	    public T nextValue() { 
			this.goForward();
			return current.value; 
		}

		@Override
		public void addLeft(T v) {
			Element nouveau = new Element();
			nouveau.value = v;
			Element gauche = current.left;
			gauche.right = nouveau;
			nouveau.left = gauche;
			nouveau.right = current;
			current.left = nouveau;
		}

		@Override
		public void addRight(T v) {
			Element nouveau = new Element();
			nouveau.value = v;
			Element droite = current.right;
			droite.right = nouveau;
			nouveau.right = droite;
			nouveau.left = current;
			current.right = nouveau;
		}
		
		@Override
		public void setValue(T v) {
			current.value = v;
		}

		@Override
		public String toString() {
			return "parcours de liste : pas d'affichage possible \n";
		}

		@Override
		public void selfDestroy() {
			// TODO Auto-generated method stub
			
		}

	} // class IterateurListe

	private Element flag;

	public List() {
		flag = new Element();
		flag.left = flag;
		flag.right = flag;
	}
	
	public ListIterator iterator() { 
		return new ListIterator();
	}
	
	public boolean isEmpty() { 
		return flag.left == flag && flag.right == flag ;
	}
	
	public void clear() {
		flag.left = flag;
		flag.right = flag;
	}
	
	public void setFlag(T v) {
		flag.value = v;
	}
	
	public void addHead(T v) {
		Element e = new Element();
		e.value = v;
		e.left = flag;
		if (flag.right != flag) {
			Element suivant = flag.right;
			e.right = suivant;
			suivant.left = e;
		} else {
			e.right = flag;
		}
		flag.right = e;
	}
	
	public void addTail(T v) {
		Element e = new Element();
		e.value = v;
		e.right = flag;
		if (flag.left != flag) {
			Element precedent = flag.left;
			e.left = precedent;
			precedent.right = e;
		} else {
			e.left = flag;
		}
		flag.left = e;
	}

	@SuppressWarnings("unchecked")
	public List<T> clone() {
		List<T> nouvListe = new List<T>();
		ListIterator p = iterator();
		while (!p.isOnFlag()) {
			nouvListe.addTail((T) p.getValue().clone());
			// UNE COPIE EST NECESSAIRE !!!
			p.goForward();
		}
		return nouvListe;
	}

	@Override
	public String toString() {
		String s = "contenu de la liste : \n";
		ListIterator p = iterator();
		while (!p.isOnFlag()) {
			s = s + p.getValue().toString() + " ";
			p.goForward();
		}
		return s;
	}
}