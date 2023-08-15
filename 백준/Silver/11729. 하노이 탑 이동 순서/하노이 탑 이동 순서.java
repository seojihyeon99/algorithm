import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int n;
	static int count;
	static StringBuilder sb = new StringBuilder();
	// 옮긴 횟수를 구하는 함수
	static void move(int size, int start, int arrive) {
		int mid = 6 - (start + arrive);
		
		// size가 2이상 이라면
		if(size >= 2) {
			move(size-1, start, mid); 	// 맨 아래 원판을 제외한 묶음을 시작->중간 장대로 옮김
			move(1, start, arrive);		// 맨 아래 원판을 시작->도착 장대로 옮김
			move(size-1, mid, arrive); 	// 맨 아래 원판을 제외한 묶음을 중간->도착 장대로 옮김
		}		
		// size가 1이라면
		else if(size == 1) {
			count++; // 딱 1번 start->arrive로 이동함
			return;
		}
	}
	
	// 수행 과정을 출력하는 함수
	static void printmove(int size, int start, int arrive) {
		int mid = 6 - (start + arrive);
		
		// size가 2이상 이라면
		if(size >= 2) {
			printmove(size-1, start, mid); 			// 맨 아래 원판을 제외한 묶음을 시작->중간 장대로 옮김
			sb.append(start + " " + arrive + "\n");	// 맨 아래 원판을 시작->도착 장대로 옮김
			printmove(size-1, mid, arrive); 		// 맨 아래 원판을 제외한 묶음을 중간->도착 장대로 옮김
		}		
		// size가 1이라면
		else if(size == 1) {
			sb.append(start + " " + arrive + "\n");
		}
	
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 총 원판의 개수 n
		n = Integer.parseInt(br.readLine());

		// 옮긴 횟수 k 출력
		move(n, 1, 3);
		System.out.println(count);
		
		// 수행 과정 출력(a, b => a번째 원판을 b번째 원판으로)
		printmove(n, 1, 3);
		System.out.println(sb.toString());
	}

}
