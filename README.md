# CSCI 205 - Software Engineering and Design
Bucknell University,
Lewisburg, PA
### Course Info
Instructor: Lily Romano, Joshua Stough

Semester: Spring 2024
## Team Information

Team 10

List team members:

Mateo Herrera (Scrum Master)- 2026 Computer Science Bachelor of Science

Mike Merola (Developer)- 2026 Computer Science and Engineering

Jason Chung (Developer )- 2026 Computer Science Bachelor of Science

Miguel Romero (Product Owner)- 2026 Computer Science and Engineering


## Project Information
The project is the 2048 game. The main focus of the project was not implementing the game 2048 but to practice
Agile and Scrum habits. The 2048 game was the byproduct of practicing Agile and Scrum. The language used was Java, 
and it incorporates the use of Scene Builder, which is a JavaFX application, to make a visual representation of 
the game. It also uses JUnit tests to ensure the methods of the classes used work as intended. 

The most important aspects of the 2048 game includes a board and the movement of the tiles combining when necessary.
The board class is a 2-D observable list that updates whenever changes in the board occur (either in movement or 
combination). The buttons on the side are used to allow the player to perform movements of the tiles (either up, down, 
left, or right). When movement happens, all the tiles move in the direction wanted, and when possible, combination 
occurs.

The game also has a New Game button that resets the board to allow the player to start a new game of 2048. It clears the
board and generates new tiles for the player to start over again. The game can also detect when the player wins or loses.
Like the original game, when the player reaches 2048, a popup occurs saying that the player won. On the contrary, if the
player loses (no more movements possible), a popup also occurs telling the player that they have lost the 2048 game. 
After exiting the popup, the player can start a new game to play again.

The GameManager class checks when the player wins or loses. While the movement class handles the combination logic and 
the movement of the tiles, it also helps determine when to generate new tiles. The responsibility lies to the movement
class because they would be able to tell when changes to the board occur when movement happens. Generating new tiles 
creates a new tile object that are what is inside the 2-D observable list. 

## Package Structure
The package structure used has the JavaFXML in a separate package from the logic of the game. The necessary logic for 
the game is outside the JavaFXML package, so the JavaFXML files can access the methods and instances of the classes.
The JUnit tests for the classes are in a separate package from the actual classes since they are used for testing.

## Third-party Libraries Used
- JavaFX Version 0.1.0 https://openjfx.io/javadoc/21/
- JavaFX Scene Builder Version 21.0.0 https://gluonhq.com/
- JUnit Version 5.8.2 https://junit.org/junit5/

## Demo
Here is a video demo of the game: https://mediaspace.bucknell.edu/media/t/1_rrc4c6at