import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String original = br.readLine(); // 원본 문자열
		String changed = br.readLine(); // 바꾼 문자열
		int length = original.length(); // 원본 문자열 길이
		
		int result = 0;
		Queue<String> queue = new ArrayDeque<>();
		queue.offer(changed);
		while(!queue.isEmpty()) {
			String cur = queue.poll();
			
			// 마지막 문자 비교
			switch(cur.charAt(cur.length()-1)) {
			case 'A':
				// A-1) A를 뗄 수 있음
				if(cur.length()-1 >= length) {
					String next = cur.substring(0, cur.length()-1);
					queue.offer(next);
					if(next.equals(original)) {
						result = 1;
						break;
					}
				}
				// A-2) 뒤집고 맨뒤가 'B'여야함!! => B를 뗄 수 있음
				String str = reverse(cur); // 뒤집고
				if(str.charAt(str.length()-1) == 'B' && str.length()-1 >= length) {
					String next = str.substring(0, str.length()-1);
					queue.offer(next);
					if(next.equals(original)) result = 1;
				}
				break;
			case 'B':
				// B-1) 뒤집고 맨뒤가 'B'여야함!! => B를 뗄 수 있음
				str = reverse(cur); // 뒤집고
				if(str.charAt(str.length()-1) == 'B' && str.length()-1 >= length) {
					String next = str.substring(0, str.length()-1);
					queue.offer(next);
					if(next.equals(original)) result = 1;
				}
				break;
			}
			
			if(result == 1) break;
		}
		
		System.out.println(result);
		
	}
	
	static String reverse(String s) {
		StringBuffer sb = new StringBuffer(s); // 기존 문자열
		String str = sb.reverse().toString(); // 뒤집은 문자열
		return str;
	}
}
