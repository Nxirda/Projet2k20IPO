package frog;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

import java.awt.*;

public class Matrix {
    private Game game;
    private Case leftPosition;
    private final Color colorRtL = Color.RED;

    public Matrix(Game game,Case lP){
        this.game = game;
        this.leftPosition = new Case ( lP.absc ,lP.ord);
    }

    public int getOrd(){
        return this.leftPosition.ord;
    }
    public Case getCase(){
        return this.leftPosition;
    }
    public void setOrd(int i){
        this.leftPosition = new Case (this.leftPosition.absc,i);
    }

    public boolean estSurTerrain() {
        return this.leftPosition.absc  > 0 || this.leftPosition.absc < this.game.width;
    }

    public boolean couvreCases (Case maCase) {
        if (maCase.ord != this.leftPosition.ord) {
            return false;
        } else {
            return maCase.absc == this.leftPosition.absc; //&& maCase.absc < this.leftPosition.absc ;
        }
    }


    /* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture */
    public void addToGraphics() {
        Color color = colorRtL;
        game.getGraphic().add(new Element(leftPosition.absc , leftPosition.ord - this.game.getFrog().ord, color));
    }

}

