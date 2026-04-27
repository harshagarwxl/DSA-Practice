class Solution {
    
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) return nums;

        // 1. Find the minimum element
        int min = nums[0];
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }

        // 2. If there are negative numbers, shift all elements up to make them >= 0
        if (min < 0) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] -= min; // Subtracting a negative is the same as adding its absolute value
            }
        }

        // 3. Run the Radix Sort
        radixSort(nums);

        // 4. Shift all elements back down to their original values
        if (min < 0) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] += min; 
            }
        }

        return nums;
    }

    public static void radixSort(int[] arr) {
        // Find max using a standard loop instead of Streams for better performance
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }

        // Do counting sort for every digit
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n]; 
        int[] count = new int[10]; 

        // Count occurrences of the current digit
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        // Change count[i] so that count[i] now contains actual position
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array (iterate backward to keep the sort stable)
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Copy the output array to arr[]
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }
}