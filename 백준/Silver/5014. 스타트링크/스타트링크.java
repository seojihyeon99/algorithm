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
		int top = Integer.parseInt(st.nextToken()); // 건물 꼭대기 층
		int start = Integer.parseInt(st.nextToken()); // 현재 있는 층
		int arrive = Integer.parseInt(st.nextToken()); // 도착해야하는 층
		int up = Integer.parseInt(st.nextToken()); // 위로 올라갈 수 있는 층
		int down = Integer.parseInt(st.nextToken()); // 아래로 내려갈 수 있는 층
		
		boolean[] visited = new boolean[top+1];
		
		String result = "use the stairs";
		
		Queue<int[]> queue = new ArrayDeque<>(); // 현재 층, 버튼 누른 횟수
		queue.offer(new int[] {start, 0});
		visited[start] = true;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			// 스타트링크에 도착했다면
			if(cur[0] == arrive) {
				result = String.valueOf(cur[1]);
				break;
			}
			
			// 위로 올라가는 버튼 누름
			// 건물 꼭대기 층보다 작고, 아직 방문하지 않았다면
			if((cur[0]+up <= top) && !visited[cur[0]+up]) {
				queue.offer(new int[] {cur[0]+up, cur[1]+1});
				visited[cur[0]+up] = true;
			}
			
			// 아래로 내려가는 버튼 누름
			if((cur[0]-down > 0) && !visited[cur[0]-down]) {
				queue.offer(new int[] {cur[0]-down, cur[1]+1});
				visited[cur[0]-down] = true;
			}
		}
		
		System.out.println(result);
	}

}