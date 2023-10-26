package src;

import java.util.List;
import java.util.ArrayList;

public class BST_DS {
    private static class Node {
        int value;
        Node left;
        Node right;
        Node parent;

        // Constructor of the Node class, which initializes a Node object.
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
