import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
	static int n; // 괄호식의 길이
	
	// 괄호검사하는 함수(1-유효함. 0-유효하지 않음)
	static int checkPair(char[] arr) {
		Stack<Character> stack = new Stack<>();
		char now;
		for(int i=0; i<n; i++) {
			switch(arr[i]) {
			// 여는 괄호라면 -> 스택에 푸쉬
			case '(':
				stack.push('(');
				break;
			case '{':
				stack.push('{');
				break;
			case '[':
				stack.push('[');
				break;
			case '<':
				stack.push('<');
				break;	
			// 닫는 괄호라면 -> 스택에서 팝하고, 짝 맞는지 확인
			case ')':
				now = stack.pop();
				if(now != '(') { // 괄호의 짝이 맞지 않다면, 0-유효하지 않음
					return 0;
				}
				break;
			case '}':
				now = stack.pop();
				if(now != '{') { // 괄호의 짝이 맞지 않다면, 0-유효하지 않음
					return 0;
				}
				break;
			case ']':
				now = stack.pop();
				if(now != '[') { // 괄호의 짝이 맞지 않다면, 0-유효하지 않음
					return 0;
				}
				break;
			case '>':
				now = stack.pop();
				if(now != '<') { // 괄호의 짝이 맞지 않다면, 0-유효하지 않음
					return 0;
				}
				break;
			}
		}
		
		if(!stack.isEmpty()) { // 괄호식 모두 검사 후 스택이 비어있지 않다면 -> 0-유효하지 않음
			return 0;
		}
		
		return 1;
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스 수만큼 반복
		for(int t=1; t<=10; t++) {
			n = Integer.parseInt(br.readLine()); // 괄호식의 길이
			
			String s = br.readLine(); // 괄호수식
			
			char[] arr = s.toCharArray();
						
			sb.append("#"+t+" "+checkPair(arr)+"\n"); // 괄호검사(1-유효함. 0-유효하지 않음)
		}
		
		System.out.println(sb.toString());
		
	}
}