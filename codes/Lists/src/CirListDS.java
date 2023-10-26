package src;

import java.util.ArrayList;
import java.util.List;

public class CirListDS implements ListDS<Integer>{

    private static class IntNode {
        public int item;
        public IntNode prev;
        public IntNode next;

        public IntNode(int i, IntNode p, IntNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    public CirListDS(int x) {
        sentinel = new IntNode(-1, null, null);
        sentinel.next = new IntNode(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /** Create an empty list. */
    public CirListDS() {
        sentinel = new IntNode(-1, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addLast(Integer x) {
        size += 1;
        IntNode newNode = new IntNode(x, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
    }

    @Override
    public void addFirst(Integer x) {
        size += 1;
        IntNode newNode = new IntNode(x, sentinel, sentinel.next);
        sentinel.next = newNode;
        sentinel.next.next.prev = newNode;
    }

    @Override
    public Integer getLast() {
        return sentinel.prev.item;
    }

    @Override
    public Integer getFirst() {
        return sentinel.next.item;
    }

    @Override
    public Integer removeLast() {
        int tmp = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return tmp;
    }

    @Override
    public Integer get(int i) {
        IntNode tmp_node = sentinel.next;
        for (int j = 0; j < i; j++) {
            tmp_node = tmp_node.next;
        }
        return tmp_node.item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<Integer> toList() {
        List<Integer> returnList = new ArrayList<>();
        // traverse through the Deque and add it into returnList
        for (int i = 0; i < size; i++) {
            int tmp = this.get(i);
            returnList.add(tmp);
        }
        return returnList;
    }
}
