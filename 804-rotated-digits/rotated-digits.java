import java.util.HashSet;
import java.util.Arrays;

class Solution {
    public int rotatedDigits(int n) {
        int count = 0;
        
        // Digits that stay valid after rotation
        HashSet<Integer> validDigits = new HashSet<>(Arrays.asList(0, 1, 8, 2, 5, 6, 9));
        // Digits that force the number to be different from the original
        HashSet<Integer> diffusiveDigits = new HashSet<>(Arrays.asList(2, 5, 6, 9));

        for (int i = 1; i <= n; i++) {
            if (isGood(i, validDigits, diffusiveDigits)) {
                count++;
            }
        }
        
        return count;
    }

    private boolean isGood(int num, HashSet<Integer> valid, HashSet<Integer> diffusive) {
        boolean hasDiffusive = false;
        
        while (num > 0) {
            int digit = num % 10;
            
            // If any digit is not in the valid set (3, 4, 7), the whole number is invalid
            if (!valid.contains(digit)) {
                return false;
            }
            
            // Check if we found at least one digit that changes the value (2, 5, 6, or 9)
            if (diffusive.contains(digit)) {
                hasDiffusive = true;
            }
            
            num /= 10;
        }
        
        return hasDiffusive;
    }
}