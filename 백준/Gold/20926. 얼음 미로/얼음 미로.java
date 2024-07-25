import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int x;
	int y;
	int time;
	
	public Node(int x, int y, int time) {
		this.x = x; 
		this.y = y; 
		this.time = time;
	}
	
	@Override
	public int compareTo(Node o) {
		return this.time - o.time;
	}
}

class Main {	
	// 방향벡터(상우하좌)
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken()); // 가로 크기
		int n = Integer.parseInt(st.nextToken()); // 세로 크기

		char[][] map = new char[n][m]; // 얼음 미로
		boolean[][] visited = new boolean[n][m]; // 해당 지점 방문 여부
		int[][] dist = new int[n][m]; // 각 지점까지의 최단경로
		// dist 초기화
		for(int i=0; i<n; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);			
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int finishx = -1; int finishy=-1;
		
		// 2차원 배열 입력받기
		for(int r=0; r<n; r++) {
			String s = br.readLine();
			for(int c=0; c<m; c++) {
				map[r][c] = s.charAt(c);
				
				// 출발지점
				if(map[r][c] == 'T') {
					pq.offer(new Node(r, c, 0));
					dist[r][c] = 0;
				}
				else if(map[r][c] == 'E') {
					finishx = r;
					finishy = c;
				}
			}
		}
		
		int time = -1;
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			// 이미 방문한 지점이라면
			if(visited[cur.x][cur.y]) continue;
			visited[cur.x][cur.y] = true; // 방문처리
			
			// 도착지에 도착한 경우
			if(map[cur.x][cur.y] == 'E') {
				time = cur.time;
				break;
			}
			
			for(int dir=0; dir<4; dir++) {
				int cnt = slide(cur.x, cur.y, dir, map, n, m); // 해당 방향으로 슬라이드 할 수 있는 칸수
				
				// 범위에 벗어나거나 구멍에 빠지는 경우
				if(cnt == -1) continue;
				
				// 미끄러질때까지의 걸린시간의 합 구함
				int sum = 0;
				for(int i=1; i<=cnt; i++) {
					int nx = cur.x + i*dx[dir];
					int ny = cur.y + i*dy[dir];
					
					// 출구를 만나는 경우
					if(map[nx][ny] == 'E') break;
					
					sum += (map[nx][ny]-'0');				
				}
				
				// 미끄러진 최종 좌표
				int nx = cur.x + cnt*dx[dir];
				int ny = cur.y + cnt*dy[dir];				
				// 거리 갱신
				if(!visited[nx][ny] && dist[nx][ny] > dist[cur.x][cur.y] + sum) {
					dist[nx][ny] = dist[cur.x][cur.y] + sum;
					
					pq.offer(new Node(nx, ny, dist[nx][ny]));
				}
			}
		}
		
		System.out.println(time);
	}
	
	static int slide(int x, int y, int dir, char[][] map, int n, int m) {
		int cnt = 1;
		while(true) {
			int nextx = x + cnt*dx[dir];
			int nexty = y + cnt*dy[dir];
			
			// 범위 벗어나거나 구멍을 빠지는 경우
			if(nextx<0 || nextx>=n || nexty<0 || nexty>=m || map[nextx][nexty] == 'H') {
				return -1;
			}
			
			// 바위를 만나는 경우
			if(map[nextx][nexty] == 'R') {
				return cnt-1;
			}
			
			// 출구를 만나는 경우
			if(map[nextx][nexty] == 'E') {
				return cnt;
			}
			
			cnt++;
		}
	}
}