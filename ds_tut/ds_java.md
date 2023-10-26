# Data Structure with Java
- - -
author: Josh Hug
editor: Kevin Shuey    
title: Data Structure with Java   
date: 2023-05-05   
ref: ucb_CS61B  

- - -

**Coverage:**  

[1. Intruduction to Java](#1)  
[2. Lists](#2)     
[3. Testing](#3)    
[4. Java Characteristics](#4)  
[5. Asymptotics Analysis](#5)   
[6. Disjoints Sets](#6)  
[7. ADTs and Trees](#7)    
[8. ](#8)



## 1. <span id='1'>Intruduction to Java</span>

### 1.1 Essentials  

#### 1.1.1 Hello World

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
```

Here are some key syntactic features:
* It always consists of a class declaration, using the key word `public class`.
* The code to run is inside a method called `main`.
* Curly braces `{` and `}` are used to conclude the codes.
* Statements must end with semi-colons `;`.

#### 1.1.2 Running a Java Program
The most common way is to run it through a sequence:  
`Hello.java` -> Compiler `javac` -> `Hello.class` -> Interpreter `java` -> (executing)  

Example:
```shell
$ javac HelloWorld.java
$ java HelloWorld
```


#### 1.1.3 Variables and Loops
```java
public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        while (x < 10) {
            System.out.print(x + " ");
            x = x + 1;
        }
    }
}
```

Here are some features of the program:
* The variable `x` must be declared and given a type before being used.
* The loop definition is contained inside of **curly braces**, and the boolean expression is inside **parentheses**.
* Our print statement is just `System.out.print` instead of `System.out.println`, which means **no newline**.
* Our print statement adds a number to a **space**, which makes sure the numbers don't run into each other. 
* The prompt ends up on the same line as the numbers, **without a newline**.

#### 1.1.4 Static Typing
Every variable, parameter, and functions in Java has a so-called `static type`.   
This is in contrast to dynamically typed languages like **Python**, where users can run into **type error**.  
To summarize, static typing has the following advantages:
* easier for the programmer to debug their code
* never run into type errors
* easier to understand and reason  

```java
String h = 5 + "horse";
int h = 5 + "horse";   //compiler error
System.out.println(5 + "10");   //510
System.out.println(5 + 10);   //15
```

#### 1.1.5 Functions
Here is a comparison:  
* Python function:

  ```python
  def larger(x,y):
    if x > y: 
        return x
    return y
    print (larger(8,10))
  ```

* Java function:

  ```java
  public class LargerDemo {
    public static int larger(int x, int y) {
        if (x > y) {
            return x;
        }
        return y;
    }
  
    public static void main(String[] args) {
        System.out.println(larger(8, 10));
    }
  }
  ```

#### 1.1.6 Comments
Comments are always needed to describe your codes.  
* Line comments: `//` 
* Block comments: `/*` and `*/`.
* Javadoc comments: `/**` and `*/`.  
    One special note here is that almost all methods and classes should be described by **Javadoc**. 

### 1.2 Objects (Defining and Using Classes)

#### 1.2.1 Static and Non-Static Methods

**Static Methods**  

All code in Java must be part of a class. Here's an example:  
```java
public class Dog {
    public static void makeNoise() {
        System.out.println("Bark!");
    }
}
```

To run the class, either will do:  
* to add a `main` method inside `Dog` class
* to create a "**client**" class `DogLauncher` that runs methods from the `Dog` class.
  ```java
   public class DogLauncher {
    public static void main(String[] args) {
        Dog.makeNoise();
    }
  }
  ```

**Instance Variables and Object Instantiation**

Here is an example of instantiation:
```java
public class Dog {
    public int weightInPounds;

    public void makeNoise(){
        if (weightInPounds < 10) {
            System.out.println("yipyipyip!");
        } else if(weightInPounds < 30) {
            System.out.println("bark, bark");
        } else{
            System.out.println("woof!");
        }
    }
}
public class DogLauncher {
    public static void main(String[] args) {
        Dog d;
        d = new Dog();
        d.weightInPounds = 20;
        d.makeNoise()    
    }
}
```
Some key observations and terminology:
* An `object` in Java is an instance of any class.
* Variables and methods of a class are **members** of a class.
* The `Dog` class has its own variables, known as **instance variables**, or **non-static variables**.
* The nethod inside `Dog` class has no `static` keyword, which is called **instance methods**.
* To call the `makeNoise` method, we have to first **instantaite** a `Dog` using keyword `new`, and then call `d.makeNoise` instead of `Dog.makeNoise`.  

**Constructors in Java**  

For example:  
```java
public class Dog {
    public int weightInPounds;    // Instance variable (as many as you want)

    public Dog(int w) {           // Constructor (how to instantiate the class)
        weightInPounds = w;         
    }

    public void makeNoise() {     // Non-static method (invoked by an instance of the class)
        if (weightInPounds < 10) {
            System.out.println("yipyipyip!");
        } else if (weightInPounds < 30) {
            System.out.println("bark. bark.");
        } else {
            System.out.println("woof!");
        }    
    }
}
```
A constructor could be used to construct objects in Java.  
* The constructor will be invoked anytime that we try to create a `Dog` using the new keyword and a single integer parameter. 
* The constructor is very similar to the `__init__` method in Python.  

For example:
```java
public class DogLauncher {
    public static void main(String[] args) {
        Dog d = new Dog(20);        //Declaration, instatiation and assignment
        d.makeNoise();
    }
}
```

**Array Instantiation, Arrays of Objects**  
* Create an array that can hold two `Dog` objects.
* Create each actual `Dog` for arrays of instantiated objects in Java

For example:  
```java
public class DogArrayDemo {
    public static void main(String[] args) {
        /* Create an array of two dogs. */
        Dog[] dogs = new Dog[2];
        dogs[0] = new Dog(8);
        dogs[1] = new Dog(20);

        dogs[0].makeNoise();
    }
}
```

#### 1.2.2 Class Methods and Instance Methods
Java allows two types of methods:
* **Class methods**, aka static methods: invoked using the class name  
  ```java
  public static Dog maxDog(Dog d1, Dog d2) {
      if (d1.weightInPounds > d2.weightInPounds) {
          return d1;
      }
      return d2;
   }
    
    Dog d = new Dog(15);
    Dog d2 = new Dog(100);
    Dog.maxDog(d, d2);
  ```
* **Instance methods**, aka non-static methods: invoked using a specific instance variable  
  ```java
  public Dog maxDog(Dog d2) {
      if (this.weightInPounds > d2.weightInPounds) {
          return this;
      }
      return d2;
   }
  
  Dog d = new Dog(15);
  Dog d2 = new Dog(100);
  d.maxDog(d2);
  ```

**Static Variables**  
It is occasionally useful for classes to have static variables.   
* Static variables should be accessed using the name of the class, e.g. `Dog.binomen`.
* While Java technically allows you to access a static variable using an instance name, it is bad style.  

For example: 
```java
public class Dog {
    public int weightInPounds;
    public static String binomen = "Canis familiaris";
    ...
}
```

#### 1.2.3 public static void main (String[] args)  
Demystification of the declaration used for the main method:  
* `public`: Most of our methods start with this keyword.
* `static`: It is a static method, not associated with any particular instance.
* `void`: It has no return type.
* `main`: Specific name of the method.
* `String[] args`: A particular parameter that is passed to the main method.

**Command Line Arguments**  
Since `main` is called by the Java interpreter itself rather than another Java class, it is the interpreter's job to supply these arguments. They refer usually to the command line arguments. 

For example:
```java
public class ArgsDemo {
    public static void main(String[] args) {
        System.out.println(args[0]);
    }
}
```
This program prints out the 0th command line argument:  
```shell
$ java ArgsDemo these are command line arguments
>>these
```

#### 1.2.4 Using Libraries  
Knowing how to find and use existing libraries is often possible to save yourself tons of work and debugging by turning to the web for help.  



## 2. <span id='2'>Lists</span>

### 2.1 Introduction

#### 2.1.1 Mystery of the Walrus  

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

#### 2.1.2 Reference Types  

In Java, there are 8 `primitive type`s: byte, short, int, long, float, double, boolean, char. Others are thought of as a `reference type`.
* When declaring a variable of any reference type, Java allocates a box of 64 bits **refering to the address**, no matter what type of object.
* Reference Types obey GRoE as well.

#### 2.1.3 Parameter Passing
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

#### 2.1.4 Instantiation of Arrays  
Instantiating an array is very similar to instantiating an object.   
For example, 
```java
int[] x;        // x can only hold the address of an int array. 
x = new int[]{0, 1, 2 ,95, 4}
                /* The new keyword creates 5 boxes of 32 bits each and   
                   returns the address of the overall object for assignment to x. */
```

####  2.1.5 IntList   
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


### 2.2 SLList 
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

### 2.3 DLList 

#### 2.3.1 Implementation of DLList

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

#### 2.3.2 Generic DLLists

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

### 2.4 Arrays

#### 2.4.1 Array Knowledge

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


#### 2.4.2 AList
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


## 3. <span id='3'>Utilities</span>

### 3.1 Testing
Programmers knows theire codes work based on tests they write.
* Unit Test: A softwre method by which individual units of source code are tested to determine whether they are fit for use.  
  For example, `JUnit`, `AssertJ`, and `Truth`.

Here is an implementation of **QuickSort**:  

```java
public class Sort {
    public static void sort(String[] x) {
        sort(x, 0, x.length - 1);
    }

    private static void sort(String[] x, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(x, low, high);
            sort(x, low, partitionIndex - 1);
            sort(x, partitionIndex + 1, high);
        }
    }

    private static int partition(String[] x, int low, int high) {
        String pivot = x[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (x[j].compareTo(pivot) <= 0) {
                i++;
                swap(x, i, j);
            }
        }

        swap(x, i + 1, high);
        return i + 1;
    }

    static void swap(String[] x, int a, int b) {
        String temp = x[a];
        x[a] = x[b];
        x[b] = temp;
    }
}
```

Here is the corresponding Testing Demo with `Truth`:

```java
package Sort;

import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class TestSort {
    /** Test the sort method of the sort class. */
    @Test
    public void testSort() {
        String[] input = {"rawr", "a", "zaza", "newway"};
        String[] expected = {"a","newway", "rawr", "zaza"};
        Sort.sort(input);
        
        assertThat(input).isEqualTo(expected);
    }
}
```

### 3.2 Coding Philosophy

The philosophy of **unit test-driven** programming emphasizes writing tests before writing functional code. By defining expected outputs or behaviors, developers can continually verify if the code is working as expected and provide safeguards for code maintenance and iterations. This approach helps to improve code quality and changes our perception of software development.

### 3.3 Debugging

There are multiple ways for debugging:

* **Using an IDE's Debugger:** Most modern IDEs come equipped with robust debugging tools. These debugging tools allow you to set **breakpoints**, step through the code, inspect variable values, and evaluate expressions, among others.
* **Using Visualized Tools:** These tools offer a reliable method to take a look at the Java implementation condition, such as **Java visualizer**.
* **Partner Collaboration:** When in trouble, **referring to others** is also of great importance.

## 4. <span id='4'>Java Characteristics</span>

### 4.1 Inheritance, Implements

Consider a method:
```java
public static String longest(SLList<String> list) {
    int maxDex = 0;
    for (int i = 0; i < list.size(); i += 1) {
        String longestString = list.get(maxDex);
        String thisString = list.get(i);
        if (thisString.length() > longestString.length()) {
            maxDex = i;
        }
    }
    return list.get(maxDex);
}
```

Actually, implemented by `ALList` is also OK, i.e. changing the parametre to ` AList<String> list`.  
If there are these two methods in the same class, it is actually allowed, which is called *overloading* in Java.  
However, overloading has several downsides:  
* Super repetitive and ugly.  
* More codes to maintain, especially for possible partial correction when debugging.  
* Inconvenient for more list types.  

**Hypernyms, Hyponyms, and Interface Inheritance**

Similar to what it is like in language, formalize this relationship in Java:     
If a SLList is a hyponym of List61B, then the SLList class is a **subclass** of the List61B class and the List61B class is a **superclass** of the SLList class.   

Here is the List61B interface and establish the relationship hierarchy.

**Step 1: Create a hypernym**
```java
public interface List61B<Item> {
    public void addFirst(Item x);
    public void add Last(Item y);
    public Item getFirst();
    public Item getLast();
    public Item removeLast();
    public Item get(int i);
    public void insert(Item x, int position);
    public int size();
}
```

**Step 2: specify hyponyms of the class**

Add to `public class AList<Item> {}` as
`public class AList<Item> implements List61B<Item>{}`.


**Overriding**
If a subclass has a  method with the same signature as in the superclass, then the subclass **overrides** the method.
* `Animal`'s subclass `Pig` overrides the `makeNoise()` method.
  ```java 
  public interface Animal {
    public void makeNoise();
  }
  
  public class Pig implements Animal {
    public voide makeNoide() {
        System.out.print("oink");
    }
  }
  ```

* `makeNoise` is **overloaded**, with the same name but different signatures.
  ```java
  public class Dog implements Animal {
    public void makeNoise(Dog x) {}
  }
  ```
  

For CS61B, mark every method with the `@Override` annotation in case the code won't compile unless it is actually an overriding method.    
Reason:
* Protect against typos, which would raise error.  
* Remind that the method definition came from higher heritance. 

**Interface Inheritance**

With the usage of keyword `implements`,    
* All sub-classes are said to inherit the interface from the super-class.     
* Interface consists of all the method signatures.   

With the philosophy of GRoE, `AList` is able to fit into a `List61B` box well since `AList` share an "is-a" relationship with `List61B`.

**Implementation Inheritance**

Compared to interface interitance where subclass inherits signatures but NOT implementation, use `default` keyworf to specify a method that subclasses shoulf inherit from an interface, which is called **implementation inheritance**, inheriting signatures AND implementation.

In the `interface`, add a new function as follows, then everything implementing the class can use the method:

```java
default public void print() {
    for (int i = 0; i < size(); i += 1) {
        System.out.print(get(i) + " ");
    }
    System.out.println();
}
```

For an `SLList`, it is inefficient. The method could also be override like:

```java
@Override
public void print() {
    for (Node p = sentinel.next; p != null; p = p.next) {
        System.out.print(p.item + " ");
    }
}
```

Java is able to call the appropriate `print`  due to something called **dynamic method selection**.

+ Dynamic method selection only happens for **overridden** methods.

  That is, when instance method of subtype overrides some method in supertype, regarding **a subclass inheritant from a super one**.

+ Dynamic method selection does not happen for **overloaded** methods.

  That is, when **the same** class has two methods, one for supertype and the other is for subtype.

  ```java
  public static void peek(List61B<String> list) {
      System.out.println(list.getLast());
  }
  public static void peek(SLList<String> list) {
      System.out.println(list.getFirst());
  }
  ```

**Interface vs Implementation Inheritance**

The first one allows to generalize code in a powerful and simple way, while the latter allows code-reuse, where subclasses can rely on superclasses or interfaces.  

However, both of them guaratee a **"is - a"** relationship instead of **"has - a"**.

### 4.2 Extends, Encapsulation, Casting

**Extends**

When a class is a hyponym of another class, use `extends`.

Because of `extends`, the subclass `VengefulSLList` inherits nearly all members of superclass `SLList`:

- All instance and static variables.
- All methods.
- All nested classes.
- However, constructors are **not** inherited!

To discuss the constructor behaviour in detail, one should be aware that all constructors must start with a call to one of the super class’s constructors. If not, Java will automatically do it.

For example,

```java
public VengefulSLList() {
   deletedItems = new SLList<Item>();
}

// equivalent to
public VengefulSLList() {
   super();
   deletedItems = new SLList<Item>();
}

// NOT equivalent to

public VengefulSLList() {
   super(x);
   deletedItems = new SLList<Item>();
}
```

By the way, extends should only be used for **"is - a"** as well, and all types in Java is a descendant of the `Object` class.

**Encapsulation**

* **Module**  is a set of methods that work together as a whole to perform some task or set of related tasks. 

* A module is said to be **encapsulated** if its implementation is completely hidden, and it can be accessed only through a documented interface.

**Casting**

Java has a special syntax for specifying the compile-time type of any expression.

- Put desired type in parenthesis before the expression.
- Tells compiler to pretend it sees a particular type.

```java
Object obj = "Hello, world!";
String str = (String) obj;

System.out.println(str);
```

### 4.3 Higher Order Functions

Compare these two approaches with Python,

* **Explicit HoF** Approach:

  ```java
  def print_larger(x, y, compare, stringify):
      if compare(x, y):
          return stringify(x)
      return stringify(y)
  ```

  Using the explicit higher order function approach, there is **a common way** to print out the larger of two objects.

* **Subtype Polymorphism** Approach:

  ```java
  def print_larger(x, y):
      if x.largerThan(y):
          return x.str()
      return y.str()
  ```

In contrast, in the subtype polymorphism approach, the **object** itself makes the choices. 

As to Higher Order Function, it is a function that treats another function as data.

* In Python, it performs like

  ```python
  # Part I:
  def tenX(x):
     return 10*x
  # Part II: 
  def do_twice(f, x):
     return f(f(x))
   
  print(do_twice(tenX, 2))
  ```

* In Java 7 (which is old school), variables cannot contain pointers to functions.

  * To mimic **Part I**, use an `interface` instead:

    ```java
    public interface IntUnaryFunction {
    	int apply(int x);
    }
    
    public class TenX implements IntUnaryFunction {
    	public int apply(int x) {
       		return 10 * x;
    	}
    }
    ```

  * To finish, code as follows:  

    ```java
    public class HoFDemo {
    	public static int do_twice(IntUnaryFunction f, int x) {
       		return f.apply(f.apply(x));
    	}
    	
    	public static void main(String[] args) {
       		System.out.println(do_twice(new TenX(), 2));
    	}
    }
    ```

### 4.4 Comparables, Comparators

  Focus on one line of code which will raise error:

```java
if (items[i] > items[maxDex]) {}
```

This line is based on the confusing assumption that `>` operator works for all arbitrary `Objects` types, which is not the case.

Here are possible solutions:

* Define the `maxDog` function of the `Dog` class specifically:

  ```java
  public static Dog maxDog(Dog[] dogs) {
      if (dogs == null || dogs.length == 0) {
          return null;
      }
      Dog maxDog = dogs[0];
      for (Dog d : dogs) {
          if (d.size > maxDog.size) {
              maxDog = d;
          }
      }
      return maxDog;
  }
  ```

  It works, but a `maxCat` function is needed for the `Cat` class, a `maxWhale` is needed for the `Whale` class, etc.

* Java has a built-in interface called `Comparable`:

  ```java
  import java.util.Comparable;
  
  // which is actually
  public interface Comparable<T> {
    public int compareTo(T obj);
  }
  ```

  It behaves in the principle of

  * Return -1 if `this` < `o`,
  * Return 0 if `this` equals `o`,
  * Return 1 if `this` > `o`.

  In this way, rewrite the `Dog` class like:

  ```java
  public class Dog implements Comparable<Dog> {
      ...
      public int compareTo(Dog uddaDog) {
          return this.size - uddaDog.size;
      }
  }
  ```

* Java also has a built-in interface called `Comparator`:

  ```java
  import java.util.Comparator;
  
  // which is actually
  public interface Comparator<T> {
      int compare(T o1, T o2);
  }
  ```

  It behaves in the principle of

  * Return negative number if `o1` < `o2`,
  * Return 0 if `o1` equals `o2`,
  * Return positive number if `o1` > `o2`.

### 4.5 Built-in Lists and Sets

**Lists**

To instantiate an implementation from built-in `List`, we could

```java
java.util.List<Integer> L = new java.util.ArrayList<>();
```

Or we could,

```java
import java.util.List;
import java.util.ArrayList;
```

**Sets**
Sets are a collection of **unique** elements, with **no** sense of order. Instatiation is just as above.

Here are examples in Java and Python:

```java
Set<String> s = new HashSet<>();
s.add("Tokyo");
s.add("Lagos");
System.out.println(S.contains("Tokyo")); // true
```

In Python, we does not use a method but the keyword `in`.

```python
s = set()
s.add("Tokyo")
s.add("Lagos")
print("Tokyo" in s) # True
```

Here is an implementation of `ArraySet`.

```java
import java.util.Iterator;

public class ArraySet<T> implements Iterable<T> {
    private T[] items;
    private int size; // the next item to be added will be at position size

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i += 1) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map.
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("can't add null");
        }
        if (contains(x)) {
            return;
        }
        items[size] = x;
        size += 1;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /** returns an iterator (a.k.a. seer) into ME */
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    public static void main(String[] args) {
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);

        //iteration
        for (int i : aset) {
            System.out.println(i);
        }
    }
}
```

### 4.6 Iteration

Java Iterator is an **object** that enables a programmer to traverse a container. It offers a unified method of traversal, and is often provided via the `interface`. 

In order for a class to support **iteration**, an enhanced loop could be tranlated into a manual approach, from

```java
Set<String> s = new HashSet<>();
...
for (String city : s) {
    ...
}
```

to the codes like

```java
Set<String> s = new HashSet<>();
...
Iterator<String> seer = s.iterator();
while (seer.hasNext()) {
    String city = seer.next();
    ...
}
```

### 4.7 Exceptions

In python you may have seen this with the `raise` keyword. In Java, Exceptions are objects and we throw exceptions using the following format:

```java '
throw new ExceptionObject(*para1*, ...)
```

Though there would be an `Excpetion` is used or not, but this is better as

+ Controllability of the code.
+ More useful Exception type and helpful error message for users.

### 4.6 Iterators



## 5. <span id='5'>Asymptotics Analysis</span>

### 5.1 Introduction

The process of efficient programming could be considered in two perspectives:
* Programming Cost (time, readability, maintainance)
* Execution Cost (**Time/Space** Complexity)

Take the example below as an example, which is to determine if there is a duplicate element in a **sorted** array.

```java
List<Integer> example = [-3, -1, 2, 4, 4, 8, 10, 12];
```

There is a significant difference between a **naive** and **better** algorithm.

* Assume the naive one to compare each pair of the elements, like

  ```java
  // Naive algorithm: compare everything
  public static boolean dup1(int[] A) {  
    for (int i = 0; i < A.length; i += 1) {
      for (int j = i + 1; j < A.length; j += 1) {
        if (A[i] == A[j]) {
           return true;
        }
      }
    }
    return false;
  }
  ```

* Assume the better one to compare each element with just the element next to it, like

  ```java
  // Better algorithm: compare only neighbors
  public static boolean dup2(int[] A) {
    for (int i = 0; i < A.length - 1; i += 1) {
      if (A[i] == A[i + 1]) { 
        return true; 
      }
    }
    return false;
  }
  ```

### 5.2 Notation

Rather than physically timing the amount of time it takes for the algorithm to run, one can instead count the total number of operations given that the **input size** is `N`, probably diveded by **worst case** and **average case**.

* Big-Theta, which is **the order of growth**.           

  $\Theta(N^3+4N^5) = \Theta(N^5)$

* Big-Oh, which is like **"less than or equal"**.

  $N^3+4N^5\in\mathbf{O}(N^6)$

### 5.3 Classic Cases

#### 5.3.1 For Loops

In the end, there is **no shortcut** to doing runtime analysis. It requires careful thought, but there are a few useful techniques and things to know: **find exact sum**, **write out examples** and **draw pictures**.

**Example 1:**

```java
int N = A.length;
for (int i = 0; i < N; i += 1)
   for (int j = i + 1; j < N; j += 1)
      if (A[i] == A[j])
         return true;
return false;
```

There are two ways of approaching the runtime analysis : **counting numbers of operations** and **geometric visualization**.

* **Counting:** 

  Total number $C(N) = 1 + 2 + 3 + ... + (N-1) = N (N-1) /2 $, which is  part of $N^2$ family.

  Since  `==` is a constant time operation, the runtime for the worst case is $\Theta(N^2)$.

* **Visualizing**: The **area** of a right triangle with a side length $N-1$, which is in the $N^2$ family.

**Example 2:**

```java
public static void printParty(int N) {
   for (int i = 1; i <= N; i = i * 2) {
      for (int j = 0; j < i; j += 1) {
         System.out.println("hello");   
         int ZUG = 1 + 1;
      }
   }
}
```

Total number $C(N)=1+2+4+8+...+N$,  which is $\Theta(N)$.

#### 5.3.2 Recursion

**Example:**

```java
public static int f3(int n) {
   if (n <= 1) 
      return 1;
   return f3(n-1) + f3(n-1);
}
```

Total number $C(N)=1+2+4+...+2^N-1$, with a **recurrence relation** of $C(N)= 2C(N-1)+1$.

As a result, it is in the scope of a $\Theta(2^N)$.

#### 5.3.3 Binary Search

To find a key in a sorted array, the **binary search** compares keys against the middle entry: too small, go left; too big, go right; equal, found.   

Here is an classic implementation, though old-fashioned:

```java
static int binarySearch(String[] sorts, String x, int lo, int hi) {
  	if (lo > hi) return -1;
		int m = (lo + hi) / 2;
		int cmp = x.compareTo(sorted[m])
    if (cmp < 0) return binarySearch(sorted, x, lo, m-1);
		else if (cmp > 0) return binarySearch(sorted, x, m+1, hi);
		else return m;
}
```

Though intuitively, the total number of the worst has something to do with $\log_{2}{N}$, here is the exact computation: $C(N) = ⌊\log_{2}{N}⌋+1$

Each call takes constant time, resulting in a $\Theta(\log{N})$, given the fact that the base of logarithm does not matter.

#### 5.3.4 Mergesort

Recall selection sort with $\Theta(N^2)$:

```java
public static void selectSort(int[] arr){
    for(int i = 0; i < arr.length; i++){
        int min = i;

        // Compare crt number with later ones, and record index of the smallest.
        for(int j = i + 1; j < arr.length; j++){
           // If the later one is smaller than min, record index of new min.
           if(arr[j] < arr[min]){ min = j;}
        }
        // If index of min is not that of crt i, min is updated.
        if(i != min){
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }
}
```

* Find the smallest item among the unsorted items, move it to the front, and ‘fix’ it in place.

* Sort the remaining unfixed items using selection sort.

However, the merging of two sorted lists costs only $\Theta(N)$, which is relatively faster. 

To make it clear, the runtime for the **split-in-half-then-merge-them** sort is $N+2(N/2)^2$, which is about **half** of $N^2$ for selection sort. If it continues to be halved, lists of size 1 will be reached, when selection sort will not be of use.

As a result, here is the implementaion of **mergesort** with $\Theta(N\log{N})$:

```java
public class MergeSort {
    public static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;	// left pointer
        int j = mid + 1;// right pointer
        int k = 0;
       
      	// move smaller number into the array
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // move the remaining left in
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        // move the remaining right in
        while (j <= high) {
            temp[k++] = a[j++];
        }
        // cover "nums" with new array
        for (int k2 = 0; k2 < temp.length; k2++) {
            a[k2 + low] = temp[k2];
        }
    }

    public static void mergeSort(int[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            // left
            mergeSort(a, low, mid);
            // right
            mergeSort(a, mid + 1, high);
            // merge left and right
            merge(a, low, mid, high);
            return Arrays.toString(a);
        }
    }
}
```

## <span id='6'>6. Disjoint Sets</span>

### 6.1 Introduction

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

### 6.2 QuickFind and QuickUnion

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

### 6.3 Weighted Quick Union (WQU)

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

### 6.4 Summary

| Implementation            | `isConnected`           | `connect`               |
| ------------------------- | ----------------------- | ----------------------- |
| Quick Find                | $\Theta(1)$             | $\Theta(1)$             |
| Quick Union               | $\mathbf{O}(N)$         | $\mathbf{O}(N)$         |
| Weighted Quick Union      | $\mathbf{O}(\log N)$    | $\mathbf{O}(\log N)$    |
| WQU with Path Compression | $\mathbf{O}(\alpha(N))$ | $\mathbf{O}(\alpha(N))$|

For M operations on N elements, WQU with Path Compression is in time complexity: $\mathbf{O}(N+M(\lg^∗N))$, which behaves as constant in long term.

## <span id='7'>7. ADTs and Trees</span>

### 7.1 Abstract Data Types

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

### 7.2 Binary Search Trees

#### 7.2.1 Introduction

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

 #### 7.2.2 Operations

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

#### 7.2.3 Application

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

### 7.3 B-Trees

#### 7.3.1 BST Performance

Every tree has its **height** and nodes in each **depth**. The **average depth** of a tree is the mean of the depth of every node. Height and depth determines the runtime of BST operations, while the height determines the **worst-case** runtime to find a node, the average depth **average-case** runtime.

The **order** to insert the node in has a major impact on the height and average depth of a BST. For example, the order `1, 2, 3, 4, 5, 6, 7` results in a height 6 and an average depth 3; the order `4, 2, 1, 3, 6, 5, 7` results in a height 2 and an average depth 1.43, which is much better. 

For the **best** case of a "bushy" tree, it has a height of $\Theta(\log{N})$; for the **worst** case of a "spindly" one, it has a height of $\Theta(N)$, which is similar to a linked list. For **randomly generated** ones, it can be proved $E(d) = 2\ln N$ and $E(h) = 4.311\ln N$. However, there were cases when real-time data coming **sequentially**, with no time to shuffle the data.

### 7.4 B-Tree implementation

By simply **avoiding new leaves to be inserted**, the height would never increase. So in the new implementation, it simply stacks the new value into a existing leaf node at the appropriate location and moves up a value when reaching a certain number of values. Sometimes there are adaptions, and the children of a overstuffed node could be split into ranges more than 2. Only in the case when the root is above the limit could the tree height be increased.

This splitting-tree data strcture with perfect balance is called **B-Tree**. The one with a limit of 3 items per node is called **2-3-4 tree**; while with the limit of 2, **2-3 tree**.

The B-Tree has the variant that **all leaves are the same distance from the root** and, **a non-leaf node with k items must have exactly k + 1 children**.

For the details in the implementation, please refer to the codes below:

```java
```



