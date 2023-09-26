import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for(int t=1; t<=tc; t++) {
			int n = Integer.parseInt(br.readLine()); // 수열의 길이
			
			// 수열 저장 배열 생성 및 입력받기
			int[] arr = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			
			int[] lis = new int[n];
			int max = Integer.MIN_VALUE;
			for(int i=0; i<n; i++) {
				lis[i] = 1;
				for(int j=0; j<i; j++) {
					if(arr[j]< arr[i] && lis[i] < lis[j]+1) {
						lis[i] = lis[j] + 1;
						if(max < lis[i]) max = lis[i];
					}
				}
			}
			
			sb.append("#" + t + " " + max + "\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
}
