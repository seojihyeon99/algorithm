import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 고도가 바뀌는 지점의 수
		
		int cnt = 0; // 최소 건물의 수
		boolean[] exist = new boolean[500001]; // 해당 높이의 건물 존재 여부
		exist[0] = true; // 0이란 높이의 건물은 존재할 수 없음
		Stack<Integer> stack = new Stack<>();

		// 고도가 바뀌는 지점의 좌표 입력 받음
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			st.nextToken(); // 바뀐 고도의 x좌표
			int cury = Integer.parseInt(st.nextToken()); // 바뀐 고도의 y좌표
			
			// 스택이 비어있을 경우
			if(stack.isEmpty() && cury != 0) {
				stack.push(cury);
				exist[cury] = true;
				continue;
			}
			
			// 스택이 비어있지 않은 경우
			while(!stack.isEmpty()) {
				// 현재 바뀐 높이 >= 최근의 바뀐 높이
				if(cury >= stack.peek()) {
					break;
				}
				
				// 현재 바뀐 높이 < 최근의 바뀐 높이
				cnt++; // 건물 개수 증가
				exist[stack.pop()] = false; // 해당 높이의 건물 존재하지 않음 처리
			}

			// 스택에 현재 바뀐 높이가 없다면 push하고, 해당 높이의 건물 존재 처리
			if(!exist[cury]) {
				stack.push(cury);
				exist[cury] = true;
			}
		}
		
		while(!stack.isEmpty()) {
			stack.pop();
			cnt++; // 건물 개수 증가
		}
		
		System.out.println(cnt);
		
	}
	
	
}