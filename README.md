<h1><b> Report assignment 3:<b> <h1>
<br>
Programed by: Amit Bibi and Lihi Belfer.
<br>
Work Space: eclipse. 
<br>
In this project we have created a basic Pacman game, which Map of Ariel city is its game board.
The aim of this game : the pacmans need to eat all the fruits on the screen as fast as possible.
Each pacman has a speed of movement (in meters per second) and an eating radius (in meters) that defines proximity the minimum required for a pacman to eat his fruit.

<br>
The user gets to choose in what way he wants to build the game, with a mouse click where he wants to put a pacman and a fruit or rather to import a CSV file and the game will be built for him.
<br>
When the game starts we can see a single pacman or couple of pacmans moves on the screen though the fruits and eat them one by one in the moment they get to them.
<br>
While the game processes we made a unique class which is writing all the pacmans steps in a real time consideration.
<br>
The game ends when all the fruits disappear, once it happened the good part of the program begins, class called “Path2KML” create a KML file in a specific direction we gave it so the user can run his game on Google Earth and enjoy the replay game.
For example: 
 
As we can see in this particular game there are 2 pacmans, a left one with 2 fruits and a right one with only one fruit that eatable for him .
After we run the game it will look as expected like this:
 
Explanation: the left pacman ate first his closest fruit (left one)
After that he ate the second close fruit he had (right one)
In the meantime, the other pacman (right one) ate his closest and only fruit he had.
<br>
The packages the project includes: 
<br>
 
<br>
Game package include the next classes:
<br> 
GUI package includes:
<br>
MyFrame class which responsible main window board game.
<br>

<br>
Map class:
<br>
A class that represents a map that contains a map image file and all the required parameters of its adjustment to a global coordinate system.
<br>
Pacman class:
<br>
A class representing a "Pacman" with position (Point), direction and ability to move.
<br>
Fruit class:
<br>
A group that represents a "destination" in a known geographic location, without a prime motion.
<br>
Game class:
<br>
A class that includes a collection of fruits and pacmans, the class has the ability to build a csv file format and save its information to such a file.
<br>
Path class:
<br>
A trail composed of a path. Include methods of length, as well as additional geographic information as needed.
<br>
ShortestPathAlgo class:
<br>
This class is the main algorithmic department of the game it calculates the optimal route (fastest way) that all fruits will be "eaten" as quickly as possible by the pacmans. The class include calculating "fruit tracks" for each of the suppliers. In addition, it sums up the current score at each time point for each Pacman is the sum of the weights of each fruit that he ate so far, and the overall score at any point in time.
<br>
MyFrame class:
<br>
A graphic class that allows robots and fruits to be displayed on the map, showing the algorithmic activity. The great thing is that the class is real-time display, meaning the user would be able to see the pacmans move in a current time. 
<br>
Path2KML class:
<br>
A class that allows the user to track the movement of a pacman in the in real time to kml file in the class so that it can run in KML and see the movement in the Google Earth software.
<br>
 <br>
Additional auxiliary class to the program are:
<br>
MyCoords class responsible for the calculation between two points, it helped us with functions like add, distance and azimuth.
<br>
Geom class responsible to storage all the 3D points such as: Fruits and pacmans.
<br>
In addition, we ran some equalization test of a certain element.
<br>
We made a test class (junit class), there we have tasted the ability of simple classes like Fruit and Pacman, the converting files operation, converting coordinates to pixels and opposite process back to coordinates and the GUI class as well.
<br>
We gathered information from: Google, YouTube for videos explanations, Stack Overflow, GeekforGeeks, Oracle for information of classes in Java (most of them for the GUI package), Yael Landau’s code for (GUI, read files and write files) and specific website below:
<br> <list>
<li> Source code - Starting task point – Boaz GitHub -https://github.com/benmoshe/OOP_EX2-EX4 <li>
<br>
<li> Calculate azimuth - https://stackoverflow.com/questions/9457988/bearing-from-one-coordinate-to-another <li>
<br>
<li> Convert global coordinates to pixels - https://stackoverflow.com/questions/38748832/convert-longitude-and-latitude-coordinates-to-image-of-a-map-pixels-x-and-y-coor <li>
<br>
<li> Calculate time - https://stackoverflow.com/questions/5471700/is-there-a-command-in-java-to-measure-the-execution-time <li>
<br>
<li> current time - https://www.epochconverter.com/ <li>
<br>
<li> JFileChooser  https://stackoverflow.com/questions/14589386/how-to-save-file-using-jfilechooser-in-java <li>
<br>
<li> In addition, we used Google Earth software to run the game that we are writing on KML file. <li>
