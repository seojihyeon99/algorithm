import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int num = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int opNum = Integer.parseInt(br.readLine()); // 연산의 수
		
		// 수행해야 하는 연산 수만큼 반복
		for(int i=0; i<opNum; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String op = st.nextToken(); // 연산 종류
			if(op.equals("all") || op.equals("empty")) {
				switch(op) {
				case "all":
					num =(int)Math.pow(2, 20) - 1; // 11...1 (1이 20개)
					break;
				case "empty":
					num = 0; // 00...0 (0이 20개)
					break;
				}
			}
			else {
				int n = Integer.parseInt(st.nextToken()); // 연산자 뒤 숫자
				switch(op) {
				case "add":
					num = num | (1 << (n - 1));
					break;
				case "remove":
					num = num & ((int)Math.pow(2, 20) - 1 ^ (1 << (n - 1))); // XOR 연산
					break;
				case "check": // check 연산이 주어질때마다, 결과를 출력
					sb.append((num >> (n - 1) & 1) + "\n");
					break;
				case "toggle":
					num = num ^ (1 << (n - 1)); // XOR 연산(0->1, 1->0)
					break;
				}		
			}
		}
		
		System.out.println(sb);
	}
}
