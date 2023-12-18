import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * dp의 답 => dp[현재 몇번째 계단인지][그 전까지 1이 연속한 횟수] (0이면 답이 될 수 없는 결과)
 * 처음에 계단이 1일때 계속 ArrayIndexOutOfBounds가 났음.. => n>=2로 조건 분기해줘야!
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 계단의 개수
		int[] score = new int[n+1]; // 해당 번째 계단의 점수
		// 계단의 점수 입력받기
		for(int i=1; i<=n; i++) {
			score[i] = Integer.parseInt(br.readLine());
		}
		
		int[][] dp = new int[n+1][3];
		dp[0][0] = 0;
		dp[0][1] = 0;
		dp[0][2] = 0;
		dp[1][0] = 0;
		dp[1][1] = score[1];
		dp[1][2] = 0;
		
		if(n >= 2) {
			dp[2][0] = score[2];
			dp[2][1] = 0;
			dp[2][2] = score[1]+score[2];
			
			for(int i=3; i<=n; i++) {
				dp[i][0] = score[i] + Math.max(Math.max(dp[i-2][0], dp[i-2][1]), dp[i-2][2]);
				dp[i][1] = score[i] + dp[i-1][0];
				dp[i][2] = 0; // 뒤의 3자리가 +2 +1 +1이면  -> 계단 3개 연속해서 밟은것이므로, 답이될 수 x
			}			
		}
		
		System.out.println(Math.max(Math.max(dp[n][0], dp[n][1]), dp[n][2]));
	}
}
