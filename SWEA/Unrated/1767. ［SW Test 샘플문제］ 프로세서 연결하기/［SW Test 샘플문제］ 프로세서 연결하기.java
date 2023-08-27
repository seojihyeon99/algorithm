import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int n; // 배열 한변 크기
	static int[][] arr; // 배열
	
	// 방향벡터(상,하,좌,우)
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static List<int[]> core = new ArrayList<>(); // 코어의 좌표 저장(가장자리 제외)
	
	static int maxPower; // 전원에 연결된 최대 코어수
	static int resultline; // 그때의 가장 짧은 선의 길이
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		int tc = Integer.parseInt(br.readLine()); // 총 테스트 케이스 수
		// 테스트 케이스 수만큼 반복
		for(int t=1; t<=tc; t++) {
			n = Integer.parseInt(br.readLine()); // 배열 한변 크기
			resultline = 0;
			core.clear();
			maxPower = 0;

			boolean[][] selected = new boolean[n][n]; // 전선 또는 프로세서 이미 설치되어있는지 여부 
			
			int powercnt = 0; // 파워에 연결된 개수
			// 배열 생성 및 입력받기
			arr = new int[n][n];
			for(int r=0; r<n; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int c=0; c<n; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
					if((r == 0 || r == n-1 || c == 0 || c == n-1) && arr[r][c] == 1) { // 가장자리에 위치한 Core는 이미 전원 연결됨
						maxPower++;
						selected[r][c] = true;
					}
					else if(arr[r][c] == 1) { // 가장자리에 위치하지 않은 Core
						selected[r][c] = true;
						core.add(new int[] {r,c});
					}
				}
			}
//			System.out.println("dfs 시작전 추가된 core 수 : " + maxPower);
//			System.out.println("리스트에 들어간 core 수 : " + core.size());
			dfs(selected, 0, maxPower, 0);
			
			sb.append("#" + t + " " + resultline + "\n");
		}
		System.out.println(sb);
	}
	
	
	static void dfs(boolean[][] selected, int count, int powercnt, int lines) { // count : 현재 몇개의 core 봤는지(전원 연결된거 제외), powercnt : 현재 core 개수
		if(count == core.size()) {
			if(powercnt > maxPower) {
				maxPower = powercnt;
				resultline = lines;
//				System.out.println("이때의 전원연결된 최대 코어수 :  "+powercnt + ", 전선 길이 : " + lines);
//				print(selected);
			}
			else if (powercnt == maxPower && resultline > lines) { // 더 적은 전선 사용가능하면
				resultline = lines;
//				System.out.println("이때의 전원연결된 최대 코어수 :  "+powercnt + ", 전선 길이 : " + lines);
//				print(selected);
			}
			return;
		}
		
		int curx = core.get(count)[0];
		int cury = core.get(count)[1];
		// 4방향에 대한 dfs 수행
		for(int dir=0; dir<4; dir++) {
			int length = 1;
			while(true) {
				int x = curx + dx[dir]*length;
				int y = cury + dy[dir]*length;
				if((x == 0 || x == n-1 || y == 0 || y == n-1) && !selected[x][y]) { // 해당 방향으로 가장자리에 도달했으면
					// 원본배열 복사
					boolean[][] copy = new boolean[n][n];
					for(int r=0; r<n; r++) {
						copy[r] = Arrays.copyOf(selected[r], n);
					}
					
					// 배열에서 해당 line부분들 체크
					for(int j=1; j<n; j++) {
						x = curx + dx[dir]*j;
						y = cury + dy[dir]*j;
						
						if(x<0 || x>=n || y<0 || y>=n) {
							break;
						}
						copy[x][y] = true;
					}
					dfs(copy, count+1, powercnt+1, lines+length);
					break;
				}
				else if(selected[x][y]) { // 해당 방향으로 가장자리 도달 전에, 이미 해당 셀 누가 쓰고 있다면
					break;
				}
				length++; // 전선의 길이 1 증가
			}
		}
		
		// 4방향 모두 전원을 연결할 수 없는(또는 연결하지 않는) 경우 -> 다른 매개변수는 변경없이 다음 core 보러~(count+1)
		dfs(selected, count+1, powercnt, lines);

	}
	
//	static void print(boolean[][] copy) {
//		for(int  r=0; r<n; r++) {
//			for(int c=0; c<n; c++) {
//				System.out.printf("%6s", copy[r][c]);
//			}
//			System.out.println();
//		}
//		System.out.println("==========================================");
//	}
}

