import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 자료형 long 이용 => 이때 캐스팅을 괄호 안에서 해줘야!!
 */
class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		
		int[] dp = new int[10001]; // dp[i] : i명일때 완벽한 악수의 수
		dp[0] = 1;
		dp[2] = 1;
		dp[4] = 2;
		
		for(int i=6; i<=n; i+=2) { // 홀수일때는 완벽한 악수 불가능!! (한 명 남음)
			// 한 사람(기준) => 나머지 사람들과 악수
			for(int j=0; j<=i-2; j+=2) {
				// 한 사람(기준)과 악수하는 사람의 왼쪽 부분
				int left = j;
				
				// 한 사람(기준)과 악수하는 사람의 오른쪽 부분
				int right = i-2-j;
				
				dp[i] += ((long)dp[left]*dp[right]) % 987654321;
				dp[i] = dp[i] % 987654321;
			}
		}
		
		System.out.println(dp[n]);
		
	}
}