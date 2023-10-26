public class bubbleSort {
    public static int[] bubbleSort(int[] arr) {
        int n = arr.length;
        int flag = 0;
        // The outer loop controls the leading index of the loop.
        for (int i = 0; i < n - 1; i++) {
            // The inner loop manages the comparison of the neighbours in a loop.
            for (int j = i; j < n - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap j and j + 1
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag++;
                }
                if (flag == 0) {
                    break;
                }
                flag = 0;
            }
        }
        return arr;
    }
}
