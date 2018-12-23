package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import GIS.GIS_ele;
import GIS.GIS_lay;
import GUI.MyFrame;
import Game.Map;
import Game.ShortestPathAlgo;
import Geom.Point3D;

public class Path2KML {

	String color [] = {"red" , "green", "blue", "pink", "yellow", "brown", "white", "black"};
	public Path2KML() {
	}




	public void write2KML(String xmlFile, String s) {
		PrintWriter pw = null;

		try 
		{
			pw = new PrintWriter(new File(xmlFile));
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		pw.write(s);
		pw.close();
	}

	public String write2KML(ShortestPathAlgo s) {
		StringBuilder sb2= new StringBuilder();
		StringBuilder sb = new StringBuilder();
		Point3D p = new Point3D(0,0,0);
		//in the beginning kml
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"); 
		sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
		sb.append("<Document><Style id=\"pacman\"><IconStyle>\n<Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href>\n</Icon></IconStyle></Style><Style id=\"fruit\"><IconStyle>\n");
		sb.append("<Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style>\n");
		for (int i = 0; i <s.getPath().size(); i++) {	
			p = new Point3D(convert(s.getPath().get(i).getPacman().getP().x(),s.getPath().get(i).getPacman().getP().y()));
			sb.append("<Folder><name>Pacman "+s.getPath().get(i).getPacman().getID()+"</name>\n");			
			sb2.append("<Placemark><name>"+s.getPath().get(i).getPacman().getID()+"</name>\n");
			sb2.append("<description>pacman</description>\n");
			sb2.append("<LineString>\n");
			sb2.append("<coordinates>\n");
			for (int j = 0; j < s.getPath().get(i).getFruitsForTime().size(); j++) {
				Point3D p1 = new Point3D(convert(s.getPath().get(i).getFruitsForTime().get(j).getP().x() ,s.getPath().get(i).getFruitsForTime().get(j).getP().y()));		
				sb.append("<Placemark>\n");
				sb.append("<description>");
				sb.append("</description><styleUrl>#"+"fruit"+"</styleUrl>");
				sb.append('\n');
				sb.append("<Point>\n<coordinates>"+p1.x()+","+p1.y()+"</coordinates>\n</Point>");
				sb.append('\n');	
				sb.append("<TimeStamp>\n");
				sb.append("<when>"+ new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(new java.util.Date (s.getPath().get(i).getTimeStampPointCreatedLong().get(j)*1000)) +"</when>\n");
				sb.append("</TimeStamp>\n");				
				sb.append("</Placemark>");
				sb.append('\n');		
			}
			sb2.append("</coordinates>\r\n"+"</LineString>\r\n" +"</Placemark>\n");
			sb.append(sb2);
			sb2.delete(0, sb2.length()-1);
			sb.append("</Folder>");
		}
		//in the end kml
		sb.append("</Document></kml>");
		String string = sb.toString();
		return string;
	}

	private Point3D convert(double x, double y) {
		MyFrame m = new MyFrame();
		Map p = new Map();
		return p.convert2Coords(m.getMyImage().getHeight(), m.getMyImage().getWidth() , x, y);
	}
}