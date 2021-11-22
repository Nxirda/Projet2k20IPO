package frog;

import gameCommons.Game;
import gameCommons.IMatrix;
import util.Case;

import java.util.Random;

public class Matrix implements IMatrix {

    private Game game;
    private Case maCase;

    public Matrix(Game game){
        this.game = game;
        Random random = new Random();
        int nb;
        int nb2;
        nb = random.nextInt(this.game.width);
        nb2 = random.nextInt(this.game.height);
        this.maCase = new Case(nb,nb2);
    }

    public Case getMaCase() {
        return maCase;
    }
}
