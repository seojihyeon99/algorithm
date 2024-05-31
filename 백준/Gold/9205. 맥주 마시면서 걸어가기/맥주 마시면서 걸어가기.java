import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for(int t=0; t<tc; t++) {
			Queue<int[]> store = new ArrayDeque(); // 편의점들의 좌표
			int n = Integer.parseInt(br.readLine()); // 편의점 수
			
			// 집 좌표 입력 받기
			int[] house = new int[2];
			StringTokenizer st = new StringTokenizer(br.readLine());
			house[0] = Integer.parseInt(st.nextToken());
			house[1] = Integer.parseInt(st.nextToken());
			
			// 편의점 좌표 입력 받기
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				store.offer(new int[] {x, y});
			}
			
			// 락 페스티벌 좌표 입력 받기
			int[] end = new int[2];
			st = new StringTokenizer(br.readLine());
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());
			
			sb.append(bfs(house, store, end) + "\n");
		}
		
		System.out.println(sb);
	}
	
	/*
	 * 맥주 1병당 50m 이동 가능 => 맥주 20병당 1000m 이동 가능!
	 * queue : 현재 편의점의 좌표
	 * store : 남은 편의점의 좌표
	 */
	static String bfs(int[] house, Queue<int[]> store, int[] end) {
		Queue<int[]> queue = new ArrayDeque<>(); // 현재 편의점의 좌표(x, y)

		// 시작지점(집 좌표) 큐에 넣음
		queue.offer(new int[] {house[0], house[1]});

		while(!queue.isEmpty()) {
			int[] cur = queue.poll(); // 현재 편의점의 좌표(x, y)
						
			// 50미터 이내에 락 페스티벌(end)이 있다면 도착 가능
			if(Math.abs(end[0] - cur[0]) + Math.abs(end[1] - cur[1]) <= 1000) {
				return "happy";
			}
	
			int len = store.size();
			for(int i=0; i<len; i++) {
				int[] next = store.poll(); // 남은 편의점

				// 50미터 이내에 남은 편의점이 있다면
				if(Math.abs(cur[0] - next[0]) + Math.abs(cur[1] - next[1]) <= 1000) {
					queue.offer(new int[] {next[0], next[1]}); // 이동 (현재 편의점의 좌표로 넣음)
				}
				// 50미터 이내에 편의점이 없다면
				else {
					store.offer(new int[] {next[0], next[1]}); // 이동 불가 (남은 편의점의 좌표로 넣음)
				}
			}
			
		}
		
		return "sad";
	}
	
}