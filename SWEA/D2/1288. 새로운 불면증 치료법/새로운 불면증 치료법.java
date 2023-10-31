import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		// 테스트 케이스 수만큼 반복
		for(int i=1; i<=tc; i++) {
			int n = Integer.parseInt(br.readLine()); // 현재 입력받은 n
			int cnt = 0; // 몇 번의 양을 세었는지
			int[] result = new int[10];
			while(true) {
				cnt++; // 양을 센 횟수 증가
				
				// cnt*n을 string으로 변환하여, 각 자릿수에 해당하는 숫자를 result 배열에 체크
				char[] nums = String.valueOf(n*cnt).toCharArray();
				for(char c : nums) {
					result[c - '0'] = 1;
				}
				
				// 현재 result 배열의 결과가 '1111111111'인지 비교
				int sum = 0;
				for(int j=0; j<=9; j++) sum += result[j];
				if(sum == 10) break;
			}
			sb.append("#" + i + " " + n*cnt +"\n");
		}
		
		System.out.println(sb.toString());
	}
}
