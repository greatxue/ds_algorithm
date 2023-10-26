class Solution_3 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len_of_total = nums1.length + nums2.length;
        int[] nums_total = new int[len_of_total];
        int i = 0, j = 0;
        double median = 0;

        // First we combine two arrays into one sorted array `nums_total1.
        while (i + j < len_of_total) {
        
            // for the case both arrays `nums1`, `nums2` is waiting to add into `nums_total`
            if (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    nums_total[i + j] = nums1[i];
                    i++;
                }
                else {
                    nums_total[i + j] = nums2[j];
                    j++;
                }
            }
            
            // for the cases either one array is all into `nums_total`
            else if (i < nums1.length) {
                nums_total[i + j] = nums1[i];
                i++;
            }
            else if (j < nums2.length){
                nums_total[i + j] = nums2[j];
                j++;
            }
        }

        // Then we figure out the median.
        if ((len_of_total % 2) == 0) {
            median = (nums_total[len_of_total / 2 - 1] + nums_total[len_of_total / 2]) / 2.0;
        }
        else if ((len_of_total % 2) != 0) {
            median = nums_total[(len_of_total - 1) / 2];
        }

        return median;
    }
}