import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	
	static int n; // 우리의 개수
	static int max; // 각 우리의 최대 햄스터 수
	static int m; // 기록의 수
	static int[] arr; // 각 우리별 햄스터의 개수
	static int[][] record; // 기록들(시작, 끝, 햄스터 수 합)
	static int maxSum;
	static int[] result; // 정답
	
	static void perm(int idx) { // 현재 idx 우리까지 햄스터 수 결정됨
		if(idx >= n) {
			
			for(int i=0; i<m; i++) {
				int l = record[i][0];
				int m = record[i][1];
				int s = record[i][2];
				
				int sum = 0; // 햄스터 수의 합
				for(int j=l; j<=m; j++) {
					sum+= arr[j];
				}
				
				if(sum != s) return;
			}
			
			int total = 0;
			for(int i=0; i<n; i++) {
				total += arr[i];
			}
			
			if(total > maxSum) {
				maxSum = total;
				result = Arrays.copyOf(arr, n);
			}
			
			return;
		}
		
		for(int i=0; i<=max; i++) {
			arr[idx] = i;
			perm(idx+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	   
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for(int t=1; t<=tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 우리의 개수
			max = Integer.parseInt(st.nextToken()); // 각 우리의 최대 햄스터 수
			m = Integer.parseInt(st.nextToken()); // 기록의 수
			maxSum = -1;
		   
			arr = new int[n]; // 각 우리별 햄스터의 개수 (임시)
			result = new int[n]; // 각 우리별 햄스터의 개수 (최종)
			record = new int[m][3]; // 기록들(시작, 끝, 햄스터 수 합)
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				record[i][0] = Integer.parseInt(st.nextToken())-1; // 시작 우리
				record[i][1] = Integer.parseInt(st.nextToken())-1; // 끝 우리
				record[i][2] = Integer.parseInt(st.nextToken()); // 햄스터 개수 합
			}
		   
			sb.append("#" + t + " ");
			
			perm(0);

			if(maxSum >= 0) {
				for(int i=0; i<n; i++) {
					sb.append(result[i] + " ");
				}									
			} else {
				sb.append(-1);					
			}
			
			sb.append("\n");
		}
	   
		System.out.println(sb);
	}
}