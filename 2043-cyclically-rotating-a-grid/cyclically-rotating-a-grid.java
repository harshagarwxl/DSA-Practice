class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        // The number of concentric layers
        int layers = Math.min(m, n) / 2;

        for (int layer = 0; layer < layers; layer++) {
            // Define the boundaries of the current layer
            int top = layer;
            int bottom = m - 1 - layer;
            int left = layer;
            int right = n - 1 - layer;

            // Step 1: Unwrap the layer into a 1D list (Clockwise traversal)
            List<Integer> list = new ArrayList<>();
            
            // Top row (Left to Right)
            for (int j = left; j <= right; j++) list.add(grid[top][j]);
            // Right column (Top+1 to Bottom)
            for (int i = top + 1; i <= bottom; i++) list.add(grid[i][right]);
            // Bottom row (Right-1 down to Left)
            for (int j = right - 1; j >= left; j--) list.add(grid[bottom][j]);
            // Left column (Bottom-1 down to Top+1)
            for (int i = bottom - 1; i > top; i--) list.add(grid[i][left]);

            // Step 2: Calculate effective rotations
            int len = list.size();
            int effectiveK = k % len;

            // Step 3: Rewrap the elements back into the grid
            // To rotate counter-clockwise, we start pulling from the `effectiveK` index 
            int idx = effectiveK; 

            // Top row
            for (int j = left; j <= right; j++) {
                grid[top][j] = list.get(idx % len);
                idx++;
            }
            // Right column
            for (int i = top + 1; i <= bottom; i++) {
                grid[i][right] = list.get(idx % len);
                idx++;
            }
            // Bottom row
            for (int j = right - 1; j >= left; j--) {
                grid[bottom][j] = list.get(idx % len);
                idx++;
            }
            // Left column
            for (int i = bottom - 1; i > top; i--) {
                grid[i][left] = list.get(idx % len);
                idx++;
            }
        }

        return grid;
    }
}