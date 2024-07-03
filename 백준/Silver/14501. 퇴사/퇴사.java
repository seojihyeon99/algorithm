import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 퇴사를 하는 날짜
		int[] dp = new int[n+1]; // 해당 날짜에 대한 최대 금액을 저장
		
		for(int i=1; i<=n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int time = Integer.parseInt(st.nextToken())-1; // 소요 일수
			int price = Integer.parseInt(st.nextToken()); // 가치
			
			if(i+time <= n) {
				dp[i+time] = Math.max(dp[i+time], dp[i-1] + price);	
			}
			
			if(i<n)
				dp[i+1] = Math.max(dp[i+1], dp[i]);
		}
		
		System.out.println(dp[n]);
	}
}