package src;

import java.util.ArrayList;
import java.util.List;

public class DLListDS implements ListDS<Integer> {
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
    private IntNode rearguard;
    private int size;


    /** Initialize a list with a specific item within. */
    public DLListDS(int x) {
        sentinel = new IntNode(-1, null, null);
        rearguard = new IntNode(-1, null, null);
        sentinel.next = new IntNode(x, sentinel, rearguard);
        rearguard.prev = sentinel.next;
        size = 1;
    }

    /** Create an empty list. */
    public DLListDS() {
        sentinel = new IntNode(-1, null, null);
        rearguard = new IntNode(-1, null, null);
        sentinel.next = rearguard;
        rearguard.prev = sentinel;
        size = 0;
    }

    @Override
    public void addLast(Integer x) {
        size += 1;
        IntNode newNode = new IntNode(x, rearguard.prev, rearguard);
        rearguard.prev.next = newNode;
        rearguard.prev = newNode;
    }

    @Override
    public void addFirst(Integer x) {
        size += 1;
        IntNode newNode = new IntNode(x, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
    }

    @Override
    public Integer getLast() {
        return rearguard.prev.item;
    }

    @Override
    public Integer getFirst() {
        return sentinel.next.item;
    }

    @Override
    public Integer removeLast() {
        int tmp = rearguard.prev.item;
        rearguard.prev.prev.next = rearguard;
        rearguard.prev.prev = rearguard.prev;
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



