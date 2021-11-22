package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import util.Case;

import frog.Frog;
import environment.environment;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;

public class Main {

	public static void main(String[] args) {

		//Caract�ristiques du jeu
		int width = 26;
		int height = 20;
		int tempo = 100;
		int minSpeedInTimerLoops = 5;
		ArrayList<Game> games = new ArrayList<>();
		ArrayList<IFroggerGraphics> graphics = new ArrayList<>();
		ArrayList<IEnvironment> environments = new ArrayList<>();
		ArrayList<IFrog> frogs = new ArrayList<>();

		IFroggerGraphics graphic = new FroggerGraphic(width, height);
		double defaultDensity1 = graphic.getDensity();
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity1);
		IFrog frog = new Frog(game);
		game.setFrog(frog);
		graphic.setFrog(frog);
		IEnvironment env = new environment(game);
		game.setEnvironment(env);

		games.add(game);
		graphics.add(graphic);
		environments.add(env);
		frogs.add(frog);
				
		//Boucle principale : l'environnement s'actualise tous les tempo milisecondes
		Timer timer = new Timer(tempo, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//début
				if(frogs.get(frogs.size() -1).getRestart() == true){
					try {
						Thread.sleep(500);
					}catch (InterruptedException ie){
					}
					switch (graphics.get(graphics.size() -1).getStraw()) {
						case (1):
							games.remove(0);
							environments.remove(0);
							frogs.remove(0);
							graphic.clear();
							games.add(new Game(graphics.get(0), width, height, minSpeedInTimerLoops, 0.1));
							frogs.add(new Frog(games.get(games.size() -1)));
							games.get(0).setFrog(frogs.get(0));
							graphics.get(0).setFrog(frogs.get(0));
							environments.add(new environment(games.get(0)));
							games.get(0).setEnvironment(environments.get(0));
							break;
						case (2):
							games.remove(0);
							environments.remove(0);
							frogs.remove(0);
							graphic.clear();
							games.add(new Game(graphics.get(0), width, height, minSpeedInTimerLoops, 0.2));
							frogs.add(new Frog(games.get(games.size() -1)));
							games.get(0).setFrog(frogs.get(0));
							graphics.get(0).setFrog(frogs.get(0));
							environments.add(new environment(games.get(0)));
							games.get(0).setEnvironment(environments.get(0));
							break;
						case (3):
							games.remove(0);
							environments.remove(0);
							frogs.remove(0);
							graphic.clear();
							games.add(new Game(graphics.get(0), width, height, minSpeedInTimerLoops, 0.5));
							frogs.add(new Frog(games.get(games.size() -1)));
							games.get(0).setFrog(frogs.get(0));
							graphics.get(0).setFrog(frogs.get(0));
							environments.add(new environment(games.get(0)));
							games.get(0).setEnvironment(environments.get(0));
							break;
					}
					/*
					game.score = 0;
					game.setDensity(graphic.getDensity());
					game.setGame(new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity1));
					env.setGame(game);
					frog.setMaCase(new Case(width/2,0));
					frog.setRestart(false);
					game.setFrog(frog);
					graphic.setFrog(frog);

					 */
				}
				games.get(0).update();
				graphics.get(0).repaint();
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}
}
