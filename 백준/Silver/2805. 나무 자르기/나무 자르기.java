import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 나무의 수
		int m = Integer.parseInt(st.nextToken()); // 필요한 나무의 길이
		
		// 나무의 높이 저장
		int[] tree = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		
		int height = 0; // 절단기 높이의 최댓값(처음에 제일 최대로 설정)
		
		int start = 0;
		int end = 1_000_000_000;
		while(true) {
			int middle = (start + end)/2; // 임시 절단기 높이
			if(start == middle) {
				long sum = 0; // 잘린 나무의 길이
				middle = end; // 임시 절단기 높이 end로 설정
				for(int i=0; i<n; i++) {
					if(middle < tree[i]) { // 절단기 길이보다 나무 길이가 더 길다면 => 잘림
						sum += tree[i] - middle;
					}
				}
				if(sum == m) {
					height = end;
					break;
				}
				
				height = start;
				break;
			}

			long sum = 0; // 잘린 나무의 길이
			for(int i=0; i<n; i++) {
				if(middle < tree[i]) { // 절단기 길이보다 나무 길이가 더 길다면 => 잘림
					sum += tree[i] - middle;
				}
			}
			
			if(sum > m) { // 잘린 길이합 > 필요한 나무 길이 -> 절단기 높이를 높혀야 
				start = middle;
			}
			else if(sum < m) { // 잘린 길이합 < 필요한 나무 길이 -> 절단기 높이를 낮춰야 
				end = middle;
			}
			else { // 잘린 길이합이 필요한 나무 길이와 같으면 -> 해당 절단기 높이가 정답!
				height = middle;
				break;
			}		

		}
		
		System.out.println(height);
	}

}
