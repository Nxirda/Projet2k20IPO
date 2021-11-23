package gameCommons;

import util.Case;
import util.Direction;

public interface IFrog {
	
	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
	public Case getPosition();
	
	/**
	 * Donne la direction de la grenouille, c'est � dire de son dernier mouvement 
	 * @return
	 */
	//public Direction getDirection();

	//public void setGame(Game game);
	/**
	 * D�place la grenouille dans la direction donn�e et teste la fin de partie
	 * @param key
	 */
	public void move(Direction key);

	public int getAbsc();

	public void setLife(Boolean b);

	//public void setMaCase(Case case1);

	public boolean getEstEnVie();

	public void setRestart(boolean b);

	public boolean getRestart();

	public boolean estDansCase(Case c);

}
