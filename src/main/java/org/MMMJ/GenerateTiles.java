/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 4/11/24
 *Time: 10:09 PM
 *
 *Project: csci205_final_project
 *Package: org.MMMJ
 *Class: GenerateSquares
 *Description:
 * **************************************** */
package org.MMMJ;

import java.util.Random;

public class GenerateTiles {
    /** A random number**/
    private Random random;

    /** An instance of the {@link Board} class**/
    private Board board;

    /**
     * Constructor for the Generate tiles class
     * @param board - a 2D tile array
     */
    public GenerateTiles(Board board){
        this.random = new Random();
        this.board = board;
    }

    /**
     * Generates a new square between 2 and 4 has a ration that chooses between the two
     * @return the tile object with the new number
     */
    public Tile generateNewTile(){
        int ratio = random.nextInt(100);
        if(ratio <= 75){
            return new Tile(2);
        }else{
            return new Tile(4);
        }
    }

    /**
     * Finds a random new position on the board that is empty
     *
     * @return a list [row, col] of the row,column pair
     */
    public int[] findEmptyPosition() throws BoardIsFullException {
        if(!board.isBoardFull()){
            int testRow;
            int testCol;
            int[] emptyPos = new int[2];
            while(true) {
                testRow = random.nextInt(board.getSize());
                testCol = random.nextInt(board.getSize());
                try {
                    board.testTile(testRow, testCol);
                    break;
                } catch (TileOccupiedException | OutOfBoardException e) {
                }
            }
            emptyPos[0] = testRow;
            emptyPos[1] = testCol;
            return emptyPos;
        }
        else{
            throw new BoardIsFullException("Board is full");
        }
    }


    public static void main(String[] args) throws TileOccupiedException, OutOfBoardException, BoardIsFullException {
        Board board = new Board(2);
        GenerateTiles generateTiles = new GenerateTiles(board);
        board.addTile(0,0,new Tile(4));
        board.addTile(0,1,new Tile(4));
        board.addTile(1,0,new Tile(2));


        board.printBoard();
        int[] emptyPos = generateTiles.findEmptyPosition();
        for (int pos: emptyPos){
            System.out.println(pos);
        }
    }
}