import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int round; // 몇 이닝 하는지
	static int[][] result; // 각 선수가 각 이닝에서 어떤 결과 얻는지
	static int player; // 현재 타자의 인덱스
	
	static int[] numbers = new int[9];
	static boolean[] selected = new boolean[9]; // 해당 번호의 타자가 선택됐는지
	
	static int maxScore = Integer.MIN_VALUE; // 현재 최고 점수
	
	static void perm(int count) {
		// 1번 선수는 항상 4번 타자로 정해짐!!
		if(count == 3) {
			numbers[3] = 0;
			selected[0] = true;
			perm(count+1);
		}
		// 타순 다 정해졌으면 -> 게임 시작
		else if(count == 9) {
			game();
			return;
		}
		
		for(int i=1; i<9; i++) { // 1번 선수(0)는 이미 위치 정해져있기때문에, 나머지만 선수들만 고려! 
			if(selected[i]) continue;
			selected[i] = true;
			numbers[count] = i;
			perm(count+1);
			selected[i] = false;
		}
 	}

	static void game() {
		player = 0; // 현재 타자의 인덱스를 게임 시작시 초기화
		int score = 0;
		// 이닝수만큼 반복
		for(int i=0; i<round; i++) {
			// 각 이닝 시작시, 주자(각 루에 있는 사람) x
			boolean[] existed = new boolean[3]; // 현재 0 :1루, 1 : 2루, 2 : 3루에 사람 있는지 
			int cnt = 0; // 아웃된 선수의 개수
			boolean finished = false; // 한 이닝이 끝났는지 여부
			while(true) { // 한 이닝이 끝날때까지 반복
				for(int j=player; j<player+9; j++) {
					int curidx = numbers[j%9]; // 현재 타자의 인덱스
					if(result[i][curidx] == 1) { // 안타를 쳤다면 -> 모두 1루씩 전진
						for(int k=2; k>=0; k--) {
							// 이미 3루에 존재하는 타자는 득점
							if(k+1 > 2 && existed[k]) {
								score++;
								existed[k] = false;
							}
							// 1,2루에 존재하는 타자는 -> 2,3루로 감
							else if(k+1 <= 2 && existed[k]) {
								existed[k+1] = true; // 그 다음 루로 가고
								existed[k] = false; // 해당 루에 사람 없다 표시
							}
						}
						existed[0] = true; // 현재 친 타자는 0(1루)로							
					}
					else if(result[i][curidx] == 2) { // 2루타를 쳤다면 -> 모두 2루씩 전진
						for(int k=2; k>=0; k--) {
							// 이미 2~3루에 존재하는 타자들 모두 득점
							if(k+2 > 2 && existed[k]) {
								score++;
								existed[k] = false;
							}
							// 1루에 존재하는 타자는 -> 3루로 감
							else if(k+2 <= 2 && existed[k]) {
								existed[k+2] = true; // +2 루로 가고
								existed[k] = false; // 해당 루에 사람 없다 표시
							}
						}
						existed[1] = true; // 현재 친 타자는 1(2루)로						
					}
					else if(result[i][curidx] == 3) { // 3루타를 쳤다면 -> 모두 3루씩 전진
						// 이미 1~3루에 존재하는 타자들 모두 득점
						for(int k=0; k<3; k++) {
							if(existed[k]) {
								score++;
								existed[k] = false;
							}
						}
						existed[2] = true; // 현재 친 타자는 2(3루)로
					}
					else if(result[i][curidx] == 4) { // 홈런을 쳤다면 -> 모두 4칸씩 전진
						// 이미 1~3루에 존재하는 타자들 모두 득점
						for(int k=0; k<3; k++) {
							if(existed[k]) {
								score++;
								existed[k] = false;
							}
						}
						score++; // 현재 친 타자도 득점
					}
					else if(result[i][curidx] == 0) { // 아웃됐다면 -> 아웃된 선수 1명 증가
						cnt++;
					}
					
					if(cnt == 3) { // 아웃 3명 됐다면 -> 현재 라운드 종료
						finished = true;
						player = (j+1)%9; // 다음 시작 타자의 인덱스를 기록
						break;
					}
				}				
				if(finished) break;
			}
		}
		if(score > maxScore) { // 현재 최고 점수보다 크면 -> 업데이트
			maxScore = score;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		round = Integer.parseInt(br.readLine()); // 몇 이닝 하는지
		
		// 각 선수가 각 이닝에서 어떤 결과 얻는지 배열 생성 및 입력받기
		result = new int[round][9];
		for(int i=0; i<round; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		perm(0);
		
		System.out.println(maxScore);
	}

}
