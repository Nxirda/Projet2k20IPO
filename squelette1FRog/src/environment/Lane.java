package environment;

import java.util.ArrayList;
import java.util.Iterator;

import frog.Matrix;
import util.Case;
import gameCommons.Game;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private Matrix m;
	private boolean leftToRight;
	double density;
	private int timer;
	private boolean matrix = false;

	public Lane(Game game, int ord, double density, boolean x) {
		this.game = game;
		this.ord = ord;
		this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;
		this.leftToRight = game.randomGen.nextBoolean();
		this.density = density;
		this.matrix = x;
		this.m = new Matrix(game, new Case(3,3));
		//Matrixes.add(m);
		for(int i = 0; i <  game.width; ++i) {
			this.moveCars(true);
			this.mayAddCar();
		}
	}

	public Matrix getM(){
		return this.m;
	}

	public void isMatrix(boolean x){
		this.matrix = x;
	}
	public boolean getMatrx(){
		return this.matrix;
	}

	public boolean hasCars(){
		if(this.cars.isEmpty()){
			return false;
		}
		return true;
	}

	public int getOrd(){
		return this.ord;
	}

	public Lane(Game game, int ord, boolean x) {
		this(game, ord, game.defaultDensity, x);
	}

	public void update() {
		this.timer ++;
		if(this.matrix == true) {
			this.m.addToGraphics();
		}
		if (this.timer <= this.speed) {
			this.moveCars(false);
		} else {
			this.moveCars(true);
			this.removeOldCars();
			this.mayAddCar();
			this.timer = 0;
		}
	}

	public void setOrd(int ord){
		this.ord = ord;
	}

		// TODO

		// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
		// d'horloge" �gal � leur vitesse
		// Notez que cette m�thode est appel�e � chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut �tre ajout�e

	// TODO : ajout de methodes

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(premiereCase()) && isSafe(avantPremiereCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, avantPremiereCase(), leftToRight));
			}
		}
	}


	public boolean isSafe(Case pos) {
		Iterator i = this.cars.iterator();

		while(i.hasNext()) {
			Car car = (Car) i.next();
			if (car.couvreCases(pos)) {
				return false;
			}
		}
		return true;
	}

	private void moveCars(boolean b) {
		Iterator i = this.cars.iterator();
		while(i.hasNext()) {
			Car car = (Car) i.next();
			car.move(b);
		}
		this.removeOldCars();
	}

	private void removeOldCars() {
		ArrayList<Car> toBeRemoved = new ArrayList();
		Iterator i = this.cars.iterator();
		Car c;
		while(i.hasNext()) {
			c = (Car) i.next();
			if (!c.estSurTerrain() || c.getOrd() < this.game.getFrog().ord - 5) {
				toBeRemoved.add(c);
			}
		}
		for(int i2=0; i2 < toBeRemoved.size(); i2++){
			c = toBeRemoved.get(i2);
			this.cars.remove(c);
		}
	}

	private Case premiereCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case avantPremiereCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

	public String toString() {
		return "Lane [ord=" + this.ord + ", cars=" + this.cars + "]";
	}

	public void setDensity(double d){
		density = d;
	}

}
