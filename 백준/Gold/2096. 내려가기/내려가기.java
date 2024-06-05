import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 배열의 세로 길이
	
		// 배열 입력 받기
		int[][] map = new int[n][3];
		for(int r=0; r<n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<3; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	
		int[][] mindp = new int[n][3]; // 최소 누적합
		int[][] maxdp = new int[n][3]; // 최대 누적합
		// 첫번째 행까지의 최소 및 최대 누적합
		mindp[0][0] = map[0][0]; mindp[0][1] = map[0][1]; mindp[0][2] = map[0][2];
		maxdp[0][0] = map[0][0]; maxdp[0][1] = map[0][1]; maxdp[0][2] = map[0][2];
		
		// 그 다음행부터 최소 및 최대 누적합 업데이트
		for(int r=1; r<n; r++) {
			mindp[r][0] = Math.min(mindp[r-1][0], mindp[r-1][1]) + map[r][0];
			mindp[r][1] = Math.min(Math.min(mindp[r-1][0], mindp[r-1][1]), mindp[r-1][2]) + map[r][1];
			mindp[r][2] = Math.min(mindp[r-1][1], mindp[r-1][2]) + map[r][2];
			
			maxdp[r][0] = Math.max(maxdp[r-1][0], maxdp[r-1][1]) + map[r][0];
			maxdp[r][1] = Math.max(Math.max(maxdp[r-1][0], maxdp[r-1][1]), maxdp[r-1][2]) + map[r][1];
			maxdp[r][2] = Math.max(maxdp[r-1][1], maxdp[r-1][2]) + map[r][2];
		}
		
		System.out.println(Math.max(Math.max(maxdp[n-1][0], maxdp[n-1][1]), maxdp[n-1][2]) + " " + Math.min(Math.min(mindp[n-1][0], mindp[n-1][1]), mindp[n-1][2]));
		
	}
	
	
}