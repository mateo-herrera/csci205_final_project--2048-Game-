package org.MMMJ;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jason Chung
 * Unit tests for GameManager class
 */
class GameManagerTest {

    private GameManager game;
    @BeforeEach
    void setUp() throws TileOccupiedException, OutOfBoardException {
        // Desired board and goal
        this.game = new GameManager(3, 32);
        // Fill board with tiles
        this.game.getBoard().addTile(1,1, new Tile(16));
        this.game.getBoard().addTile(0, 0, new Tile(2));
        this.game.getBoard().addTile(0, 2, new Tile(3));
        this.game.getBoard().addTile(2, 0, new Tile(1));
        this.game.getBoard().addTile(2, 2, new Tile(5));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void didPlayerWin () throws TileOccupiedException, OutOfBoardException, BoardIsFullException {
        // Make sure the game isn't automatically in win condition
        assertFalse(this.game.didPlayerWin());
        this.game.getBoard().addTile(0, 1, new Tile(16));
        this.game.getMovement().moveTile("w");
        // Should see that player has 32, which is the win condition
        assertTrue(this.game.didPlayerWin());
    }

    @Test
    void didPlayerLose() throws TileOccupiedException, OutOfBoardException, BoardIsFullException {
        // Make sure player isn't automatically in lose condition
        assertFalse(this.game.didPlayerLose());
        this.game.getBoard().addTile(0, 1, new Tile(6));
        // Should still be possible to make moves
        this.game.getBoard().addTile(1, 0, new Tile(7));
        // Should still be possible to make moves
        assertFalse(this.game.didPlayerLose());
        this.game.getBoard().addTile(1, 2, new Tile(8));
        // Should still be possible to make moves
        assertFalse(this.game.didPlayerLose());
        this.game.getBoard().addTile(2,1,new Tile(9));
        // Should now be true since no more moves possible
        assertTrue(this.game.didPlayerLose());
    }

    @Test
    void getScore() throws TileOccupiedException, OutOfBoardException {
        //Score default from setting up JUnit tests
        assertEquals(this.game.calculateScore(), 27);
        // Add some more tiles and see if score is updating
        this.game.getBoard().addTile(0,1, new Tile(15));
        assertEquals(this.game.calculateScore(), 42);
        this.game.getBoard().addTile(1,0, new Tile(24));
        assertEquals(this.game.calculateScore(), 66);
        this.game.getBoard().addTile(1,2,new Tile(34));
        assertEquals(this.game.calculateScore(), 100);
    }

    @Test
    void processUserInputForNewTile() throws OutOfBoardException, BoardIsFullException, TileOccupiedException {
        Board compareBoard = this.game.makeCopyOfBoard();
        // Should be exact replica of board
        assertTrue(this.game.getBoard().equals(compareBoard));

        // Let's make sure invalid key doesn't change the board
        this.game.processUserInputForNewTile("g");
        assertTrue(this.game.getBoard().equals(compareBoard));

        // Now let's put a real possible movement
        this.game.processUserInputForNewTile("w");
        assertFalse(this.game.getBoard().equals(compareBoard));
    }
    @Test
    void getBoard() throws TileOccupiedException, OutOfBoardException {
        // Shouldn't be the same as the pre-existing board from the setup
        Board testBoard1 = new Board(3);
        assertFalse(this.game.getBoard().equals(testBoard1));

        // Should be able to return false if board is of different size
        Board testBoard2 = new Board(4);
        assertFalse(this.game.getBoard().equals(testBoard2));

        testBoard1.addTile(1,1, new Tile(16));
        testBoard1.addTile(0, 0, new Tile(2));
        testBoard1.addTile(0, 2, new Tile(3));
        testBoard1.addTile(2, 0, new Tile(1));
        testBoard1.addTile(2, 2, new Tile(5));

        // Should be the exact same boards
        assertTrue(this.game.getBoard().equals(testBoard1));
    }

    @Test
    void makeCopyOfBoard () throws OutOfBoardException, TileOccupiedException, BoardIsFullException {
        // Make an exact replica of the current board
        Board copyBoard = this.game.makeCopyOfBoard();
        assertTrue(this.game.getBoard().equals(copyBoard));

        // Let's add a tile and see if the copy board makes the same board again
        this.game.getBoard().addTile(1,0, new Tile(45));
        Board copyBoard2 = this.game.makeCopyOfBoard();
        assertTrue(this.game.getBoard().equals(copyBoard2));

        // Let's do a movement and see if the board copier can still replicate the board
        this.game.getMovement().moveTile("d");
        Board copyBoard3 = this.game.makeCopyOfBoard();
        assertTrue(this.game.getBoard().equals(copyBoard3));
    }
}