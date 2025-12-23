package org.MMMJ;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    private Tile tile1;
    @BeforeEach
    void setUp(){
        tile1 = new Tile(2);
    }

    @Test
    void testEquals() {
        Tile tile2 = new Tile(4);
        Tile tile3 = new Tile(2);
        assertTrue(tile1.equals(tile3));
        assertFalse(tile1.equals(tile2));
    }

    @Test
    void testToString() {
        Tile tile2 = new Tile();
        assertEquals(tile1.toString(), "   2");
        assertEquals(tile2.toString(),"    ");
    }
}