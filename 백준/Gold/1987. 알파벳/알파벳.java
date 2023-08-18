import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] alpha = new boolean[26]; // 알파벳(A~Z) 사용여부 저장
	static char[][] map;
	
	// 방향벡터(상,하,좌,우)
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int row, col;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(st.nextToken()); // 행크기
		col = Integer.parseInt(st.nextToken()); // 열크기
		
		// 맵 생성 및 입력받기
		map = new char[row][col];
		for(int r=0; r<row; r++) {
			String s = br.readLine();
			for(int c=0; c<col; c++) {
				map[r][c] = s.charAt(c);
			}
		}
		
		alpha[map[0][0]-'A'] = true;
		dfs(0,0,1);

		System.out.println(maxlen);
	}
	static int maxlen ;
	
	static void dfs(int r, int c, int dist) {
		if(dist > maxlen) {
			maxlen = dist;
		}
		
		for(int i=0; i<4; i++) {
			int curx = r+dx[i];
			int cury = c+dy[i];
			
			if(curx>=0 && curx<row && cury>=0 && cury<col) {
				if(!alpha[map[curx][cury]-'A']) { // 해당 알파벳이 사용되지 않았다면
					alpha[map[curx][cury]-'A'] = true; // 방문 했다 표시
					dfs(curx,cury,dist+1); // 그 좌표 다음 방문으로
					alpha[map[curx][cury]-'A'] = false; // 다시 돌아올 때 방문 해제
				}
			}
		}
	}
}
