package src;

public class PWQuickUnionDS implements DisjointSetsDS {
    private int[] parent;
    private int[] size;

    public PWQuickUnionDS(int num) {
        parent = new int[num];
        size = new int[num];
        for (int i = 0; i < num; i++) {
            parent[i] = i; // each node is its own parent initially
            size[i] = 1; // each component is of size 1 initially
        }
    }

    // path compression
    private int find(int p){
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }


    @Override
    public void connect(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return; // already connected

        // Weighted quick union: connect smaller tree to larger tree
        if (size[i] < size[j]) {
            parent[i] = j;
            size[j] += size[i];
        } else {
            parent[j] = i;
            size[i] += size[j];
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
