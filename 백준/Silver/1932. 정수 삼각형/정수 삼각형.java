import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 삼각형의 크기
		int[] dp = new int[n+2];
		
		// 처음 행의 숫자 입력받기
		dp[1] = Integer.parseInt(br.readLine());
		
		// 두번째 행 ~ n번째 행 dp 구하기
		for(int r=2; r<=n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] temp = new int[n+2]; // 현재 임시 dp
			for(int c=1; c<=r; c++) {
				temp[c] = Math.max(dp[c], dp[c-1]) + Integer.parseInt(st.nextToken());
			}
			dp = temp;
		}
		
		int max = 0;
		for(int c=1; c<=n; c++) {
			max = Math.max(max, dp[c]);
		}
		System.out.println(max);
	}
	
	
}