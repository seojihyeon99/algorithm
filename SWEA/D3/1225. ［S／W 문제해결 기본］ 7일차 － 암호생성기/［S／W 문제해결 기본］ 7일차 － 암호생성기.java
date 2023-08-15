import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static Queue<Integer> makePassword(Queue<Integer> queue) {
		
		while(true) {
			// 1개의사이클
			for(int i=1; i<=5; i++) {
				// 맨 앞의 원소를 i 감소시킴
				int nextput = queue.poll() - i;
				// 만약 0보다 작으면 -> 0으로
				if(nextput<=0) {
					nextput = 0;
					queue.offer(nextput); // 해당 원소를 다시 큐의 마지막에 넣음
					return queue;
				}
				queue.offer(nextput); // 해당 원소를 다시 큐의 마지막에 넣음
			}
			
//			// 모든 값이 한자리수이면 암호생성 완료
//			int count=0; // 한자리수의 개수
//			Iterator<Integer> it = queue.iterator(); // 큐를 순회하는 it
//			// 큐의 전체 값을 순회하면서
//			for(int j=1; j<=8; j++) {
//				// 각 요소가 10보다 작으면
//				if(it.next() < 10) {
//					count++; // 한자리수의 개수를 1 증가
//				};
//			}
//			if(count == 8) {
//				break;
//			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스 10번 반복
		for(int i=0; i<10; i++) {
			int t = Integer.parseInt(br.readLine());
			
			Queue<Integer> queue = new ArrayDeque<Integer>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<8; j++) {
				queue.offer(Integer.parseInt(st.nextToken()));
			}
			
			queue = makePassword(queue);
			
			sb.append("#" + t + " ");
			for(int j=0; j<8; j++) {
				sb.append(queue.poll() + " ");
			}
			
			System.out.println(sb.toString());
			
			sb.setLength(0); // 다음 출력을 위해 StringBuilder 초기화
		}
	}
}
