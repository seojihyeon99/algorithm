/**
 * [아이디어]
 * 그리디
 * 
 * 파이프라인을 우상 -> 우 -> 우하 순으로 설치
 * 연결할 수 있는 맨 위로 붙여서 파이프를 설치하다보면, 
 * 아래에서 파이프를 연결할 수 있는 공간이 더 많이 생기기 때문에,
 * 결국 이는 놓을 수 있는 파이프라인의 최대 개수가 됨 
 * 
 * 그리디 아이디어는 생각했으나.. 
 * 백트래킹방법이 잘못되어서.. 계속 답 안나왔음ㅠㅠ
 * if(flag==true) return;이 아니라, 그냥 break를 해버렸음ㅠㅠ(해당 행에 설치 미완료해도 다음 방향 안보게 됨)
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n; // 행크기
	static int m; // 열크기
	static boolean[][] map; // 맵
	
	// 방향벡터(우상, 우, 우하)
	static int[] dx = {-1,0,1};
	static int[] dy = {1,1,1};
	
	static int npipe; // 파이프라인 개수
	static boolean flag; // 해당 행에 파이프 설치 여부
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); //행크기
		m = Integer.parseInt(st.nextToken()); //열크기
		
		// 맵 생성 및 입력 받기
		map = new boolean[n][m];
		for(int r=0; r<n; r++) {
			String s = br.readLine();
			for(int c=0; c<m; c++) {
				if(s.charAt(c) == 'x') {
					map[r][c] = true;
				}
			}
		}
		
		// 각 행마다 파이프 설치
		for(int i=0; i<n; i++) {
			flag = false;
			map[i][0] = true;
			buildPipe(i,0);			
		}
		
		System.out.println(npipe);
	}
	
	// 파이프 설치
	static void buildPipe(int row, int col) { 
		// 원웅이의 빵집에 도착했다면
		if(col == m-1) {
			flag = true; // 해당 행에 파이프 설치 완료했다고 표시
			npipe++; // 설치한 파이프 수 1 증가
			return;
		}
		
		for(int i=0; i<3; i++) { // 우상->우->우하 순으로 봄
			int x = row + dx[i];
			int y = col + dy[i];
			if(x>=0 && x<n && y>=0 && y<m && !map[x][y]) { // 배열 인덱스 넘지 않고, 방문하지 않았다면
				map[x][y] = true; // 방문처리
				buildPipe(x, y); // 다음 재귀로~
				if(flag == true) // 재귀 완료후에 해당 행에 파이프 설치 가능하다면
					return; // 뒤의 방향벡터는 실행 x(백트래킹)
			}
		}			
	}

}