import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int n;
	static int[][] arr;
	// 방향벡터(위, 아래)
	static int[] dx = {-1, 1};
	static int[] dy = {0, 0};
	static int sum;
	
	// 정중앙 좌표 구하는 함수
	static int middlePoint() {
		for(int i=0; i<n; i++) {
			// n이 1일때는 (0,0), n이 3일때는 (1,1), n이 5일때는 (2,2), n이 7일때는 (3,3)...
			if((2*i + 1) == n) {
				return i;
			}
		}
		return 0;
	}
	
	public static void diamond(int row, int middle, int size) {
		if(size > 1) {
			for(int i=1; i <= size/2; i++) {
				sum += arr[row][middle - i]; // 왼쪽으로 가면서 더해줌
				sum += arr[row][middle + i]; // 오른쪽으로 가면서 더해줌
			}
			sum += arr[row][middle]; // 젤 가운데꺼 더해줌
			
			if(row < middle) {
				// 위쪽 행으로
				diamond(row - 1, middle, size-2);				
			}
			else if(row > middle) {
				// 아래쪽 행으로 
				diamond(row + 1, middle, size-2);
			}
			else if(row == middle) {
				// 위 아래 행 모두 진행
				diamond(row - 1, middle, size-2);		
				diamond(row + 1, middle, size-2);
			}
		}
		// size가 1일때(0번재 행일때 또는 n-1번째 행일때)
		else {			
			sum+=arr[row][middle];
			return;
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine()); 	// 테스트 케이스 수
		
		for(int i=1; i<=t; i++) {
            sum = 0; // 다음 반복을 위해 sum 초기화
            
			n = Integer.parseInt(br.readLine()); 		// 배열의 크기
			
			// 배열 생성 및 초기화
			arr = new int[n][n];
			for(int r=0; r<n; r++) {
				String s = br.readLine();
				for(int c=0; c<n; c++) {
					arr[r][c] = s.charAt(c) - '0';
				}
			}
			
			// 정중앙 좌표
			int middle = middlePoint();
			
			
			diamond(middle, middle, n);
			System.out.println("#" + i + " " + sum);
			
		}	
		
	}
}
