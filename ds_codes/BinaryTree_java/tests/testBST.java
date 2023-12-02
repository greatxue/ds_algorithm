package tests;

import org.junit.jupiter.api.Test;
import src.BST_DS;

import static com.google.common.truth.Truth.assertThat;

public class testBST {
    @Test
    public void testInsert(){
        BST_DS BST = new BST_DS();
        BST.insert(3);
        assertThat(BST.toList()).containsExactly(3).inOrder();
        // Test the case with repetitive value and whether is in ascending order.
        BST.insert(5);
        BST.insert(5);
        BST.insert(6);
        BST.insert(8);
        BST.insert(-1);
        assertThat(BST.toList()).containsExactly(-1, 3, 5, 6, 8).inOrder();
    }

    @Test
    public void testFind() {
        BST_DS BST_2 = new BST_DS();
        BST_2.insert(5);
        BST_2.insert(5);
        BST_2.insert(6);
        BST_2.insert(8);
        assertThat(BST_2.find(8)).isEqualTo(true);
        assertThat(BST_2.find(5)).isEqualTo(true);
        assertThat(BST_2.find(10)).isEqualTo(false);

        BST_DS BST_3 = new BST_DS();
        assertThat(BST_3.find(10)).isEqualTo(false);
    }

    @Test
    public void testFindMaxAndMin() {
        BST_DS BST_4 = new BST_DS();
        BST_4.insert(5);
        BST_4.insert(5);
        BST_4.insert(8);
        BST_4.insert(10);
        BST_4.insert(-1);
        assertThat(BST_4.findMax()).isEqualTo(10);
        assertThat(BST_4.findMin()).isEqualTo(-1);

        BST_DS BST_5 = new BST_DS();
        assertThat(BST_5.findMax()).isEqualTo(null);
        BST_5.insert(8);
        assertThat(BST_5.findMax()).isEqualTo(8);
        assertThat(BST_5.findMin()).isEqualTo(8);
    }

    @Test
    public void testDelete() {
        BST_DS BST_6 = new BST_DS();
        BST_6.insert(5);
        BST_6.insert(5);
        BST_6.insert(8);
        BST_6.insert(10);
        BST_6.insert(-1);
        BST_6.insert(6);
        BST_6.insert(7);
        BST_6.delete(14);
        // Initial check and deletion with node of non-existence-value
        assertThat(BST_6.toList()).containsExactly(-1, 5, 6, 7, 8, 10).inOrder();
        BST_6.delete(-1);
        // Deletion with no child
        assertThat(BST_6.toList()).containsExactly(5, 6, 7, 8, 10).inOrder();
        BST_6.insert(-1);
        BST_6.delete(6);
        // Deletion with one child
        assertThat(BST_6.toList()).containsExactly(-1, 5, 7, 8, 10).inOrder();
        BST_6.insert(6);
        BST_6.delete(8);
        // Deletion with two children
        assertThat(BST_6.toList()).containsExactly(-1, 5, 6, 7, 10).inOrder();
        // Deletion of root node
        BST_DS BST_7 = new BST_DS();
        BST_7.insert(5);
        BST_7.delete(5);
        assertThat(BST_7.toList()).containsExactly().inOrder();
        // Deletion of empty tree with node of non-existence-value
        BST_DS BST_8 = new BST_DS();
        BST_8.delete(5);
        assertThat(BST_8.toList()).containsExactly().inOrder();
    }

}
