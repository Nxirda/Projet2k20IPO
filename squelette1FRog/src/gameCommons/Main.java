package gameCommons;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

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
		
		//Cr�ation de l'interface graphique
		IFroggerGraphics graphic = new FroggerGraphic(width, height);
		//Cr�ation de la partie
		double defaultDensity1 = graphic.getDensity();
		//Difficulty one
		//Game game1 = new Game(graphic, width, height, minSpeedInTimerLoops, 0.1D);
		//Difficulty two
		Game game2 = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity1);
		//Difficulty three
		//Game game3 = new Game(graphic, width, height, minSpeedInTimerLoops, 0.4D);

		Game game = game2;
		//Cr�ation et liason de la grenouille
		IFrog frog = new Frog(game);
		game.setFrog(frog);
		graphic.setFrog(frog);
		//Cr�ation et liaison de l'environnement
		IEnvironment env = new environment(game);
		game.setEnvironment(env);
				
		//Boucle principale : l'environnement s'actualise tous les tempo milisecondes
		Timer timer = new Timer(tempo, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(graphic.getDensity() != game.defaultDensity){
					game.setDensity(graphic.getDensity());
					String i = ""+graphic.getDensity();
					switch (i) {
						case ("0.1"):
							System.out.println("You restarted in difficulty 1" + " densite de "+game.defaultDensity);
							env.setGame(game);
							game.restart(game.defaultDensity);
							break;
						case ("0.2"):
							System.out.println("You restarted in difficulty 2" + " densite de "+game.defaultDensity);
							env.setGame(game);
							game.restart(game.defaultDensity);
							break;
						case ("0.5"):
							System.out.println("You restarted in difficulty 3" + " densite de "+game.defaultDensity);
							env.setGame(game);
							game.restart(game.defaultDensity);
							break;
					}
				}
				if(frog.getRestart() == true){
					try {
						Thread.sleep(500);
					}catch (InterruptedException ie){
					}
					//System.out.println(frog.getPosition().ord);
					//System.out.println(frog.getRestart());
					game.score = 0;
					game.setDensity(graphic.getDensity());
					//game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity1);
					game.setGame(new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity1));
					env.setGame(game);
					//game.restart(game.defaultDensity);
					//graphic.setFrog(frog);
					frog.setMaCase(new Case(width/2,0));
					//frog.setRestart(false);
					graphic.restart();
					frog.setRestart(false);
					game.setFrog(frog);
					graphic.setFrog(frog);
					game.update();
					//System.out.println(frog.getEstEnVie());
					//System.out.println(frog.getPosition().ord);


				}
				//System.out.println(frog.getEstEnVie());
				game.update();
				graphic.repaint();
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}
}
