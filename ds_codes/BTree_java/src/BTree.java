package src;

/** A simple implementation of a 2-3 tree, which could balance itself well. */
public class BTree {
    private static class Node {
        int numOfKeys = 1;
        int[] keys = new int[2]; // The maximum number to hold keys in a Node is 3 in a 2-3 tree.
        Node[] children = new Node[3];
        Node parent;
        boolean isLeaf = true;

        // Constructor of the Node class, which initializes a Node object.
        private Node(int key, boolean isLeaf) {
            keys[0] = key;
            this.isLeaf = isLeaf;
        }

        /** Inserts the key into the Node, which is a sub-process of the insertion. */
        private void insertInNode(int key) {
            // Finds appropriate index to insert the key.
            int index = 0;
            while (index < numOfKeys && key > keys[index]) {
                index++;
            }
            // Moves existing key away to make space for the new key.
            for (int i = numOfKeys - 1; i >= index; i--) {
                keys[i + 1] = keys[i];
            }
            // Inserts the new key.
            keys[index] = key;
            numOfKeys++;
        }

        /** Determines whether a specific key contains in the Node. */
        private boolean nodeContains(int key) {
            for (int i = 0; i < numOfKeys; i++) {
                if (keys[i] == key) {
                    return true;
                }
            }
            return false;
        }

        /** Splits the Node, while the numOfKeys in Node reaches the limit of 3. */
        private void splitNode(int key) {
            if (this.numOfKeys == 2) {
                if (key <= keys[0]) {
                    helperSplitNode(key, keys[0], keys[1]);
                } else if (key <= keys[1]) {
                    helperSplitNode(keys[0], key, keys[1]);
                } else {
                    helperSplitNode(keys[0], keys[1], key);
                }

            }
        }
        private void helperSplitNode(int k1, int k2, int k3) {
            Node node_1 = new Node(k1, true);
            Node node_2 = new Node(k3, true);
            if (this.parent.numOfKeys < 2) {
                this.parent.insertInNode(k2);
            } else {
                this.parent.splitNode(k2);
            }
        }
    }

    // Define the root of the tree.
    private Node root;

    /** Inserts a new value to the B-Tree. */
    public void insert(int value) {

        if (root == null) {
            // Assigns the root with a new Node if the B-Tree is empty.
            root = new Node(value, true);
        } else {
            // Inserts value into a to-be-filled Node.
            if (root.numOfKeys == 1) {
                root.insertInNode(value);
            }
            // Inserts value into a full Node.
            if (root.numOfKeys == 2) {
                //TODO

            }
        }
    }
}
