import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int k; // 집합 S의 크기(1~49 숫자 중 k개가 있음)
	static boolean[] ischoosed; // 집합 S의 숫자들의 선택 여부
	static StringBuilder sb = new StringBuilder();
	
	static void comb(int[] arr, int start, int count) { // start:현재 몇번째 숫자 보고있는지, count:선택된 숫자의 개수
		if(count==6) { //6개의 숫자를 다 고르면
			for(int i=0; i<k; i++) {
				if(ischoosed[i]) {
					sb.append(arr[i] + " ");
				}
			}
			sb.append("\n");
			return;
		}
		
		for(int i=start; i<k; i++) {
			ischoosed[i] = true;
			comb(arr, i+1, count+1);
			ischoosed[i] = false;
		}		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			ischoosed = new boolean[k];
			
			if(k == 0) break; // k가 0이면 반복 종료
			
			// 배열 생성 및 입력받아 초기화
			int[] arr = new int[k];
			for(int i=0; i<k; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			comb(arr,0,0);
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
}
