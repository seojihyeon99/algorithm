import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * dp[현재 몇번째 이름을 적는지][현재 줄에 적힌 글자의 수] = 이전 줄까지 남게 되는 칸 수의 제곱의 합의 최소 값
 * 
 * 현재 이름을 쓰는 경우
 * 1) 이전 이름과 같은 줄에 씀
 * => 이전 이름에서 적힌 글자의 수 j이고, 현재 이름의 글자의 수가 cur인 경우
 *    현재 상태 dp[i][j+1+cur]는 이전 상태 dp[i-1][j]와 같음
 * 
 * 2) 이전 이름과 다른 줄에 씀
 * => 이전 이름의 남은 칸의 수를 계산하여 더해줌. 
 * => 현재 상태 dp[i][cur]는 이전 상태 dp[i-1][j]에 남은 칸의 수의 제곱인(m-j)*(m-j)을 더한 값임
 */

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 이름의 개수
		int m = Integer.parseInt(st.nextToken()); // 노트의 가로 길이
		
		int[][] dp = new int[n][m+1];
		for(int i=0; i<n; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
		
		int first = Integer.parseInt(br.readLine()); // 첫 번째 글자의 길이
		dp[0][first] = 0;
		
		for(int i=1; i<n; i++) {
			int cur = Integer.parseInt(br.readLine()); // 현재 보는 글자의 길이
			
			for(int j=1; j<=m; j++) { // j : 이전 이름에서 적힌 글자의 수
				if(dp[i-1][j] == Integer.MAX_VALUE) continue;
				
				// 같은 줄에 이름 씀
				if(j+1+cur <= m) { // 이름 사이에는 1칸 띄워야(+1)
					dp[i][j+1+cur] = Math.min(dp[i-1][j], dp[i][j+1+cur]);
				}
				
				// 다음 줄에 이름 씀
				dp[i][cur] = Math.min(dp[i-1][j] + (m-j)*(m-j), dp[i][cur]);
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(int i=1; i<=m; i++) {
			min = Math.min(dp[n-1][i], min);
		}
		
		System.out.println(min);
	}
}