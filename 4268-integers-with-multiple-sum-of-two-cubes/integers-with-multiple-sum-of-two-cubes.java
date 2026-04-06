class Solution {
    public List<Integer> findGoodIntegers(int n) {
        int limit =  (int) Math.cbrt(n);
        Map <Integer , Integer > sumCounts = new HashMap<>();
        for(int a = 1 ; a <= limit; a++)
        {
            for( int b = a; b<= limit; b++)
            {
                long sum = (long) a*a*a + (long) b*b*b;
                if( sum > n)
                    break;
                int sumofint = (int) sum;
                sumCounts.put(sumofint,sumCounts.getOrDefault(sumofint,0)+1);
            }
        }
        List <Integer> result = new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry : sumCounts.entrySet())
        {
            if(entry.getValue() >= 2)
                result.add(entry.getKey());
        }

        Collections.sort(result);
        return result;
    }
}