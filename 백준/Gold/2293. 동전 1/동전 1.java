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
		int n = Integer.parseInt(st.nextToken()); // 동전 종류의 개수
		int k = Integer.parseInt(st.nextToken()); // 만들어야 할 동전의 가치
		
		// 동전 정보 저장
		int[] coins = new int[n+1];
		for(int i=1; i<=n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[k+1]; // 이전까지의 동전을 고려했을 때의 dp배열
		dp[0] = 1; // 아무 동전 없이 0원을 만드는 경우의 수는 1개 (아무 동전도 사용 x)
		
		// 고려하는 동전을 1개씩 늘려감
		for(int i=1; i<=n; i++) {
			int[] newDp = new int[k+1];
			int coin = coins[i]; // 현재 추가된 동전
			for(int j=0; j<=k; j++) {
				// 추가된 동전 사용 x
				newDp[j] = dp[j];
				
				// 추가된 동전 사용 o(무조건 1개는 포함)
				if(j-coin >= 0) {
					newDp[j] += newDp[j-coin];
				}
			}
			// 다시 이전까지의 동전을 고려했을 때의 dp배열에 복사
			dp = Arrays.copyOf(newDp, k+1);
		}
		
		System.out.println(dp[k]);
	}
}