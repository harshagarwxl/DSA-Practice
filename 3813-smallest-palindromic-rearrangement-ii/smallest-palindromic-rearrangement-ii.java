import java.math.BigInteger;
import java.util.*;

/**
 * Efficient k-th lexicographic palindromic permutation for a palindromic input string.
 *
 * Key optimization:
 *   - Use multinomial formula for total permutations: total! / (v1! v2! ...)
 *   - Maintain current multinomial count and update it in O(1) when fixing a character:
 *       newCount = currentCount * (countOfChar) / remainingTotal
 *     This avoids recomputing multinomials from scratch for every candidate and
 *     removes the expensive repeated binomial/gcd work that caused TLE.
 *
 * Uses BigInteger to avoid overflow and to compare with k safely.
 */
public class Solution {
    public String smallestPalindrome(String s, int k) {
        BigInteger kBig = BigInteger.valueOf(k);

        // Frequency map (sorted)
        TreeMap<Character, Integer> freq = new TreeMap<>();
        for (char c : s.toCharArray()) freq.put(c, freq.getOrDefault(c, 0) + 1);

        // Check palindrome feasibility and find middle char if any
        int oddCount = 0;
        Character middleChar = null;
        for (Map.Entry<Character, Integer> e : freq.entrySet()) {
            if ((e.getValue() & 1) == 1) {
                oddCount++;
                middleChar = e.getKey();
            }
        }
        if (oddCount > 1) return "";

        // Build half multiset (char -> count/2)
        TreeMap<Character, Integer> halfFreq = new TreeMap<>();
        int halfLen = 0;
        for (Map.Entry<Character, Integer> e : freq.entrySet()) {
            int c = e.getValue() / 2;
            if (c > 0) {
                halfFreq.put(e.getKey(), c);
                halfLen += c;
            }
        }

        // Quick check: if halfLen == 0 (all chars were middle or empty)
        if (halfLen == 0) {
            // Only one palindrome exists (the original single char or empty)
            return kBig.compareTo(BigInteger.ONE) <= 0 ? s : "";
        }

        // Precompute factorials as BigInteger up to halfLen
        BigInteger[] fact = new BigInteger[halfLen + 1];
        fact[0] = BigInteger.ONE;
        for (int i = 1; i <= halfLen; i++) fact[i] = fact[i - 1].multiply(BigInteger.valueOf(i));

        // Compute initial total permutations: total! / (v1! v2! ...)
        BigInteger totalPerms = fact[halfLen];
        for (int v : halfFreq.values()) {
            if (v > 1) totalPerms = totalPerms.divide(fact[v]);
        }
        if (kBig.compareTo(totalPerms) > 0) return "";

        // We'll maintain currentPerms = permutations of the remaining multiset
        BigInteger currentPerms = totalPerms;
        int remaining = halfLen;

        StringBuilder half = new StringBuilder();
        for (int pos = 0; pos < halfLen; pos++) {
            // iterate characters in lexicographic order
            for (Map.Entry<Character, Integer> entry : halfFreq.entrySet()) {
                char c = entry.getKey();
                int cnt = entry.getValue();
                if (cnt == 0) continue;

                // If we fix this char now, the new permutations count is:
                // permsIfChoose = currentPerms * cnt / remaining
                BigInteger permsIfChoose = currentPerms.multiply(BigInteger.valueOf(cnt))
                                                      .divide(BigInteger.valueOf(remaining));

                if (kBig.compareTo(permsIfChoose) > 0) {
                    // skip these permutations
                    kBig = kBig.subtract(permsIfChoose);
                } else {
                    // choose this char
                    half.append(c);
                    // update halfFreq, remaining, and currentPerms
                    halfFreq.put(c, cnt - 1);
                    currentPerms = permsIfChoose;
                    remaining--;
                    break;
                }
            }
        }

        // Build full palindrome
        StringBuilder palindrome = new StringBuilder();
        palindrome.append(half);
        if (middleChar != null) palindrome.append(middleChar);
        palindrome.append(new StringBuilder(half).reverse());

        return palindrome.toString();
    }
}
