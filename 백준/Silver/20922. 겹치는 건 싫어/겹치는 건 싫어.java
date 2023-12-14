import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Queue<Integer> queue = new ArrayDeque<>();
	static int[] arr = new int[100001];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] c = br.readLine().split(" ");
		int n = Integer.parseInt(c[0]); // 수열의 길이
		int k = Integer.parseInt(c[1]); // 최대로 포함할 수 있는 같은 정수의 수
		
		int max = 0; // 최장 연속 부분 수열의 길이
		// 수열을 하나씩 입력받으면서
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			int cur = Integer.parseInt(st.nextToken()); // 현재 입력받은 정수
			queue.offer(cur);
			
			if(max >= (n-i) + queue.size()) break; // 백트래킹
			
			while(true) {
				if(arr[cur] + 1 <= k) {
					arr[cur]++; // 해당 원소의 수 1 증가
					if(max < queue.size()) max = queue.size(); // max가 업데이트 가능하다면 업데이트
					break;
				}
				else {
					arr[queue.poll()]--; // 왼쪽 끝 인덱스 원소의 수 1 감소
				}
			}
		}
		
		System.out.println(max);
		
	}
}