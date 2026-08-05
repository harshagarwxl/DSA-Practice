class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        
        for (int[] edge : invocations) {
            graph.get(edge[0]).add(edge[1]);
        }
        
        // Step 1: Find suspicious set
        boolean[] suspicious = new boolean[n];
        dfs(k, graph, suspicious);
        
        // Step 2: Check if removal is possible
        for (int[] edge : invocations) {
            int a = edge[0], b = edge[1];
            if (!suspicious[a] && suspicious[b]) {
                // Non-suspicious method calls suspicious → cannot remove
                return allMethods(n);
            }
        }
        
        // Step 3: Return remaining methods
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) result.add(i);
        }
        return result;
    }
    
    private void dfs(int node, List<List<Integer>> graph, boolean[] suspicious) {
        if (suspicious[node]) return;
        suspicious[node] = true;
        for (int nei : graph.get(node)) {
            dfs(nei, graph, suspicious);
        }
    }
    
    private List<Integer> allMethods(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) res.add(i);
        return res;
    }
}
