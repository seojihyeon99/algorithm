import java.util.*;

class Solution {
    // 방향벡터 (상하좌우)
    int[] dx = new int[] {-1, 1, 0, 0};
    int[] dy = new int[] {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        int n = maps.length; // 세로 길이
        int m = maps[0].length; // 가로 길이
        
        int answer = -1;
        
        boolean[][] visited = new boolean[n][m]; // 방문여부
        Queue<int[]> queue = new ArrayDeque<>(); // x좌표, y좌표, 지나간 칸 수
        queue.offer(new int[] {0,0,1}); // 처음 시작 좌표
        visited[0][0] = true; // 시작점 방문처리
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            // 도착지점이라면
            if(cur[0] == n-1 && cur[1] == m-1) {
                answer = cur[2];
                break;
            }
            
            // 상하좌우 이동
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i]; // 이동할 x좌표
                int ny = cur[1] + dy[i]; // 이동할 y좌표
                
                // 배열의 인덱스 넘는 경우 pass
                if(nx<0 || nx >=n || ny<0 || ny>=m) continue;
                
                // 벽이 있거나 이미 방문한적 있다면 pass
                if(maps[nx][ny] == 0 || visited[nx][ny]) continue;
                
                queue.offer(new int[] {nx, ny, cur[2]+1}); // 지나간 칸 수 1 증가
                visited[nx][ny] = true; // 방문처리
            }
            
        }
        
        return answer;
    }
}