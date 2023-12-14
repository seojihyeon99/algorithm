import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int cnt = 0; // 총 방법의 수
		int n = Integer.parseInt(br.readLine()); // 입력받는 정수의 개수
		
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(br.readLine()); // 현재 입력받은 정수
			// 최대로 3을 많이 썼을때의 개수
			int three = num/3;
			
			// 3의 개수를 1개씩 줄여감
			for(int j=three; j>=0 ; j--) {
				// 최대로 2를 많이 썼을때의 개수
				int two = (num - 3*j)/2;
				cnt += (two + 1);
			}
			
			sb.append(cnt + "\n");
			cnt = 0; // cnt 초기화
		}
		
		System.out.println(sb);
	}
}
