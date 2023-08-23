import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static char[] letter;
	static boolean[] used;

	static int l; // 몇자리 암호인지
	static int c; // 후보 문자 몇개인지
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken()); // 몇자리 암호인지
		c = Integer.parseInt(st.nextToken()); // 후보 문자 몇개인지
		
		letter = new char[c];
		used = new boolean[c];
	
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<c; i++) {
			letter[i] = st.nextToken().charAt(0);
			
		}

		Arrays.sort(letter); // 사전순 정렬
		
		comb(0, 0, 0);
		
		System.out.println(sb.toString());
	}
	
	static void comb(int start, int count, int vowelcnt) { // count: 현재 뽑힌 문자의 개수 , vowelcnt: 현재까지 뽑힌 자음의 개수
		if(count == l) {
			if(vowelcnt>=1 && l-vowelcnt>=2) {
				for(int i=0; i<c; i++) {
					if(used[i]) {
						sb.append(letter[i]);
					}
				}
				sb.append("\n");
			}
			return;
		}
		for(int i=start; i<c; i++) {
			used[i] = true;
			if(letter[i] == 'a'||letter[i] == 'e'||letter[i] == 'i'||letter[i] == 'o'||letter[i] == 'u') {
				comb(i+1, count+1, vowelcnt+1);
			}
			else {
				comb(i+1, count+1, vowelcnt);					
			}
			used[i] = false;
		}
	}
		
}