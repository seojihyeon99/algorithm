import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int n; // 고객의 수
	
	static int[] company;
	static int[] house;
	
	static int[][] arr; // 고객들의 좌표 저장
	static int min; // 최소이동거리
	
	static boolean[] selected;
	
	static void perm(int prevx, int prevy, int count, int sum) { // count : 현재 방문한 고객들 수, sum : 지금까지의 총 이동거리 합
		if(count == n) {
			sum += Math.abs(prevx-house[0]) + Math.abs(prevy-house[1]);
			if(sum < min) {
				min = sum;
			}
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(selected[i]) continue;
			selected[i] = true;
			perm(arr[i][0], arr[i][1], count+1, sum + Math.abs(prevx-arr[i][0]) + Math.abs(prevy-arr[i][1]));
			selected[i] = false;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=tc; t++) {
			min = Integer.MAX_VALUE; // 최소이동거리
			
			n = Integer.parseInt(br.readLine());
			
			selected = new boolean[n];
			
			arr = new int[n][2];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			company = new int[2];
			company[0] = Integer.parseInt(st.nextToken());
			company[1] = Integer.parseInt(st.nextToken());
			
			house = new int[2];
			house[0] = Integer.parseInt(st.nextToken());
			house[1] = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<n; i++) {
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			perm(company[0], company[1], 0, 0);
			
			sb.append("#" + t + " " + min + "\n"); 
		}
		
		System.out.println(sb.toString());
	}
}
