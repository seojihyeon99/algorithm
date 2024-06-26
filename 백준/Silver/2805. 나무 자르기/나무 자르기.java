import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 나무의 수
		int m = Integer.parseInt(st.nextToken()); // 집에 가져갈 나무의 길이
		
		int[] tree = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int right = 1_000_000_000;
		int height = (left + right)/2; // 현재 절단기 높이
		
		while(left <= right) {
			int mid = (left + right)/2; // 현재 절단기 높이
			
			long sum = 0; // 현재 절단기 높이일 때, 잘린 높이들의 합
			// 절단기로 나무들을 자름
			for(int i=0; i<n; i++) {
				int cut = (tree[i] - mid) > 0 ? tree[i] - mid : 0; // '나무 높이 > 절단기'이면 잘림
				sum += cut;	
				
				if(sum >= m) break;
			}
			
			if(sum >= m) {
				left = mid+1;
				height = mid;
			}
			else {
				right = mid-1;
			}
			
		}
		
		System.out.println(height);
		
	}
}