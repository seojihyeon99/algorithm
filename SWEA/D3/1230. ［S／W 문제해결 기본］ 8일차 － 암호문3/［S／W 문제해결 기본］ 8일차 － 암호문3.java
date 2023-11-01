import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=10; t++) {
			int n = Integer.parseInt(br.readLine()); // 원본 암호문의 개수
			List<String> list = new LinkedList<>(); // 암호문 연결리스트
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				list.add(st.nextToken());
			}
			
			int m = Integer.parseInt(br.readLine()); // 명령어의 개수
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<m; i++) {
				char op = st.nextToken().charAt(0);
				switch(op) {
				case 'I': // 삽입
					int idx = Integer.parseInt(st.nextToken()); // 삽입할 위치
					int num = Integer.parseInt(st.nextToken()); // 삽입할 개수
					for(int j=0; j<num; j++) {
						list.add(idx++, st.nextToken());
					}
					break;
				case 'D': // 삭제
					idx = Integer.parseInt(st.nextToken()); // 삭제할 위치
					num = Integer.parseInt(st.nextToken()); // 삭제할 개수
					for(int j=0; j<num; j++) {
						list.remove(idx);						
					}
					break;
				case 'A': // 추가
					num = Integer.parseInt(st.nextToken()); // 추가할 개수
					for(int j=0; j<num; j++) {
						list.add(st.nextToken());
					}
					break;
				}
				
			}
			
			sb.append("#" + t + " ");
			for(int i=0; i<10; i++) {
				sb.append(list.get(i) + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}