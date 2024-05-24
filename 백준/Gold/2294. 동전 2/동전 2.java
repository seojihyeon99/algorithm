import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 동전의 종류 수
        int k = Integer.parseInt(st.nextToken()); // 만들 금액

        int[] dp = new int[k+1]; // DP[금액] = 쓰인 동전의 최소 개수
        int[] coins = new int[n]; // 동전의 금액 저장

        // dp 배열 초기화 (각 동전을 1개씩만 사용했을 경우 채움) 및 동전 금액 저장
        for(int i=0; i<n; i++) {
            int amount = Integer.parseInt(br.readLine());
            coins[i] = amount;
        }

        for(int i=1; i<k+1; i++) {
            int min = Integer.MAX_VALUE;

            // 'dp[현재 금액 - (각 동전의 금액)] + 1'을 했을때의 최솟값을 구함
            for(int j=0; j<n; j++) {
                int coin = coins[j]; // 현재 코인 금액

                // 동전의 금액이 만드려는 금액과 같다면, 1개가 최소로 만들 수 있음
                if(i == coin) {
                    dp[i] = 1;
                    min = 1;
                    break;
                }

                // 해당 코인을 추가하기 전에 이전 금액에 동전이 쓰였다면
                if((i-coin) >= 0 && dp[i-coin] > 0) {
                    min = Math.min(min, dp[i-coin] + 1);
                }
            }

            // 해당 금액을 만들 수 있다면, 업데이트함
            if(min != Integer.MAX_VALUE) dp[i] = min;
        }

        if(dp[k] == 0) {
            System.out.println(-1);
        }
        else {
            System.out.println(dp[k]);
        }

    }


}