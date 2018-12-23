import javax.swing.JFrame;
import org.junit.jupiter.api.Test;
import File_format.Csv2kml;
import File_format.MultiCSV;
import GIS.GIS_ele;
import GIS.GIS_lay;
import GUI.MyFrame;
import Game.Fruit;
import Game.Game;
import Game.Map;
import Game.Pacman;
import Geom.Point3D;

public class Main {

	public static void main(String[] args) {

		MyFrame window = new MyFrame();
		window.setVisible(true);
		window.setSize(1400,700);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}