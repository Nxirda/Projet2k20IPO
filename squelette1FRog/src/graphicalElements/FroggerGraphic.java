package graphicalElements;

import javax.swing.*;

import java.net.InetAddress;

import frog.Matrix;
import gameCommons.IFrog;
import util.Direction;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.net.UnknownHostException;
import java.util.ArrayList;


public class FroggerGraphic extends JPanel implements IFroggerGraphics, KeyListener {
	private ArrayList<Element> elementsToDisplay;
	private int pixelByCase = 16;
	private int width;
	private int height;
	private IFrog frog;
	private Matrix matrix;
	private JFrame frame;
	public double defaultDensity = 0.2;
	public int numberOfRestarts = 0;
	private int straw;

	public FroggerGraphic(int width, int height) {
		this.width = width;
		this.height = height;
		elementsToDisplay = new ArrayList<Element>();

		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(width * pixelByCase, height * pixelByCase));
		this.frame = new JFrame("Frogger");
		this.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		//matrixGameScreen();
		beforeGameScreen();
		//elementsToDisplay.add(new Element(this.matrix.getMaCase(), Color.red));
		frame.setVisible(true);
		frame.addKeyListener(this);
	}

	//Méthodes

	public int getStraw(){
		return straw;
	}

	public void setNumberOfRestarts(int i){
		numberOfRestarts += i;
	}

	public int getNumberOfRestarts(){
		return numberOfRestarts;
	}

	public void setFrame(JFrame f){
		this.frame = f;
	}


	public double getDensity(){
		return defaultDensity;
	}

	public ArrayList<Element> getElementsToDisplay(){
		return elementsToDisplay;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Element e : elementsToDisplay) {
			g.setColor(e.color);
			g.fillRect(pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), pixelByCase, pixelByCase - 1);
		}
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


	//partie gérant l'écran de début

	public void beforeGameScreen() {
		clear();
		frame.dispose();
		frame.getContentPane().removeAll();

		//dimension game : 416,348

		ImageIcon img = new ImageIcon("/Users/adri/IdeaProjects/Projet2k20IPO/squelette1FRog/src/Screens/start-game2.jpg");
		Image image = img.getImage();
		Image newing = image.getScaledInstance(416, 348,  java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newing);
		JLabel back = new JLabel(img);
		back.add(ajouteBoutton2());

		JLabel label = new JLabel("Welcome to Frogger");
		/*
		Icon back1 = new ImageIcon("/Users/adri/Desktop/matrix-code.gif");
		JLabel label = new JLabel(back1);
		*/

		//partie ecriture de welcome to frogger
		label.setFont(new Font("Verdana", 1, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.red);
		label.setBounds(0,-125,100,100);
		label.setSize(this.getSize());

		frame.getContentPane().add(label);
		frame.getContentPane().add(back);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		setFrame(frame);
		frame.setVisible(true);

		frame.repaint();
	}

	public JButton ajouteBoutton2(){
		JButton btn = null;
		btn = new JButton ("Difficulté 1");
		btn.setForeground(Color.red);
		btn.setBounds(25,280,100,25);
		frame.getContentPane().add(btn);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				straw = 1;
				restart();
			}
		});
		btn = new JButton ("Difficulté 2");
		btn.setForeground(Color.red);
		btn.setBounds(145,280,100,25);
		frame.getContentPane().add(btn);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				straw = 2;
				restart();
			}
		});

		btn = new JButton ("Difficulté 3");
		btn.setForeground(Color.red);
		btn.setBounds(265,280,100,25);
		frame.getContentPane().add(btn);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				straw = 3;
				restart();
			}
		});
		return btn;
	}

	//partie gérant l'écran de fin

	public JButton ajouteBoutton() {
		JButton btn = null;
		btn = new JButton("Rejouer");
		btn.setBounds(160,200,100,50);
		frame.getContentPane().add(btn);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				//numberOfRestarts ++;
				restart();
			}
		});
		return btn;
	}

	public void endGameScreen(String s) {
		frame.remove(this);
		frame.getContentPane().removeAll();
		JLabel label = new JLabel(s);

		ImageIcon img = new ImageIcon("/Users/adri/IdeaProjects/Projet2k20IPO/squelette1FRog/src/Screens/engamescreen.png");
		Image image = img.getImage();
		Image newing = image.getScaledInstance(416, 348,  java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newing);
		JLabel back = new JLabel(img);
		back.add(ajouteBoutton());

		//JButton btn = ajouteBoutton();
		label.setFont(new Font("Verdana", 1, 20));
		label.setForeground(Color.blue);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setSize(this.getSize());
		frame.getContentPane().add(label);
		frame.getContentPane().add(back);
		frame.setVisible(true);
		frame.repaint();
	}

	public void matrixGameScreen(){
		clear();
		frame.dispose();
		frame.getContentPane().removeAll();
		Icon back1 = new ImageIcon("/Users/adri/IdeaProjects/Projet2k20IPO/squelette1FRog/src/Screens/matrix-code.gif");
		JLabel label = new JLabel(back1);
		String nomHote ;
		String adresseIPLocale ;
		JLabel label2 = new JLabel();
		try{
			InetAddress inetadr = InetAddress.getLocalHost();
			//nom de machine
			nomHote = (String) inetadr.getHostName();
			System.out.println("Nom de la machine = "+nomHote );
			//adresse ip sur le réseau
			adresseIPLocale = (String) inetadr.getHostAddress();
			System.out.println("Adresse IP locale = "+adresseIPLocale );
			label2 = new JLabel("<html> System Failure <br> Nom de la machine : " +nomHote +"<br> Your IP adress is : " + adresseIPLocale +"</html>");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		label2.setSize(this.getSize());
		label2.setForeground(Color.green);
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label2);
		frame.getContentPane().add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		setFrame(frame);
		frame.setVisible(true);
		frame.repaint();

		/*
		while(true){
			Random random = new Random();
			int nb;
			nb = random.nextInt(255);
			System.out.print(nb+".");
		}
		 */
	}


	public void restart(){
		clear();
		frame.dispose();
		setFrame(new JFrame("Frogger"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		frog.setRestart(true);
		frog.setLife(true);
		frame.addKeyListener(this);
	}

}
