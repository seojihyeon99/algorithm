import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * dp[i][j] : i열까지 고려했을 때, 현재 i열에서 떼어낸 스티커가 j행(0이면 선택  x)일때의 경우의 수
 * 무조건 1개의 열에는 1개의 스티커만 뗄 수 있고, 그 전에 떼어낸 행과 같은 행을 떼는 것은 불가능함
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for(int i=0; i<tc; i++) {
			int n = Integer.parseInt(br.readLine()); // 열의 칸 수
			int[][] dp = new int[3][n+1];
			
			StringTokenizer st1 = new StringTokenizer(br.readLine()); // 1행
			StringTokenizer st2 = new StringTokenizer(br.readLine()); // 2행
			
			// 1번째 열 dp 테이블 작성
			dp[0][1] = 0;
			dp[1][1] = Integer.parseInt(st1.nextToken());
			dp[2][1] = Integer.parseInt(st2.nextToken());
			
			// 2번째 열 ~ 
			for(int j=2; j<=n; j++) {
				dp[0][j] = Math.max(Math.max(dp[0][j-1], dp[1][j-1]), dp[2][j-1]);
				dp[1][j] = Math.max(dp[0][j-1], dp[2][j-1]) + Integer.parseInt(st1.nextToken());
				dp[2][j] = Math.max(dp[0][j-1], dp[1][j-1]) + Integer.parseInt(st2.nextToken());
			}
			
			sb.append(Math.max(Math.max(dp[0][n], dp[1][n]), dp[2][n]) + "\n");
		}
		
		System.out.println(sb);
	} 
}
