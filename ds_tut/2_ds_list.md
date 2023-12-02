# 2. Lists

## 2.1 Introduction

### 2.1.1 Mystery of the Walrus  

Case 1:
```java
Walrus a = new Walrus(1000, 8.3);
Walrus b;
b = a;
b.weight = 5;
System.out.println(a);      // 5, 8.30
System.out.println(b);      // 5, 8.30
```
Case 2:
```java
int x = 5;
int y;
y = x;
x = 2;
System.out.println("x is: " + x);   // 2
System.out.println("y is: " + y);   // 5
```

**Bits**  
* All information in the computer is stored in **memory** as a sequence of binary digits.
* When declaring a variable of a certain type, it sets aside enough bits for the variable, and creates an internal table mapping each variable name to a location.
  

**The Golden Rule of Equals (GRoE)**
* When writing `y = x`, Java interpreter is told to copy the bits from x into y. 
* This simple idea of copying the bits is true for ANY assignment using `=` in Java.   

### 2.1.2 Reference Types  

In Java, there are 8 `primitive type`s: byte, short, int, long, float, double, boolean, char. Others are thought of as a `reference type`.
* When declaring a variable of any reference type, Java allocates a box of 64 bits **refering to the address**, no matter what type of object.
* Reference Types obey GRoE as well.

### 2.1.3 Parameter Passing
* Passing Parameters obeys the rule of **copying the bits**, also called "pass by value".
* In Java, one **always** passes by value.  

For example, the call to `doStuff` would not change `x` but `walrus` here.  
```java
public class PassByValueFigure {
    public static void main(String[] args) {
           Walrus walrus = new Walrus(3500, 10.5);
           int x = 9;

           doStuff(walrus, x);
           System.out.println(walrus);
           System.out.println(x);
    }

    public static void doStuff(Walrus W, int x) {
           W.weight = W.weight - 100;
           x = x - 5;
    }
}
```

### 2.1.4 Instantiation of Arrays  
Instantiating an array is very similar to instantiating an object.   
For example, 
```java
int[] x;        // x can only hold the address of an int array. 
x = new int[]{0, 1, 2 ,95, 4}
                /* The new keyword creates 5 boxes of 32 bits each and   
                   returns the address of the overall object for assignment to x. */
```

###  2.1.5 IntList   
Here is the implementation of the "Linked List":  
```java
public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /** Return the size of the list using recursion. */
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }

    /** Return the size using no recursion. */
    public int iterativeSize() {
        IntList p = this;
        int totalSize = 0;
        while (p != null) {
            totalSize += 1;
            p = p.rest;
        }
        return totalSize;
    }

    /** Return the ith item of the IntList. */
    public int get(int i) {
        if (i == 0) {
            return first;
        }
        return rest.get(i - 1);
    }

    public static void main(String[] args) {
        IntList L = new IntList(15, null);
        L = new IntList(10, L);
        L = new IntList(5, L);

        System.out.println(L.size());
        System.out.println(L.iterativeSize());
    }
}

```

Remark:  
* Syntax like `if (this == null){return 0};` won't always work, as there would be a `NullPointer` error if object L is null.   
* It is adviced to use `p` as a reminder of the variable holding a pointer. `this` cannot be reassigned in Java.     
* The method above takes linear time with respect to list size.  


## 2.2 SLList 
Here is the implementation:   
```java
public class SLList {
    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    /** Create an empty list */
    public SLList() {
        sentinel = new IntNode(66, null);
        size = 0;
    }

    public SLList(int x) {
        sentinel = new IntNode(66, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    /** Add x to the front of the list. */
    public void addFirst(int x) {
        size += 1;
        sentinel.next = new IntNode(x, sentinel.next);
    }

    /** Return the first item of the list. */
    public int getFirst() {
        return sentinel.next.item;
    }

    /** Add an item to the end of the list. */
    public void addLast(int x) {
        size += 1;

        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    /** Return the size of the list. */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        /* Create a list of one integer 5 */
        SLList L = new SLList();
        L.addFirst(10);
        L.addLast(5);
        System.out.println(L.size());
    }
}
```

Remark:  

**Rebranding:**
* The `SLList` hides the detail of the `null` link from the user, compares to ` IntList L1 = new IntList(5, null)`.
* While the **Naked recursion** allows the `IntList` user to potentionally have variables pointing to the middle of the structure,
`SLList` class acts as a middle man hiding user from the raw data structure.

**Private and Public:**
* `private` keyword is applied to prevent codes from other classes from using members or constructor of a class.  
* **Restricting access** is to hide implementation details from users of the class, both easier to understand and safer for later change.  
* Nested classes are useful when one class is subordinate to another class.  

**Nested Class:**
* `IntNode` class never uses any instance members of the outer class, hence keyword `static` could be applied.  

**Methods:**  
* Two methods with the same name but different signatures are overloaded and allowed in Java.         

**Caching:**
* The practice of saving important data to speed up retrieval is known as **caching**.         

**Sentinel Nodes:**
* Based on the structure containing `first`, one solution to fix `addLast` bug for the empty list is like:  
  ```java
  if (first == null){
    first = new IntNode(x, null);
    return ;
  }
  ```
* However, to keep complexity under control wherever possible, a `sentinel node` could be created to unify all cases. That's why the data structure is improved.  

**Invariants:**  
* An invariant is a fact about a data structure that is guaranteed to be true.  

## 2.3 DLList 

### 2.3.1 Implementation of DLList

Here is the implementation:   
```java
public class DLList {
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

    public DLList(int x) {
        sentinel = new IntNode(66, null, null);
        rearguard = new IntNode(33, null, null);
        sentinel.next = new IntNode(x, sentinel, rearguard);
        rearguard.prev = sentinel.next;
        size = 1;
    }

    /** Create an empty list. */
    public DLList() {
        sentinel = new IntNode(66, null, null);
        rearguard = new IntNode(33, null, null);
        sentinel.next = rearguard;
        rearguard.prev = sentinel;
        size = 0;
    }

    /** Add x to the front of the list. */
    public void addFirst(int x) {
        size += 1;
        IntNode newNode = new IntNode(x, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
    }

    /** Return the 1st item of the list. */
    public int getFirst() {
        return sentinel.next.item;
    }

    /** Add an item to the end of the list. */
    public void addLast(int x) {
        size += 1;
        IntNode newNode = new IntNode(x, rearguard.prev, rearguard);
        rearguard.prev.next = newNode;
        rearguard.prev = newNode;
    }

    /** Return the size of the list. */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        /* Create a list of one integer 5 */
        DLList L = new DLList(10);
        L.addFirst(10);
        L.addLast(5);
        System.out.println(L.size());
    }
}
```

Another implementation with circulated version: 
```java
public class CirList {
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
    
    public CirList(int x) {
        sentinel = new IntNode(66, null, null);
        sentinel.next = new IntNode(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /** Create an empty list. */
    public CirList() {
        sentinel = new IntNode(66, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Add x to the front of the list. */
    public void addFirst(int x) {
        size += 1;
        IntNode newNode = new IntNode(x, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
    }

    /** Return the 1st item of the list. */
    public int getFirst() {
        return sentinel.next.item;
    }

    /** Add an item to the end of the list. */
    public void addLast(int x) {
        size += 1;
        IntNode newNode = new IntNode(x, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
    }
    
    /** Return the size of the list. */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        /* Create a list of one integer 5 */
        CirList L = new CirList(10);
        L.addFirst(10);
        L.addLast(5);
        System.out.println(L.size());
    }
}
```

Remark:

**addLast**
* `addLast` and `getLast` could be fast, but `removeLast` could not, as there is no easy way to get the **second-to-last node**.  
* For example, `addLast` could be advanced like:
  ```java
  public void addLast(int x) {
        last.next = new IntNode(x, null);
        last = last.next;
        size += 1;
    }
  ```
  **Back Pointers**
  Back pointers allow a list to support adding, getting, and removing the front and back of a list in constant time, with `prev` and `next`.

**Sentinel Upgrade**
There is a subtle issue  where the last pointer sometimes points at the sentinel node, and sometimes at a real node:
* One fix is to add a second sentinel node to the **back** of the list. 
* An alternate approach is to implement the list so that it is **circular**, with the front and back pointers sharing the same sentinel node.

### 2.3.2 Generic DLLists

To implement a **generic list**, codes could be replaced like:  
```java
public class GenDLList<LochNess> {
    private class PubNode {
        public LochNess item;
        public PubNode prev;
        public PubNode next;
        
        public PubNode(LochNess i, PubNode p, PubNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }
    
    private PubNode sentinel;
    private int size;
    
    public GenDLList(LochNess x) {
        sentinel = new PubNode(null, null, null);
        sentinel.next = new PubNode(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }
}
```

A special syntax is also needed to initiate the class. Remember to use **reference types** instead of **peimitives**, that is, `Integer` instead of `int`.
```java
DLList<Integer> d1 = new DLList<>(5);
d1.insertFront(10);
```

Some rules working with generic lists:
* Always try to use `Integer`, `Double`, `Character`, `Boolean`, `Long`, `Short`, `Byte`, or `Float` instead of primitive equivalents.
* In the `.java` file implementing a data structure, specify your generic type name only once at the very top of the file after the class name.
* In other `.java` files using data structure, specify the specific desired type during declaration, and use the empty diamond operator during instantiation.
* Though redundant, the one below is also perfectly valid:  
  `DLList<Integer> d1 = new DLList<Integer>(5);`

## 2.4 Arrays

### 2.4.1 Array Knowledge

**Array Basics**
Arrays are a special type of object that consists of a numbered sequence of memory boxes.   

Arrays consist of  
* A fixed integer length, N  
* A sequence of N memory boxes of **the same type**, and are numbered **0 through (N - 1)**.  

Unlike classes, arrays do **NOT** have methods.

**Array Creation**
There valid notations:  
* Create array containing 3 int boxes with default value:   
  `x = new int[3];` 
* `y = new int[]{1, 2, 3, 4, 5};`
* `new` could be omited  foe declaring a vlue:    
  `int[] z = {9, 10, 11, 12, 13};`


**Array Access and Modification**
Understand following codes well:  
```java
int[] z = null;
int[] x, y;

x = new int[]{1, 2, 3, 4, 5};
y = x;
x = new int[]{-1, 2, 5, 4, 99};
y = new int[3];
z = new int[0];
int xL = x.length;

String[] s = new String[6];
s[4] = "ketchup";
s[x[3] - x[1]] = "muffins";

int[] b = {9, 10, 11};
System.arraycopy(b, 0, x, 3, 2);
```

For `Arraycopy`, it is likely to be faster compared to "item by item with a loop":  
```java
System.arraycopy(b, 0,x, 3, 2)  // Java
```
which is the equivalent of  
```python
x[3:5] = b[0:2]  # Python
```

**2D Arrays**

Understand following codes well:  
```java
int[][] pascalsTriangle;
pascalsTriangle = new int[4][];
int[] rowZero = pascalsTriangle[0];

pascalsTriangle[0] = new int[]{1};
pascalsTriangle[1] = new int[]{1, 1};
pascalsTriangle[2] = new int[]{1, 2, 1};
pascalsTriangle[3] = new int[]{1, 3, 3, 1};
int[] rowTwo = pascalsTriangle[2];
rowTwo[1] = -5;

int[][] matrix;
matrix = new int[4][];
matrix = new int[4][4];

int[][] pascalAgain = new int[][]{{1}, {1, 1},
                        {1, 2, 1}, {1, 3, 3, 1}};
            
```

**Arrays vs Classes**
The key differences between memory boxes in arrays and classes:
* Array boxes are numbered and accessed using `[]` notation, and class boxes are named and accessed using dot notation.  
* Array boxes must all be the same type. Class boxes can be different types. 

One particularly impact is that `[]` notation allows specifying index at runtime.   
For example, consider the code below:
```java
int indexOfInterest = askUserForInteger();
int[] x = {100, 101, 102, 103};
int k = x[indexOfInterest];
System.out.println(k);
```
It is forbidden as follows:  
```java
String fieldOfInterest = "mass";
Planet p = new Planet(6e24, "earth");
double mass = p[fieldOfInterest];
```
or `double mass = p.fieldOfInterest;`

There is a way to specify desired fields at runtime called **reflection**, but it is considered very bad coding style for typical programs.  

**Arrays in Java**
Compared to arrays in other languages, Java arrays:
* Have no special syntax for "slicing" (such as in `Python`).
* Cannot be shrunk or expanded (such as in `Ruby`).
* Do not have member methods (such as in `Javascript`).
* Must contain values only of the same type (unlike `Python`).


### 2.4.2 AList
Here is the implementation:
```java
public class AList<Item> {
    private Item[] items;
    private int size;

    /** Create an empty list. */
    public AList() {
        items = (Item[]) new Object[100];
        size = 0;
    }


    /** Insert an item to the end of the list. */
    public void addLast(Item x) {
        items[size] = x;
        size += 1;
    }

    /** Adjust the size of the list. */
    private void resize(int capacity) {
        int[] a = new int[capacity];
        System.arraycopy(items, 0, a, 0, size);

    }

    /** Insert an item at the end of thelist, even if out-of-range. */
    public void insertBack(Item x) {
        int RFACTOR = 2;
        if (size == items.length) {
               resize(size * RFACTOR);
        }
        items[size] = x;
        size += 1;
    }

    /** Return the last item of the list. */
    public Item getLast() {
        return items[size - 1];
    }

    /** Get the (i+1)th item of the list. */
    public Item get(int i) {
        return items[i];
    }

    /** Return the size of the list. */
    public int size() {
        return size;
    }
    
    /** Delete item from back of the list and return deleted item. */
    public Item deleteBack() {
        Item x = getLast();
        items[size - 1] = null;
        size -= 1;
        return x;
    }
}
```

**Linked List Performance**
Since there are only references to the `first` and `last` items of the list, the worst case execution time for `get` or `getBack` is linear in the size of the entire list.

**Naive Array Based List**
ALists have constant time access, with invariants as follows:
* The position of the next item to-be-inserted is always `size`.
* The number of items in the AList is always `size`.
* The position of the last item in the list is always `size - 1`.

**removeLast**
To remove the last item, decide which of `size`, `items`, and `items[i]` needs to change so that our invariants are preserved after the operation.  
As `size` has been decreased by 1, the last `int` item has logically been deleted. `items[size - 1] = null;` will not do any harm, but not necessary.  
However, for `objects` it is not the case. Recall that Java only destroys objects when the last reference has been lost. That is why reference should be nulled out to avoid "lotering".

**Resizing the array**
The naive array list shows a parabola, for following implements:
```java
public void insertBack(int x) {
    if (size == items.length) {
        resize(size + 1);
    }
    items[size] = x;
    size += 1;
}
```

The performance problems could be fixed by growing the size of our array by a **multiplicative** amount, rather than an **additive** amount, which is how `Python` works:   
```java
public void insertBack(int x) {
    if (size == items.length) {
           resize(size * RFACTOR);
    }
    items[size] = x;
    size += 1;
}
```

Also, the size of the array should be halved when ratio R falls to less than 0.25.
R = (size of the list) / (length of the items array)

**Generic ALists**
Java does not allow us to create an array of generic objects due to an obscure issue with the way generics are implemented. So we need to use technic `cast`:
```java
Item[] items = (Item []) new Object[8];
```

The other change is that any "delete" items should be nulled out.

