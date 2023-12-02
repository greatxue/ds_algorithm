# 6. Disjoint Sets

## 6.1 Introduction

Two sets are named **disjoint sets** if they have no elements in common. 

A **Disjoint-Sets (or Union-Find)** data structure keeps track of a fixed number of elements partitioned into a number of disjoint sets. The data structure has two operations:

* `connect(x, y)`: connect `x` and `y`, also known as `union`.

* `isConnected(x, y)`: returns true if `x` and `y` are connected (i.e. part of the same set).

The big idea for the data structure is to have **the connected elements** in the same **set**.

With the intuition in mind, define how the **interface** should be like (which determines *what* it behaves instead of *how* it is accomplished):

```java
public interface DisjointSets {
    /** connects two items P and Q */
    void connect(int p, int q);
  
    /** checks to see if two items are connected */
    boolean isConnected(int p, int q); 
}
```

## 6.2 QuickFind and QuickUnion

The idea of `List<Set<Integer>>` is rejected, as it could be slow based on the implementation of Java `List`. 

* `ListOfSetsDS` is complicated and slow.

* It has to iterate over all sets, resulting in a `LIstOfSetsDS` with linear complexity.

Why not use a list of integers instead of a list of sets' integers? Here is part of the implementation:

```java
public class QuickFindDS implements DisjointSets {

    private int[] id;

    /* Θ(N) */
    public QuickFindDS(int N){
        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
        }
    }

    /* need to iterate through the array => Θ(N) */
    public void connect(int p, int q){
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++){
            if (id[i] == pid){
                id[i] = qid;
            }
        }
    }

    /* Θ(1) */
    public boolean isConnected(int p, int q){
        return (id[p] == id[q]);
    }
}
```


* Represent each variable in the same set with the same `id`, a randomly picked number.
* `isConnected(x, y)` will be really fast, with constant time complexity.

* However, `connect(x, y)` is still slow.

That's why `QuickUnion` is here:

``` java
public class QuickUnionDS implements DisjointSetsDS{
    private int[] parent;

    public QuickUnionDS(int num) {
        parent = new int[num];
        for (int i = 0; i < num; i++) {
            parent[i] = -1;
        }
    }
  
    private int find(int p){
        while(parent[p] >= 0) {
            p = parent[p];
        }
        return p;
    }
    
  	@Override
    public void connect(int p, int q) {
        int i = find(p);
        int j = find(q);
        parent[i] = j;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
```

Instead of an `id`, the `QuickUnionDS` is constructed in the system where each index of the item is assigned to its parent. If no parents are found, then it is a root assigned with a negative value.

* The implementation could be visualized as a tree.
* The potential performance issue with `QuickUnionDS` is that the tree could become very long, where finding the root could become very expensive. Then `connect` and `isConnected` are all based on the process of traversing all the items to get the root.

## 6.3 Weighted Quick Union (WQU)

To advance the implementation, that is, to keep the tree balanced so as to perform both `connect` and `isConnected` reasonably well, **Weighted Quick Union** is built.

```java
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
```

* Whenever `connect`  is called, link the root of the smaller tree to the larger one. 

With **Path Compression**, a better approach is as follows:

```java
// path compression
private int find(int p){
    if (p != parent[p]) {
        parent[p] = find(parent[p]);
    }
    return parent[p];
}
```

*  Every time `isConnected` is called, link the node directly to the root. 

## 6.4 Summary

| Implementation            | `isConnected`           | `connect`               |
| ------------------------- | ----------------------- | ----------------------- |
| Quick Find                | $\Theta(1)$             | $\Theta(1)$             |
| Quick Union               | $\mathbf{O}(N)$         | $\mathbf{O}(N)$         |
| Weighted Quick Union      | $\mathbf{O}(\log N)$    | $\mathbf{O}(\log N)$    |
| WQU with Path Compression | $\mathbf{O}(\alpha(N))$ | $\mathbf{O}(\alpha(N))$|

For M operations on N elements, WQU with Path Compression is in time complexity: $\mathbf{O}(N+M(\lg^∗N))$, which behaves as constant in long term.
