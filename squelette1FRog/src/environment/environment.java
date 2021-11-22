package environment;

import java.util.ArrayList;
import java.util.Iterator;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class environment implements IEnvironment {

    private ArrayList<Lane> routes;
    private Game game;

    public environment(Game game) {
        this.game = game;
        this.routes = new ArrayList();
        this.routes.add(new Lane(game, 0, 0.0D));

        for(int i = 1; i < game.height ; ++i) {
            if(i % 5 == 0){
                this.routes.add(new Lane(game,i,0.0D));
            }
            else {
                this.routes.add(new Lane(game, i));
            }
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
            this.routes.add(new Lane(game,this.game.height, this.game.defaultDensity));
        }
        for(int i=0;i<this.routes.size();i++) {
            if(i%5 == 0){
                routes.get(i).setDensity(0.0D);
                routes.get(i).update();
            }
            Lane lane = this.routes.get(i);
            lane.update();
        }
    }

    public void clear(){
        this.routes = new ArrayList();
    }

    public void setDensity(double density){
        for (Lane lane : routes) {
            lane.setDensity(density);
        }
    }

    public void setGame(Game game){
        this.game = game;
    }

}

