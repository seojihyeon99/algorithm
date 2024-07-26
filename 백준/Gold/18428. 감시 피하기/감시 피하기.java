import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {	
	static int[] pos; // 장애물의 위치
	static boolean result;
	static List<int[]> teachers = new ArrayList<>(); // 선생님 위치 정보
	// 방향벡터 (x, y)
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static void comb(int start, int idx, int n, char[][] map) { // idx : 현재까지 선택된 장애물 개수
		// 종료조건 (장애물 3개 설치함)
		if(idx == 3) {
			// 장애물을 설치한 맵을 만듦
			char[][] newMap = new char[n][n];
			for(int i=0; i<n; i++) {
				newMap[i] = Arrays.copyOf(map[i], n);
			}
			for(int i=0; i<3; i++) {
				int r = pos[i]/n;
				int c = pos[i]%n;
				newMap[r][c] = 'O';
			}
			
			// 선생님 
			for(int i=0; i<teachers.size(); i++) {
				int[] cur = teachers.get(i);
				
				// 상하좌우 감시
				for(int j=0; j<4; j++) {
					int k = 1;
					while(true) {
						int nx = cur[0] + k*dx[j];
						int ny = cur[1] + k*dy[j];
						
						if(nx<0 || nx>=n || ny<0 || ny>=n) break;
						
						if(newMap[nx][ny] == 'O') break;
						else if(newMap[nx][ny] == 'S') return;
						
						k++;
					}
				}
			}
			
			result = true; // 감시피할 수 있는 장애물 놓을 수 있음
			return;
		}
		
		for(int i=start; i<n*n; i++) {
			int r = i/n;
			int c = i%n;
			
			// 장애물 설치할 수 있는 빈칸이라면
			if(map[r][c] == 'X') {
				pos[idx] = i;
				comb(i+1, idx+1, n, map);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 복도의 길이
		
		// 복도 정보 입력받기
		char[][] map = new char[n][n];
		for(int r=0; r<n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				map[r][c] = st.nextToken().charAt(0);
				
				if(map[r][c] == 'T') {
					teachers.add(new int[] {r, c});
				}
			}
		}
		
		pos = new int[3];
		comb(0, 0, n, map);
		
		if(result) {
			System.out.println("YES");			
		} else {
			System.out.println("NO");						
		}
	}
}