import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	// 방향벡터(상하좌우)
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 행의 개수
		int m = Integer.parseInt(st.nextToken()); // 열의 개수
		
		Queue<int[]> queue = new ArrayDeque<>(); // 빙산 정보(x좌표, y좌표, 높이)
		
		// 배열 초기화 및 입력 받기
		int[][] map = new int[n][m];
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<m; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] > 0) {
					queue.offer(new int[] {r, c, map[r][c]}); // x좌표, y좌표, 높이
				}
			}
		}
		
		int year = 0;
		boolean isSeparated = false;
		while(!queue.isEmpty()) {
			year++; // 1년 증가
			
			// 이웃한 0 개수만큼 빙산 높이 감소 (0보다 더 줄어들진 x)
			map = decreaseIce(n, m, map, queue);
			
			// 빙산 다 녹거나, 1개밖에 남지 않은 경우 => 두 덩어리로 분리될 수 없음
			if(queue.size() <= 1) break;
			
			// 두 덩어리 이상으로 분리되었는지 확인
			if(isSeperated(n, m, map, queue)) {
				isSeparated = true;
				break;
			};
			
		}
		
		if(isSeparated)
			System.out.println(year);
		else
			System.out.println(0);
	}
	
	static boolean isSeperated(int n, int m, int[][] map, Queue<int[]> queue) {
		int len = queue.size(); // 남은 빙산의 크기
		int cnt = 0; // 첫번째 그룹의 빙산 개수
		
		boolean[][] visited = new boolean[n][m]; // 방문여부
		
		int[] first = queue.peek();
		Queue<int[]> group = new ArrayDeque<>();
		group.add(first); // 원래 큐에 있던 좌표를 하나 넣음
		visited[first[0]][first[1]] = true; // 방문 처리
		cnt++; // 첫번째 그룹의 빙산 개수 1증가
		while(!group.isEmpty()) {
			int[] cur = group.poll();
			
			for(int i=0; i<4; i++) {
				int nextx = cur[0] + dx[i];
				int nexty = cur[1] + dy[i];
				
				// 배열의 인덱스를 넘지 않고, 빙산이고, 방문하지 않았다면
				if(map[nextx][nexty]>0 && !visited[nextx][nexty]) {
					group.add(new int[] {nextx, nexty, cur[2]});
					visited[nextx][nexty] = true; // 방문 처리
					cnt++; // 첫번째 그룹의 빙산 개수 1증가
				}
			}
		}
		
		if(cnt != len)
			return true;
		
		return false;
	}
	
	static int[][] decreaseIce(int n, int m, int[][] map, Queue<int[]> queue) {
		int len = queue.size();
		
		// 원본 배열 복제!! (원본 건드리면 안됨!!@@@@)
		int[][] newMap = new int[n][m];
		for(int i=0; i<n; i++) {
			newMap[i] = Arrays.copyOf(map[i], m);
		}
		
		for(int i=0; i<len; i++) {
			int[] cur = queue.poll();
			
			int zeroCnt = 0;
			for(int j=0; j<4; j++) {
				int nextx = cur[0] + dx[j];
				int nexty = cur[1] + dy[j];
				
				// 이웃한 곳이 0이라면, 0의 개수 증가
				if(map[nextx][nexty] == 0) {
					zeroCnt++;
				}
			}
			
			int height = cur[2]-zeroCnt;
			// 빙산이 다 녹았다면
			if(height <= 0) {
				newMap[cur[0]][cur[1]] = 0;
			}
			// 빙산이 다 녹지 않았다면
			else {
				newMap[cur[0]][cur[1]] = height;
				queue.offer(new int[] {cur[0], cur[1], height}); // 큐에 빙산 그대로 추가
			}
		}
		
		// 복제한 배열을 반환
		return newMap;
	}
}