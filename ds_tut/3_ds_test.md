# 3. Testing and Debugging

## 3.1 Testing
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

## 3.2 Coding Philosophy

The philosophy of **unit test-driven** programming emphasizes writing tests before writing functional code. By defining expected outputs or behaviors, developers can continually verify if the code is working as expected and provide safeguards for code maintenance and iterations. This approach helps to improve code quality and changes our perception of software development.

## 3.3 Debugging

There are multiple ways for debugging:

* **Using an IDE's Debugger:** Most modern IDEs come equipped with robust debugging tools. These debugging tools allow you to set **breakpoints**, step through the code, inspect variable values, and evaluate expressions, among others.
* **Using Visualized Tools:** These tools offer a reliable method to take a look at the Java implementation condition, such as **Java visualizer**.
* **Partner Collaboration:** When in trouble, **referring to others** is also of great importance.