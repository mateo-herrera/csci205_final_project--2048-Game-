/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Jason Chung
 * Section: 02 / 61
 * Date: 4/18/2024
 * Time: 3:40 PM
 *
 * Project: csci205_final_project
 * Package: org.MMMJ
 * Class: GameManager
 *
 * Description: GameManager class for 2048
 *
 * ****************************************
 */
package org.MMMJ;

import javafx.collections.ObservableList;

/**
 * @author Jason Chung
 * Class to handle win and lose conditions
 */
public class GameManager {
    /** The board of the game*/
    private  Board board;
    /** The size of the board (can be customizable or defaulted to 4)*/
    private int board_size; // Can change later for making custom board sizes
    /** The number to end the game at (can be customizable or defaulted to 2048)*/
    private int gameEndNumber; // Change to 2048 for actual game, smaller number for testing only
    /** Movement instance of the game to do movements*/
    private  Movement movement; // Sounds clunky, change later to maybe something abstract or interface
    /** Generate Tiles instance to generate tiles for the board*/
    private GenerateTiles generateTiles;

    /**
     * Constructor class for GameManager that generates the default game of 4x4 board and goal of 2048
     */
    public GameManager() {
        this.board_size = 4; // Default board size if not specified
        this.board = new Board(board_size);
        this.gameEndNumber = 2048; //Default way for user to win
        this.movement = new Movement(this.board);
        this.generateTiles = new GenerateTiles(this.board);
    }

    /**
     * Constructor for user that wants a different board size and different goal to get to
     * @param userDesiredSize - integer of the user's desired board size
     * @param userDesiredEndNum - integer of the user's desired end number
     */
    public GameManager(int userDesiredSize, int userDesiredEndNum){
        this.board_size = userDesiredSize;
        this.board = new Board(this.board_size);
        this.gameEndNumber = userDesiredEndNum;
        this.movement = new Movement(this.board);
        this.generateTiles = new GenerateTiles(this.board);
    }

    /**
     * Constructor for initializing with own board and movement as well as the game winning number
     *
     * @param theBoard - the board being used
     * @param theMovement - the movement being used
     * @param userDesiredEndNum - integer of the user's desired end number
     */
    public GameManager(Board theBoard, Movement theMovement, int userDesiredEndNum){
        this.board = theBoard;
        this.board_size = theBoard.getSize();
        this.movement = theMovement;
        this.gameEndNumber = userDesiredEndNum;
    }
    /** Getter method for accessing the board for unit testing*/
    public Board getBoard() {return board;}

    /** Getter method for accessing movement for unit testing*/
    public Movement getMovement() {return movement;}

    /**
     * Player win is defined as player reaches the desired number
     * @return boolean true if player has reached the desired number. Otherwise, return false
     */
    public boolean didPlayerWin() {
        // Iterate through the board to find the winning number
        for (ObservableList<Tile> row : this.board.getBoard()){
            for (Tile tile : row){
                if (tile.getCurrNum() == this.gameEndNumber){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Player lose is defined as no more possible movements can be made
     * @return boolean true if player can not make more moves. Otherwise, return false if possible moves can still be made
     */
    public boolean didPlayerLose() throws TileOccupiedException, OutOfBoardException, BoardIsFullException {
        // Create copy of board and movement to not interfere with user's current board
        // Used for predicting possible movements
        Board tempBoard = makeCopyOfBoard();
        Movement tempMovement = new Movement(tempBoard);
        // Try each possible movement; board will change when combination happens or tiles change positions
        tempMovement.moveTile("w");
        tempMovement.moveTile("s");
        tempMovement.moveTile("a");
        tempMovement.moveTile("d");
        // Check if board changed from any possible movement
        return board.equals(tempBoard);
    }




    /**
     * Helper method to creating a separate board for seeing if possible moves can be made or not
     * @return Board copy of the player's current board
     * @throws OutOfBoardException - in case of placing a tile out of the board
     */
    public Board makeCopyOfBoard() throws OutOfBoardException {
        Board tempBoard = new Board(this.board.getSize());
        for (int row = 0; row < this.board.getSize(); row++){
            for (int column = 0; column < this.board.getSize(); column++){
                int tileValue = this.board.getTileAt(row, column).getCurrNum();
                tempBoard.replaceTile(row, column, new Tile(tileValue));
            }
        }
        return tempBoard;
    }

    /**
     * Used to get the score of the game by summing up the numbers on the board
     * @return the sum of the numbers on the board
     */
    public int calculateScore(){
        int score = 0;
        for (int row = 0; row < this.board.getSize(); row++){
            for (int column = 0; column < this.board.getSize(); column++){
                Tile curTile = this.board.getTileAt(row, column);
                score += curTile.getCurrNum();
            }
        }
        return score;
    }

    /**
     * Sees if there was any change in the board to generate new tiles for the player
     * If there is, place a new tile on the board
     * @param userInput - The key/movement the player did
     * @throws OutOfBoardException - when handling movement of the board
     * @throws TileOccupiedException - when trying to generate a new tile
     */
    public void processUserInputForNewTile(String userInput) throws OutOfBoardException, TileOccupiedException, BoardIsFullException {
        Board tempBoard = makeCopyOfBoard();
        Movement tempMovement = new Movement(tempBoard);
        // Shouldn't need to worry about invalid inputs since moveTile handles wrong inputs
        this.movement.moveTile(userInput);
        tempMovement.moveTile(userInput);
        if (!board.equals(tempMovement.getTheBoard())){
            int [] emptySpot = this.generateTiles.findEmptyPosition();
            // Index 0 refers to the row of the empty tile location
            // Index 1 refers to the column of the empty tile location
            this.board.addTile(emptySpot[0], emptySpot[1], this.generateTiles.generateNewTile());
        }
    }

    public int getGameEndNumber(){
        return gameEndNumber;
    }

    public static void main(String[] args) throws TileOccupiedException, OutOfBoardException, BoardIsFullException {



    }
}