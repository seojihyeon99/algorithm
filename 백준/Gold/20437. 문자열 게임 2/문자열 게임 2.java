import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine()); // 게임의 수
		for(int i=0; i<t; i++) {
			String s = br.readLine(); // 문자열
			int k = Integer.parseInt(br.readLine()); // 문자 몇개 포함하는지
			
			List<Integer>[] list = new List[26]; // 문자열에서 해당 알파벳의 위치
			for(int j=0; j<26; j++) {
				list[j] = new ArrayList<>();
			}
			
			// 해당 문자의 위치 각 알파벳 리스트에 넣어줌
			for(int j=0; j<s.length(); j++) {
				list[s.charAt(j) - 'a'].add(j);
			}
			
			int min = Integer.MAX_VALUE; // 가장 짧은 연속 문자열의 길이
			int max = Integer.MIN_VALUE; // 첫번째 글자 = 마지막 글자인 가장 긴 연속 문자열의 길이
			for(int j=0; j<26; j++) {
				if(list[j].size() < k) continue;
				
				for(int l=0; l+k-1<list[j].size(); l++) {
					int left = l;
					int right = l+k-1;
					
					min = Math.min(list[j].get(right) - list[j].get(left) + 1, min);
					max = Math.max(list[j].get(right) - list[j].get(left) + 1, max);
				}
			}			
			
			if(min != Integer.MAX_VALUE) {
				sb.append(min + " " + max + "\n");
			} else {
				sb.append("-1\n");
			}
		}
	
		System.out.println(sb);
	}
}
