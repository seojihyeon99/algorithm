import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 방향벡터(상,하,좌,우)
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		boolean[][] visited = new boolean[n][n]; // 방문여부 저장
		
		// 배열 생성 및 초기화
		int[][] arr = new int[n][n];		
		for(int r=0; r<n; r++) {
			String s = br.readLine();
			for(int c=0; c<n; c++) {
				arr[r][c] = s.charAt(c)-'0';
			}
		}
		
		List<Integer> list = new ArrayList<>(); // 각 단지내 집의 수	
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				if(!visited[r][c] && arr[r][c]==1) {
					int count = 0; // 단지 내 집의 수
					Queue<int[]> queue = new ArrayDeque<>(); // int[] : {row, col}
					queue.offer(new int[] {r,c}); // 해당 좌표 큐에 넣어줌
					visited[r][c] = true; // 방문처리
					count++; // 단지 내 집의 수 1 증가
					while(!queue.isEmpty()) { // 큐가 빌때까지 반복
						int[] cur = queue.poll(); // 큐에서 하나 꺼냄
						// 상하좌우 이동가능한 곳 있는지 확인
						for(int i=0; i<4; i++) {
							int nextx = cur[0] + dx[i];
							int nexty = cur[1] + dy[i];
							// 배열의 인덱스 넘지않고, 방문하지 않았고, 집(1)이면
							if(nextx>=0 && nextx<n && nexty>=0 && nexty<n && !visited[nextx][nexty] && arr[nextx][nexty]==1) {
								queue.offer(new int[] {nextx, nexty}); // 큐에 넣어줌
								visited[nextx][nexty] = true; // 방문처리
								count++; // 단지 내 집의 수 1 증가
							}
						}
					}
					list.add(count); // 해당 좌표의 bfs 다 끝났다면, 단지내 집의 수 list에 넣어줌
				}
			}
		}
		
		list.sort(null); // 각 단지내 집의 수를 오름차순으로 정렬
		
		for(int i=0; i<list.size(); i++) {
			sb.append(list.get(i)+"\n");
		}		
		System.out.println(list.size() + "\n"+ sb.toString());
		
	}
}
