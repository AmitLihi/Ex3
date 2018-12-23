package GUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import File_format.Path2KML;
import Game.Fruit;
import Game.Game;
import Game.Map;
import Game.Pacman;
import Game.Path;
import Game.ShortestPathAlgo;
import Geom.Point3D;

/**
 * This class represents the GUI of this game
 * @author Amit & Lihi
 *
 */
public class MyFrame extends JFrame implements MouseListener
{
	public BufferedImage myImage;
	public BufferedImage myImageFruit;
	public BufferedImage myImagePacman;
	public Game game = new Game();
	public Map map = new Map();
	public int pacORFru=-1;//pacman =0 fruit =1
	public ArrayList <Path> finalPath;
	public ShortestPathAlgo s;
	public boolean flag = false;
	public Game gamePlay = new Game();
	public Path2KML path2kml = new Path2KML();
	int IDF = 0;
	int IDP = 0;



	public MyFrame() 
	{
		initGUI();		
		this.addMouseListener(this);
	}

	public int getPacORFru() {
		return pacORFru;
	}

	public void setPacORFru(int pacORFru) {
		this.pacORFru = pacORFru;
	}

	private void initGUI() 
	{
		//insert buttons to the manueBar
		MenuBar menuBar = new MenuBar();
		Menu menu1 = new Menu("Add"); 
		MenuItem pacman = new MenuItem("Packman");
		MenuItem fruit = new MenuItem("Fruit");

		Menu menu2 = new Menu("Import/Export"); 
		MenuItem csv = new MenuItem("Import from CSV");
		MenuItem kml = new MenuItem("Export to KML");
		MenuItem toCsv = new MenuItem("Export to CSV");

		Menu menu3 = new Menu("Play"); 
		MenuItem play = new MenuItem("Play the game");

		menuBar.add(menu3);
		menuBar.add(menu1);
		menuBar.add(menu2);
		menu1.add(pacman);
		menu1.add(fruit);
		menu2.add(csv);
		menu2.add(kml);
		menu2.add(toCsv);
		menu3.add(play);
		this.setMenuBar(menuBar);

		pacman.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setPacORFru(0);
			}
		});

		fruit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setPacORFru(1);
			}
		});

		//this is the import from csv button
		csv.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				File F = new File("C:\\Users\\Amit\\eclipse-workspace\\As2");
				File namedir;
				File namepath;
				chooser.setCurrentDirectory(F);
				chooser.showOpenDialog(null);
				namedir = chooser.getCurrentDirectory();
				namepath = chooser.getSelectedFile();
				game.readFile(namepath.getPath()); // bring coords to pixels
				for (int i = 0; i <game.sizePacman(); i++) {
					game.getPacman(i).setP(map.convert2Pix(myImage.getHeight(),myImage.getWidth(), game.getPacman(i).getP().x(), game.getPacman(i).getP().y()));
				}
				for (int i = 0; i <game.sizeFruit(); i++) {
					game.getFruit(i).setP(map.convert2Pix(myImage.getHeight(),myImage.getWidth(), game.getFruit(i).getP().x(), game.getFruit(i).getP().y()));
				}
				repaint();
			}
		});

		//this is the export to kml button
		kml.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("C:\\Users\\Amit\\eclipse-workspace\\As3"));
				int retrival = chooser.showSaveDialog(null);
				if (retrival == JFileChooser.APPROVE_OPTION) {
					path2kml.write2KML(chooser.getSelectedFile().getPath()+".kml", s.getSaveToKml());
				}
				repaint();
			}
		});

		play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				flag = true;
				s = new ShortestPathAlgo(game);
				finalPath = s.movementAlgo();
				StartThred();
			}
		});

		toCsv.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("C:\\Users\\Amit\\eclipse-workspace\\As3"));
				int retrival = chooser.showSaveDialog(null);
				if (retrival == JFileChooser.APPROVE_OPTION) {
					game.writeFile(chooser.getSelectedFile().getPath()+".csv", s.getSaveToCsv());
				}
				repaint();
			}
		});

		addWindowListener(new WindowAdapter() 
		{public void windowClosing(Window e) 
		{dispose(); System.exit(0);}  
		});

		try {
			//try to read the image (map)
			myImage = ImageIO.read(new File("example.png"));
			myImageFruit = ImageIO.read(new File("apple.png"));
			myImagePacman = ImageIO.read(new File("pacman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	int x = -1;
	int y = -1;

	/**
	 * this function is for starting the threads
	 */
	public void StartThred () {
		int n=0;
		//run all the pacmans so that each one will move at the same time with thread
		for (int i = 0; i < finalPath.size(); i++) {
			myThreads m1 = new myThreads(finalPath.get(i), this, n);
			m1.start();
			n++;
		}
	}

	public void paintAgainImage() {
		repaint();
	}

	/**
	 * This function is a graphic function of java which load the actual game and vizualize it 
	 */
	public void paint(Graphics g)
	{
		int w = this.getWidth();
		int h = this.getHeight();
		g.drawImage(myImage, 0, 0, w, h, this);
		for (int i = 0; i < game.sizePacman(); i++) {
			int r = 10;
			x = (int)game.getPacman(i).getP().x() - (r / 2);
			y = (int)game.getPacman(i).getP().y() - (r / 2);
			g.drawImage(myImagePacman, x, y, w/40, h/20, this);
		}
		for (int i = 0; i < game.sizeFruit(); i++) {
			int r = 10;
			x = (int)game.getFruit(i).getP().x() - (r / 2);
			y = (int)game.getFruit(i).getP().y() - (r / 2);
			g.drawImage(myImageFruit, x, y, w/40, h/20, this);
		}
		if(flag == true) {
			g.setColor(Color.red);
			for (int i = 0; i < s.getPath().size() ; i++) {
				for (int j = 0; j < s.getPath().get(i).sizeWay()-1; j++) {
					g.drawLine((int)s.getPath().get(i).getWay().get(j).x(), (int)s.getPath().get(i).getWay().get(j).y(), (int)s.getPath().get(i).getWay().get(j+1).x(), (int)s.getPath().get(i).getWay().get(j+1).y());
				}
			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent arg) {
		System.out.println("mouse Clicked");
		System.out.println("("+ arg.getX() + "," + arg.getY() +")");
		x = arg.getX();
		y = arg.getY();
		if(this.pacORFru == 0) {
			game.addPacman(new Pacman ( new Point3D (x,y,0),IDP));
			IDP++;
		}
		if (this.pacORFru == 1) {
			game.addFruit(new Fruit ( new Point3D (x,y,0), IDF));
			IDF++;
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("mouse entered");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		System.out.println("mouseExited");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		System.out.println("mousePressed");
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		System.out.println("mouseReleased");
	}

	public BufferedImage getMyImage() {
		return this.myImage;
	}

	public Game getGame() {
		return game;
	}

	public class myThreads extends Thread {

		private Path finalPath;
		private MyFrame mw = new MyFrame();
		int n=0;

		public myThreads() {

		}

		public myThreads(Path finalPath,MyFrame mw, int n) {
			this.finalPath = finalPath;
			this.mw = mw;
			this.n=n;
		}

		@Override
		public void run() {
			for (int i = 0; i < finalPath.sizeWay(); i++) {
				//make a new point made out from "way"
				Point3D point  = new Point3D(finalPath.getWay().get(i).x(),finalPath.getWay().get(i).y(),finalPath.getWay().get(i).z());
				//put "point" as a pacman inside game
				this.mw.getGame().getPacmen().get(n).setP(point);
				this.mw.repaint();
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
		}
	}

}