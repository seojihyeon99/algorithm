import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] princess = new int[25];
	static int[] numbers = new int[7]; // 선택된 학생의 인덱스
//	static int[]  = new int[7]; // 선택된 학생의 인덱스
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int r=0; r<5; r++) {
			String s = br.readLine();
			for(int c=0; c<5; c++) {
				if(s.charAt(c) == 'Y') // 임도연파(0)
					princess[r*5 + c] = 0;
				else // 이다솜파(1)
					princess[r*5 + c] = 1;
			}
		}
		
//		System.out.println(Arrays.toString(princess));
		comb(0, 0);
		
		System.out.println(total);
	}
	
	static int total;
	static void comb(int count, int start) {
		if(count == 7) { // 7명 선택 완료했다면
			// 해당 7명중에 이다솜파(1)가 4명 보다 작으면 할필요 x
			if(countPrincess() < 4) {
				return;
			}
			
			// 해당 7명중에 이다솜파(1)가 4명 이상이면
			// 이 7명이 모두 연결되어있는지 체크
			boolean[] visited = new boolean[25];
			Queue<Integer> queue = new ArrayDeque<>();
			
			int cnt = 0;

			queue.offer(numbers[0]);
			cnt++;
			visited[numbers[0]] = true;
			
			while(!queue.isEmpty()) {
				int cur = queue.poll();
				
				int top = cur - 5;
				if(top >= 0 && !visited[top] && isExist(top)) {
					queue.offer(top);
					cnt++;
					visited[top] = true;
				}
				
				int bottom = cur + 5;
				if(bottom < 25 && !visited[bottom] && isExist(bottom)) {
					queue.offer(bottom);
					cnt++;
					visited[bottom] = true;
				}
				
				int left = cur - 1;
				if(left >=0 && left%5 != 4 && !visited[left] && isExist(left)) {
					queue.offer(left);
					cnt++;
					visited[left] = true;
				}
				
				int right = cur + 1;
				if(right < 25 && right%5 != 0 && !visited[right] && isExist(right)) {
					queue.offer(right);
					cnt++;
					visited[right] = true;
				}
			}
			
			// 연결되어있다면
			if(cnt == 7) total++;
//			System.out.println(Arrays.toString(numbers));
			
//			System.out.println("현재 cnt : " + cnt);
			return;
		}
		
		for(int i=start; i<25; i++) {
			numbers[count] = i;
			comb(count+1, i+1);
		}
	}
	
	static boolean isExist(int n) {
		for(int i=0; i<7; i++) {
			if(numbers[i] == n) return true;
		}
		return false;
	}
	
	static int countPrincess() {
		int sum = 0;
		for(int i=0; i<7; i++) {
			sum += princess[numbers[i]];
		}
		return sum;
	}
}
