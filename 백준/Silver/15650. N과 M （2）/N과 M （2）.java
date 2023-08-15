import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int n;
	static int m;
	static int[] numbers;
	static StringBuilder sb = new StringBuilder();
	
	static void comb(int idx, int start) {
		if(idx == m) {
			for(int i=0; i<numbers.length; i++) {
				sb.append(numbers[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=start; i<=n; i++) {
			numbers[idx] = i;
			comb(idx+1, i+1); // start 아님에 주의!!!
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt(); //(1~n)까지 수 중에서
		m = sc.nextInt(); // 길이가 m인 조합을 구함
		
		numbers = new int[m];
		
		comb(0, 1);
		System.out.println(sb.toString());
	}
}
