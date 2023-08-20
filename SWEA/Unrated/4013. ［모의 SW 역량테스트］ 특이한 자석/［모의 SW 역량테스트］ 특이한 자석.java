import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int[][] magnet = new int[4][8]; // 자석들의 자성정보(날 8개, 화살표 위치부터 시계방향으로 인덱스 0부터 기록)
	static boolean[] isrotate;
	static int[] rotateStatus; // 시계 1, 반시계 -1, 아무것도x :0
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		// 테스트 케이스 수만큼 반복
		for(int t=1; t<=tc; t++) {
			int k = Integer.parseInt(br.readLine()); // 자석을 회전시키는 횟수
			
			// 자석들의 날 8개의 자성정보 입력받기(N극=0, S극=1)
			for(int i=0; i<4; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<k; i++) {
				st = new StringTokenizer(br.readLine());
				isrotate = new boolean[8];
				rotateStatus = new int[4];
				rotate(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())); // 자석번호 0번부터 시작
//				print();
//				System.out.println(Arrays.toString(rotateStatus));
				for(int j=0; j<4; j++) {
					if(rotateStatus[j] == 1) {
						turnClock(j);
					}
					else if(rotateStatus[j] == -1) {
						turnReverseClock(j);
					}
				}
//				System.out.println("==================");
			}
			
			
			int sum = 0;
			for(int i=0; i<4; i++) {
				if(magnet[i][0] == 1) { // 화살표 위치의 날의 자성이 S극이면 2^i점 획득
					sum += Math.pow(2, i);
				}
			}
			sb.append("#" + t + " " + sum + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	// 하나의 자석이 1칸 회전될 때, 붙어있는 자석은 서로 붙어 있는 날의 자성(N극 : 0, S극 1)이 다를 경우에만 반대 방향으로 1칸 회전(시계방향 : 1, 반시계방향 -1)
	static void rotate(int num, int dir) { // num : 자석번호, dir : 방향(시계=1, 반시계=-1)
		isrotate[num] = true;

		/* 자기 회전*/
		if(dir == 1) { // 시계방향이라면
//			turnClock(num);
			rotateStatus[num] = 1;
		}
		else if(dir == -1) {
//			turnReverseClock(num); // 반시계방향이라면
			rotateStatus[num] = -1;
		}
		/* 왼쪽 좌석 회전 */
		if(num != 0 && !isrotate[num-1]) { // 맨 왼쪽 자석이 아니고, 회전한적 없으면
			if(magnet[num][6] != magnet[num-1][2]) { // 두 자석의 극이 다르다면
				rotate(num-1, dir*-1);
			}
		}
		/* 오른쪽 좌석 회전 */
		if(num != 3 && !isrotate[num+1]) {
			if(magnet[num][2] != magnet[num+1][6]) { // 두 자석의 극이 다르다면
				rotate(num+1, dir*-1);
			}				
		}	
	}
	
	// 시계방향으로 회전
	static void turnClock(int num) {
		int temp = magnet[num][7];
		for(int i=6; i>=0; i--) {
			magnet[num][i+1] = magnet[num][i];
		}
		magnet[num][0] = temp;
//		System.out.println((num+1)+"번째자석" + "시계방향으로 회전");
	}
	
	// 반시계방향으로 회전
	static void turnReverseClock(int num) {
		int temp = magnet[num][0];
		for(int i=1; i<=7; i++) {
			magnet[num][i-1] = magnet[num][i];
		}
		magnet[num][7] = temp;
//		System.out.println((num+1)+"번째자석" + "반시계방향으로 회전");
	}
	
//	static void print() {
//		for(int i=0; i<4; i++) {
//			for(int j=0; j<8; j++) {
//				System.out.print(magnet[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("==================");
//	}
}
