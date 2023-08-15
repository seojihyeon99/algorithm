import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int[][] arr;
	static int n; // 배열 크기
	static int m; // 파리채 크기
	static int maxBug; // 최대 죽인 파리수 
	
	static void catchBug() {
		// 왼쪽 위의 시작부분이 될수 있는 좌표 (r,c) 구하기 => 행은 0 ~ n-m 좌표까지 가능, 열도 0~ n-m 좌표까지 가능
		for(int r=0; r<=n-m; r++) {
			for(int c=0; c<=n-m; c++) {
				int sum = 0; // 죽인 파리수
				
				// 현재 해당 m 구역의 합 구하기(왼쪽 위부터 시작해서~~ 오른쪽 아래까지 순서로 합 구함)
				for(int i=r; i<r+m; i++) {
					for(int j=c; j<c+m; j++) {
						sum+=arr[i][j];
					}
				}
				
				if(sum > maxBug) {
					maxBug = sum;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tcase = Integer.parseInt(br.readLine());
		
		// 테스트 케이스만큼 반복
		for(int t=1; t<=tcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			// 2차원 배열 생성 및 초기화
			arr = new int[n][n]; 
			for(int r=0; r<n; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<n; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxBug = Integer.MIN_VALUE; // 항상 함수 시작전 min값을 정수최댓값으로
			
			catchBug();
			
			System.out.println("#" + t + " " + maxBug);
		}
	}
}
