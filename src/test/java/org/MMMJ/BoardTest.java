package org.MMMJ;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    int boardSize;
    Board theBoard;
    @BeforeEach
    void setUp() throws TileOccupiedException, OutOfBoardException {
        boardSize = 5;
        theBoard = new Board(boardSize);

    }

    /**
     * Test the equals function which should iterate through the list making sure the tiles have equals numbers
     *
     * @throws TileOccupiedException If the tile position is already occupied.
     * @throws OutOfBoardException   If the tile position is out of the board boundaries.
     */
    @Test
    void equals() throws TileOccupiedException, OutOfBoardException {
        Board testBoard1 = new Board(5);
        Board testBoard2 = new Board(4);
        // different sizes should automatically fail
        assertFalse(theBoard.equals(testBoard2));

        // one has an items the other does not
        testBoard1.addTile(1,1,new Tile(4));
        assertFalse(theBoard.equals(testBoard1));

        theBoard.addTile(1,1,new Tile(4));
        assertTrue(theBoard.equals(testBoard1));
    }

    /**
     * Tests the is full function testing with a non full board then full board
     *
     * @throws TileOccupiedException If the tile position is already occupied.
     * @throws OutOfBoardException   If the tile position is out of the board boundaries.
     */
    @Test
    void isFull() throws TileOccupiedException, OutOfBoardException {
        Board board = new Board(2);
        assertFalse(board.isBoardFull());
        board.addTile(0,0, new Tile(8));
        board.addTile(0,1, new Tile(9));
        board.addTile(1,0, new Tile(10));
        board.addTile(1,1, new Tile(11));
        assertTrue(board.isBoardFull());
    }

    /**
     * Verifies each tile is initialized  with the right metadata
     */
    @Test
    void initBoard() {
        Random random = new Random();

        // gets random tile to guess since all tiles in this case should be initialized the same
        int randomX = random.nextInt(boardSize);
        int randomY = random.nextInt(boardSize);
        // test whether the tile class data is initialized correctly
        Tile randomTile = theBoard.getTileAt(randomX, randomY);
        assertEquals(randomTile.getXPos(), randomX);
        assertEquals(randomTile.getYPos(),randomY);
        assertEquals(randomTile.getCurrNum(), 0);
    }

    /**
     * Tests adding a tile to the board.
     *
     * @throws TileOccupiedException If the tile position is already occupied.
     * @throws OutOfBoardException   If the tile position is out of the board boundaries.
     */
    @Test
    void addTile() throws TileOccupiedException, OutOfBoardException {
        // Its actually in the board
        theBoard.addTile(3,4,new Tile(8));
        Tile tileAt = theBoard.getTileAt(3,4);

        // information is updated correctly in the Tile class
        assertEquals(tileAt.getCurrNum(), 8);
        assertEquals(tileAt.getXPos(), 3);
        assertEquals(tileAt.getYPos(), 4);
    }

    /**
     * Tests adding a tile that results in an OutOfBoardException.
     */
    @Test
    void addTileOutOfBoardException(){
        assertThrows(OutOfBoardException.class,() -> theBoard.addTile(boardSize,boardSize, new Tile(4)));

    }

    /**
     * Tests adding a tile to an occupied position and replacing a tile.
     *
     * @throws TileOccupiedException If the tile position is already occupied.
     * @throws OutOfBoardException   If the tile position is out of the board boundaries.
     */
    @Test
    void addTileOccupiedException() throws TileOccupiedException, OutOfBoardException {
        theBoard.addTile(4,4,new Tile(8));
        // should not be able to add tile to an existing spot
        assertThrows(TileOccupiedException.class,() -> theBoard.addTile(4,4, new Tile(4)));
        // should be able to replace the tile
        assertDoesNotThrow(() -> theBoard.replaceTile(4,4, new Tile(2)));

    }

    @Test
    void isBoardFull(){

    }




}