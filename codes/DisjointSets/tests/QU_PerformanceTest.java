package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.DisjointSetsDS;
import src.QuickUnionDS;
import src.WeightedQuickUnionDS;

public class QU_PerformanceTest {
    public void performanceTest(DisjointSetsDS ds, String name) {
        String lineSeparator = System.lineSeparator();
        int size = 100000000;

        long st = System.currentTimeMillis();
        for (int i = 0; i < size - 1; i++) {
            ds.isConnected(i, 7);
        }
        long ed = System.currentTimeMillis();
        System.out.println("Fixed Test for 'isConnected': Time taken for " + name + ": " + (ed - st) + " ms");

        long st2 = System.currentTimeMillis();
        for (int i = 0; i < size - 1; i++) {
            ds.connect(i, 3);
            ds.connect(i, 7);
            ds.connect(i, 100);
        }
        long ed2 = System.currentTimeMillis();
        System.out.println("Fixed Test for 'connect': Time taken for " + name + ": " + (ed2 - st2) + " ms");


        long start = System.currentTimeMillis();
        // Create a deep tree
        for (int i = 0; i < size - 1; i++) {
            ds.connect(i, i + 1);
        }
        // Check if the first and the last element are connected
        // This will need to traverse the tree from the root to the leaf
        Assertions.assertTrue(ds.isConnected(0, size - 1));
        long end = System.currentTimeMillis();

        // Check the time taken to find if two nodes are connected
        System.out.println("Neighbour Test: Time taken for " + name + ": " + (end - start) + " ms" + lineSeparator);
    }

    @Test
    public void testPWQuickUnion() {
        performanceTest(new WeightedQuickUnionDS(100000000), "PWQuickUnion");
    }

    @Test
    public void testWeightedQuickUnionPerformance() {
        performanceTest(new WeightedQuickUnionDS(100000000), "WeightedQuickUnion");
    }

    @Test
    public void testQuickUnionPerformance() {
        performanceTest(new QuickUnionDS(100000000), "QuickUnion");
    }
}
