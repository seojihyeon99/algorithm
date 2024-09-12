import java.util.*;

class Solution {  
    public int solution(int n, int[][] results) {
        Set[] upper = new Set[n]; // 자기보다 순위 높음
        Set[] lower = new Set[n]; // 자기보다 순위 낮음
        for(int i=0; i<n; i++) {
            upper[i] = new HashSet<Integer>();
            lower[i] = new HashSet<Integer>();
        }
        
        for(int i=0; i<results.length; i++) {
            int winner = results[i][0] - 1;
            int looser = results[i][1] - 1;
            
            lower[winner].add(looser); // 순위 낮음
            upper[looser].add(winner); // 순위 높음
        }
        
        while(true) {
            boolean changed = false;
            
            for(int i=0; i<n; i++) {
                // 높은거보다 더 높은거
                int prevUp = upper[i].size();
                Iterator<Integer> up = upper[i].iterator();
                HashSet<Integer> tmp1 = new HashSet<>();
                while(up.hasNext()) {
                    int cur = up.next();
                    tmp1.addAll(upper[cur]);
                }
                upper[i].addAll(tmp1);
                if(prevUp != upper[i].size()) changed = true;

                // 낮은거보다 더 낮은거
                int prevLow = lower[i].size();
                Iterator<Integer> low = lower[i].iterator();
                HashSet<Integer> tmp2 = new HashSet<>();
                while(low.hasNext()) {
                    int cur = low.next();      
                    tmp2.addAll(lower[cur]);
                }
                lower[i].addAll(tmp2);
                if(prevLow != lower[i].size()) changed = true;
            }
            
            if(!changed) break;
        }

        
        int sum = 0; // 순위 정해진 것의 개수
        for(int i=0; i<n; i++) {
            int l = lower[i].size();
            int u = upper[i].size();
            
            if(l+u == n-1) sum++;
        }
        
        return sum;
    }
}