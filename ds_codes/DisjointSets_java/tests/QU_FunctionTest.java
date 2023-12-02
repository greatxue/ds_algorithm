package tests;

import org.junit.jupiter.api.Test;
import src.DisjointSetsDS;
import src.PWQuickUnionDS;
import src.QuickUnionDS;
import src.WeightedQuickUnionDS;

import static org.junit.jupiter.api.Assertions.*;

public class QU_FunctionTest {
    @Test
    private void testDS(DisjointSetsDS ds) {
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

    @Test
    /** This test performs basic operations on QuickUnionDS. */
    public void testQuickUnionDS() {
        testDS(new QuickUnionDS(10));
    }

    @Test
    /** This test performs basic operations on WeightedQuickUnionDS. */
    public void testWeightedQuickUnionDS() {
        testDS(new WeightedQuickUnionDS(10));
    }

    @Test
    /** This test performs basic operations on PWQuickUnionDS. */
    public void testPWQuickUnionDS() {
        testDS(new PWQuickUnionDS(10));
    }
}
