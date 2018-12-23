package Coords;

import java.text.DecimalFormat;

import Geom.Point3D;

public class MyCoords implements coords_converter {

	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		Point3D p =new Point3D (gps);
		double x = Math.asin(local_vector_in_meter.x()/6371000);
		double y = Math.asin(local_vector_in_meter.y()/6371000);
		double z = gps.z()+local_vector_in_meter.z();
		double ln = Math.cos(gps.x()*(Math.PI/180));
		y = y/ln;
		x = local_vector_in_meter.r2d(x);
		y = local_vector_in_meter.r2d(y);
		p.add(x,y,z);
		return p;

	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		Point3D p = vector3D(gps0,gps1);
		return Math.sqrt(Math.pow(p.x(), 2)+Math.pow(p.y(), 2)+Math.pow(p.z(), 2));		
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double x = gps1.x()-gps0.x();
		double y = gps1.y()-gps0.y();
		x = (Math.sin(Math.toRadians(x)))*6371000;
		double ln = Math.cos(gps0.x()*(Math.PI/180));
		y = (Math.sin(Math.toRadians(y)))*6371000*ln;
		double z = gps1.z()-gps0.z();
		Point3D p = new Point3D(x,y,z);
		return p;
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double arr[] = new double[3];
		double gpsX1 = gps1.x()*Math.PI/180;
		double gpsX0 = gps0.x()*Math.PI/180;
		double y = gps1.y()- gps0.y();
		y = (y*Math.PI)/180;
		double x = Math.sin(y) * Math.cos(gpsX1);
		y = Math.cos(gpsX0) * Math.sin(gpsX1) - Math.sin(gpsX0)*Math.cos(gpsX1)*Math.cos(y);
		double azimuth = Math.atan2(x,y);
		if(Math.toDegrees(azimuth)<0)
			azimuth = 360+Math.toDegrees(azimuth);
		else
			azimuth = Math.toDegrees(azimuth);
		arr[0] = azimuth;
		arr[1] = Math.atan((gps1.z()-gps0.z())/distance3d(gps0,gps1))*(180/Math.PI);
		arr[2] = distance3d(gps0,gps1);
		return arr;
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		if(p.x() < -90 || p.x() > 90)
			return false;
		if(p.y() < -180 || p.y() > 180)
			return false;
		if(p.z() < -450)
			return false;
		return true;
	}

}
