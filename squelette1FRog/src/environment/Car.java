package environment;

import java.awt.Color;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;

	public Car(Game game,Case lP, boolean lTR){
		this.game = game;
		this.leftPosition = new Case (lTR ? lP.absc - this.length : lP.absc, lP.ord);
		this.leftToRight = lTR;
		this.length =  game.randomGen.nextInt(3) + 1;
	}

	public int getOrd(){
		return this.leftPosition.ord;
	}
	public void setOrd(int i){
		this.leftPosition = new Case (this.leftPosition.absc,i);
	}
	
	public void move(boolean bool){
		if(bool) {
			this.leftPosition = new Case(this.leftPosition.absc + (this.leftToRight ? 1 : -1), this.leftPosition.ord);
		}
		this.addToGraphics();
	}

	public boolean estSurTerrain() {
		return this.leftPosition.absc + this.length > 0 || this.leftPosition.absc < this.game.width;
	}

	public boolean couvreCases (Case maCase) {
		if (maCase.ord != this.leftPosition.ord) {
			return false;
		} else {
			return maCase.absc >= this.leftPosition.absc && maCase.absc < this.leftPosition.absc + this.length;
		}
	}
	
	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture */
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord - this.game.getFrog().ord, color));
		}
	}

}
