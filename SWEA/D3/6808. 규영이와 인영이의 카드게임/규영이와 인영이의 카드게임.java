import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static boolean[] card; // 전체 카드(1~18)중 규영 선택 여부
	static int[] player1 = new int[9]; // 규영이가 가지고 있는 카드
	static int[] player2 = new int[9]; // 인영이가 가지고 있는 카드
	static int[] numbers = new int[9]; // 인영이가 뽑은 카드 차례대로 저장
	static boolean[] selected; // 인영이가 순열에서 카드 사용 여부
	static int player1win;
	static int player1loose;
	
	// 신영이가 가진 카드의 순열을 구하는 함수
	static void perm(int count) {
		if(count==9) { // 9개를 다 뽑았다면 종료
			int win = 0; // 규영이가 이김
			int loose = 0; // 신영이가 이김
			for(int i=0; i<9; i++) {
				if(player1[i]>numbers[i]) { // 규영이가 이김
					win += (player1[i] + numbers[i]);
				}
				else if(player1[i]<numbers[i]) { // 신영이가 이김
					loose += (player1[i] + numbers[i]);
				}
			}
			if(win>loose) player1win++;
			else if(win<loose) player1loose++;
			
			return;
		}
		
		for(int i=0; i<9; i++) {
			if(selected[i]) continue; // 이미 골랐으면 -> 다음 카드로
			
			numbers[count] = player2[i]; // 고르지 않았다면 -> 카드 저장함
			selected[i] = true; // 카드 사용했다고 체크
			perm(count+1); // 다음 자릿수의 순열을 뽑으러~
			selected[i] = false;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		
		for(int t=1; t<=tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 다음 반복을 위한 초기화들..
			card = new boolean[18];
			selected = new boolean[9];
			player1win = 0;
			player1loose = 0;
			
			// 규영이가 받은 카드 배열에 입력받기
			for(int i=0; i<9; i++) {
				player1[i] = Integer.parseInt(st.nextToken());
				card[player1[i]-1] =true; // 규영이가 선택
			}
			
			// 인영이가 가진 카드 구함
			int idx = 0; // 배열에서 저장할 인덱스
			for(int i=0; i<18; i++) { // 전체 카드에서 규영이가 받은거(9장) 제외한 카드(9장)를 다 가짐
				if(!card[i]) { // 규영이가 선택하지 않았으면
					player2[idx++] = i+1; // 인영이가 가짐
				}
			}
			
			perm(0);
			
			sb.append("#" + t + " " + player1win + " " + player1loose + "\n");
		}
		
		System.out.println(sb.toString());
	}
}
