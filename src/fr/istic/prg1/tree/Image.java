package fr.istic.prg1.tree;

import java.util.Scanner;

import fr.istic.prg1.tree_util.AbstractImage;
import fr.istic.prg1.tree_util.Iterator;
import fr.istic.prg1.tree_util.Node;
import fr.istic.prg1.tree_util.NodeType;

/**
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 5.0
 * @since 2016-04-20
 * 
 *        Classe décrivant les images en noir et blanc de 256 sur 256 pixels
 *        sous forme d'arbres binaires.
 * 
 */

public class Image extends AbstractImage {
	private static final Scanner standardInput = new Scanner(System.in);

	public Image() {
		super();
	}

	public static void closeAll() {
		standardInput.close();
	}

	/**
	 * @param x
	 *            abscisse du point
	 * @param y
	 *            ordonnée du point
	 * @pre !this.isEmpty()
	 * @return true, si le point (x, y) est allumé dans this, false sinon
	 */
	@Override
	public boolean isPixelOn(int x, int y) {
		int maxX = 255;
		int minX = 0;
		int minY = 0;
		int maxY = 255;
		boolean surX = false; // On commmence par Y
		Iterator<Node> it = this.iterator(); 
		while(!it.isEmpty() && it.getValue().state == 2) {
			if (surX) {
				if (x < (maxX + minX) / 2) {
					it.goLeft();
					maxX = (maxX + minX) / 2;
				} else {
					it.goRight();
					minX = (maxX + minX) / 2;
				}
				surX = false;
			} else {
				if (y < (maxY + minY) / 2) {
					it.goLeft();
					maxY = (maxY + minY) / 2;
				} else {
					it.goRight();
					minY = (maxY + minY) / 2;
				}
				surX = true;
			}
		  }
		  return it.getValue().state == 1;
	}

	/**
	 * this devient identique à image2.
	 *
	 * @param image2
	 *            image à copier
	 *
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void affect(AbstractImage image2) {
		Iterator<Node> it1 = this.iterator();
		it1.clear();
		Iterator<Node> it2 = image2.iterator();
		this.affectAux(it1,it2);
	}
	
	public void affectAux(Iterator<Node>it1, Iterator<Node> it2) {
		if(!it2.isEmpty()) {
			it1.addValue(it2.getValue());//getValue récupère la valeur du noeud courant
			it1.goLeft();
			it2.goLeft();
			affectAux(it1,it2);
			it1.goUp();
			it2.goUp();
			it1.goRight();
			it2.goRight();
			affectAux(it1,it2);
			it1.goUp();
			it2.goUp();
		}
	}

	/**
	 * this devient rotation de image2 à 180 degrés.
	 *
	 * @param image2
	 *            image pour rotation
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void rotate180(AbstractImage image2) {
		Iterator<Node> it = this.iterator();
		Iterator<Node> it1 = image2.iterator();
		it.clear();
		rotate180Aux(it,it1);
	}
	
	public void rotate180Aux(Iterator<Node> it, Iterator<Node> it1) {
		if(!it.isEmpty()) {
			
		}
	}

	/**
	 * this devient rotation de image2 à 90 degrés dans le sens des aiguilles
	 * d'une montre.
	 *
	 * @param image2
	 *            image pour rotation
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void rotate90(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction non demeand�e");
		System.out.println("-------------------------------------------------");
		System.out.println();	    
	}

	/**
	 * this devient inverse vidéo de this, pixel par pixel.
	 *
	 * @pre !image.isEmpty()
	 */
	@Override
	public void videoInverse() {
		Iterator<Node> it = this.iterator();
		this.videoInverseAux(it);
	}
	
	public void videoInverseAux(Iterator<Node> it) {
		switch(it.nodeType()) {
			case DOUBLE : 
				it.goLeft();
			    this.videoInverseAux(it);
			    it.goUp();
			    it.goRight();
			    this.videoInverseAux(it);
			    it.goUp();
			    break;
			case LEAF : 
				if(it.getValue().state==1) {
					it.setValue(Node.valueOf(0));
			    } else {
			    	it.setValue(Node.valueOf(1));
			    }
				break;
		}
	}
	

	/**
	 * this devient image miroir verticale de image2.
	 *
	 * @param image2
	 *            image à agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorV(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction � �crire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient image miroir horizontale de image2.
	 *
	 * @param image2
	 *            image à agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorH(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction � �crire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient quart supérieur gauche de image2.
	 *
	 * @param image2
	 *            image à agrandir
	 * 
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomIn(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction � �crire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * Le quart supérieur gauche de this devient image2, le reste de this
	 * devient éteint.
	 * 
	 * @param image2
	 *            image à réduire
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomOut(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction � �crire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient l'intersection de image1 et image2 au sens des pixels
	 * allumés.
	 * 
	 * @pre !image1.isEmpty() && !image2.isEmpty()
	 * 
	 * @param image1 premiere image
	 * @param image2 seconde image
	 */
	@Override
	public void intersection(AbstractImage image1, AbstractImage image2) {
		  Iterator<Node> it = this.iterator();
		  Iterator<Node> it1 = image1.iterator();
		  Iterator<Node> it2 = image2.iterator(); 
		  it.clear();
		  boolean aGauche = true;
		  this.interAux(it,it1,it2,aGauche);
	}
	

private void interAux(Iterator<Node> it,Iterator<Node> it1,Iterator<Node> it2, boolean aGauche) {
		if (it1.getValue().state == 2 && it2.getValue().state == 2) {
			it.setValue(it1.getValue());
			if(aGauche) {
				it.goLeft();
				it1.goLeft();
				it2.goLeft();
				this.interAux(it,it1,it2,aGauche);
				it.goUp();
				it1.goUp();
				it2.goUp();
			} else {
				it.goRight();
				it1.goRight();
				it2.goRight();
				this.interAux(it,it1,it2,aGauche);
				it.goUp();
				it1.goUp();
				it2.goUp();
			}
			 
		} else if (it1.getValue().state == it2.getValue().state) {
			it.setValue(it1.getValue());
			it.goUp();
			it1.goUp();
			it2.goUp();
			if(aGauche) {
				aGauche = false;
			} else {
				aGauche = true;
			}
			this.interAux(it,it1,it2,aGauche);
			it.goUp();
			it1.goUp();
			it2.goUp();
		} else {
			if(it1.getValue().state != 2) {
				it.setValue(it1.getValue());
			}else {
				it.setValue(it2.getValue());
			}
			it.goUp();
			it1.goUp();
			it2.goUp();
			if(aGauche) {
				aGauche = false;
			} else {
				aGauche = true;
			}
			this.interAux(it,it1,it2,aGauche);
			it.goUp();
			it1.goUp();
			it2.goUp();
		}
	}


	/**
	 * this devient l'union de image1 et image2 au sens des pixels allumés.
	 * 
	 * @pre !image1.isEmpty() && !image2.isEmpty()
	 * 
	 * @param image1 premiere image
	 * @param image2 seconde image
	 */
	@Override
	public void union(AbstractImage image1, AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction � �crire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * Attention : cette fonction ne doit pas utiliser la commande isPixelOn
	 * 
	 * @return true si tous les points de la forme (x, x) (avec 0 <= x <= 255)
	 *         sont allumés dans this, false sinon
	 */
	@Override
	public boolean testDiagonal() {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction � �crire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	    return false;
	}

	/**
	 * @param x1
	 *            abscisse du premier point
	 * @param y1
	 *            ordonnée du premier point
	 * @param x2
	 *            abscisse du deuxième point
	 * @param y2
	 *            ordonnée du deuxième point
	 * @pre !this.isEmpty()
	 * @return true si les deux points (x1, y1) et (x2, y2) sont représentés par
	 *         la même feuille de this, false sinon
	 */
	@Override
	public boolean sameLeaf(int x1, int y1, int x2, int y2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction � �crire");
		System.out.println("-------------------------------------------------");
		System.out.println();
		return false;
	}

	/**
	 * @param image2
	 *            autre image
	 * @pre !this.isEmpty() && !image2.isEmpty()
	 * @return true si this est incluse dans image2 au sens des pixels allumés
	 *         false sinon
	 */
	@Override
	public boolean isIncludedIn(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction � �crire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	    return false;
	}

}
