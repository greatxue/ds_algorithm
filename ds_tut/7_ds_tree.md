# 7. ADTs and Trees

## 7.1 Abstract Data Types

The most important interfaces in the `java.util` library are extended from the `Collection` interface, like **Lists**, **Sets** and **Maps**.

Some commonly used ADTs are:

* **Stacks:** Structures that support **last-in-first-out** retrieval of elements
  * `push(int x)`: puts x on top of the stack
  * `int pop()`: takes the element on top of the stack
* **Lists:** An ordered set of elements
  * `add(int i)`: adds an element
  * `int get(int i)`: gets element at index i
* **Sets:** An unordered set of unique elements **with no repeats**
  * `add(int i)`: adds an element
  * `contains(int i)`: returns a boolean for whether or not the set contains the value
* **Maps:** Set of **key/value** pairs
  * `put(K key, V value)`: puts a key-value pair into the map
  * `V get(K key)`: gets the value corresponding to the key

## 7.2 Binary Search Trees

### 7.2.1 Introduction

**Linked Lists** are great, but it takes a long time to search for an item, even if the list is sorted. Meanwhile, **binary search** makes it possible to find an element in an array faster, for $\log{N}$ time.

To combine the thoughts of the two, one way to optimize is to have a reference to the **middle** node, which takes constant time; and further optimize it by adding pointers to the middle of each **recursive half** like so. Streching this structure vertically, a tree is implemnted with each juncture spliting in 2, which is called a **binary tree**. Here are some basic characteristics:

* **Constraints:** Only 1 path between any two nodes.
* **root:** the node having no parents
* **leaves:** nodes with no children

Remember that the structure **with a cycle** is never a valid tree. To be specific,

* **Binary Trees:** Each node has either 0, 1 or 2 children, in addition to requirements above.

* **Binary Search Trees:** For every **node X** of the tree, the key in the **left** subtree is **less** than X's key, while the key in the **right** subtree is **greater** than X's key.

Here is the implementation of the module:

```java
private class BST<Key> {
    private Key key;
    private BST left;
    private BST right;

    public BST(Key key, BST left, BST Right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public BST(Key key) {
        this.key = key;
    }
}
```

 ### 7.2.2 Operations

**Search**

* Recall the characteristic of binary search trees. Similar to that of binary search, it **starts at the root** and move to left or right child, repeating the process recursively. As a result, it will **find the item**, or it **reaches a leaf**, in which case the tree does not contain the item. Here is the implementation:

```java
static BST find(BST T, Key sk) {
   if (T == null)
      return null;
   if (sk.equals(T.key))
      return T;
   else if (sk < T.key)
      return find(T.left, sk);
   else
      return find(T.right, sk);
}
```

For a "bushy" tree, the `find` operation will run in $\log{N}$ time, as the height of the tree is $\log{N}$.

**Insert**

* **Always** insert at a leaf node! Search the tree, and add if not within.

```java
static BST insert(BST T, Key ik) {
  if (T == null)
    return new BST(ik);
  if (ik < T.key)
    T.left = insert(T.left, ik);
  else if (ik > T.key)
    T.right = insert(T.right, ik);
  return T;
}
```

**Delete**

* **Deletion with no children:** 

  For a leaf, just delete the **parent pointer** and the node will be swept away by the garbage collector.

* **Deletion with 1 children:**

  With respect to the node-to-delete, just reassign the **parent's child** pointer to the **node's child**.

* **Deletion with 2 children:**

  Take the **right-most node** in the left subtree or the **left-most node** in the right subtree, replace the node-to-delete with either node and remove the initial x-most node.

  This is called **Hubbard deletion**.

### 7.2.3 Application

Multiple **ADTs** could be implemented using BST:

* `Set`: With BST, the runtime of `contains` could be decreased to $\Theta(\log{N})$.
* `map`: Make a binary tree into a map by having each BST node holding `(key, value)` pairs instead of sigular values.

Here is an implementation:

```java
import java.util.List;
import java.util.ArrayList;

public class BST_DS {
    private static class Node {
        int value;
        Node left;
        Node right;
        Node parent;

        // Constructor of the Node class, which initiates a Node object.
        private Node(int value) {
            this.value = value;
            left = null;
            right = null;
            parent = null;
        }
    }

    // Define the root node of the tree.
    private Node root;

    /** Inserts a new value to the tree. */
    public void insert(int value) {
        if (root == null) {
            root = new Node(value); // If the tree is empty, assign a new node address to the root
        } else {
            insertNode(root, value); // Otherwise, call the private insertNode method.
        }
    }

    /** Helper method to insert a node with value to the tree. */
    private void insertNode(Node crt, int value) {
        if (value > crt.value) {
            if (crt.right != null) {
                insertNode(crt.right, value); // If the child node is null, create a new node there.
            } else {
                crt.right = new Node(value); // If the child node is not null, continue to call the insertNode method
                crt.right.parent = crt;
            }
        } else if (value < crt.value) {
            if (crt.left != null) {
            insertNode(crt.left, value);
            } else {
                crt.left = new Node(value);
                crt.left.parent = crt;
            }
        }
    }

    /** Searches for a specific value in the tree. */
    public boolean find(int value) {return findNode(root, value);}

    /** Helper method to find whether there is a node with specific value. */
    private boolean findNode(Node crt, int value) {
        if (crt == null) {
            return false;
        } else {
            if (value == crt.value) {
                return true;
            } else if (value < crt.value) {
                return findNode(crt.left, value);
            } else {
                return findNode(crt.right, value);
            }
        }
    }

    /** Finds the node with maximum value, i.e. the right-most node. */
    public Object findMax() {
        Node crt = root;
        if (crt == null) {
            return null;
        }
        while (crt.right != null) {
            crt = crt.right;
        }
        return crt.value;
    }

    /** Finds the node with minimum value, i.e. the left-most node. */
    public Object findMin() {
        Node crt = root;
        if (crt == null) {
            return null;
        }
        while (crt.left != null) {
            crt = crt.left;
        }
        return crt.value;
    }

    /** Returns the node with minimum value from the specific start node. */
    private Node findMinNode(Node crt) {
        while (crt.left != null) {
            crt = crt.left;
        }
        return crt;
    }


    /** Deletes the node with specific value */
    public void delete(int value) {
        Node nodeToDelete = findNodeToDelete(root, value);
        // Case 0: Node-to-delete does not exist in the tree.
        if (nodeToDelete == null) {
            return;
        }

        Node parent = nodeToDelete.parent;
        // Case I: Deletion with no children
        if (nodeToDelete.left == null && nodeToDelete.right == null) {
            if (parent == null) {
                root = null; // Special case: Node-to-delete is the root, and there are no children.
            } else if (parent.left == nodeToDelete) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }

        // Case II: Deletion with one child
        else if (nodeToDelete.left == null || nodeToDelete.right == null) {
            Node child = (nodeToDelete.left != null) ? nodeToDelete.left : nodeToDelete.right;
            if (parent.left == nodeToDelete) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }

        // Case III: Deletion with two children
        else {
            Node successor = findMinNode(nodeToDelete.right);
            int successorValue = successor.value;
            this.delete(successorValue); // remove the successor node
            nodeToDelete.value = successorValue; // assign the successor value to the node-to-delete
        }
    }

    /** Helper method to return a node with specific value. */
    private Node findNodeToDelete(Node crt, int value) {
        if (crt == null) {
            return null;
        } else {
            if (value == crt.value) {
                return crt;
            } else if (value < crt.value) {
                return findNodeToDelete(crt.left, value);
            } else {
                return findNodeToDelete(crt.right, value);
            }
        }
    }

    /** Converts the tree to JavaList by in-order traversal for unit tests. */
    public List<Integer> toList() {
        List<Integer> TreeList = new ArrayList<>();
        TreeList = inOrderTraversal(root, TreeList);
        return TreeList;
    }

    /** Helper method to converts the tree to JavaList. */
    private List<Integer> inOrderTraversal(Node crt, List<Integer> TreeList) {
        // It traverses the tree with left-root-right order, ensuring the list in ascending order.
        if (crt != null) {
            this.inOrderTraversal(crt.left, TreeList);
            TreeList.add(crt.value);
            this.inOrderTraversal(crt.right, TreeList);
        }
        return TreeList;
    }
}
```

## 7.3 B-Trees

### 7.3.1 BST Performance

Every tree has its **height** and nodes in each **depth**. The **average depth** of a tree is the mean of the depth of every node. Height and depth determines the runtime of BST operations, while the height determines the **worst-case** runtime to find a node, the average depth **average-case** runtime.

The **order** to insert the node in has a major impact on the height and average depth of a BST. For example, the order `1, 2, 3, 4, 5, 6, 7` results in a height 6 and an average depth 3; the order `4, 2, 1, 3, 6, 5, 7` results in a height 2 and an average depth 1.43, which is much better. 

For the **best** case of a "bushy" tree, it has a height of $\Theta(\log{N})$; for the **worst** case of a "spindly" one, it has a height of $\Theta(N)$, which is similar to a linked list. For **randomly generated** ones, it can be proved $E(d) = 2\ln N$ and $E(h) = 4.311\ln N$. However, there were cases when real-time data coming **sequentially**, with no time to shuffle the data.

## 7.4 B-Tree implementation

By simply **avoiding new leaves to be inserted**, the height would never increase. So in the new implementation, it simply stacks the new value into a existing leaf node at the appropriate location and moves up a value when reaching a certain number of values. Sometimes there are adaptions, and the children of a overstuffed node could be split into ranges more than 2. Only in the case when the root is above the limit could the tree height be increased.

This splitting-tree data strcture with perfect balance is called **B-Tree**. The one with a limit of 3 items per node is called **2-3-4 tree**; while with the limit of 2, **2-3 tree**.

The B-Tree has the variant that **all leaves are the same distance from the root** and, **a non-leaf node with k items must have exactly k + 1 children**.

For the details in the implementation, please refer to the codes below:

```java
```



