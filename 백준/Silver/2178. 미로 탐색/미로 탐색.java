import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int[][] map;
	private static int n;
	private static int m;
	private static boolean[][] visited;

	private static int[] dx = { -1, 1, 0, 0 }; // x 방향벡터(상하좌우)
	private static int[] dy = { 0, 0, -1, 1 }; // y 방향벡터(상하좌우)

	// 상하좌우 중 한곳으로 이동 가능한지 여부
	public static boolean canGo(int r, int c, int idx) {
		int x = r + dx[idx];
		int y = c + dy[idx];
		// 인덱스를 벗어났는지 여부 체크
		if ((x >= 0 && x < n) && (y >= 0 && y < m)) {
			// 이미 방문한곳이거나 이동할 수 없는 칸인지 체크
			if (visited[x][y] == false && map[x][y] != 0)
				return true;
		}
		return false;
	}

	public static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { r, c }); //큐에 넣어줌
		visited[r][c] = true; // 시작위치를 방문처리
		while (!queue.isEmpty()) {
			int[] point = queue.poll(); //큐에서 하나 뺌
			int x = point[0]; // 현재 디큐한 좌표의 x좌표
			int y = point[1]; // 현재 디큐한 좌표의 y좌표
			for (int i = 0; i < 4; i++) {
				// 상하좌우 중 한곳으로 이동 가능하다면
				if (canGo(x, y, i)) {
					int nx = x + dx[i]; //x 좌표 이동
					int ny = y + dy[i]; //y 좌표 이동
					map[nx][ny] = map[x][y]+1; //새로운 위치값 = 현재 위치값+1로
					queue.offer(new int[] {nx,ny}); //큐에 넣어줌
					visited[nx][ny]=true; //현재 좌표 방문처리
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m]; // 2차원 배열(미로) 저장
		visited = new boolean[n][m]; // 2차원 배열(미로)에서 방문여부 체크

		// 2차원 배열(미로) 입력 받기
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		bfs(0, 0);
		System.out.println(map[n-1][m-1]); //배열의 맨 오른쪽(m-1) 아래(n-1)값 출력 
	}
}
