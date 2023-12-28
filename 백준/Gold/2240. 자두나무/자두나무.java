import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 처음에 1번 나무 아래서 시작
 * 이동횟수가 짝수 -> 1번 나무 아래 위치, 홀수 -> 2번 나무 아래 위치
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken()); // 초
		int w = Integer.parseInt(st.nextToken()); // 움직임 횟수
		int[][] dp = new int[w+1][t+1];
		
		for(int i=1; i<=t; i++) {
			int tree = Integer.parseInt(br.readLine()); // 현재 나무의 번호
			
			// 1행
			dp[0][i] = dp[0][i-1] + (tree == 1 ? 1 : 0);
			for(int j=1; j<=Math.min(w, i); j++) {
				// 이동횟수 짝수일 경우 -> 1번 나무 아래 위치
				if(j%2 == 0) {
					if(tree == 1) { // 현재 1번 나무에서 자두가 떨어지면
						dp[j][i] = Math.max(dp[j][i-1], dp[j-1][i-1]) + 1;
					} else { // 현재 2번 나무에서 자두가 떨어지면
						dp[j][i] = Math.max(dp[j][i-1], dp[j-1][i-1]);
					}
				} else { // 이동횟수 홀수일 경우 -> 2번 나무 아래에 위치
					if(tree == 1) { // 현재 1번 나무에서 자두가 떨어지면
						dp[j][i] = Math.max(dp[j][i-1], dp[j-1][i-1]);
					} else { // 현재 2번 나무에서 자두가 떨어지면
						dp[j][i] = Math.max(dp[j][i-1], dp[j-1][i-1]) + 1;
					}					
				}
			}
			
		}
		
		int max = 0;
		for(int i=0; i<=w; i++) {
			max = Math.max(max, dp[i][t]);
		}
		
		System.out.println(max);
	}
}
