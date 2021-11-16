package graphicalElements;

import javax.swing.*;

import frog.Frog;
import gameCommons.IFrog;
import util.Direction;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FroggerGraphic extends JPanel implements IFroggerGraphics, KeyListener {
	private ArrayList<Element> elementsToDisplay;
	private int pixelByCase = 16;
	private int width;
	private int height;
	private IFrog frog;
	private JFrame frame;
	public int minSpeedInTimerLoops = 5;
	public double defaultDensity = 0.2;
	public int numberOfRestarts = 0;

	public FroggerGraphic(int width, int height) {
		JFrame frame = new JFrame();
		this.width = width;
		this.height = height;
		elementsToDisplay = new ArrayList<Element>();

		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(width * pixelByCase, height * pixelByCase));

		this.frame = new JFrame("Frogger");
		//part for the menu

		JMenuBar menu_bar1 = new JMenuBar();
		JMenu menu1 = new JMenu("Selection");

		//Different choices
		JMenuItem Difficulte1 = new JMenuItem("Difficulté_1");
		JMenuItem Difficulte2 = new JMenuItem("Difficulté_2");
		JMenuItem Difficulte3 = new JMenuItem("Difficulté_3");
		JMenuItem Restart = new JMenuItem("Restart");

		//Add choices to menu
		menu1.add(Difficulte1);
		menu1.add(Difficulte2);
		menu1.add(Difficulte3);
		menu1.add(Restart);
		menu_bar1.add(menu1);
		frame.setJMenuBar(menu_bar1);



		Difficulte1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				defaultDensity = 0.1D;

			}
		});

		Difficulte2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				defaultDensity = 0.2;
			}
		});

		Difficulte3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				defaultDensity = 0.5;
			}
		});

		/*
		Restart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					defaultDensity = 0.2;
					System.out.println("Game restarted with OG parameters");
					frog.setLife(true);
					frog.setRestart(true);
					clear();
			}
		});

		 */

		this.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(this);

	}

	public void setDefaultDensity(double d){
		this.defaultDensity = d;
	}

	public double getDensity(){
		return defaultDensity;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Element e : elementsToDisplay) {
			g.setColor(e.color);
			g.fillRect(pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), pixelByCase, pixelByCase - 1);
		}
	}

	public void setMenuBar(JMenuBar j){
		frame.setJMenuBar(j);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			frog.move(Direction.up);
			break;
		case KeyEvent.VK_DOWN:
			frog.move(Direction.down);
			break;
		case KeyEvent.VK_LEFT:
			frog.move(Direction.left);
			break;
		case KeyEvent.VK_RIGHT:
			frog.move(Direction.right);

		}
	}

	public void clear() {
		this.elementsToDisplay.clear();
	}

	public void add(Element e) {
		this.elementsToDisplay.add(e);
	}

	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	public void endGameScreen(String s) {
		frame.remove(this);
		JLabel label = new JLabel(s);
		frame.getContentPane().removeAll();
		JButton btn = ajouteBoutton();
		label.setFont(new Font("Verdana", 1, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setSize(this.getSize());
		frame.getContentPane().add(label);
		//frame.addKeyListener(btn);
		frame.repaint();
	}


	public void clearEndGameScreen(String s){
		frame.remove(this);
		JLabel label = new JLabel(s);
		label.setFont(new Font("Verdana", 1, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setSize(this.getSize());
		frame.getContentPane().add(label);
		frame.repaint();
	}

	public void restart(){
		clear();
		numberOfRestarts ++;
		frame.remove(this);
		elementsToDisplay = new ArrayList<Element>();

		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(width * pixelByCase, height * pixelByCase));

		this.frame = new JFrame("Frogger");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		frog.setRestart(true);
		frog.setLife(true);
		frame.addKeyListener(this);
	}

	public void setFrame(JFrame f){
		this.frame = f;
	}

	public int getNumberOfRestarts(){
		return numberOfRestarts;
	}


	public JButton ajouteBoutton() {
		JButton btn = null;
		btn = new JButton("Rejouer");
		btn.setBounds(0, 0, 100, 40);
		frame.getContentPane().add(btn);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				defaultDensity = 0.2;
				System.out.println("Game restarted with OG parameters");
				clear();
				//frog.setLife(true);
				frog.setRestart(true);
				System.out.println(frog.getRestart());

				 */
				restart();
			}
		});
		return btn;
		//System.out.println(frog.getRestart());
		//System.out.println("vie grenouille = " + frog.getEstEnVie());

	}

}
