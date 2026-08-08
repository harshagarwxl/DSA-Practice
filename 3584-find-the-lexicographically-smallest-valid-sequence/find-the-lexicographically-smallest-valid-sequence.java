class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // lastPos[j] stores the largest index in word1 from which 
        // the suffix word2[j...] can be matched greedily from right to left.
        int[] lastPos = new int[m + 1];
        lastPos[m] = n; // Sentinel value for empty suffix

        int r = n - 1;
        for (int j = m - 1; j >= 0; j--) {
            while (r >= 0 && word1.charAt(r) != word2.charAt(j)) {
                r--;
            }
            lastPos[j] = r;
            if (r >= 0) {
                r--; // Move pointer left for the next character match
            }
        }

        int[] result = new int[m];
        boolean usedMismatch = false;
        int j = 0; // Pointer for word2

        for (int i = 0; i < n && j < m; i++) {
            boolean isMatch = word1.charAt(i) == word2.charAt(j);

            if (isMatch) {
                result[j] = i;
                j++;
            } else if (!usedMismatch && lastPos[j + 1] > i) {
                // Use our 1 allowed mismatch at word1[i]
                result[j] = i;
                usedMismatch = true;
                j++;
            }
        }

        // If we couldn't complete the full word2, return an empty array
        return j == m ? result : new int[0];
    }
}