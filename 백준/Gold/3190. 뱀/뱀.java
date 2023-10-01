/**
 * [아이디어]
 * 시뮬레이션
 * 뱀의 이동 방향을 우:1, 하:2, 좌:3, 상:4 로 표시함
 * 2차원 배열에서 비어있는 칸은 0으로, 사과가 있는 칸은 -1로, 뱀이 위치한 칸은 그 다음 진행 방향(1~4)을 기록함
 * 
 * [시간]
 * 128ms
 * [메모리]
 * 14200KB
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	static int n; // 보드의 크기
	static int[][] map; // 보드(0: 비어있는칸, -1: 사과가 있는칸, 1~4: 뱀이 위치한 칸)
	static int napple; // 사과의 개수
	static List<int[]> list = new ArrayList<>(); // 방향 변환 정보
	
	static int[] head = new int[] {0,0}; // 뱀의 머리 위치
	static int[] tail = new int[] {0,0}; // 뱀의 꼬리 위치
	static int cur = 1; // 현재 뱀의 이동방향(초기 : 우(1))
	
	// 방향벡터 (우(1), 하(2), 좌(3), 상(4))
	static int[] dx = {0, 0, 1, 0, -1};
	static int[] dy = {0, 1, 0, -1, 0};
	
	static int time; // 현재 시간
	
	static void simulation() {
		while(true) {
			time++; // 시간 1초 증가
			
			// 매초마다 머리쪽방향으로 몸길이 1 증가함
			int x = head[0] + dx[cur];
			int y = head[1] + dy[cur];
			// 머리쪽방향이 벽과 부딪히거나 / 자신의 몸과 부딪히면 => 게임 종료
			if(x<0 || x>=n || y<0 || y>=n || (map[x][y] >= 1 && map[x][y] <=4)) break;
			
			// 해당 머리 위치에 사과 없다면 => 꼬리쪽방향 몸길이 1 감소함
			if(map[x][y] == 0) {
				int tx = tail[0]; // 꼬리 행위치
				int ty = tail[1]; // 꼬리 열위치
				int td = map[tx][ty]; // 꼬리위치의 다음 진행 방향
				tail[0] = tx + dx[td]; // 꼬리 행위치를 업데이트
				tail[1] = ty + dy[td]; // 꼬리 열위치를 업데이트
				map[tx][ty] = 0; // 이전 꼬리칸을 비워줌(0으로)
			}
			
			// 해당 초가 끝난뒤에 방향 변환 정보가 있으면
			if(list.size()>0 && list.get(0)[0] == time) {
				int next = cur + list.get(0)[1]; // 다음 이동 방향
				if(next == 0) next = 4; // 0 이하일때 방향 보정
				else if(next == 5) next = 1; // 5 이상일때 방향 보정
				cur = next; // 현재 이동방향을 업데이트
				list.remove(0); // 해당 초에 해당하는 방향 변환 정보 삭제
			}
			
			map[x][y] = cur; // 이동한 머리쪽에 현재 이동방향(정확히는 다음 이동 방향)을 기록
			head[0] = x; // 머리 행위치를 업데이트
			head[1] = y; // 머리 열위치를 업데이트
		}
		
		System.out.println(time); // 게임이 몇 초에 끝나는지 출력
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine()); // 보드의 크기
		map = new int[n][n]; // 보드 생성
		map[0][0] = 1; // 초기에 좌상단에 뱀위치
		
		napple = Integer.parseInt(br.readLine()); // 사과의 개수
		// 사과의 개수만큼 사과의 위치 입력받아, 보드에 사과 위치 표시
		for(int i=0; i<napple; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1; // 행 위치
			int c = Integer.parseInt(st.nextToken())-1; // 열 위치
			map[r][c] = -1; // 사과가 있다고 표시
		}
		
		int ndir = Integer.parseInt(br.readLine()); // 방향 변환 횟수
		// 방향 변환 횟수만큼 입력받아, list에 추가함
		for(int i=0; i<ndir; i++) {
			st = new StringTokenizer(br.readLine());
			int sec = Integer.parseInt(st.nextToken());
			int dir = 0;
			char temp = st.nextToken().charAt(0);
			if(temp == 'D') dir = 1; // 오른쪽 방향이면 1로 변환하여 저장
			else if(temp == 'L') dir = -1; // 왼쪽 방향이면 -1로 변환하여 저장
			
			list.add(new int[] {sec, dir});
		}
		
		// 시뮬레이션 시작
		simulation();
	}
}
