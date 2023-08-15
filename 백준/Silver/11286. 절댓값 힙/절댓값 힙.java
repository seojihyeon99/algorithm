import java.util.PriorityQueue;
import java.util.Scanner;

// 힙에 넣는 원소
class Node implements Comparable<Node>{
	private int num; // 원래값
	private int abs; // 절대값
	
	public Node(int num, int abs) {
		this.num = num;
		this.abs = abs;
	}
	
	public int getNum() {
		return num;
	}
	
	@Override
	public int compareTo(Node o) {
		 // 절대값이 같은 경우
		if(this.abs == o.abs) {
			return this.num - o.num; // 원래값 작은 원소부터(오름차순?)
		}
		// 절대값이 다른 경우
		return this.abs - o.abs; // 절대값 작은 원소부터(오름차순?)
	}
}

public class Main {
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		PriorityQueue<Node> heap = new PriorityQueue<>(); // 원소가 Node인 힙 구성
		
		int n = sc.nextInt(); // 연산의 개수
		
		// 연산의 개수만큼 반복
		for(int i=0; i<n; i++) {
			int num = sc.nextInt();
			// 연산이 0이 아니라면 -> 힙에 추가
			if(num != 0) {
				heap.offer(new Node(num, Math.abs(num)));
				continue;
			}
			// 연산이 0이라면 -> 힙에서 꺼냄
			// 1) 힙이 비어있음 -> 0 출력
			// 2) 힙이 비어있지 않음 -> 힙의 루트 꺼냄(절댓값이 가장 작은, 만약 절대값 같다면 원래값도 작음)
			else{
				if(heap.isEmpty()) {
					sb.append("0\n"); // 힙이 비어있는데 출력하려하면, 0 출력
				}	
				else {
					sb.append(heap.poll().getNum() + "\n");	// 힙 비어있지 않으면, 힙의 루트 하나 꺼내서 출력		
				}
			}
		}

		System.out.println(sb.toString());
	}
}	
