package Game;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;

import Coords.MyCoords;
import Geom.Point3D;
/**
 * This class convert global coordinates to pixels and pixels back to coordinates 
 * @author Amit & Lihi
 */
public class Map {
	private BufferedImage myImage;
	//disctance downright and upleft
	private Point3D downright =  new Point3D(35.21240500,32.101858,0);
	private Point3D upLeft = new Point3D(35.202574,32.106046,0);
	final double mapLongitudeStart = 35.202574, mapLatitudeStart = 32.106046;
	final double mapLongitude = 35.21240500-mapLongitudeStart, mapLatitude = 32.101858-mapLatitudeStart;

	public Map() {
		try {
			myImage = ImageIO.read(new File("example.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Point3D convert2Pix(int height, int wight, double longitude, double latitude){
		double x = longitude - mapLongitudeStart;
		double y = mapLatitudeStart - latitude;
		int x1 = (int) (wight*(x/mapLongitude));
		int y1 = Math.abs((int) (height*(y/mapLatitude)));
		return new Point3D(x1, y1);
	}

	public Point3D convert2Coords(int height, int wight, double x, double y){
		double y1 = ((y*mapLatitude)/height);
		double x1 = ((x*mapLongitude)/wight);
		double latitude = mapLatitudeStart+y1;
		double longitude = x1+mapLongitudeStart;
		return new Point3D(longitude, latitude);
	}
	
	public double distance(Point3D p1, Point3D p2) {
		MyCoords mc = new MyCoords ();
		return mc.distance3d(p1, p2);
	}
	
	public double azimuth(Point3D p1, Point3D p2) {
		MyCoords mc = new MyCoords ();
		double[] arr = mc.azimuth_elevation_dist(p1, p2);
		return arr[0];
	}
}