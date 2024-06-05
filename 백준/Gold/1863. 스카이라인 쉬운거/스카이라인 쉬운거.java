import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 고도가 바뀌는 지점의 수
		
		int cnt = 0; // 최소 건물의 수
		boolean[] exist = new boolean[500001]; // 해당 높이의 건물 존재 여부
		Stack<Integer> stack = new Stack<>();

		// 고도가 바뀌는 지점의 좌표 입력 받음
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			st.nextToken(); // 바뀐 고도의 x좌표
			int cury = Integer.parseInt(st.nextToken()); // 바뀐 고도의 y좌표
			 
			if(stack.isEmpty()) {
				stack.push(cury);
				exist[cury] = true;
			}
			else {
				while(!stack.isEmpty() && stack.peek() > cury) {
					cnt++;
					exist[stack.pop()] = false;
				}
				
				if(!exist[cury]) {
					stack.push(cury);
					exist[cury] = true;					
				}
			}
			
		}
		
		while(!stack.isEmpty()) {
			int pop = stack.pop();
			if(pop != 0) cnt++;
		}
		
		System.out.println(cnt);
		
	}
	
	
}