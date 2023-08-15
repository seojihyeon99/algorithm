import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스 10번 반복
		for(int t=1; t<=10; t++) {
			int n = Integer.parseInt(br.readLine()); // 원본 암호문의 길이
			List<Integer> list = new LinkedList<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			int command = Integer.parseInt(br.readLine()); // 명령어 개수
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<command; i++) {
				st.nextToken(); // 앞의 I 버림
				int idx = Integer.parseInt(st.nextToken()); //삽입할 위치
				int num = Integer.parseInt(st.nextToken()); //삽입할 숫자의 개수
				for(int j=0; j<num; j++) {
					list.add(idx++, Integer.parseInt(st.nextToken()));
				}
			}
			
			// 리스트 출력
			sb.append("#" + t + " ");
			int count = 0; // 출력하는 숫자 count
			for(Integer num : list) {
				sb.append(num + " ");
				count++;
				if(count == 10) break; // count 10개만 출력
			}
			sb.append("\n");			
		}
		
		System.out.println(sb.toString());
	}
}
