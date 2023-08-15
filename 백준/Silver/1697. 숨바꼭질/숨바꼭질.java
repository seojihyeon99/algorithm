import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // 수빈이의 위치
		int k = sc.nextInt(); // 동생의 위치
		boolean[] isvisited = new boolean[100001]; // 해당 좌표 방문여부 체크 -> cut하는데 사용
		
		Queue<int[]> queue = new ArrayDeque<>(); // 큐에 크기가 2인(현재 위치, 현재 시각) int[]가 담김
		queue.offer(new int[] {n, 0}); // 처음(0초일때) 수빈이의 위치를 큐에 넣어줌
		
		
		while(!queue.isEmpty()) {
			int[] curpos = queue.poll(); // 큐에서 하나 꺼냄
			// 동생 위치에 도착했다면, 종료!
			if(curpos[0] == k) {
				System.out.println(curpos[1]);
				break;
			}
			
			if(curpos[0]-1>=0 && !isvisited[curpos[0]-1]) {
				queue.offer(new int[] {curpos[0]-1, curpos[1]+1}); // x-1로 이동
				isvisited[curpos[0]-1] = true; 
			}
			if(curpos[0]+1<100001 && !isvisited[curpos[0]+1]) {
				queue.offer(new int[] {curpos[0]+1, curpos[1]+1}); // x+1로 이동
				isvisited[curpos[0]+1] = true; 
			}
			if(curpos[0]*2<100001 && !isvisited[curpos[0]*2]) {
				queue.offer(new int[] {curpos[0]*2, curpos[1]+1}); // x*2로 이동
				isvisited[curpos[0]*2] = true; 
			}
		}
		
	}
}
