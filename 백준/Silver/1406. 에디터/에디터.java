import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * 처음에 ArrayList를 이용하여 풀었는데 시간초과..
 * 생각해보니 맨뒤의 원소를 add시에는 시간복잡도 O(1)이지만, 중간에 add또는 remove하면 시간복잡도O(n)이었음..
 * 그래서 계산해보면 최악의 경우 O(500,000)*O(100,000) = 약 500억임 ㅠ
 * 
 * 어떻게 풀어야할까? 새로운 자료구조 생각!! => 커서가 맨뒤에서부터 시작!(스택 아닐까??)
 * 스택에서 삽입(push)과 삭제(pop) 연산의 시간복잡도는 O(1)임!!
 * 스택도 2개 만들어서 현재 커서를 기준으로 2개로 나뉨(현재 커서 왼쪽, 현재 커서 오른쪽)
 * 커서는 항상 왼쪽 스택의 최상단 요소+1을 가리킴(size+1)
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine(); // 문자열
		Stack<Character> st1 = new Stack<>();
		Stack<Character> st2 = new Stack<>();
		for(int i=0; i<str.length(); i++) {
			st1.push(str.charAt(i));
		}
		
		int opNum = Integer.parseInt(br.readLine()); // 명령어의 개수
		for(int i=0; i<opNum; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char op = st.nextToken().charAt(0);
			switch(op) {
			case 'L':
				if(!st1.isEmpty()) st2.push(st1.pop()); // 커서를 왼쪽으로 한 칸 옮김
				break;
			case 'D':
				if(!st2.isEmpty()) st1.push(st2.pop()); // 커서를 오른쪽으로 한 칸 옮김
				break;
			case 'B':
				if(!st1.isEmpty()) st1.pop(); // 커서의 왼쪽에 있는 문자를 삭제함
				break;
			case 'P':
				char item = st.nextToken().charAt(0);
				st1.push(item); // 문자를 커서 왼쪽에 추가함
				break;
			}
		}
		
		// 오른쪽 스택 -> 왼쪽 스택으로 합침
		while(!st2.isEmpty()) {
			st1.push(st2.pop());
		}

		// 왼쪽 스택
		while(!st1.isEmpty()) {
			sb.append(st1.pop());
		}
		
		System.out.println(sb.reverse());
		
	}
}
