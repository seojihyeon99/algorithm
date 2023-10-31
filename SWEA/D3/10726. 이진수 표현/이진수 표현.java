import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		// 테스트 케이스 수만큼 반복
		for(int t=1; t<=tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int tmp = (1 << n) - 1;
			if((m & tmp) == tmp) {
				sb.append("#" + t + " " + "ON\n"); // m의 이진수 표현의 마지막 n비트가 모두 1임
			}
			else {
				sb.append("#" + t + " " + "OFF\n"); // m의 이진수 표현의 마지막 n비트가 모두 1이 아님
			}
		}
		System.out.println(sb);
	}
}
