import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited = new boolean[100001]; // 해당 위치 방문했는지 체크
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 수빈이의 위치
		int k = Integer.parseInt(st.nextToken()); // 동생의 위치
		
		int time = 0; // 동생 찾기까지 걸린 시간
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {n, 0}); // 수빈이의 초기 위치를 넣어줌
		visited[n] = true; // 수빈이의 초기 위치 방문체크
		
		// 큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			int[] cur = queue.poll(); // 큐에서 하나 꺼내서
			
			int curn = cur[0]; // 현재 수빈이의 위치
			int curtime = cur[1]; // 현재까지 걸린 시간

			// 동생의 위치라면
			if(curn == k) {
				time = curtime; // time 업데이트해주고 break
				break;
			}
			
			// 동생의 위치 아니라면
			curtime++; // 현재까지 걸린 시간 1 증가시키고
				
			if(curn-1>=0 && !visited[curn-1]) {
				queue.offer(new int[] {curn-1, curtime}); // 수빈이의 위치를 -1									
				visited[curn-1] = true; // 해당 위치 방문체크
			}
			if(curn+1<=100000 && !visited[curn+1]) {
				queue.offer(new int[] {curn+1, curtime}); // 수빈이의 위치를 +1					
				visited[curn+1] = true; // 해당 위치 방문체크
			}
			if(curn*2<=100000 && !visited[curn*2]) {
				queue.offer(new int[] {curn*2, curtime}); // 수빈이의 위치를 *2					
				visited[curn*2] = true; // 해당 위치 방문체크
			}
		}
		
		System.out.println(time);
	}
}
