package fr.istic.prg1.tree;

import java.util.Scanner;

import fr.istic.prg1.tree_util.AbstractImage;
import fr.istic.prg1.tree_util.Iterator;
import fr.istic.prg1.tree_util.Node;
import fr.istic.prg1.tree_util.NodeType;

/**
 * @author Adrien VARY, VARLOTEAUX Maxime
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
	 * @param x abscisse du point
	 * @param y ordonnée du point
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
		while (!it.isEmpty() && it.getValue().state == 2) {
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
	 * @param image2 image à copier
	 *
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void affect(AbstractImage image2) {
		Iterator<Node> it = this.iterator();
		Iterator<Node> it2 = image2.iterator();
		it.clear();
		this.affectAux(it, it2);
	}

	private void affectAux(Iterator<Node> it, Iterator<Node> it2) {
		if (!it2.isEmpty()) {
			it.addValue(it2.getValue());
			it.goLeft();
			it2.goLeft();
			affectAux(it, it2);
			it.goUp();
			it2.goUp();
			it.goRight();
			it2.goRight();
			affectAux(it, it2);
			it.goUp();
			it2.goUp();
		}
	}

	/**
	 * this devient rotation de image2 à 180 degrés.
	 *
	 * @param image2 image pour rotation
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void rotate180(AbstractImage image2) {
		Iterator<Node> it = image2.iterator();
		Iterator<Node> it2 = this.iterator();
		it.clear();
		rotate180Aux(it, it2);
	}

	private void rotate180Aux(Iterator<Node> it, Iterator<Node> it2) {
		if (!it.isEmpty()) {
			it.addValue(it2.getValue());
			// it part à droite et it1 part à gauche
			it.goRight();
			it2.goLeft();
			rotate180Aux(it, it2);
			// on remonte
			it.goUp();
			it2.goUp();
			// on permute
			it.goLeft();
			it2.goRight();
			rotate180Aux(it, it2);
			// puis on remonte
			it.goUp();
			it2.goUp();
		}
	}

	/**
	 * this devient rotation de image2 à 90 degrés dans le sens des aiguilles d'une
	 * montre.
	 *
	 * @param image2 image pour rotation
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
		switch (it.nodeType()) {
		case DOUBLE:
			it.goLeft();
			this.videoInverseAux(it);
			it.goUp();
			it.goRight();
			this.videoInverseAux(it);
			it.goUp();
			break;
		case LEAF:
			if (it.getValue().state == 1) {
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
	 * @param image2 image à agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorV(AbstractImage image2) {
		Iterator<Node> it = this.iterator();
		Iterator<Node> it2 = image2.iterator();
		it.clear();
		mirrorVAux(it, it2);
	}

	private void mirrorVAux(Iterator<Node> it, Iterator<Node> it2) {
		// TODO Auto-generated method stub
		if (!it2.isEmpty()) {
			it.addValue(it2.getValue());
			it.goLeft();
			it2.goRight();
			affectAux(it, it2);
			it.goUp();
			it2.goUp();
			it.goRight();
			it2.goLeft();
			affectAux(it, it2);
			it.goUp();
			it2.goUp();
		}
	}

	/**
	 * this devient image miroir horizontale de image2.
	 *
	 * @param image2 image à agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorH(AbstractImage image2) {
		Iterator<Node> it = this.iterator();
		Iterator<Node> it2 = image2.iterator();
		boolean horizontal = false;
		it.clear();
		mirrorHAux(it, it2, horizontal);
	}

	private void mirrorHAux(Iterator<Node> it, Iterator<Node> it2, boolean horizontal) {
		// TODO Auto-generated method stub
		if (!it2.isEmpty()) {
			// horizontal = !horizontal;
			it.addValue(it2.getValue());

			if (horizontal) {
				horizontal = !horizontal;
				it.goLeft();
				it2.goRight();
				;

				mirrorHAux(it, it2, horizontal);

				it.goUp();
				it2.goUp();

				it.goRight();
				;
				it2.goLeft();
				;

				mirrorHAux(it, it2, horizontal);

				it.goUp();
				it2.goUp();

			} else {

				horizontal = !horizontal;

				// on ne permute pas

				it.goRight();
				it2.goRight();

				mirrorHAux(it, it2, horizontal);

				it.goUp();
				it2.goUp();

				it.goLeft();
				it2.goLeft();

				mirrorHAux(it, it2, horizontal);
				it.goUp();
				it2.goUp();
			}

		}
	}

	/**
	 * this devient quart supérieur gauche de image2.
	 *
	 * @param image2 image à agrandir
	 * 
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomIn(AbstractImage image2) {
		if (!image2.isEmpty()) {
			Iterator<Node> it = this.iterator();
			it.clear();
			zoomInAux(it, image2.iterator(), 0);
		}

	}

	private void zoomInAux(Iterator<Node> it, Iterator<Node> it2, int depth) {
		if (depth < 2) {
			if (it2.nodeType() != NodeType.LEAF) {
				it2.goLeft();
				zoomInAux(it, it2, ++depth);
			} else
				affectAux(it, it2);
		} else
			affectAux(it, it2);

	}

	/**
	 * Le quart supérieur gauche de this devient image2, le reste de this devient
	 * éteint.
	 * 
	 * @param image2 image à réduire
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomOut(AbstractImage image2) {
		Iterator<Node> it2 = image2.iterator();
		Iterator<Node> it = this.iterator();
		it.clear();
		it.addValue(Node.valueOf(2)); // pour
		it.goRight(); // se positionner
		it.addValue(Node.valueOf(0)); // quart
		it.goUp(); // supérieur
		it.goLeft(); // gauche
		it.addValue(Node.valueOf(2)); // de this
		it.goRight(); //
		it.addValue(Node.valueOf(0)); //
		it.goUp(); //
		it.goLeft(); //
		zoomOutAux(it, it2, 0);
		if (it.getValue().state == 0) {
			it.goRoot();
			it.clear();
			it.addValue(Node.valueOf(0));
		}
	}

	public void zoomOutAux(Iterator<Node> it, Iterator<Node> it2, int h) {

		int droite, gauche;
		if (h < 14) {
			if (it2.getValue().state == 2) {
				it.addValue(it2.getValue());
				it.goLeft();
				it2.goLeft();
				zoomOutAux(it, it2, h + 1);
				gauche = it.getValue().state; // noeud gauche
				it.goUp();
				it2.goUp();
				it.goRight();
				it2.goRight();
				zoomOutAux(it, it2, h + 1);
				droite = it.getValue().state; // noeud droite
				it.goUp();
				it2.goUp();
				if (gauche == droite && droite != 2) {
					it.clear();
					it.addValue(Node.valueOf(gauche));
				}
			} else
				it.addValue(it2.getValue());
		} else {
			if (it2.getValue().state == 2) {
				it2.goLeft();
				gauche = it2.getValue().state;
				it2.goUp();
				it2.goRight();
				droite = it2.getValue().state;
				it2.goUp();
				if ((gauche == 0 && droite == 0) || (gauche == 0 && droite == 2) || (gauche == 2 && droite == 0))
					it.addValue(Node.valueOf(0));
				else
					it.addValue(Node.valueOf(1));
			} else
				it.addValue(Node.valueOf(it2.getValue().state));
		}
	}

	/**
	 * this devient l'intersection de image1 et image2 au sens des pixels allumés.
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
		this.interAux(it, it1, it2, aGauche);
	}

	private void interAux(Iterator<Node> it, Iterator<Node> it1, Iterator<Node> it2, boolean aGauche) {
		if (it1.getValue().state == 2 && it2.getValue().state == 2) {
			it.addValue(it1.getValue());
			if (aGauche) {
				it.goLeft();
				it1.goLeft();
				it2.goLeft();
				this.interAux(it, it1, it2, aGauche);
				it.goUp();
				it1.goUp();
				it2.goUp();
			} else {
				it.goRight();
				it1.goRight();
				it2.goRight();
				this.interAux(it, it1, it2, aGauche);
				it.goUp();
				it1.goUp();
				it2.goUp();
			}

		} else if (it1.getValue().state == it2.getValue().state) {
			it.setValue(it1.getValue());
			it.goUp();
			it1.goUp();
			it2.goUp();
			if (aGauche) {
				aGauche = false;
			} else {
				aGauche = true;
			}
			this.interAux(it, it1, it2, aGauche);
			it.goUp();
			it1.goUp();
			it2.goUp();
		} else {
			if (it1.getValue().state != 2) {
				it.setValue(it1.getValue());
			} else {
				it.setValue(it2.getValue());
			}
			it.goUp();
			it1.goUp();
			it2.goUp();
			if (aGauche) {
				aGauche = false;
			} else {
				aGauche = true;
			}
			this.interAux(it, it1, it2, aGauche);
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
		return this.testDiagonalAux(this.iterator(), true, true);

	}

	private boolean testDiagonalAux(Iterator<Node> it, boolean horizontal, boolean goToLeft) {
		if (it.getValue().state == 1) {
			return true;
		}
		if (it.getValue().state == 0) {
			return false;
		}

		if (horizontal) {
			it.goLeft();
			boolean bLeft = this.testDiagonalAux(it, false, true);
			it.goUp();
			it.goRight();
			boolean bRight = this.testDiagonalAux(it, false, false);
			it.goUp();
			return bLeft && bRight;
		} else {
			if (goToLeft) {
				it.goLeft();
				boolean bLeft = this.testDiagonalAux(it, true, true);
				it.goUp();
				return bLeft;
			} else {
				it.goRight();
				boolean bRight = this.testDiagonalAux(it, true, false);
				it.goUp();
				return bRight;
			}
		}
	}

	/**
	 * @param x1 abscisse du premier point
	 * @param y1 ordonnée du premier point
	 * @param x2 abscisse du deuxième point
	 * @param y2 ordonnée du deuxième point
	 * @pre !this.isEmpty()
	 * @return true si les deux points (x1, y1) et (x2, y2) sont représentés par la
	 *         même feuille de this, false sinon
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
	 * @param image2 autre image
	 * @pre !this.isEmpty() && !image2.isEmpty()
	 * @return true si this est incluse dans image2 au sens des pixels allumés false
	 *         sinon
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
