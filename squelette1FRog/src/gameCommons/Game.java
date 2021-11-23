package gameCommons;

import java.awt.*;
import java.util.Random;

import frog.Frog;
import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;
import util.Case;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public int height;
	public final int minSpeedInTimerLoops;
	public double defaultDensity;
	public int score = 0;
	public int gameRestarts = 0;

	// Lien aux objets utilis�s
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant d�placement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
	}

	public void setDensity(double D){
		this.defaultDensity = D;
	}
	public Game getGame(){
		return this;
	}

	public void setGame(Game g){
		this.graphic = g.graphic;
		this.defaultDensity = g.defaultDensity;
	}

	/**
	 * Lie l'objet frog � la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	public Case getFrog(){
		return this.frog.getPosition();
	}

	public IFrog donneFrog(){
		return frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un �cran de fin appropri� si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		if(! environment.isSafe(this.frog.getPosition())){
			graphic.clear();
			String s ="<html> You Lost Haha ! <br> You survived crossing " + (this.score)+ " roads ! </html>";
			frog.setLife(false);
			this.graphic.endGameScreen(s);
		}
		return false;
	}
	public boolean testMatrix() {
		if(environment.isInMatrix()){
			graphic.clear();
			String s ="<html> You Lost Haha ! <br> You survived crossing " + (this.score)+ " roads ! </html>";
			frog.setLife(false);
			this.graphic.matrixGameScreen();
		}
		return false;
	}

	/**
	 * Teste si la partie est gagnee et lance un �cran de fin appropri� si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagn�e
	 */

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public void update() {
		if(this.frog.getEstEnVie()) {
			graphic.clear();
			environment.update();
			Case c = new Case(this.frog.getAbsc(), 0);
			this.graphic.add(new Element(c, Color.GREEN));
			testLose();
			testMatrix();
		}
	}
}
