# 4. Java Characteristics

## 4.1 Inheritance, Implements

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

## 4.2 Extends, Encapsulation, Casting

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

## 4.3 Higher Order Functions

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

## 4.4 Comparables, Comparators

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

## 4.5 Built-in Lists and Sets

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

## 4.6 Iteration

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

## 4.7 Exceptions

In python you may have seen this with the `raise` keyword. In Java, Exceptions are objects and we throw exceptions using the following format:

```java '
throw new ExceptionObject(*para1*, ...)
```

Though there would be an `Excpetion` is used or not, but this is better as

+ Controllability of the code.
+ More useful Exception type and helpful error message for users.

