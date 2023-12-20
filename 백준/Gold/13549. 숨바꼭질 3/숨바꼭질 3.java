import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()); // 수빈
		int end = Integer.parseInt(st.nextToken()); // 동생
		
		int[] pos = new int[100001]; // 해당 위치에 도착하는 가장 빠른 시간
		Arrays.fill(pos, Integer.MAX_VALUE); // 초기에 정수 최댓값으로 초기화
		
		Queue<int[]> queue = new ArrayDeque<>(); // (현재 위치, 현재 시간) 저장 
		queue.offer(new int[] {start, 0});
		pos[start] = 0; // 초기 위치의 시간 0으로 설정
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			int pos1 = cur[0]-1; // X-1의 위치(1초후 이동)
			if(pos1 >= 0 && cur[1]+1 < pos[pos1]) {
				pos[pos1] = cur[1]+1;
				queue.offer(new int[] {pos1, cur[1]+1});
			}
			
			int pos2 = cur[0]+1; // X+1의 위치(1초후 이동)
			if(pos2 <= 100000 && cur[1]+1 < pos[pos2]) {
				pos[pos2] = cur[1]+1;
				queue.offer(new int[] {pos2, cur[1]+1});
			}
			
			int pos3 = cur[0]*2; // 2*X의 위치(0초후 이동)
			if(pos3 <= 100000 && cur[1] < pos[pos3]) {
				pos[pos3] = cur[1];
				queue.offer(new int[] {pos3, cur[1]});
			}
			
//			if(pos[end] != Integer.MAX_VALUE) break;
		}
		
		System.out.println(pos[end]);
	}
}
