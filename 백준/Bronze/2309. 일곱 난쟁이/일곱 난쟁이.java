import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static boolean[] selected = new boolean[9];
	static int[] height = new int[9];
	static List<Integer> result;
	
	static void comb(int start, int count) {
		// 종료조건
		if(count== 7) {
			int sum = 0;
			for(int i=0; i<9; i++) {
				if(selected[i]) {
					sum += height[i];
				}
			}
			if(sum == 100) {
				result = new ArrayList<>();
				for(int i=0; i<9; i++) {
					if(selected[i]) {
						result.add(height[i]);
					}
				}
			}
			return;
		}
		
		for(int i=start; i<9; i++) {
			selected[i] = true;
			comb(i+1, count+1);
			selected[i] = false;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<9; i++) {
			height[i] = sc.nextInt();
		}
		Arrays.sort(height); // 오름차순으로 출력해야하므로 정렬
		
		comb(0,0);
		
		for(int i=0; i<7; i++) {
			System.out.println(result.get(i));
		}
		
 	}
}
