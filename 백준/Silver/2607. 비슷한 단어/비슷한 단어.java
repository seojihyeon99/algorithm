import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] arr = new int[26]; // 입력받은 a~z 개수 카운트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 입력으로 주어지는 단어의 개수
		// 처음 단어 입력받기
		String s = br.readLine();
		for(int i=0; i<s.length(); i++) {
			arr[s.charAt(i)-'A']++;
		}
		
		int cnt = 0; // 비슷한 단어의 개수
		// 반복문 돌면서 나머지 단어들 비교
		for(int i=1; i<n; i++) {
			s = br.readLine();
			int[] copy = Arrays.copyOf(arr, arr.length); // 비교할 배열 복사
			
			// 단어 하나씩 살펴보며, 해당 문자의 count 감소시킴
			for(int j=0; j<s.length(); j++) {
				copy[s.charAt(j)-'A']--;
			}
			
			// 해당 문자의 count 감소후 원문자와 비교
			int cnt1 = 0; // 1의 개수
			int cnt2 = 0; // -1의 개수
			boolean isResult = true;
			for(int j=0; j<26; j++) {
				if(copy[j] == 0) continue;
				else if(copy[j] == 1) cnt1++;
				else if(copy[j] == -1) cnt2++;
				else {
					isResult = false;
					break;
				}
			}
			
			if(isResult && ((cnt1 | cnt2) == 1 || (cnt1 | cnt2) == 0)) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}
