package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Coords.MyCoords;
import Geom.Point3D;
/**
 * This class repesent the path from a packman to a fruit
 * @author Amit & Lihi
 */
public class Path {

	private Pacman pacman;
	private ArrayList <Fruit> fruits;
	private ArrayList <Double> time;
	private ArrayList <Point3D> way;
	private ArrayList <Long> timeStampPointCreatedLong;
	private ArrayList <String> timeStampPointCreated;
	private ArrayList <Fruit> fruitsForTime;
	
	/**
	 * difault consructor
	 * @param p
	 * @param fruits
	 */
	public Path() {
		this.pacman = null;
		this.fruits =  new ArrayList() ;
		this.time =  new ArrayList <Double> ();
		this.way = new ArrayList ();
		this.timeStampPointCreated = new ArrayList();
		this.timeStampPointCreatedLong =  new ArrayList();
		this.fruitsForTime = new ArrayList();
		}
	
	/**
	 * Constructor that gets a pacman and creats a path with him
	 * @param p
	 */
	public Path(Pacman p) {
		this.pacman = new Pacman (p.getP());
		this.fruits =  new ArrayList() ;
		this.time =  new ArrayList <Double> ();
		this.way = new ArrayList ();
		this.timeStampPointCreated = new ArrayList();
		this.timeStampPointCreatedLong =  new ArrayList();
		this.fruitsForTime = new ArrayList();
	}
	
	///***Time***///

	public boolean addTime (double t)
	{
		return time.add(t);
	}
	
	public boolean containsTime(double t) {
		return time.contains(t);
	}
	
	public boolean isEmptyTime() {
		return time.isEmpty();
	}
	
	public boolean removeTime(double t) {
		return time.remove(t);
	}
	
	public int sizeTime() {
		return time.size();
	}
	
	///***Fruits***///
	
	public boolean addFruit (Fruit f)
	{
		return fruits.add(f);
	}
	
	public boolean containsFruit(Fruit f) {
		return fruits.contains(f);
	}
	
	public boolean isEmptyFruit() {
		return fruits.isEmpty();
	}
	
	public boolean removeFruit(Object o) {
		return fruits.remove(o);
	}
	
	public int sizeFruit() {
		return fruits.size();
	}
	
	///***Way***//
	
	public boolean addWay(Point3D p)
	{
		return way.add(p);
	}
	
	public boolean containsWay(Point3D p) {
		return way.contains(p);
	}
	
	public boolean isEmptyWay() {
		return way.isEmpty();
	}
	
	public boolean removeWay(Point3D p) {
		return way.remove(p);
	}
	
	public int sizeWay() {
		return way.size();
	}
	
	///***TimeStamp***///
	
	public boolean addtimeStampPointCreated(String s)
	{
		return timeStampPointCreated.add(s);
	}
	
	public boolean addtimeStampPointCreatedLong(long l)
	{
		return timeStampPointCreatedLong.add(l);
	}
	
	///***Getters and Setters***///

	public ArrayList <Fruit> getFruits() {
		return fruits;
	}

	public Pacman getPacman() {
		return pacman;
	}

	public ArrayList getTime() {
		return time;
	}

	public void setPacman(Pacman pacman) {
		this.pacman = pacman;
	}

	public void setFruits(List <Fruit> fruits) {
		this.fruits = new ArrayList (fruits);
	}

	public void setTime(List<double[]> list) {
		this.time = new ArrayList(list);
	}

	public Fruit getSpecFruit(int i) {
		return fruits.get(i);
	}

	public double getSpecTime(int i) {
		return (double) time.get(i);
	}

	public ArrayList getTimestampPointCreated() {
		return timeStampPointCreated;
	}

	public void setTimestampPointCreated(ArrayList timestampPointCreated) {
		this.timeStampPointCreated = timestampPointCreated;
	}

	public ArrayList<Long> getTimeStampPointCreatedLong() {
		return timeStampPointCreatedLong;
	}

	public void setTimeStampPointCreatedLong(ArrayList<Long> timeStampPointCreatedLong) {
		this.timeStampPointCreatedLong = timeStampPointCreatedLong;
	}

	public ArrayList<Point3D> getWay() {
		return way;
	}

	public void setWay(ArrayList<Point3D> way) {
		this.way = way;
	}

	public ArrayList<Fruit> getFruitsForTime() {
		return fruitsForTime;
	}

	public void setFruitsForTime(List<Fruit> list) {
		this.fruitsForTime = new ArrayList(list) ;
	}
	
}