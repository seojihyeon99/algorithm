import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static char[][] arr = new char[100][100];
	static boolean[][] visited;
	static int row;	// 도착점의 x좌표
	static int col;	// 도착점의 y좌표
	static int resultcol;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		// 일단 테케 1개로 바꿔놓음
		for(int i=0; i<10; i++) {
			visited = new boolean[100][100];
			int t = Integer.parseInt(br.readLine());
			// 2차원 배열 초기화
			for(int r=0; r<100; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int c=0; c<100; c++) {
					arr[r][c] = st.nextToken().charAt(0);
					if(arr[r][c] == '2') {
						row = r;
						col = c;
					}
				}
			}

			// row가 99행부터 ~~~ 0행까지 반복 시작
			while(true) {
				visited[row][col] = true; // 현재 방문처리
				
				// row가 0이면 종료
				if(row == 0) {
					resultcol = col;
					break;
				}
				
				// 왼쪽에 갈수 있는 사다리 있고, 방문하지 않았으면
				if(col-1 >= 0 && arr[row][col-1] == '1' && !visited[row][col-1]) {
					col -= 1; // 열의 값 1 감소
					visited[row][col] = true; // 방문 체크
					continue;
				}
				
				// 오른쪽에 갈수 있는 사다리 있고, 방문하지 않았으면
				if(col+1 < 100 && arr[row][col+1] == '1' && !visited[row][col+1]) {
					col += 1; // 열의 값 1 감소
					visited[row][col] = true; // 방문 체크
					continue;
				}
				
				// 왼쪽/오른쪽 모두 갈 수 없다면 -> 위쪽으로
				row-=1; // 행의 값 1 감소
			}
			
			
			System.out.println("#"+t+" "+resultcol);
		}		
	}
}
