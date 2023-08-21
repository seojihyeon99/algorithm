import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 이번 분기가 몇분인지
		
		Stack<int[]> stack = new Stack<>(); // 가장 최근 처리를 위해 스택(만점점수, 업무 걸리는 시간) 이용
		
		int totalscore = 0; // 총 업무 점수
		// 해당 분기가 시작(각 분마다 업무 있거나/없거나 주어짐)
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int problem = Integer.parseInt(st.nextToken()); // 업무 주어졌는지(1:주어짐, 0:안주어짐)
			if(problem == 0) { // 해당 시점에는 업무 주어지지 않음
				if(!stack.isEmpty()) { // 스택이 비어있지 않다면 -> 처리할 업무 있음
					int[] cur = stack.pop(); // 스택에서 팝
					if(cur[1]-1 == 0) { // 남은 업무가 1분짜리라면 -> 바로 끝낼수있음
						totalscore += cur[0]; // 총 업무 점수에 더함 
					}
					else { // 남은 업무가 1분짜리가아니라면 -> 이번에 1분만 진행하고 , 스택에 푸쉬해야
						stack.push(new int[] {cur[0],cur[1]-1}); // 스택에서 푸쉬					
					}
				}
			}
			else { // 해당 시점에는 업무 주어짐
				int score = Integer.parseInt(st.nextToken()); // 업무 만점 점수
				int t = Integer.parseInt(st.nextToken()); // 업무 해결에 걸리는 시간
				if(t-1 == 0) { // 받은 업무가 1분짜리라면 -> 받자마자 바로 끝낼수있음
					totalscore += score; // 총 업무 점수에 더함 
				}
				else { // 받은 업무가 1분짜리가아니라면 -> 이번에 1분만 진행하고 , 스택에 푸쉬해야
					stack.push(new int[] {score, t-1}); // 스택에 푸쉬
				}
			}
		}
		
		System.out.println(totalscore);
		
	}
}
