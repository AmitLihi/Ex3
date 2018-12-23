package Game;

import java.util.ArrayList;
import Coords.MyCoords;
import Geom.Point3D;
/**
 * This class represents the Pacman creature, which aim is chasing fruits and eating them
 * @author Amit & Lihi
 */
public class Pacman {

	private double radius;
	private double speed;
	private Point3D p;
	private int ID;
	private String timeStamp;
	private long timeStampLong;
	private double scoor = 0;
	
	/**
	 * Constructor that gets 3Dpoint and creats a new Pacman with this point
	 * @param p
	 */
	public Pacman(Point3D p) {
		this.p = new Point3D(p);
		this.radius = 1;
		this.speed = 1;
		this.ID = 0;
		this.timeStampLong = System.currentTimeMillis()/1000;
	}
	
	public Pacman(Point3D p, int id) {
		this.p = new Point3D(p);
		this.radius = 1;
		this.speed = 1;
		this.ID = id;
		this.timeStampLong = System.currentTimeMillis()/1000;
		}
	
	/**
	 * Construstor that gets coords,speed radius and id of the Pacman and creates a new Pacman from it
	 * @param lon
	 * @param lat
	 * @param alt
	 * @param speed
	 * @param radius
	 */
	public Pacman(double lon, double lat, double alt, double speed, double radius, int id)
	{
		this.p = new Point3D (lon,lat,alt);
		this.radius = radius;
		this.speed = speed;
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
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Point3D getP() {
		return p;
	}

	public void setP(Point3D p) {
		this.p = p;
	}

	public long getTimeStampLong() {
		return timeStampLong;
	}

	public void setTimeStampLong(long timeStampLong) {
		this.timeStampLong = timeStampLong;
	}

	public double getScoor() {
		return scoor;
	}

	public void setScoor(double s) {
		this.scoor = this.scoor + s;
	}

	@Override
	public String toString() {
		return "pacman [radius=" + radius + ", speed=" + speed + ", p=" + p + "]";
	}
}
