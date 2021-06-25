package fr.istic.prg1.tree;

import java.awt.Frame;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import fr.istic.prg1.tree_util.Iterator;
import fr.istic.prg1.tree_util.Node;
import fr.istic.prg1.tree_util.NodeType;

import java.awt.event.WindowEvent;

/**
 * Classe pour l'affichage des images de la classe Image sous forme d'arbres
 * binaires.
 * 
 * @author MickaÃ«l Foursov <foursov@univ-rennes1.fr>
 * @version 4.1
 * @since 2015-11-17
 */

public final class TreeFrame extends Frame {
	private static final long serialVersionUID = 3552009243767566671L;
	private Iterator<Node> it;
	private static int spaceX = 30; // espacement horizontal des noeuds
	private static int smallestNodeSizeX = 30; // taille minimale des noeuds
	private static final int spaceY = 40; // espacement vertical des noeuds
	private static Ellipse2D.Float circle = new Ellipse2D.Float(100, 100, 100,
			100);
	private static Font font = new Font("Tiresias PCFont Z", Font.PLAIN, 18);

	/**
	 * CrÃ©er une fenÃªtre pour l'affichage de a sous forme d'arbre binaire.
	 * 
	 * @param a
	 *            image Ã  afficher
	 */
	public TreeFrame(AbstractImage a) {
		super();
		this.it = a.iterator();
		int height = a.height();
		int size = a.numberOfNodes();
		if (size > 40)
			spaceX = (int) Math.ceil(1200. / (size + 3.));
		smallestNodeSizeX = (spaceX < 15) ? 15 : spaceX;
		setSize((size + 3) * spaceX, (height + 2) * spaceY);
		setTitle("Arbre binaire");
		setBackground(Color.white);
		setFont(font);
		setVisible(true); // affichage effectif
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent winEvt) {
				dispose();
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		// effet : appelee implicitement pour effectuer l'affichage du Frame
		Graphics2D ga = (Graphics2D) g;
		ga.setStroke(new BasicStroke(2)); // epaisseur des arcs
		it.goRoot();
		assert !it.isEmpty() : "affichage sur le butoir";
		it.goLeft();
		int sizeGauche;
		if (it.isEmpty()) {
			sizeGauche = 0;
		} else {
			sizeGauche = AbstractImage.xNumberOfNodes(it);
		}
		it.goUp();
		ga.setStroke(new BasicStroke(4));
		paintAux(ga, it, spaceX * (sizeGauche + 2), spaceY + 10);
		// System.out.println("fin paint");
		// g.finalize();
	}

	private void paintAux(Graphics2D ga, Iterator<Node> it, int x, int y) {
		// fonction auxiliaire d'affichage
		// affiche les arcs connectant le noeud courant aux fils eventuels
		// fait l'appel recursif pour les fils en calculant la position x
		// ensuite affiche le noeud courant et sa valeur
		// dans cet ordre cela permet d'avoir des arcs bien positionnes
		assert !it.isEmpty() : "affichage sur le butoir";
		int sizeRightLeft = 0;
		int sizeLeftRight = 0;
		if (it.nodeType() == NodeType.DOUBLE) {
			it.goRight();
			it.goLeft();
			if (!it.isEmpty())
				sizeRightLeft = AbstractImage.xNumberOfNodes(it);
			it.goUp();
			int newX = x + (sizeRightLeft + 1) * spaceX;
			int newY = y + spaceY;
			Line2D.Float line = new Line2D.Float(x, y, newX, newY);
			ga.setPaint(Color.green);
			ga.draw(line);
			paintAux(ga, it, newX, newY);
			it.goUp();

			it.goLeft();
			it.goRight();
			if (!it.isEmpty())
				sizeLeftRight = AbstractImage.xNumberOfNodes(it);
			it.goUp();
			newX = x - (sizeLeftRight + 1) * spaceX;
			newY = y + spaceY;
			line = new Line2D.Float(x, y, newX, newY);
			ga.setPaint(Color.green);
			ga.draw(line);
			paintAux(ga, it, newX, newY);
			it.goUp();
		}
		int smallestNodeSizeY = (smallestNodeSizeX < 25) ? 25
				: smallestNodeSizeX;
		circle.setFrame(x - spaceX / 2, y - spaceY / 2, smallestNodeSizeX,
				smallestNodeSizeY);
		// x et y sont les coordonnees du centre du noeud
		// et il faut donner celles de l'angle superieur gauche
		ga.setPaint(Color.red);
		ga.fill(circle);
		ga.setPaint(Color.black);
		String string = "" + it.getValue().state;
		int length = string.length();
		ga.drawString(string, x - length * spaceX / 5, y); // pour centrer la
															// valeur
	}
}