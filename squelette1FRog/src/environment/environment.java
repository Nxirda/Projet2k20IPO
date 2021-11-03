package environment;

import java.util.ArrayList;
import java.util.Iterator;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
//import environment.Lane;

public class environment implements IEnvironment {

    private ArrayList<Lane> routes;
    private Game game;

    public environment(Game game) {
        this.game = game;
        this.routes = new ArrayList();
        this.routes.add(new Lane(game, 0, 0.0D));

        for(int i = 1; i < game.height - 1; ++i) {
            this.routes.add(new Lane(game, i));
        }

        this.routes.add(new Lane(game, game.height - 1, 0.0D));
    }

    public boolean isSafe(Case c) {
        return ((Lane)this.routes.get(c.ord)).isSafe(c);
    }

    public boolean isWinningPosition(Case c) {
        return c.ord == this.game.height - 1;
    }

    public void update() {
        Iterator i = this.routes.iterator();

        while(i.hasNext()) {
            Lane lane = (Lane)i.next();
            lane.update();
        }

    }
}

