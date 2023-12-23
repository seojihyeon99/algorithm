import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * dp[i] : vip를 제외한 이어지는 좌석 크기가 i일때의 경우의 수 
 * dp[1] = 1, dp[2] = 2, 
 * dp[i] = dp[i-1] + dp[i-2] (그전꺼 뒤에 i붙힌 것 + 그전전꺼 뒤에 i, i-1 순서로 붙힌 것)
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 좌석의 수(n)
		int m = Integer.parseInt(br.readLine()); // 고정석의 개수(m)
		// 고정석 입력받기
		Queue<Integer> queue = new ArrayDeque<>();
		int size = 0;
		for(int i=0; i<m; i++) {
			queue.offer(Integer.parseInt(br.readLine()));
			size++;
		}
		
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;
		for(int i=2; i<=n; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		if(n == 1) System.out.println(1);
		else {
			int sum = 1; // 총 경우의 수
			int left = 1;
			int right = 0;
			for(int i=0; i<size; i++) {
				int cur = queue.poll();
				right = cur;
				if(left != right)
					sum = sum * (dp[right - left]);
				left = right + 1;
			}
			if(left < n) sum = sum * (dp[n - left + 1]);
			System.out.println(sum);
		}		
		
	}
}
