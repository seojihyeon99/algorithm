class Solution {
    int max = 0; // 방문 가능한 최대 던전수
    
    public void dfs(int idx, int health, int[][] dungeons, int n, int[] order, boolean[] visited) { // idx : 방문 순서 정해진 던전수, health : 현재 피로도
        // 1) 모든 던전 탐험
        if(idx == n) {
            max = Math.max(max, idx);
            return;
        }
        
        for(int i=0; i<n; i++) {
            if(visited[i]) continue;
            
            // 2) 최소 피로도 보다 작은 경우
            if(dungeons[i][0] > health) {
                max = Math.max(max, idx);
                continue;
            }
            
            visited[i] = true;
            order[idx] = i;
            dfs(idx+1, health - dungeons[i][1], dungeons, n, order, visited);
            visited[i] = false;
        }
        
    } 
    
    public int solution(int k, int[][] dungeons) {
        int n = dungeons.length; // 던전 개수
        int[] order = new int[n]; // 던전 방문 순서
        boolean[] visited = new boolean[n]; // 해당 던전 방문 여부
        
        dfs(0, k, dungeons, n, order, visited);
        
        return max;
    }
}