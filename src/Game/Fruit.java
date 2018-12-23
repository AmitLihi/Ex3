package Game;

import java.util.ArrayList;

import Geom.Point3D;
/**
 * This class represents a Fruit which is the Pacmans target
 * @author Amit & Lihi
 */
public class Fruit {

	private Point3D p;
	private double weight;
	private int ID;
	private String timeStamp;
	private long timeStampLong;
	

	/**
	 * Constructor that gets 3Dpoint and creats a new Fruit with this point
	 * @param p
	 */
	public Fruit(Point3D p) {
		this.p = new Point3D(p);
		this.weight = 1;
		this.ID = 0;
		this.timeStampLong = System.currentTimeMillis()/1000;				
	}
	
	public Fruit(Point3D p, int id) {
		this.p = new Point3D(p);
		this.weight = 1;
		this.ID = id;
		this.timeStampLong = System.currentTimeMillis()/1000;				
	}
	/**
	 * Construstor that gets coords and weight of the Fruit and creates a new Fruit from it	
	 * @param lon
	 * @param lat
	 * @param alt
	 * @param weight
	 */
	public Fruit (double lon, double lat, double alt, double weight, int id) {
		this.p = new Point3D (lon,lat,alt);
		this.weight = 1;
		this.ID = id;
		this.timeStampLong = System.currentTimeMillis()/1000;
		this.timeStamp = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (timeStampLong*1000));
	}
	
	///*** Getters & Setters ***///
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Point3D getP() {
		return p;
	}

	public void setP(Point3D p) {
		this.p = p;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public long getTimeStampLong() {
		return timeStampLong;
	}
	public void setTimeStampLong(long timeStampLong) {
		this.timeStampLong = timeStampLong;
	}
	@Override
	public String toString() {
		return "Fruit [p=" + p + ", weight=" + weight + "]";
	}

}
