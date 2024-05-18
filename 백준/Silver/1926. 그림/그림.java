import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 방향벡터(동서남북)
	static int[] dx = new int[] {-1,1,0,0};
	static int[] dy = new int[] {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 세로
		int m = Integer.parseInt(st.nextToken()); // 가로
		
		// 배열 초기화 및 입력받기
		int[][] arr = new int[n][m];
		boolean[][] visited = new boolean[n][m]; 
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<m; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int maxSize = 0;
		int cnt = 0;
		for(int r=0; r<n; r++) {
			for(int c=0; c<m; c++) {
				if(arr[r][c] == 1 && !visited[r][c]) {
					int size = 0;
					Queue<int[]> queue = new ArrayDeque();
					queue.offer(new int[] {r,c});
					visited[r][c] = true;
					cnt++; // 그림 개수 증가
					size++; // 그림 크기 증가
					while(!queue.isEmpty()) {
						int[] cur = queue.poll();
						
						for(int i=0; i<4; i++) {
							int curx = cur[0] + dx[i];
							int cury = cur[1] + dy[i];
							
							if(curx >=0 && curx <n && cury >=0 && cury < m && arr[curx][cury] == 1 && !visited[curx][cury]) {
								queue.offer(new int[] {curx, cury});
								visited[curx][cury] = true;
								size++;
							}
						}
					}
					
					if(size > maxSize) maxSize = size;
				}
			}
		}
		
		System.out.println(cnt);
		System.out.println(maxSize);
		
	}

}