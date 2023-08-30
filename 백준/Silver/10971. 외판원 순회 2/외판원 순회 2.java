import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n; // 도시의 수
	static int[][] cost; // 비용행렬
	static int[] numbers; // 숫자
	static boolean[] selected;
	static int min = Integer.MAX_VALUE;
	
	static void perm(int count) {
		// 기저조건
		if(count == n) {
			int sum = 0;
			for(int i=0; i<n; i++) {
				if(cost[numbers[i]][numbers[(i+1)%n]] == 0) return;
				sum += cost[numbers[i]][numbers[(i+1)%n]];
			}
			min = Math.min(min, sum);
			return;
		}
		
		// 순열 생성
		for(int i=0; i<n; i++) {
			if(selected[i]) continue;
			
			selected[i] = true;
			numbers[count] = i;
			perm(count+1);
			selected[i] = false;			
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		// 비용행렬 생성 및 입력받기
		cost = new int[n][n];
		for(int r=0; r<n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				cost[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		numbers = new int[n];
		selected = new boolean[n];
		
		perm(0);
		
		System.out.println(min);
	}
	
}
