class Solution {
    // Array to store the parent/root of each tree node
    private int[] parent;

    // Find the root of the set with path compression for efficiency
    private int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        // Path compression: attach nodes directly to the root
        return parent[i] = find(parent[i]);
    }

    // Union two sets together by making one root point to the other
    private void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            parent[rootI] = rootJ;
        }
    }

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Edge case: 1x1 grid is always valid
        if (m == 1 && n == 1) return true;

        // Initialize Union-Find structure
        parent = new int[m * n];
        for (int i = 0; i < m * n; i++) {
            parent[i] = i; 
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int currentType = grid[r][c];
                int currentIndex = r * n + c;

                // 1. Check connection to the RIGHT
                if (c + 1 < n) {
                    int rightType = grid[r][c + 1];
                    // Current pipe must go right (1, 4, 6) AND next pipe must go left (1, 3, 5)
                    if ((currentType == 1 || currentType == 4 || currentType == 6) && 
                        (rightType == 1 || rightType == 3 || rightType == 5)) {
                        union(currentIndex, currentIndex + 1);
                    }
                }

                // 2. Check connection DOWN
                if (r + 1 < m) {
                    int downType = grid[r + 1][c];
                    // Current pipe must go down (2, 3, 4) AND next pipe must go up (2, 5, 6)
                    if ((currentType == 2 || currentType == 3 || currentType == 4) && 
                        (downType == 2 || downType == 5 || downType == 6)) {
                        union(currentIndex, currentIndex + n);
                    }
                }
            }
        }

        // Return true if top-left and bottom-right are in the same tree
        return find(0) == find(m * n - 1);
    }
}