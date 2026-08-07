import java.util.*;

class Solution {
    private static final int[][] DIGIT_FACTORS = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    public String smallestNumber(String num, long t) {
        int[] req = factorize(t);
        if (req == null) return "-1"; // Contains prime factor > 7

        int n = num.length();

        // 1. Precompute prefix factor counts and find first '0'
        int[][] pref = new int[n + 1][4];
        int firstZero = n;
        for (int i = 0; i < n; i++) {
            int d = num.charAt(i) - '0';
            if (d == 0) {
                firstZero = i;
                break;
            }
            for (int k = 0; k < 4; k++) {
                pref[i + 1][k] = pref[i][k] + DIGIT_FACTORS[d][k];
            }
        }

        // 2. Check if num itself is valid (exact match)
        if (firstZero == n) {
            boolean ok = true;
            for (int k = 0; k < 4; k++) {
                if (pref[n][k] < req[k]) {
                    ok = false;
                    break;
                }
            }
            if (ok) return num;
        }

        // 3. Try finding a valid prefix i < n
        int limit = Math.min(n - 1, firstZero);
        for (int i = limit; i >= 0; i--) {
            int curDigit = num.charAt(i) - '0';

            for (int d = curDigit + 1; d <= 9; d++) {
                int[] remNeeded = new int[4];
                for (int k = 0; k < 4; k++) {
                    remNeeded[k] = Math.max(0, req[k] - pref[i][k] - DIGIT_FACTORS[d][k]);
                }

                int remLen = n - 1 - i;
                if (minDigitsNeeded(remNeeded[0], remNeeded[1], remNeeded[2], remNeeded[3]) <= remLen) {
                    // Valid prefix found! Construct answer of length n.
                    String prefix = num.substring(0, i) + d;
                    String suffix = buildSmallest(remLen, remNeeded[0], remNeeded[1], remNeeded[2], remNeeded[3]);
                    return prefix + suffix;
                }
            }
        }

        // 4. If no length-n answer exists, generate the smallest answer of length > n
        int targetLen = Math.max(n + 1, minDigitsNeeded(req[0], req[1], req[2], req[3]));
        return buildSmallest(targetLen, req[0], req[1], req[2], req[3]);
    }

    private int[] factorize(long t) {
        int[] need = new int[4]; // [2, 3, 5, 7]
        int[] primes = {2, 3, 5, 7};
        for (int i = 0; i < 4; i++) {
            while (t % primes[i] == 0) {
                need[i]++;
                t /= primes[i];
            }
        }
        if (t > 1) return null; // Contains prime > 7
        return need;
    }

    private int minDigitsNeeded(int c2, int c3, int c5, int c7) {
        c2 = Math.max(0, c2);
        c3 = Math.max(0, c3);
        c5 = Math.max(0, c5);
        c7 = Math.max(0, c7);

        int count = c7 + c5 + (c3 / 2) + (c2 / 3);
        int rem2 = c2 % 3;
        int rem3 = c3 % 2;

        if (rem2 == 0 && rem3 == 0) {
            // 0 extra digits
        } else if (rem2 == 2 && rem3 == 1) {
            count += 2; // Needs 2 extra digits (e.g., '2' and '6')
        } else {
            count += 1; // Needs 1 extra digit
        }

        return count;
    }

    private String buildSmallest(int remLen, int c2, int c3, int c5, int c7) {
        c2 = Math.max(0, c2);
        c3 = Math.max(0, c3);
        c5 = Math.max(0, c5);
        c7 = Math.max(0, c7);

        int minLen = minDigitsNeeded(c2, c3, c5, c7);
        StringBuilder sb = new StringBuilder();

        // Pad front with '1's
        for (int i = 0; i < remLen - minLen; i++) {
            sb.append('1');
        }

        // Collect optimal digits for remaining factors
        List<Character> digits = new ArrayList<>();
        for (int i = 0; i < c7; i++) digits.add('7');
        for (int i = 0; i < c5; i++) digits.add('5');

        for (int i = 0; i < c3 / 2; i++) digits.add('9');
        for (int i = 0; i < c2 / 3; i++) digits.add('8');

        int rem2 = c2 % 3;
        int rem3 = c3 % 2;

        if (rem2 == 0 && rem3 == 1) digits.add('3');
        else if (rem2 == 1 && rem3 == 0) digits.add('2');
        else if (rem2 == 1 && rem3 == 1) digits.add('6');
        else if (rem2 == 2 && rem3 == 0) digits.add('4');
        else if (rem2 == 2 && rem3 == 1) {
            digits.add('2');
            digits.add('6');
        }

        Collections.sort(digits);
        for (char ch : digits) {
            sb.append(ch);
        }

        return sb.toString();
    }
}