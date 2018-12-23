package Game;

import java.util.ArrayList;
import java.util.Iterator;

import File_format.GameReaderWriter;
import Geom.Point3D;
/**
 * This class represent a Game created from an Arraylist of fruits and an Arraylist of pacmans
 * @author Amit & Lihi
 */
public class Game {

	private ArrayList <Fruit> fruits = new ArrayList<Fruit>();
	private ArrayList <Pacman> pacmen = new ArrayList<Pacman>();
	private GameReaderWriter gr = new GameReaderWriter();
	
	/**
	 * Default Constructor
	 */
	public Game (){
	
	}
	
	/**
	 * This constructor gets a path to the file,sends it to the GameReaderWriter class for reading it 
	 * @param path
	 */
	public Game(String path) {
		this.gr = new GameReaderWriter();
		gr.readFile(path, this);
	}
	
	/**
	 * Reading file function that gets a path from the constructor
	 * @param path
	 */
	public void readFile(String path) {
		this.gr.readFile(path, this);
	}
	
	/**
	 * This Function gets a path to the file,sends it to the GameReaderWriter class for writing it 
	 * @param path
	 * @return 
	 */
	public String writeFile() {
		GameReaderWriter gr = new GameReaderWriter();
		String main = gr.writeFile(this);
		return main;
	}
	
	public void writeFile(String path, String main) {
		GameReaderWriter gr = new GameReaderWriter();
		gr.writeFile(path, main);
	}
	
	
	 ///***fruits***///
	
	public boolean addFruit (Fruit f)
	{
		return fruits.add(f);
	}
	
	public boolean containsFruit(Fruit o) {
		return fruits.contains(o);
	}
	
	public boolean isEmptyFruit() {
		return fruits.isEmpty();
	}
	
	public boolean removeFruit(Fruit o) {
		return fruits.remove(o);
	}
	
	public int sizeFruit() {
		return fruits.size();
	}
	
	public ArrayList getFruits() {
		return fruits;
	}
	
	public Fruit getFruit(int i) {
		return this.fruits.get(i);
	}
	
	 ///***Pacman***///
	
	public boolean addPacman (Pacman p)
	{
		return pacmen.add(p);
	}
	
	public boolean containsPacman(Object o) {
		return pacmen.contains(o);
	}
	
	public boolean isEmptyPacman() {
		return pacmen.isEmpty();
	}
	
	public boolean removePacman(Pacman o) {
		return pacmen.remove(o);
	}
	
	public int sizePacman() {
		return pacmen.size();
	}
	
	public ArrayList <Pacman> getPacmen() {
		return pacmen;
	}
	
	public Pacman getPacman(int i) {
		return this.pacmen.get(i);
	}

	@Override
	public String toString() {
		return "Game [fruits=" + fruits + ", packmen=" + pacmen + "]";
	}

}