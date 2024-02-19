import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] nums = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[2][n];
		
		dp[0][0] = 0;
		dp[1][0] = 1;
		for(int i=1; i<n; i++) {
			// 포함 x
			dp[0][i] = Math.max(dp[0][i-1], dp[1][i-1]);
			// 포함 o
			int max = 0;
			for(int j=i-1; j>=0; j--) {
				if(nums[j] < nums[i]) {
					max = Math.max(max, dp[1][j]);
				}
			}
//			System.out.println("i = " + i + ", max = " + max);
			dp[1][i] = max + 1;
		}
		
//		for(int r=0; r<2; r++) {
//			for(int c=0; c<n; c++) {
//				System.out.print(dp[r][c] + " ");
//			}
//			System.out.println();
//		}
		
		int max = 0;
		for(int i=0; i<n; i++) {
			max = Math.max(Math.max(max, dp[0][i]), dp[1][i]);
		}
		
		System.out.println(max);
	}
}