import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine()); // 수열의 크기
		int[] arr = new int[n+1]; // 수열
		int[][] dp = new int[n+1][n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i][i] = 1; // 크기가 1일때는 무조건 팰린드롬임
		}
		
		// dp 배열 모두 채우기
		for(int c=2; c<=n; c++) {
			for(int r=1; r<c; r++) {
				if(arr[r] != arr[c]) { // 양 끝이 대칭이 아니면
					dp[r][c] = 0;
				}
				else {
					if(c-r == 1) dp[r][c] = 1; // 길이가 1 차이고, 양 끝이 대칭이면
					else
						dp[r][c] = dp[r+1][c-1];
				}
			}
		}
		
		int m = Integer.parseInt(br.readLine()); // 질문의 개수
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()); // 시작 인덱스
			int e = Integer.parseInt(st.nextToken()); // 끝 인덱스
			sb.append(dp[s][e] + "\n");
		}
		
		System.out.println(sb);
	}
}
