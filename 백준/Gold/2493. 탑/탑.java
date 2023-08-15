import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine()); // 탑들의 개수
		Stack<int[]> stack = new Stack<>();
		
		// 탑들의 높이를 저장하는 배열 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			int num = Integer.parseInt(st.nextToken());
			while(true) {
				if(stack.empty()) {
					sb.append(0 + " ");
					stack.push(new int[]{num, i});
					break;
				}
				else {
					if(stack.peek()[0] > num) {
						sb.append(stack.peek()[1] + " ");
						stack.push(new int[] {num, i});
						break;
					}
					stack.pop();
				}		
			}
			
		}
		
		System.out.println(sb.toString());
	}
}
