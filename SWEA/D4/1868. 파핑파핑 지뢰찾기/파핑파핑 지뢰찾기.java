/**
 * [아이디어]
 * 지뢰맵을 입력받을 때 지뢰인 칸('*')은 다음에 방문하지 않도록, 방문처리를 함
 * 또한 지뢰맵을 입력받고 난 직후 다시 지뢰맵을 순회하며, 지뢰맵의 지뢰가 없는 칸 '.'을 -> 8방향에 지뢰의 개수 0~8로 바꿈
 * 
 * 지뢰 찾기 게임 findMine함수는 2차원 배열을 순회하면서 0인 좌표를 만나면, 해당 0의 좌표를 큐에 넣고, zeroClick 함수를 통해 해당 0에 의해 자동 클릭될 수 있는 무리들을 모두 처리해줌
 * 이렇게 하여 해당 0에 의해 자동 클릭될 수 있는 무리들을 처리할때마다 zeroClickCnt가 1씩 증가함.
 * 나머지 0에 의한 자동 클릭이 되지 않는 애들은 => 따로 한번씩 클릭해줘야 됨 : 전체 배열 원소의 개수(n*n) - 방문하지 않은 곳의 개수(visited가 false인 곳)
 * 따라서 전체 클릭 횟수는 zeroClickCnt + {전체 배열 원소의 개수(n*n) - 방문하지 않은 곳의 개수(visited가 false인 곳)}가 됨
 * 
 * [메모리]
 * 36,636 kb
 * [시간]
 * 208ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
	static boolean[][] visited; // 방문여부 저장
	static int zeroClickCnt; // 0 무리를 클릭한 횟수
	
	// 방향벡터(8방 : 좌상단, 상단, 우상단, 우, 우하단, 하단, 좌하단, 좌)
	static int[] dx = {-1,-1,-1,0,1,1,1,0};
	static int[] dy = {-1,0,1,1,1,0,-1,-1};
	
	static Queue<int[]> queue = new ArrayDeque<>(); // 0인 좌표를 저장하는 큐
	
	// 지뢰 찾기 게임 
	static int findMine(int[][] map, int n) {
		// 2차원 배열을 순회하면서
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				if(map[r][c] == 0 && !visited[r][c]) { // 해당 좌표값이 0이고(8방향 탐색 클릭 없이 자동으로 더 할 수 있음), 방문하지 않았다면
					queue.offer(new int[] {r,c}); // 큐에 넣어주고
					zeroClickCnt++; // 0 무리 클릭 개수 증가
					zeroClick(r, c, map, n); // 0 무리에 대한 처리함수 시작
				}
			}
		}
		
		int notvisited = 0; // 방문하지 않은 개수 => 0에 의한 클릭이 일어나지 않았으므로, 다 일일히 click 해줘야!!
		// 2차원 배열을 순회하면서
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				if(visited[r][c] == false) notvisited++; //방문하지 않았다면, 방문하지 않은 개수(notvisited) 중가
			}
		}
		
		return notvisited + zeroClickCnt; // 방문하지 않은 개수(0에 의한 클릭 일어나지 않은 개수) + 0 무리를 클릭한 개수 = 총 클릭 개수
	}
	
	// 해당 0에 의해 자동 클릭될 수 있는 무리들 처리 함수
	static void zeroClick(int r, int c, int[][] map, int n) {
		while(!queue.isEmpty()) { // 큐가 빌때까지 반복
			int[] cur = queue.poll(); // 큐에서 하나 꺼내서
			int x = cur[0]; // 해당 0인 값의 x좌표
			int y = cur[1]; // 해당 0인 값의 y좌표
			
			if(!visited[x][y]) { // 해당 0인 좌표를 방문하지 않았으면
				visited[x][y] = true; // 해당 0인 좌표 방문 처리
				for(int i=0; i<8; i++) { // 8방향 탐색
					int nx = x + dx[i];
					int ny = y + dy[i];
					// 배열 인덱스 벗어나지 않았고, 0인 곳이라면
					if(nx>=0 && nx<n && ny>=0 && ny<n && map[nx][ny] == 0 && !visited[nx][ny]) {
						queue.offer(new int[] {nx,ny}); // 큐에 해당 0의 좌표 또 넣어줌
					}
					// 배열 인덱스 벗어나지 않았고, 0인 곳이 아니라면
					else if(nx>=0 && nx<n && ny>=0 && ny<n && map[nx][ny] != 0 && !visited[nx][ny]) {
						visited[nx][ny] = true; // 해당 0이 아닌 값을 방문처리
					}
				}
				
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		// 테스트 케이스만큼 반복
		for(int t=1; t<=tc; t++) {
			zeroClickCnt = 0; // 다음 반복을 위해 0 무리를 클릭한 횟수 초기화
			
			int n = Integer.parseInt(br.readLine()); // 배열 한변의 길이(1<=N<=300)
			
			visited = new boolean[n][n]; // 방문여부 저장 배열 초기화
			
			// 배열 생성 및 초기화
			int[][] map = new int[n][n];
			for(int r=0; r<n; r++) {
				String s = br.readLine();
				for(int c=0; c<n; c++) {
					map[r][c] = s.charAt(c); // '*'이면 지뢰 있음. '.'이면 지뢰 없음
					if(map[r][c] == '*') { // 만약 지뢰 칸이라면
						visited[r][c] = true; // 해당 칸 방문처리(지뢰찾기 게임에서 이미 방문했으므로, 이 칸은 고려하지 x => 즉 지뢰칸 누를 수 없음)
					}
				}
			}
			
			// 배열을 순회하면서 8방향 지뢰의 수 구하여, map을 업데이트 함
			for(int r=0; r<n; r++ ) {
				for(int c=0; c<n; c++) {
					if(map[r][c] == '.') {
						int nMine = 0; // 주변 지뢰의 수
						// 8방 탐색 시작
						for(int i=0; i<8; i++) {
							int x = r + dx[i];
							int y = c + dy[i];
							// 배열 인덱스 벗어나지 않았고, 지뢰가 있다면
							if(x>=0 && x<n && y>=0 && y<n && map[x][y] == '*') {
								nMine++; // 주변 지뢰의 수를 1 증가
							}
						}
						map[r][c] = nMine; // 지뢰맵의 값을 8방향 지뢰의 수로 업데이트
					}
				}
			}
			
			int result = findMine(map, n);
			sb.append("#" + t + " " + result + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
}
