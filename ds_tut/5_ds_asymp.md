# 5. Asymptotics Analysis

## 5.1 Introduction

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

## 5.2 Notation

Rather than physically timing the amount of time it takes for the algorithm to run, one can instead count the total number of operations given that the **input size** is `N`, probably diveded by **worst case** and **average case**.

* Big-Theta, which is **the order of growth**.           

  $\Theta(N^3+4N^5) = \Theta(N^5)$

* Big-Oh, which is like **"less than or equal"**.

  $N^3+4N^5\in\mathbf{O}(N^6)$

## 5.3 Classic Cases

### 5.3.1 For Loops

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

### 5.3.2 Recursion

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

### 5.3.3 Binary Search

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

### 5.3.4 Mergesort

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