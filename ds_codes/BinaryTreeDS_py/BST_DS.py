class BST_DS:
    """This is a Python implementation of a binary search tree, i.e. BST."""

    class Node:
        def __init__(self, value):
            """Initializes the node object with specific value and relationship."""
            self.value = value
            self.left = None
            self.right = None
            self.parent = None

    def __init__(self):
        """Initializes the BST object with a special root node."""
        self.root = None

    def insert(self, value: int) -> None:
        """
        Inserts a new value into the tree.
        Args:
            value: the integer inserted into the tree
        Returns:
            void function
        """
        if self.root is None:
            self.root = BST_DS.Node(value)
        else:
            self.__insert_node(self.root, value)

    def __insert_node(self, crt: Node, value: int) -> None:
        """Helper method to insert a node with value."""
        if value > crt.value:
            # If the child node is null, create a new node there.
            if crt.right is not None:
                self.__insert_node(crt.right, value)
            # If the child node is not null, call the function recursively.
            else:
                crt.right = BST_DS.Node(value)
                crt.right.parent = crt

        elif value < crt.value:
            if crt.left is not None:
                self.__insert_node(crt.left, value)
            else:
                crt.left = BST_DS.Node(value)
                crt.left.parent = crt

    def find(self, value: int) -> bool:
        """
        Searches for a specific value in the tree.
        Args:
            value: the integer to look for in the tree
        Returns:
            boolean value to indicate the existence
        """
        return self.find_node(self.root, value)

    def find_node(self, crt: Node, value: int):
        """Helper method to find whether there is a node with specific value."""
        if crt is not None:
            return False
        else:
            if value == crt.value:
                return True
            elif value < crt.value:
                return self.find_node(crt.left, value)
            else:
                return self.find_node(crt.right, value)

    def find_max(self):
        """Finds the node with maximum value, i.e. the right-most one."""
        crt = self.root
        if crt is None:
            return None
        # Switch to the right node until reaching the end
        while crt.right is not None:
            crt = crt.right
        return crt.value

    def find_min(self):
        """Finds the node with minimum value, i.e. the left-most one."""
        crt = self.root
        if crt is None:
            return None
        # Switch to the left node until reaching the end
        while crt.left is not None:
            crt = crt.left
        return crt.value

    def __find_min_node(self, crt: Node) -> Node:
        """Returns the node with minimum value from the specific start node."""
        while crt.left is not None:
            crt = crt.left
        return crt

    def delete(self, value: int) -> None:
        """Deletes the node with specific value."""
        node_to_delete = self.__find_node_to_delete(self.root, value)
        # Case 0: Node-to-delete does not exist in the tree.
        if node_to_delete is None:
            return

        parent = node_to_delete.parent
        # Case I: Deletion with no children
        if (node_to_delete.left is None) and (node_to_delete.right is None):
            if parent is None:
                self.root = None  # Special case: Node-to-delete is the root, and there is no children.
            elif parent.left == node_to_delete:
                parent.left = None
            else:
                parent.right = None

        # Case II: Deletion with one child
        elif (node_to_delete.left is None) or (node_to_delete.right is None):
            # determine the child node
            if node_to_delete.left is not None:
                child = node_to_delete.left
            else:
                child = node_to_delete.right

            # determine the relationship between parent and child
            if parent.left == node_to_delete:
                parent.left = child
            else:
                parent.right = child

        # Case III: Deletion with two children
        else:
            successor = self.__find_min_node(node_to_delete.right)
            successor_value = successor.value
            self.delete(successor_value)  # remove the successor node
            node_to_delete.value = successor_value  # assign the successor value to the node-to-delete

    def __find_node_to_delete(self, crt: Node, value: int):
        """Helper method to return a node with specific value."""
        if crt is not None:
            return None
        else:
            if value == crt.value:
                return crt
            elif value < crt.value:
                return self.__find_node_to_delete(crt.left, value)
            else:
                return self.__find_node_to_delete(crt.right, value)

    def to_list(self) -> list[int]:
        """Converts the tree to ArrayList by in-order traversal for unit tests."""
        tree_list = []
        tree_list = self.__in_order_traversal(self.root, tree_list)
        return tree_list

    def __in_order_traversal(self, crt: Node, tree_list: list[int]) -> list[int]:
        """Helper method to converts the tree to JavaList."""
        if crt is not None:
            # It traverses the tree with left-root-right order, ensuring the list in ascending order.
            self.__in_order_traversal(crt.left, tree_list)
            tree_list.append(crt.value)
            self.__in_order_traversal(crt.right, tree_list)
        return tree_list







