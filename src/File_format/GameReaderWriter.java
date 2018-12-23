package File_format;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import GIS.GIS_ele;
import GIS.GIS_lay;
import GIS.GIS_pro;
import GUI.MyFrame;
import Game.Fruit;
import Game.Game;
import Game.Map;
import Game.Pacman;
import Geom.Point3D;

/**
 * This class is responsible for Reading and Writing files
 * @author Amit & Lihi
 *
 */
public class GameReaderWriter {

	/**
	 * This function reads a csv file
	 * @param csvFile
	 * @param g
	 */
	public void readFile(String csvFile, Game g) {
		int rows=0,cols=1;
		String line="";
		try (BufferedReader check = new BufferedReader(new FileReader(csvFile))) //build a reader for the file
		{
			while ((line = check.readLine()) != null) // run the file and check how many rows and cols it has
			{
				rows++;
				if(rows==1) {
					for (int i=0; i<line.length();i++) {
						if(line.charAt(i)==',') {
							cols++;
						}
					}
				}
			}
			BufferedReader br = new BufferedReader(new FileReader(csvFile));//run the file again this time to put it in a metrix string
			String str [][] = new String [rows][cols];
			int i=0;
			while ((line = br.readLine()) != null) 
			{
				str[i] = line.split(",");
				i++;
			}

			for (int j = 1; j < str.length; j++) {
				if(str[j][0].equals("P")) {
					Pacman p = new Pacman(Double.parseDouble(str[j][3]),Double.parseDouble(str[j][2]),Double.parseDouble(str[j][4]),Double.parseDouble(str[j][5]),Double.parseDouble(str[j][6]),Integer.parseInt(str[j][1]));
					g.addPacman(p);
				}
				else {
					Fruit f = new Fruit(Double.parseDouble(str[j][3]),Double.parseDouble(str[j][2]),Double.parseDouble(str[j][4]),Double.parseDouble(str[j][5]),Integer.parseInt(str[j][1]));
					g.addFruit(f);
				}
			}
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * This function writes with a csv file
	 * @param csvFile
	 * @param g
	 */
	public String writeFile(Game g) {

		Point3D p = new Point3D(0,0,0);
		StringBuilder sb = new StringBuilder();
		sb.append("Type,id,Lat,Lon,Alt,Speed/Weight,Radius," + g.sizePacman() + "," + g.sizeFruit() + "\n");
		for (int j = 0; j < g.sizePacman(); j++) {
			p = new Point3D (convert(g.getPacman(j).getP().x(),g.getPacman(j).getP().y())); // convert to coords back from pix
			sb.append("P," + g.getPacman(j).getID() + "," +p.y()+ "," + p.x()+ "," + p.z() + "," + g.getPacman(j).getSpeed()+ "," + g.getPacman(j).getRadius() + "\n");
		}
		for (int j = 0; j < g.sizeFruit(); j++) {
			p = new Point3D (convert(g.getFruit(j).getP().x(),g.getFruit(j).getP().y())); // convert to coords back from pix
			sb.append("F," + g.getFruit(j).getID() + "," +p.y()+ "," + p.x()+ "," + p.z() + "," + g.getFruit(j).getWeight() + "\n");
		}
		String s = (sb.toString());
		return s;
	}

	public void writeFile(String csvFile, String s) {

		PrintWriter pw = null;

		try 
		{
			pw = new PrintWriter(new File(csvFile));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		pw.write(s);
		pw.close();
	}

	private Point3D convert(double x, double y) {
		MyFrame m = new MyFrame();
		Map p = new Map();
		return p.convert2Coords(m.getMyImage().getHeight(), m.getMyImage().getWidth() , x, y);
	}
}