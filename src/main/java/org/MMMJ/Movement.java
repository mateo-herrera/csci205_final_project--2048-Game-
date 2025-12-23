/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 4/11/24
 *Time: 3:25 PM
 *
 *Project: csci205_final_project
 *Package: org.MMMJ
 *Class: Movement
 *Description:
 * **************************************** */
package org.MMMJ;

import javafx.collections.ObservableList;

import java.util.Scanner;

public class Movement {
    private Board theBoard;

    private GenerateTiles genTile;

    public Movement(Board board){
        this.theBoard= board;
        genTile = new GenerateTiles(this.theBoard);
    }

    /**
     * Constructor for the movement class
     */
    public Movement() {
        this.theBoard = new Board(5);
    }

    /**
     * Determines if a tile can move to an empty tile or not and if combining event can happen
     * @param tile - The tile being moved
     * @param key - The direction the player wants to do a move
     * @return true if the tile can move to an empty tile. Otherwise, false
     * @throws OutOfBoardException - when trying to place a tile out of bounds of the board
     */
    public boolean checkCollision(Tile tile ,String key) throws OutOfBoardException {
        switch (key){
            case "w":
                if (tile.getXPos() - 1 >= 0  &&  theBoard.getTileAt(tile.getXPos() - 1,tile.getYPos()).getCurrNum() == 0) {
                    return true;
                }else if(tile.getXPos() - 1 >= 0){
                    Tile tile2 = theBoard.getTileAt(tile.getXPos() - 1, tile.getYPos());
                    this.combine(tile, tile2);
                }
                break;
            case "s":
                if (tile.getXPos() + 1 < theBoard.getSize() && theBoard.getTileAt(tile.getXPos() + 1, tile.getYPos()).getCurrNum() == 0) {
                    return true;
                }else if (tile.getXPos() + 1 < theBoard.getSize()){
                    Tile tile2 = theBoard.getTileAt(tile.getXPos() + 1, tile.getYPos());
                    this.combine(tile, tile2);
                }
                break;
            case "a":
                if (tile.getYPos() - 1 >= 0 && theBoard.getTileAt(tile.getXPos() , tile.getYPos() - 1).getCurrNum() == 0 ) {
                    return true;
                }else if (tile.getYPos() - 1 >= 0 ){
                    Tile tile2 = theBoard.getTileAt(tile.getXPos(), tile.getYPos() - 1);
                    this.combine(tile, tile2);
                }
                break;
            case "d":
                if (tile.getYPos() + 1 < theBoard.getSize() && theBoard.getTileAt(tile.getXPos() , tile.getYPos() + 1).getCurrNum() == 0 ) {
                    return true;
                } else if (tile.getYPos()  + 1 < theBoard.getSize()){
                    Tile tile2 = theBoard.getTileAt(tile.getXPos(), tile.getYPos() + 1);
                    this.combine(tile, tile2);
                }
                break;
            default:
                return false;
        }
        return false;
    }

    /**
     * Uses a switch statement to see which direction the user wants to
     * move the tiles on the board
     * @param userInput - direction the user wants to move
     * @throws TileOccupiedException
     * @throws OutOfBoardException
     */
    public void moveTile(String userInput) throws TileOccupiedException, OutOfBoardException, BoardIsFullException {
        switch(userInput){
            case "w":
                Board localBoard = this.makeCopyOfBoard();
                for(ObservableList<Tile> row : theBoard.getBoard()){
                    for(Tile tile : row) {
                        if (tile.getCurrNum() != 0){
                            while (checkCollision(tile, "w")) {
                                int oldYPos = tile.getYPos();
                                int oldXPos = tile.getXPos();
                                theBoard.addTile(oldXPos - 1, oldYPos, tile);
                                theBoard.replaceTile(oldXPos, oldYPos, new Tile());
                            }


                        }

                    }
                }
                if(!this.theBoard.equals(localBoard)){
                    placeGenTile();
                }
                break;
            case "s":
                localBoard = this.makeCopyOfBoard();
                for(int i = theBoard.getSize() -1;  i >= 0; i--) {
                    for (int j = theBoard.getSize() -1; j >= 0; j--) {
                        Tile tile = theBoard.getTileAt(i,j);
                        if(tile.getCurrNum() != 0) {
                            while (checkCollision(tile, "s")) {
                                int oldYPos = tile.getYPos();
                                int oldXPos = tile.getXPos();
                                theBoard.addTile(oldXPos + 1, oldYPos, tile);
                                theBoard.replaceTile(oldXPos, oldYPos, new Tile());
                            }
                        }
                    }
                }
                if(!this.theBoard.equals(localBoard)){
                    placeGenTile();
                }

                break;
            case "a":
                localBoard = this.makeCopyOfBoard();
                for(ObservableList<Tile> row : theBoard.getBoard()) {
                    for (Tile tile : row) {
                        if(tile.getCurrNum() != 0) {
                            while (checkCollision(tile, "a")) {
                                int oldYPos = tile.getYPos();
                                int oldXPos = tile.getXPos();
                                theBoard.addTile(oldXPos , oldYPos - 1, tile);
                                theBoard.replaceTile(oldXPos, oldYPos, new Tile());
                            }
                        }
                    }
                }
                if(!this.theBoard.equals(localBoard)){
                    placeGenTile();
                }

                break;
            case "d":
                localBoard = this.makeCopyOfBoard();

                for(int i = theBoard.getSize() -1;  i >= 0; i--) {
                    for (int j = theBoard.getSize() -1; j >= 0; j--) {
                        Tile tile = theBoard.getTileAt(i,j);
                        if(tile.getCurrNum() != 0) {
                            while (checkCollision(tile, "d")) {
                                int oldYPos = tile.getYPos();
                                int oldXPos = tile.getXPos();
                                theBoard.addTile(oldXPos , oldYPos + 1, tile);
                                theBoard.replaceTile(oldXPos, oldYPos, new Tile());
                            }
                        }
                    }
                }
                if(!this.theBoard.equals(localBoard)){
                    placeGenTile();
                }
                break;
        }
    }

    public  void placeGenTile() throws BoardIsFullException, OutOfBoardException, TileOccupiedException {
        int[] emptyPos = genTile.findEmptyPosition();
        theBoard.addTile(emptyPos[0], emptyPos[1], genTile.generateNewTile());
    }


    /**
     * Combining method to determine if combining can happen, and if so, updating the board when necessary
     * @param tile1 - The tile being combined into tile2
     * @param tile2 - The tile that is being updated
     * @throws OutOfBoardException - thrown when trying to replace a tile outside the board
     */
    public void combine(Tile tile1, Tile tile2) throws OutOfBoardException {
        if(tile1.equals(tile2)){
            int newNumber = tile2.getCurrNum() * 2;
            tile1.setCurrNum(0);
            tile2.setCurrNum(newNumber);
            this.theBoard.replaceTile(tile1.getXPos(),tile2.getYPos(),tile1);
            this.theBoard.replaceTile(tile2.getXPos(),tile2.getYPos(),tile2);
        }

    }
    /** Getter method that returns the board*/
    public Board getTheBoard(){return theBoard;}

    /**
     * @return a copy of the board currently set up
     * @throws OutOfBoardException if the tile trying to be replaced outside the board limits
     */
    public Board makeCopyOfBoard() throws OutOfBoardException {
        Board tempBoard = new Board(this.theBoard.getSize());
        for (int row = 0; row < this.theBoard.getSize(); row++){
            for (int column = 0; column < this.theBoard.getSize(); column++){
                int tileValue = this.theBoard.getTileAt(row, column).getCurrNum();
                tempBoard.replaceTile(row, column, new Tile(tileValue));
            }
        }
        return tempBoard;
    }

    public static void main(String[] args) throws TileOccupiedException, OutOfBoardException, BoardIsFullException {
        Scanner scnr = new Scanner(System.in); // Scanner used for testing with key inputs
        Board testBoard = new Board(4);


    }



}