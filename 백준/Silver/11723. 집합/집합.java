import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] arr = new int[21];
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
					for(int j=1; j<=20; j++) {
						arr[j] = 1;
					}
					break;
				case "empty":
					for(int j=1; j<=20; j++) {
						arr[j] = 0;
					}
					break;
				}
			}
			else {
				int n = Integer.parseInt(st.nextToken()); // 연산자 뒤 숫자
				switch(op) {
				case "add":
					arr[n] = 1;
					break;
				case "remove":
					arr[n] = 0;
					break;
				case "check": // check 연산이 주어질때마다, 결과를 출력
					sb.append(arr[n] + "\n");
					break;
				case "toggle":
					arr[n] = arr[n]^1; // XOR 연산(0->1, 1->0)
					break;
				}		
			}
		}
		
		System.out.println(sb);
	}
}
