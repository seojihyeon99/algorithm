import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 8방향 벡터
		int[] dx = new int[] {-2,-1,1,2,-2,-1,1,2};
		int[] dy = new int[] {-1,-2,-2,-1,1,2,2,1};
		
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for(int i=0; i<tc; i++) {
			int l = Integer.parseInt(br.readLine()); // 한 변의 길이
			
			boolean[][] visited = new boolean[l][l]; // 방문 여부 저장
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 나이트의 초기 좌표
			int[] start = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			st = new StringTokenizer(br.readLine());
			// 나이트의 최종 좌표
			int[] finish = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			
			int cnt = 0; // 이동횟수
			Queue<int[]> queue = new ArrayDeque<>(); // x좌표, y좌표, 현재 시각
			queue.offer(new int[] {start[0], start[1], 0});
			visited[start[0]][start[1]] = true;
			while(!queue.isEmpty()) {
				int[] cur = queue.poll();
				
				// 나이트가 도착 지점에 도착했다면 종료
				if(cur[0] == finish[0] && cur[1] == finish[1]) {
					cnt = cur[2];
					break;
				}
				
				for(int j=0; j<8; j++) {
					int nextx = cur[0] + dx[j];
					int nexty = cur[1] + dy[j];
					
					// 배열의 인덱스 넘지 않고, 방문한 적이 없다면
					if(nextx>=0 && nextx<l && nexty>=0 && nexty<l && !visited[nextx][nexty]) {
						queue.offer(new int[] {nextx, nexty, cur[2]+1}); // 이동 횟수 1 증가
						visited[nextx][nexty] = true;
					}
				}
			}
			
			sb.append(cnt+"\n");
		}
		
		System.out.println(sb.toString());
		
	}
}