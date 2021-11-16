package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class Frog implements IFrog {
	
	private Game game;
	private Case maCase;
	private Direction dir;
	private boolean estEnVie;
	private boolean restart;

	public Frog(Game game){
		this.game = game;
		this.maCase = new Case(game.width/2,0);
		this.dir = Direction.up;
		this.estEnVie = true;
	}

	public void setRestart(boolean b){
		restart = b;
	}

	public boolean getRestart(){
		return restart;
	}

	public void setMaCase(Case case1){
		maCase = case1;
	}

	public boolean getEstEnVie(){
		return estEnVie;
	}

	public void setGame(Game game){
		this.game = game;
	}

	public int getAbsc() {
		return this.maCase.absc;
	}

	public Case getPosition(){
		return this.maCase;
	}

	public Direction getDirection(){
		return this.dir;
	}

	public void move(Direction key){
		if (key == Direction.up && estEnVie){// && this.maCase.ord +1 <= this.game.height){
			this.maCase = new Case (this.maCase.absc, this.maCase.ord +1);
			this.game.height ++;
			this.game.score ++;
		}

		if (key == Direction.down && this.maCase.ord -1 >= 0 && estEnVie){
			this.maCase = new Case (this.maCase.absc, this.maCase.ord -1);
			this.game.height --;
			this.game.score --;
		}

		if (key == Direction.right && this.maCase.absc +1 <= this.game.width-1 && estEnVie){
			this.maCase = new Case (this.maCase.absc +1, this.maCase.ord);
		}
		if (key == Direction.left && this.maCase.absc -1 >= 0 && estEnVie){
			this.maCase = new Case (this.maCase.absc -1, this.maCase.ord);
		}
	}

	public void setLife(Boolean b){
		this.estEnVie = b;
	}

}
