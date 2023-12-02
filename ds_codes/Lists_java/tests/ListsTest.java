package tests;

import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

import src.*;

public class ListsTest {
    @Test
    /** This test performs calls from AListDS. */
    public void AListTest() {
        AListDS<String> lds = new AListDS<>();
        lds.addLast("front"); // ["front"]
        lds.addLast("middle"); // ["front", "middle"]
        lds.addLast("back"); // ["front", "middle", "back"]
        assertThat(lds.toList()).containsExactly("front", "middle", "back").inOrder();
        assertThat(lds.removeLast()).isEqualTo("back");

        AListDS<Integer> lds2 = new AListDS<>();
        lds2.addFirst(6);
        for (int i = 0; i < 6; i++) {
            lds2.insertBack(3);
        }
        assertThat(lds2.toList()).containsExactly(6,3,3,3,3,3,3).inOrder();
        assertThat(lds2.getLast()).isEqualTo(3);
        assertThat(lds2.getFirst()).isEqualTo(6);
        assertThat(lds2.size()).isEqualTo(7);
        assertThat(lds2.get(2)).isEqualTo(3);
    }

    @Test
    /** This test performs calls from DLListDS. */
    public void DLListTest() {
        DLListDS lds3 = new DLListDS();
        lds3.addFirst(6);
        for (int i = 0; i < 3; i++) {
            lds3.addLast(3);
            lds3.addFirst(5);
        }
        assertThat(lds3.toList()).containsExactly(5,5,5,6,3,3,3).inOrder();
        assertThat(lds3.getLast()).isEqualTo(3);
        assertThat(lds3.getFirst()).isEqualTo(5);
        assertThat(lds3.size()).isEqualTo(7);
        assertThat(lds3.get(2)).isEqualTo(5);
    }

    @Test
    /** This test performs calls from CirListDS. */
    public void CirListTest() {
        CirListDS lds4 = new CirListDS();
        lds4.addFirst(6);
        for (int i = 0; i < 3; i++) {
            lds4.addLast(3);
            lds4.addFirst(5);
        }
        assertThat(lds4.toList()).containsExactly(5,5,5,6,3,3,3).inOrder();
        assertThat(lds4.getLast()).isEqualTo(3);
        assertThat(lds4.getFirst()).isEqualTo(5);
        assertThat(lds4.size()).isEqualTo(7);
        assertThat(lds4.get(2)).isEqualTo(5);
    }







}
