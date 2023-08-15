import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int start;
	static int maxMove;
	static int count;
	
	// 상하좌우 방향벡터
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static void Move(int[][] arr, int row, int col) {
		// 이동하려는 방 존재하면 이동
		for(int i=0; i<4; i++) {
			int x = row + dx[i];
			int y = col + dy[i];
			// 이동하려는 방에 적힌 숫자가 혀냊 방에 적힌 숫자보다 정확히 1 더 크면 이동
			if(arr[x][y] != 0 && arr[x][y] == (arr[row][col] + 1)) {
				count++;
				Move(arr, x, y);
				break;
			}
		}

	}
	
	
	
	
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 int t = Integer.parseInt(br.readLine());
		 
		 //테스트 케이스만큼 반복
		 for(int i=1; i<=t; i++) {
			 start = Integer.MAX_VALUE;
			 maxMove = 0;
			 int n = Integer.parseInt(br.readLine());
			 
			 int[][] arr = new int[n+2][n+2];
//			 int[] visited = new int[n*n + 1] ; //해당 숫자 방문 체크
			 
			 //배열 입력받기
			 for(int r=1; r<=n; r++) {
				 StringTokenizer st = new StringTokenizer(br.readLine());
				 for(int c=1; c<=n; c++) {
					 arr[r][c] = Integer.parseInt(st.nextToken());
				 }
			 }
			 
			 //배열의 모든 원소에 대해 Move 실행
			 for(int r=1; r<=n; r++) {
				 for(int c=1; c<=n; c++) {
					 count = 1;
					 Move(arr, r, c);
					// 더크면
					if(maxMove < count) {
						start = arr[r][c]; // 당연히 업데이트
						maxMove = count;
					}
					// 같으면
					else if(maxMove == count) {
						int temp = arr[r][c];
						if(temp<start) { // 둘 중 더 작은 원소로 업데이트
							start = temp;
						}
						maxMove = count;			
					}					  
				 }
			 }
			 
			 System.out.println("#" + i + " " + start + " " + maxMove);
		 }
		 
	}
}
