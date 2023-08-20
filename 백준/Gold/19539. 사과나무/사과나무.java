import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 서지현
 *
 *
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 사과나무의 수
		
		int cnt1 = 0; // +1물뿌리개를 반드시 사용해야되는 횟수
		int cnt2 = 0; // +2물뿌리개를 사용할수도 있는 횟수
		
		int sum = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num%2 == 1) {
				cnt1 += 1;
				cnt2 += num/2;
			}
			else {
				cnt2 += num/2;
			}
			sum += num;
		}
		if(sum%3 != 0) {
			System.out.println("NO");
		}
		else if(sum%3 == 0 && cnt1 == cnt2) {
			System.out.println("YES");
		}
		else if(sum%3 == 0 && cnt1 < cnt2) {
			while(true) {
				cnt1 += 2;
				cnt2 -= 1;
				if(cnt1 == cnt2) {
					System.out.println("YES");
					break;
				}
				else if(cnt1 > cnt2) {
					System.out.println("NO");
					break;
				}
			}
		}
		else if(sum%3 == 0 && cnt1 > cnt2){
			System.out.println("NO");
		}
		
	}
}
