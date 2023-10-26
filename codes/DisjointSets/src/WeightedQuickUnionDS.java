package src;

public class WeightedQuickUnionDS implements DisjointSetsDS {
    private int[] parent;
    private int[] size;

    public WeightedQuickUnionDS(int num) {
        parent = new int[num];
        size = new int[num];

        for (int i = 0; i < num; i++) {
            parent[i] = -1;  // At initialization every node is at root.
            size[i] = 1;     // At initialization the weight of each root is 1.
        }
    }

    private int find(int p){
        while (parent[p] >= 0) {
            p = parent[p];
        }
        return p;
    }

    @Override
    public void connect(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return; // If both nodes are in the same set, no need to connect.

        // Connect the tree with less weight to the larger one.
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}

