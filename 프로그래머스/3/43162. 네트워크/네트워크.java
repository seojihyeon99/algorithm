import java.util.*;

class Solution {
    boolean[] visited; // 해당 컴퓨터 네트워크 포함 여부
    
    public void bfs(int start, boolean[] visited, int n, int[][] computers) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            for(int i=0; i<n; i++) {
                if(cur == i) continue;
                
                if(!visited[i] && computers[cur][i] == 1) {
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        
        int cnt = 0; // 총 네트워크 수
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                bfs(i, visited, n, computers);
                cnt++; // 네트워크 수 증가
            }
        }
        
        return cnt;
    }
}