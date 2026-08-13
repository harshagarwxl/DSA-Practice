class Solution {
    static class Node {
        char prefixChar, suffixChar;
        int prefixLen, suffixLen, maxLen;
        int l, r;
    }

    private Node[] seg;
    private char[] s;

    public int[] longestRepeating(String str, String queryCharacters, int[] queryIndices) {
        int n = str.length();
        int k = queryCharacters.length();
        s = str.toCharArray();
        seg = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = seg[1].maxLen; // root node stores global max
        }
        return ans;
    }

    private void build(int idx, int l, int r) {
        seg[idx] = new Node();
        seg[idx].l = l; seg[idx].r = r;
        if (l == r) {
            seg[idx].prefixChar = seg[idx].suffixChar = s[l];
            seg[idx].prefixLen = seg[idx].suffixLen = seg[idx].maxLen = 1;
            return;
        }
        int mid = (l + r) / 2;
        build(idx * 2, l, mid);
        build(idx * 2 + 1, mid + 1, r);
        seg[idx] = merge(seg[idx * 2], seg[idx * 2 + 1], l, r);
    }

    private void update(int idx, int l, int r, int pos, char c) {
        if (l == r) {
            s[pos] = c;
            seg[idx].prefixChar = seg[idx].suffixChar = c;
            seg[idx].prefixLen = seg[idx].suffixLen = seg[idx].maxLen = 1;
            return;
        }
        int mid = (l + r) / 2;
        if (pos <= mid) update(idx * 2, l, mid, pos, c);
        else update(idx * 2 + 1, mid + 1, r, pos, c);
        seg[idx] = merge(seg[idx * 2], seg[idx * 2 + 1], l, r);
    }

    private Node merge(Node left, Node right, int l, int r) {
        Node res = new Node();
        res.l = l; res.r = r;

        res.prefixChar = left.prefixChar;
        res.suffixChar = right.suffixChar;

        res.prefixLen = left.prefixLen;
        if (left.prefixLen == (left.r - left.l + 1) && left.prefixChar == right.prefixChar) {
            res.prefixLen += right.prefixLen;
        }

        res.suffixLen = right.suffixLen;
        if (right.suffixLen == (right.r - right.l + 1) && right.suffixChar == left.suffixChar) {
            res.suffixLen += left.suffixLen;
        }

        res.maxLen = Math.max(left.maxLen, right.maxLen);
        if (left.suffixChar == right.prefixChar) {
            res.maxLen = Math.max(res.maxLen, left.suffixLen + right.prefixLen);
        }

        return res;
    }
}
