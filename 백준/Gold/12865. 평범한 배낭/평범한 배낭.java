import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 물품의 수
		int k = Integer.parseInt(st.nextToken()); // 배낭의 수용 가능한 무게
		
		int[][] dp = new int[n+1][k+1]; // dp[i][j] : i번째 물건까지를 고려했을 때, 무게가 j인 배낭을 채우는 물건들의 가치합의 최대
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken()); // 현재 물품의 무게
			int v = Integer.parseInt(st.nextToken()); // 현재 물품의 가치
			
			for(int j=1; j<=k; j++) {
				// 1) 현재 물품을 배낭에 넣지 않는 경우
				int case1 = dp[i-1][j];
				
				// 2) 현재 물품을 배낭에 넣는 경우
				int case2 = case1;
				if(j-w >= 0) { // (배낭 무게 - 현재 물품 무게) >= 0이면, 현재 물품을 넣을 수 있는 가능성 있음
					case2 = dp[i-1][j-w] + v;					
				}
				
				dp[i][j] = Math.max(case1, case2); // 물품을 넣지 않는 경우과 물품을 넣는 경우 중 가치합이 더 큰 것
			}			
		}
		
		System.out.println(dp[n][k]);
		
	}
}