package environment;

import java.util.ArrayList;
import java.util.Iterator;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class environment implements IEnvironment {

    private ArrayList<Lane> routes;
    private Game game;
    public int rounds;

    public environment(Game game) {
        this.game = game;
        this.routes = new ArrayList();
        this.routes.add(new Lane(game, 0, 0.0D));

        for(int i = 1; i < game.height ; ++i) {
            this.routes.add(new Lane(game, i));
        }
    }

    public boolean isSafe(Case c) {
        if(this.routes.get(c.ord).isSafe(c)){
            return true;
        }
        return false;
    }
    /*
    public boolean isWinningPosition(Case c) {
        return c.ord == this.game.height - 1;
    }
     */

    public void update() {
        if (this.routes.size() <= this.game.height){
            System.out.println("hauteur jeux" + this.game.height + "\n");
            System.out.println("taille vecteur route" + this.routes.size() + "\n");
            this.routes.add(new Lane(game,this.game.height));
        }
        for(int i=0;i<this.routes.size();i++) {
            //System.out.println("taille vecteur route" + this.routes.size() + "\n");
            Lane lane = this.routes.get(i);
            lane.update();
        }

    }

}

