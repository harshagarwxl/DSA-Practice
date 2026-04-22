class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        UnionFind uf = new UnionFind(n);

        for (int[] swap : allowedSwaps) {
            uf.union(swap[0], swap[1]);
        }
        Map<Integer, Map<Integer, Integer>> componentCounts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            componentCounts.computeIfAbsent(root, k -> new HashMap<>())
                           .merge(source[i], 1, Integer::sum);
        }
        int hammingDistance = 0;
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            Map<Integer, Integer> counts = componentCounts.get(root);
            
            int count = counts.getOrDefault(target[i], 0);
            if (count > 0) {
                counts.put(target[i], count - 1);
            } else {
                hammingDistance++;
            }
        }

        return hammingDistance;
    }
    
    class UnionFind{
        private int [] parent;
        private int [] rank;
        public UnionFind(int n){
            parent = new int [n];
            rank = new int [n];
            for(int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]); 
        }
        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                if (rank[rootI] < rank[rootJ]) {
                    parent[rootI] = rootJ;
                } else if (rank[rootI] > rank[rootJ]) {
                    parent[rootJ] = rootI;
                } else {
                    parent[rootI] = rootJ;
                    rank[rootJ]++;
                }
            }
        }
    }
}