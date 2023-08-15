import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		Queue<Integer> queue = new ArrayDeque<>(); // 큐 생성
		
		// 입력받은 정수만큼 반복
		for(int i=1; i<=n; i++) {
			queue.offer(i);
		}
		
		while(true) {
			// 큐의 크기가 1이 되면 해당 카드 번호를 출력하고 종료
			if(queue.size() <= 1) {
				System.out.println(queue.peek());
				break;
			}
			
			queue.poll(); // 맨 위의꺼를 꺼내어 버림(큐에서는 맨 앞)
			queue.offer(queue.poll()); // 그 다음 위의꺼(큐에서는 그 다음 앞)를 꺼내어 뒤로 넣음
		}
	}
}
