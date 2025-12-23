package org.MMMJ;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MovementTest {

    private Movement myMovement;

    @BeforeEach
    void setUp() throws TileOccupiedException, OutOfBoardException {
        Board myBoard = new Board(6);
        myBoard.addTile(3,1, new Tile(4));
        myBoard.addTile(3,2, new Tile(8));
        myBoard.addTile(2,1, new Tile(2));
        myMovement = new Movement(myBoard);
    }


    @Test
    void moveTileUp() throws TileOccupiedException, OutOfBoardException, BoardIsFullException {
        myMovement.moveTile("w");
        assertEquals(myMovement.getTheBoard().getTileAt(0,1).getCurrNum(),2);
        assertEquals(myMovement.getTheBoard().getTileAt(1,1).getCurrNum(), 4);
        assertEquals(myMovement.getTheBoard().getTileAt(0,2).getCurrNum(), 8);

    }
    @Test
    void moveTileDown() throws TileOccupiedException, OutOfBoardException, BoardIsFullException {
        myMovement.moveTile("s");
        assertEquals(myMovement.getTheBoard().getTileAt(4,1).getCurrNum(),2);
        assertEquals(myMovement.getTheBoard().getTileAt(5,1).getCurrNum(), 4);
        assertEquals(myMovement.getTheBoard().getTileAt(5,2).getCurrNum(), 8);

    }
    @Test
    void moveTileRight() throws TileOccupiedException, OutOfBoardException, BoardIsFullException {
        myMovement.moveTile("d");
        assertEquals(myMovement.getTheBoard().getTileAt(2,5).getCurrNum(),2);
        assertEquals(myMovement.getTheBoard().getTileAt(3,4).getCurrNum(), 4);
        assertEquals(myMovement.getTheBoard().getTileAt(3,5).getCurrNum(), 8);
    }
    @Test
    void moveTileLeft() throws TileOccupiedException, OutOfBoardException, BoardIsFullException {
        myMovement.moveTile("a");
        assertEquals(myMovement.getTheBoard().getTileAt(2,0).getCurrNum(),2);
        assertEquals(myMovement.getTheBoard().getTileAt(3,0).getCurrNum(), 4);
        assertEquals(myMovement.getTheBoard().getTileAt(3,1).getCurrNum(), 8);
    }

    @Test
    void combineW() throws TileOccupiedException, OutOfBoardException, BoardIsFullException {
        // Add some tiles for combining upwards
        // Replace the tile 2 with 4 to see if combining happens when moving upwards
        this.myMovement.getTheBoard().replaceTile(2, 1, new Tile(4));
        this.myMovement.moveTile("w");
        // Should be updated to the tile value of 8
        assertEquals(this.myMovement.getTheBoard().getTileAt(0,1).getCurrNum(), 8);
        // Should remain the same as 8
        assertEquals(this.myMovement.getTheBoard().getTileAt(0,2).getCurrNum(), 8);
    }
    @Test
    void combineA() throws BoardIsFullException, TileOccupiedException, OutOfBoardException {
        // Add 2 to the left of 2
        this.myMovement.getTheBoard().addTile(2, 0, new Tile(2));
        this.myMovement.moveTile("a");
        // Should have 4 and the other numbers shouldn't have combined
        assertEquals(this.myMovement.getTheBoard().getTileAt(2,0).getCurrNum(), 4);
        assertEquals(this.myMovement.getTheBoard().getTileAt(3,0).getCurrNum(), 4);
        assertEquals(this.myMovement.getTheBoard().getTileAt(3,1).getCurrNum(), 8);
    }

    @Test
    void combineS() throws BoardIsFullException, TileOccupiedException, OutOfBoardException{
        this.myMovement.getTheBoard().addTile(4, 2, new Tile(8));
        this.myMovement.moveTile("s");
        // 8 Should combine while everything else remains the same
        assertEquals(this.myMovement.getTheBoard().getTileAt(5,2).getCurrNum(), 16);
        assertEquals(this.myMovement.getTheBoard().getTileAt(5,1).getCurrNum(), 4);
        assertEquals(this.myMovement.getTheBoard().getTileAt(4,1).getCurrNum(), 2);
    }
    @Test
    void combineD() throws TileOccupiedException, OutOfBoardException, BoardIsFullException {
        this.myMovement.getTheBoard().addTile(2, 3, new Tile(2));
        this.myMovement.moveTile("d");
        // 2 Should combine while everything else remains the same
        assertEquals(this.myMovement.getTheBoard().getTileAt(2,5).getCurrNum(), 4);
        assertEquals(this.myMovement.getTheBoard().getTileAt(3,4).getCurrNum(), 4);
        assertEquals(this.myMovement.getTheBoard().getTileAt(3,5).getCurrNum(), 8);
    }
}