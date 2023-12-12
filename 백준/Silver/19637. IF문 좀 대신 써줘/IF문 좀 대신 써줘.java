import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * n은 최대 10^5이고  m은 최대 10^5이므로,
 * m번 반복하면서 n번 비교하면 => n*m = 10^10(100억..)로 시간초과 => 단순 하나씩 비교 x
 * 
 * 이진탐색 활용
 */
public class Main {
	static int n; // 칭호의 개수
	static int m; // 캐릭터들의 개수
	static String[] nickname; // 칭호
	static int[] power; // 각 칭호의 전투력
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 칭호의 개수
		m = Integer.parseInt(st.nextToken()); // 캐릭터들의 개수
		
		nickname = new String[n];
		power = new int[n];
		
		// 칭호수만큼 입력받기
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			nickname[i] = st.nextToken();
			power[i] = Integer.parseInt(st.nextToken());
		}
		
		// 캐릭터수만큼 비교
		for(int i=0; i<m; i++) {
			binarySearch(Integer.parseInt(br.readLine()));
		}
		
		System.out.println(sb);
	}
	
	static void binarySearch(int num) {
		int left = 0; // 범위의 왼쪽 끝 인덱스
		int right = n - 1 ; // 범위의 오른쪽 끝 인덱스
		int mid = (left + right) / 2; // 범위의 중앙 인덱스  
		
		while(true) {
			mid = (left + right) / 2; // 범위의 중앙 인덱스  
			
			if(num > power[mid]) { // 중간보다 더 큰쪽에 위치했다면
				left = mid + 1;
			} else if(num < power[mid]) { // 중간보다 더 작은쪽에 위치했다면
				right = mid - 1;
			} else { // 중간값과 같다면
				// 다시 이분탐색
				right = mid;
				if(left == mid) {
					break;
				}
				else {
					continue;
				}
			}
			
			if(left > right) { // 왼쪽 끝 인덱스 > 오른쪽 끝 인덱스라면
				mid = left;
				break;
			}
		}
		
		sb.append(nickname[mid] + "\n");
	}
}
