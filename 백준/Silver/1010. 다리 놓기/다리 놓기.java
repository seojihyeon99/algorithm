/**
 * [아이디어]
 * 두 다리가 서로 겹쳐질 수 없으므로 -> 오른쪽 사이트들 중에서 왼쪽 사이트 수만큼 뽑기만 하면, 자동으로 연결!
 * 
 * [메모리]
 * [시간]
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스수
		// 테스트 케이스수만큼 반복
		for(int t=0; t<tc; t++) { 
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 왼쪽 사이트수
			int m = Integer.parseInt(st.nextToken()); // 오른쪽 사이트수
			
			int[][] dp = new int[m+1][n+1];
			
			for(int i=0; i<=m; i++) {
				dp[i][0] = 1;
			}
			for(int i=0; i<=n; i++) {
				dp[i][i] = 1;
			}
			
			for(int r=2; r<=m; r++) {
				for(int c=1; c<=n; c++) {
					dp[r][c] = dp[r-1][c] + dp[r-1][c-1];
				}
			}
			
			sb.append(dp[m][n]+"\n");
		}

		System.out.println(sb);
	}
	
}
