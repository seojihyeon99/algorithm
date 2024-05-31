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
			Queue<int[]> store = new ArrayDeque(); // 편의점 좌표
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
	
	static String bfs(int[] house, Queue<int[]> store, int[] end) {
		Queue<int[]> queue = new ArrayDeque<>(); // 좌표(x, y)

		// 시작지점(집 좌표) 큐에 넣음
		queue.offer(new int[] {house[0], house[1]});

		while(!queue.isEmpty()) {
			int[] pos = queue.poll(); // 현재 좌표(x, y)
			
			// 락 페스티벌에 도착했다면
			if(pos[0] == end[0] && pos[1] == end[1]) {
				return "happy";
			}
						
			// 50미터 이내에 end가 있다면 큐에 넣음
			if(Math.abs(end[0] - pos[0]) + Math.abs(end[1] - pos[1]) <= 1000) {
				queue.offer(new int[] {end[0], end[1]});
			}
	
			int len = store.size();
			for(int i=0; i<len; i++) {
				int[] cur = store.poll(); // 현재 편의점

				// 50미터 이내에 편의점이 있다면
				if(Math.abs(pos[0] - cur[0]) + Math.abs(pos[1] - cur[1]) <= 1000) {
					queue.offer(new int[] {cur[0], cur[1]}); // 좌표 넣음
				}
				// 50미터 이내에 편의점이 없다면
				else {
					store.offer(new int[] {cur[0], cur[1]}); // 해당 편의점 좌표 다시 넣음
				}
			}
			
		}
		
		return "sad";
	}
	
}