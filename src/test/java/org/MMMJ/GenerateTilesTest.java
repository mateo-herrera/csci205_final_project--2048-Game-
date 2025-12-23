package org.MMMJ;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerateTilesTest {

    GenerateTiles genTiles;

    Board theBoard;

    @BeforeEach
    void setUp() {
        theBoard = new Board(2);
        genTiles = new GenerateTiles(theBoard);
    }

    @Test
    void generateNewTile() {
        Tile generatedTile = genTiles.generateNewTile();
        // will generate a tile of 2 or 4
        assertTrue(generatedTile.getCurrNum() == 2 || generatedTile.getCurrNum() == 4);
    }

    /**
     * Testing the find empty positions function by leaving only one spot on the board and
     * saving that position to emptyPos which will only be the only position left
     *
     * @throws BoardIsFullException if the board is full
     */
    @Test
    void findEmptyPosition() throws BoardIsFullException, TileOccupiedException, OutOfBoardException {
        theBoard.addTile(0,0, new Tile(4));
        theBoard.addTile(0,1, new Tile(2));
        theBoard.addTile(1,1, new Tile(8));
        int[] emptyPos = genTiles.findEmptyPosition();
        assertEquals(1, emptyPos[0]);
        assertEquals(0, emptyPos[1]);
    }


    @Test
    void findEmptyPosBoardFullException() throws TileOccupiedException, OutOfBoardException {
        theBoard.addTile(0,0, new Tile(4));
        theBoard.addTile(0,1, new Tile(2));
        theBoard.addTile(1,1, new Tile(8));
        theBoard.addTile(1,0, new Tile(8));
        assertThrows(BoardIsFullException.class, () -> genTiles.findEmptyPosition());


    }
}