package Game;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.text.html.HTMLDocument.Iterator;
import Coords.MyCoords;
import File_format.Path2KML;
import Geom.Circle;
import Geom.Point3D;
/**
 * This class represents an algorithm for the shortest path of one pacman to each fruit
 * @author Amit & Lihi
 *
 */
public class ShortestPathAlgo {

	private ArrayList <Pacman> pacmen;
	private ArrayList <Fruit> fruits;
	private ArrayList <Path> path;
	private int size;
	private Game g;
	private String saveToKml;
	private String saveToCsv;
	private Path2KML k;

	/**
	 * Constructor for ShortestPathAlgo by getting Game. this constructor will insert elements and send to arrangeFruits function
	 * @param g
	 */
	public ShortestPathAlgo(Game g) {
		this.g = g;
		this.k = new Path2KML();
		this.size = g.sizeFruit(); // no iterator is involved so that variable will remember the number of the fruits although we delete them from the list
		this.pacmen = new ArrayList (g.getPacmen());
		this.fruits = new ArrayList (g.getFruits());
		this.path = new ArrayList();
		for (int i = 0; i < pacmen.size(); i++) {
			Path p = new Path (pacmen.get(i));
			path.add(p);
		}
		arrangeFruits();
	}

	private void arrangeFruits() {
		double minTime=0, tempTime=0;
		int indexOfPacman=0, n=0; // n variable keeps giving the right index of path in cooperation with size
		for (int i = 0; i < size; i++) { //runs until all the fruits were eaten
			for (int j = 0; j < pacmen.size(); j++) { // goes over the pacman one by one and check who is the cloest to the current fruit
				tempTime = distance(pacmen.get(j), fruits.get(n)); //check the arrival time
				if(j == 0) { //for the first itertion
					minTime = tempTime;
				}
				if(tempTime < minTime) { //choose the minimum time
					minTime = tempTime;
					indexOfPacman = j; // keep the current index of the pacman
				}
			}
			this.path.get(indexOfPacman).addFruit(fruits.get(n)); //adding the fruit to the pacman
			this.path.get(indexOfPacman).addTime(minTime); //adding the time to the pacman
			fruits.remove(n);
			tempTime = 0; // zeros the variable
			minTime = 0;
			indexOfPacman = 0;
		}
		arrangeTime();
	}

	/**
	 * This function is responsible for arranging the fruit and time array for each pacman
	 */
	private void arrangeTime() {
		for (int i = 0; i < path.size(); i++) { // runs pacmen one by one
			double[] timeArr = new double [this.path.get(i).sizeTime()];
			for (int j = 0; j < timeArr.length; j++) {	// insert time to array
				timeArr[j] = (double) this.path.get(i).getTime().get(j);
			}

			Fruit[] fruitsArr = new Fruit [this.path.get(i).sizeFruit()];
			for (int j = 0; j < fruitsArr.length; j++) {	// insert fruits to array
				fruitsArr[j] = this.path.get(i).getFruits().get(j);
			}

			for (int j = 0; j < fruitsArr.length; j++) { // arrange the fruits inside the pacman from the closet to the farest
				for (int j2 = 0; j2 < timeArr.length-1; j2++) {
					if(timeArr[j2] > timeArr[j2+1]) {
						swapTime(timeArr, j2, j2+1);
						swapFruits(fruitsArr, j2, j2+1);
					}
				}
			}
			this.path.get(i).setFruits(Arrays.asList(fruitsArr));
			this.path.get(i).setFruitsForTime(Arrays.asList(fruitsArr));
			this.path.get(i).setTime(Arrays.asList(timeArr));
			saveToCsv = g.writeFile();
		}
	}
	/**
	 * This function is responsible for the movement of each pacman to each fruit
	 * @return
	 */
	public ArrayList movementAlgo() {
		boolean toEatOrNotToEat=false, firstIteration=true;
		int counterX=0,counterY=0;
		double modX = 8,modY = 8;
		long l = 100; 
		for (int i = 0; i < path.size(); i++) { //runs the pacmen one by one
			for (int j = 0; j < path.get(i).getFruits().size(); j=j) { //runs the fruits one by one of current pacman
				toEatOrNotToEat = eatble(path.get(i).getPacman().getP(),path.get(i).getFruits().get(j).getP(), path.get(i).getPacman().getRadius());
				if(toEatOrNotToEat == true) {  // if it does, the fruit and the time will be removed
					this.path.get(i).addtimeStampPointCreatedLong(this.path.get(i).getFruits().get(j).getTimeStampLong() +  ((Long)this.path.get(i).getTime().get(j)).longValue());
					//path.get(i).getFruits().remove(j);
					this.path.get(i).getTime().remove(j);
					while(path.get(i).getFruits().size() == 0) {
						if(i < this.path.size()-1) i++;
						else return this.path;;
					}
				}
				while ((path.get(i).getPacman().getP().x() != path.get(i).getFruits().get(j).getP().x()) || (path.get(i).getPacman().getP().y() != path.get(i).getFruits().get(j).getP().y())) { //as long as the fruit and the pacman are one diff spots
					firstIteration = false;
					if((Math.abs(path.get(i).getPacman().getP().x()-path.get(i).getFruits().get(j).getP().x())/modX)<1) {
						modX=1;
					}
					if((Math.abs(path.get(i).getPacman().getP().y()-path.get(i).getFruits().get(j).getP().y())/modY)<1) {
						modY=1;
					}
					// if(xP<xF)
					if(path.get(i).getPacman().getP().x()<path.get(i).getFruits().get(j).getP().x()) {
						// raise the x
						for (int k = 0; k < path.get(i).getPacman().getSpeed() && path.get(i).getPacman().getP().x() != path.get(i).getFruits().get(j).getP().x(); k++) {
							path.get(i).getPacman().getP().set_x(path.get(i).getPacman().getP().x()+(1*modX));
						}
					}
					// if(xP>xF) 
					if(path.get(i).getPacman().getP().x()>path.get(i).getFruits().get(j).getP().x()) {
						// low the x
						for (int k = 0; k < path.get(i).getPacman().getSpeed() && path.get(i).getPacman().getP().x() != path.get(i).getFruits().get(j).getP().x(); k++) {
							path.get(i).getPacman().getP().set_x(path.get(i).getPacman().getP().x()-(1*modX));
						}
					}
					// if(yP<yF) 
					if(path.get(i).getPacman().getP().y()<path.get(i).getFruits().get(j).getP().y()) {
						// raise the y
						for (int k = 0; k < path.get(i).getPacman().getSpeed() && path.get(i).getPacman().getP().y() != path.get(i).getFruits().get(j).getP().y(); k++) {
							path.get(i).getPacman().getP().set_y(path.get(i).getPacman().getP().y()+(1*modY));
						}
					}
					// if(yP>yF)
					if(path.get(i).getPacman().getP().y()>path.get(i).getFruits().get(j).getP().y()) {
						//low the y
						for (int k = 0; k < path.get(i).getPacman().getSpeed() && path.get(i).getPacman().getP().y() != path.get(i).getFruits().get(j).getP().y(); k++) {
							path.get(i).getPacman().getP().set_y(path.get(i).getPacman().getP().y()-(1*modY));
						}
					}
					modX=8;modY=8;
					Point3D point = new Point3D(path.get(i).getPacman().getP().x(),path.get(i).getPacman().getP().y(),0);
					this.path.get(i).addWay(point); //adding the new point to the way
					// check if the pacman can already eat the fruit
					toEatOrNotToEat = eatble(path.get(i).getPacman().getP(),path.get(i).getFruits().get(j).getP(), path.get(i).getPacman().getRadius());
					if(toEatOrNotToEat == true) {  // if it does, the fruit and the time will be removed
						this.path.get(i).addtimeStampPointCreatedLong(this.path.get(i).getFruits().get(j).getTimeStampLong() + l);
						this.path.get(i).getPacman().setScoor(this.path.get(i).getFruits().get(j).getWeight());
						this.path.get(i).getFruits().remove(j);
						l = l + 100;
						while(path.get(i).getFruits().size() == 0) {
							if(i < this.path.size()-1) i++;
							else {
								saveToKml = k.write2KML(this);
								return this.path;
							}
						}
					}
				}
			}
		}
		saveToKml = k.write2KML(this);
		return this.path;
	}

	/**
	 * This function returns 'true' if this pacman can already eat this fruit (using circle class for a radius)
	 * @param pac
	 * @param fruit
	 * @param radius
	 * @return
	 */
	private boolean eatble(Point3D pac, Point3D fruit, double radius) {
		Circle c = new Circle(pac, radius);
		if(c.distance3D(fruit) > 0) {
			return false;
		}
		else return true;
	}

	private void swapTime(double arr [], int i, int j) {
		double temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private void swapFruits(Fruit [] arr, int i, int j) {
		Fruit temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/**
	 * Distance function using the formula of time=(dis-radius)/speed	
	 * @param p
	 * @param f
	 * @return
	 */
	public double distance (Pacman p, Fruit f) {
		MyCoords mc = new MyCoords ();
		Point3D ppp = new Point3D(f.getP());
		return (((ppp.distance2D(p.getP())-p.getRadius())/(p.getSpeed())));	
	}

	public ArrayList<Path> getPath() {
		return path;
	}

	public void setPath(ArrayList<Path> path) {
		this.path = path;
	}

	public ArrayList<Fruit> getFruits() {
		return fruits;
	}

	public void setFruits(ArrayList<Fruit> fruits) {
		this.fruits = fruits;
	}

	public Game getG() {
		return g;
	}

	public void setG(Game g) {
		this.g = g;
	}

	public String getSaveToKml() {
		return saveToKml;
	}

	public void setSaveToKml(String saveToKml) {
		this.saveToKml = saveToKml;
	}

	public String getSaveToCsv() {
		return saveToCsv;
	}

	public void setSaveToCsv(String saveToCsv) {
		this.saveToCsv = saveToCsv;
	}


	//	public static void main(String[] args) {
	//		Game g = new Game();
	//		g.readFile("Book12.csv");
	//		ShortestPathAlgo s = new ShortestPathAlgo(g);
	//		s.movementAlgo();
	//	}
}