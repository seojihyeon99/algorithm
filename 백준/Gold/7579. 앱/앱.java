import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 활성화 된 앱의 수
		int m = Integer.parseInt(st.nextToken()); // 확보해야되는 메모리
		
		int[] memory = new int[n]; // 앱이 차지하는 공간
		int[] cost = new int[n]; // 앱을 실행할 때 드는 비용
		
		// 차지 공간 입력받기
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		
		int sum = 0; // 모든 앱을 실행하는데 필요한 총 비용
		// 실행 비용 입력받기
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			sum += cost[i];
		}
		
		
		int[][] dp = new int[n][sum+1]; // dp[i][j] : i번째 앱까지를 고려했을 때, 비용을 j원을 쓸대 확보 가능한 최대 공간
		// dp 배열 초기화
		for(int r=0; r<n; r++) {
			Arrays.fill(dp[r], -1);
		}
		dp[0][0] = 0;
		dp[0][cost[0]] = memory[0];	
		// 차례대로 dp 배열 채우기
		for(int r=1; r<n; r++) {
			for(int c=0; c<=sum; c++) {
				// 현재 앱을 비활성화 o
				int nonActive = Integer.MIN_VALUE;
				if(c - cost[r] >= 0 && dp[r-1][c - cost[r]] != -1) {
					nonActive = dp[r-1][c-cost[r]] + memory[r];
				}
				
				// 현재 앱을 비활성화 x
				int active = dp[r-1][c];
				
				dp[r][c] = Math.max(nonActive, active);
			}
		}
		
		int result = Integer.MAX_VALUE;
		for(int c=0; c<=sum; c++) {
			if(dp[n-1][c] >= m) { // 확보해야 되는 메모리 이상이고, 가장 작은 금액이 필요하다면
				result = c;
				break;
			}
		}
		
		System.out.println(result);
		
	}
}