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
		int n = Integer.parseInt(st.nextToken()); // 사다리의 수
		int m = Integer.parseInt(st.nextToken()); // 뱀의 수
		
		// 보드판 (10 x 10)
		int[] arr = new int[101];
		// 사다리 정보 입력 받기
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			arr[s] = e;
		}
		// 뱀 정보 입력 받기
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			arr[s] = e;			
		}
		
		// 게임 시작
		System.out.println(bfs(arr));
	}
	
	static int bfs(int[] arr) {
		Queue<int[]> queue = new ArrayDeque<>(); // 현재 칸, 이동횟수
		boolean[] visited = new boolean[101]; // 방문여부
		
		// 처음 시작 좌표 넣고, 방문처리
		queue.offer(new int[] {1,0});
		visited[1] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			// 도착점(100번 칸)이라면
			if(cur[0] == 100) return cur[1];
			
			// 나올 수 있는 주사위 1~6
			for(int i=1; i<=6; i++) {
				int next = cur[0] + i; // 그 다음 주사위 좌표
				
				// 도착점(100번 칸)을 넘지않고, 아직 미방문 했다면
				if(next <= 100 && !visited[next]) {
					// 해당 칸에 사다리 또는 뱀이 존재한다면
					if(arr[next] != 0) {
						visited[next] = true; // 해당 칸 방문 처리
						visited[arr[next]] = true; // 사다리 또는 뱀 타고 간 칸 방문 처리
						queue.offer(new int[] {arr[next], cur[1] + 1}); // 이동 횟수 1 증가	
					}
					//
					else {
						visited[next] = true;
						queue.offer(new int[] {next, cur[1] + 1}); // 이동 횟수 1 증가
					}
				}
			}
		}
		
		return 0;
	}
}