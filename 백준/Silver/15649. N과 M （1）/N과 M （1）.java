import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static boolean[] visited; // 방문했는지 여부 체크
	static int[] num; // 출력할 숫자
	static int n;	// 1 ~ n까지의 숫자
	static int m;	// 그 중 몇개 뽑을지
	public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			
			n = sc.nextInt();
			m = sc.nextInt();
			
			visited = new boolean[n];
			num = new int[m];
			
			perm(0);
	}
	
	public static void perm(int digit) {
		// 종료 조건(digit가 m과 같아지면!! 인덱스 0부터 시작하므로 0 ~ m-1까지임)
		if(digit == m) {
			System.out.println(print());
			return;
		}
		
		
		for(int i=0; i<n; i++) {
			if(visited[i] == false) {
				visited[i] = true;
				num[digit] = (i+1);
				perm(digit+1);
				visited[i] = false;
			}
		}
	}
	
	public static String print() {
		String s = "";
		for(int i=0; i<num.length; i++) {
			s+=num[i] + " ";
		}
		return s;
	}
}
