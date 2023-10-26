package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import src.QuickFindDS;

public class QuickFindDSTest {
    @Test
    /** This test performs basic operations on QuickFindDS. */
    public void testQuickFindDS() {
        QuickFindDS ds = new QuickFindDS(10);

        // Initially, all elements should be disconnected
        assertFalse(ds.isConnected(1, 2));
        assertFalse(ds.isConnected(3, 4));

        // Connect elements and check their connectivity
        ds.connect(1, 2);
        ds.connect(3, 4);
        assertTrue(ds.isConnected(1, 2));
        assertTrue(ds.isConnected(3, 4));
        assertFalse(ds.isConnected(2, 3));
        assertFalse(ds.isConnected(1, 3));

        // Connecting elements indirectly
        ds.connect(2, 3);
        assertTrue(ds.isConnected(1, 3));
        assertTrue(ds.isConnected(4, 2));
    }
}
