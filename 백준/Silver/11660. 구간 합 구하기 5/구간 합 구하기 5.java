/**
 * 얘도 4번문제와 마찬가지로 최악의 경우 O(N*N*M)이라서 1000억이 넘을 수 있음
 * => 2중 for문 사용하면 x
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int[][] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 배열 크기
		int m = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수
		
		// 2차원 배열(누적합을 저장하는 배열) 생성 및 초기화
		arr = new int[n+1][n+1]; // row나 col의 인덱스가 0인건 쓰지 x		
		for(int r=1; r<=n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1; c<=n; c++) {
				// 한 행의  시작 열이라면
				if(c == 1) {
					// 이전 행까지의 누적합 + 현재 받은 정수 => 새로운 누적합
					arr[r][c] = arr[r-1][n] + Integer.parseInt(st.nextToken());
					continue;
				}
				// 한 행의 시작 열이 아니라면
				else {
					// 이전 열까지의 누적합 + 현재 받은 정수 => 새로운 누적합
					arr[r][c] = arr[r][c-1] + Integer.parseInt(st.nextToken());
				}
			}
		}
		
		// m번 반복하면서, x1 y1 x2 y2를  받아서 구간합 구하기
		for(int i=0; i<m; i++) {
			// x1, y1, x2, y2 좌표 입력받기
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int sum = 0; // 구간합 저장하는 변수
			
			// 점의 좌표가 같다면
			if(x1 == x2 && y1 == y2) {
				// y1이 첫번째 열이면(col==1)
				if(y1 == 1) {
					sum += arr[x1][y1]-arr[x1-1][n];	
				}
				// y1이 첫번째 열이 아니면(col!=1)
				else {
					sum += arr[x1][y1]-arr[x1][y1-1];					
				}
				sb.append(sum+"\n");
				continue;
			}
			
			// 점의 좌표가 다르다면		
			// y1이 첫 번째 열이면(col==1)
			if(y1 == 1) {
				sum += arr[x2][y2]-arr[x1-1][n];
				for(int k=0; k<x2-x1; k++) {
					sum -= arr[x2-k-1][n] - arr[x2-k-1][y2];
				}
			}				
			// y1이 첫번째 열이 아니면(col!=1)
			else {
				sum += arr[x2][y2] - arr[x1][y1-1];

				for(int k=0; k<x2-x1; k++) {
					sum -= arr[x2-k][y1-1]-arr[x2-1-k][y2];				
				}		
			}	
			sb.append(sum+"\n");
		}
		
		System.out.println(sb.toString());
	}
}
