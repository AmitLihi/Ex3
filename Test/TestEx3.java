import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import File_format.Path2KML;
import Game.Fruit;
import Game.Game;
import Game.Map;
import Game.Pacman;
import Game.Path;
import Game.ShortestPathAlgo;
import Geom.Point3D;

/**
 * This is the Test class
 * @author Amit & Lihi
 */
class TestEx3 {
	ArrayList <Fruit> fruits = new ArrayList();
	ArrayList <Pacman> pacmen = new ArrayList();
	Pacman pacman = new Pacman(new Point3D (5,2,3));
	Fruit fruit = new Fruit(new Point3D (1,2,3));


	@Test
	void TestPacman() {
		assertEquals (new Point3D(5,2,3).x(), pacman.getP().x());
		assertEquals (new Point3D(5,2,3).y(), pacman.getP().y());
		assertEquals (new Point3D(5,2,3).z(), pacman.getP().z());
	}

	@Test
	void TestFruit() {
		assertEquals (new Point3D(1,2,3).x(), fruit.getP().x());
		assertEquals (new Point3D(1,2,3).y(), fruit.getP().y());
		assertEquals (new Point3D(1,2,3).z(), fruit.getP().z());
	}

	@Test
	void TestGame() {
		try{
			Game ga = new Game("game_1543684662657.csv");
			ga.writeFile("game_1543684662657.csv","aaa.csv");			}
		catch(Exception e){
			fail("Should not have thrown any exception");
		}
		Game game = new Game();
		game.addFruit(fruit);
		game.addPacman(pacman);
		if (game.containsFruit(fruit)!=true)
		{
			fail("The function \"contains\" doesn't work as expected");
		}
		game.removePacman(pacman);
		if (game.containsPacman(pacman)==true)
		{
			fail("The function \"contains\" doesn't work as expected");
		}
	}

	@Test
	void TestMap() {
		Map m = new Map();
		//test converting Gps to Pixels
		//		Point3D point = new Point3D(m.convert2Pix(642,1433,35.2035022,32.1045513));
		//		assertEquals (point.x(),46.0);
		//		assertEquals (point.y(),54.0);
		//		assertEquals (point.z(),0.0);
		//test converting Pixels to Gps Coords
		//		Point3D point2 = new Point3D(m.convert2Coords(642,1433,point.x(),point.y())); 
		//		assertEquals (35.2035022,point2.x());
		//		assertEquals (32.1045513,point.y());
		//		assertEquals (0, point2.z());
	}

	@Test
	void TestPath() {
		try{
			Path path = new Path(pacman);	
		}
		catch(Exception e){
			fail("Should not have thrown any exception");
		}
	}

	@Test
	void TestShortestPathAlgo() {
		Fruit fruit1 = new Fruit(new Point3D (35.20983508,32.10362369,0));
		Fruit fruit2 = new Fruit(new Point3D (35.21035679,32.1042768,0));
		Fruit fruit3 = new Fruit(new Point3D (35.21019738,32.10451344,0));
		Pacman pacman1 = new Pacman(new Point3D (35.2035022,32.1045513,0));
		Pacman pacman2 = new Pacman(new Point3D (35.21035679,32.1042768,0));
		Game game = new Game();
		game.addFruit(fruit1);
		game.addFruit(fruit2);
		game.addFruit(fruit3);
		game.addPacman(pacman1);
		game.addPacman(pacman2);
		ShortestPathAlgo spa = new ShortestPathAlgo(game);
		if (!spa.getPath().get(1).getFruits().contains(fruit2))
		{
			fail("The function \"contains\" doesn't work as expected");
		}
		if (!spa.getPath().get(1).getFruits().contains(fruit1))
		{
			fail("The function \"contains\" doesn't work as expected");
		}
		if (!spa.getPath().get(1).getFruits().contains(fruit3))
		{
			fail("The function \"contains\" doesn't work as expected");
		}
		if (!spa.getPath().get(0).getWay().isEmpty())
		{
			fail("The function \"isempty\" doesn't work as expected");
		}

	}

	@Test
	void TestPath2KML() {
		Fruit fruit1 = new Fruit(new Point3D (35.20983508,32.10362369,0));
		Fruit fruit2 = new Fruit(new Point3D (35.21035679,32.1042768,0));
		Fruit fruit3 = new Fruit(new Point3D (35.21019738,32.10451344,0));
		Pacman pacman1 = new Pacman(new Point3D (35.2035022,32.1045513,0));
		Pacman pacman2 = new Pacman(new Point3D (35.21035679,32.1042768,0));
		Game game = new Game();
		game.addFruit(fruit1);
		game.addFruit(fruit2);
		game.addFruit(fruit3);
		game.addPacman(pacman1);
		game.addPacman(pacman2);
		ShortestPathAlgo spa = new ShortestPathAlgo(game);
		Path2KML p2kml = new Path2KML();
		try{
			p2kml.write2KML(spa);
		}
		catch(Exception e){
			fail("Should not have thrown any exception");
		}
	}
}