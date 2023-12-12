import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *	문제 조건에서 점수  <= 20억이므로, int형 표현 가능
 *	10<= 랭킹 리스트 크기 <=50이므로, 하나씩 모두 비교해도 시간복잡도 내에 가능
 *  
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int nlist = Integer.parseInt(st.nextToken()); // 현재 리스트에 있는 점수 개수(비오름차순)
		int score = Integer.parseInt(st.nextToken()); // 새로운 점수
		int nrank = Integer.parseInt(st.nextToken()); // 랭킹 리스트 크기
		
		if(nlist > 0 ) { // 현재 리스트에 점수 있으면
			st = new StringTokenizer(br.readLine());
			int sum = 0; // 새로운 점수보다 크거나 같은 점수의 개수
			int same = 0; // 같은 점수의 개수
            
			for(int i=0; i < nlist; i++) {
				int cur = Integer.parseInt(st.nextToken());  // 현재 비교하는 점수
				if(score < cur) { // 새로운 점수보다 크다면
					sum++;
				}
				else if(score == cur) { // 새로운 점수와 같다면
					same++;
				}
				else { // 새로운 점수보다 작다면
					break;
				}
			}
            
			if(sum + same < nrank) { // 랭킹 리스트 크기 넘어가지 않았으면
				System.out.println(sum + 1);
			}
			else // 랭킹 리스트 크기 넘어갔으면
				System.out.println(-1);
		}
		else { // 현재 리스트에 점수 없으면
			System.out.println(1);
		}
	}
}
