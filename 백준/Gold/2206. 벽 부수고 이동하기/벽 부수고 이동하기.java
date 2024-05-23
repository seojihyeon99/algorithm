import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    // 방향벡터
    static int[] dx = new int[] {-1, 1, 0, 0};
    static int[] dy = new int[] {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 세로 길이
        int m = Integer.parseInt(st.nextToken()); // 가로 길이

        // 배열 입력받기
        int[][] map = new int[n][m];
        for(int r=0; r<n; r++) {
            String s = br.readLine();
            for(int c=0; c<m; c++) {
                map[r][c] = s.charAt(c)-'0';
            }
        }

        boolean[][][] visited = new boolean[n][m][2]; // 방문 여부 저장 (0: 안부숨, 1: 부숨)
        int dist = -1; // 최단 경로

        Queue<int[]> queue = new ArrayDeque<>(); // x좌표, y좌표, 벽 부쉈는지 여부(0: 안부숨, 1: 부숨), 현재까지 이동 횟수
        queue.add(new int[]{0, 0, 0, 1});
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            // 도착지점 도착했다면
            if(cur[0] == n-1 && cur[1] == m-1) {
                dist = cur[3];
                break;
            }
            
            // 인접한 곳으로 이동
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                
                // 배열의 인덱스를 넘지 않고
                if(nx<0 || nx>=n || ny<0 || ny>=m) {
                    continue;
                }
                
                // 이동할 수 길이고, 해당 위치에 간 적이 없다면
                if(map[nx][ny] == 0 && visited[nx][ny][cur[2]] == false) {
                    queue.add(new int[]{nx, ny, cur[2], cur[3] + 1});
                    visited[nx][ny][cur[2]] = true;
                }
                // 벽을 만났고, 벽을 부순 적이 없고 해당 벽의 위치에 간 적이 없다면
                else if(map[nx][ny] == 1 && cur[2] == 0 && visited[nx][ny][1] == false) {
                    queue.add(new int[]{nx, ny, 1, cur[3] + 1});
                    visited[nx][ny][cur[2]] = true;
                }
            }
        }

        System.out.println(dist);

    }
}