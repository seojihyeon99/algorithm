import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 방향벡터 (상하좌우 + 말이 갈수있는 8방향)
    static int[] dx = {-1,1,0,0,-2,-1,1,2,2,1,-1,-2};
    static int[] dy = {0,0,-1,1,1,2,2,1,-1,-2,-2,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine()); // 말의 이동을 할 수 있는 횟수

        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()); // 가로 길이
        int n = Integer.parseInt(st.nextToken()); // 세로 길이

        // 배열 초기화 및 입력 받기
        int[][] map = new int[n][m];
        for(int r=0; r<n; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<m; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][][] visited = new boolean[n][m][k+1]; // 방문여부(말의 이동 횟수 포함)
        int time = -1;
        Queue<int[]> queue = new ArrayDeque<>(); // x좌표, y좌표, 말의 이동 횟수, 걸린시간
        queue.offer(new int[] {0,0,0,0});
        visited[0][0][0] = true;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            // 도착지점에 도착했을 경우
            if(cur[0] == n-1 && cur[1] == m-1) {
                time = cur[3];
                break;
            }

            // 원숭이 이동 (인접한 방향 + 말이 이동할 수 있는 방향)
            for(int i=0; i<12; i++) {
                int x = cur[0] + dx[i];
                int y = cur[1] + dy[i];

                // 배열의 인덱스를 넘었을 경우
                if(x<0 || x>=n || y<0 || y>=m) continue;

                // 인접한 방향 이동의 경우
                if(i>=0 && i<4) {
                    // 아직 방문하지 않았고, 평지라면
                    if(!visited[x][y][cur[2]] && map[x][y] == 0) {
                        queue.offer(new int[] {x, y, cur[2], cur[3]+1});
                        visited[x][y][cur[2]] = true;
                    }
                } 
                // 말의 방향으로 이동의 경우
                else if(i>=4 && cur[2]<k) {
                    // 아직 방문하지 않았고, 평지라면
                    if(!visited[x][y][cur[2]+1] && map[x][y] == 0) {
                        queue.offer(new int[] {x, y, cur[2]+1, cur[3]+1});
                        visited[x][y][cur[2]+1] = true;
                    }
                }
            }


        }

        System.out.println(time);
    }


}