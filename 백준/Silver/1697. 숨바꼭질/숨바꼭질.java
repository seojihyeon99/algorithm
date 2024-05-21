import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 수빈이의 위치
		int m = Integer.parseInt(st.nextToken()); // 동생의 위치
		
		boolean[] visited = new boolean[100001]; // 해당 좌표 방문 여부
		
		int time = 0; // 동생을 만난 가장 빠른 시간
		
		Queue<int[]> queue = new ArrayDeque<>(); // 수빈이의 위치, 시간
		queue.offer(new int[] {n,0});
		visited[n] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0];
			int t = cur[1];
			
			// 동생을 만났다면 
			if(x == m) {
				time = t;
				break;
			}
			
			// 한 칸 앞으로 걷는 경우
			int nextx = x-1;
			if(nextx >= 0 && !visited[nextx]) {
				queue.offer(new int[] {nextx, t+1}); // 1초 증가
				visited[nextx] = true;
			}
			
			// 한 칸 뒤로 걷는 경우
			nextx = x+1;
			if(nextx<=100000 && !visited[nextx]) {
				queue.offer(new int[] {nextx, t+1}); // 1초 증가
				visited[nextx] = true;
			}
			
			// 순간 이동 하는 경우
			nextx = 2*x;
			if(nextx<=100000 && !visited[nextx]) {
				queue.offer(new int[] {nextx, t+1}); // 1초 증가
				visited[nextx] = true;
			}			
		}
		
		System.out.println(time);
	}
}