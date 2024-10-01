import java.util.*;

class Solution {
    String[] answer;
    HashMap<String, ArrayList<String>> map = new HashMap<>();
    
    public void dfs(String[] result, int idx, int n, String prev) {
        if(idx == n+1) {
            for(int i=1; i<=n; i++) {
                if(result[i].compareTo(answer[i]) == 0) continue;
                
                if(result[i].compareTo(answer[i]) > 0) break;
                else {
                    answer = Arrays.copyOf(result, n+1);
                    break;                    
                }
            }
            return;
        }
        
        if(map.get(prev) == null || map.get(prev).size() == 0) return;
        int size = map.get(prev).size();
        
        for(int i=0; i<size; i++) {
            result[idx] = map.get(prev).remove(i);
            dfs(result, idx+1, n, result[idx]);
            map.get(prev).add(i, result[idx]);    
        }
    }
    
    public String[] solution(String[][] tickets) {
        int n = tickets.length;
        for(int i=0; i<n; i++) {
            String start = tickets[i][0];
            String finish = tickets[i][1];
            
            ArrayList<String> cur;
            if(map.containsKey(start)) cur = map.get(start);
            else cur = new ArrayList<>();
            
            cur.add(finish);
            map.put(start, cur);
        }
        
        answer = new String[n+1];
        Arrays.fill(answer, "ZZZ");
        answer[0] = "ICN";
        
        String[] result = new String[n+1];
        result[0] = "ICN";
        dfs(result, 1, n, "ICN");
        
        return answer;
    }
}