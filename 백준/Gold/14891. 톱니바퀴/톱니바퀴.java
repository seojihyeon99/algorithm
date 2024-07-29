import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 47분
class Main {
	static List<Integer>[] list = new List[4]; // 톱니바퀴 (1번 ~ 4번)의 극(N/S) 정보 저장
	
	static void rotate(int[] info) {
		for(int i=0; i<4; i++) {
			// 회전하지 않는 경우
			if(info[i] == 0) {
				continue;
			} 
			// 시계방향 회전인 경우 => 맨 뒤가 맨 앞으로 이동
			else if(info[i] == 1) {
				int tmp = list[i].remove(7);
				list[i].add(0, tmp);
				
			}
			// 반시계방향 회전인 경우 => 맨 앞이 맨 뒤로 이동
			else if(info[i] == -1) {
				int tmp = list[i].remove(0);
				list[i].add(tmp);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 톱니바퀴 극 정보 입력 받기
		for(int i=0; i<4; i++) {
			list[i] = new ArrayList<>();
			
			String s = br.readLine();
			for(int j=0; j<8; j++) {
				list[i].add(s.charAt(j) - '0');				
			}
		}
		
		// 회전횟수
		int n = Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1; // 현재 회전시킨 톱니번호
			int dir = Integer.parseInt(st.nextToken()); // 현재 회전방향(1: 시계, -1: 반시계)
			
			int[] info = new int[4]; // 회전방향정보 (0: 회전 x, 1: 시계, -1: 반시계)
			info[num] = dir;
			
			// 현재 톱니 기준 우측 방향 확인
			int r = list[num].get(2); // 현재 톱니의 오른쪽 맞닿은 부분
			int rdir = dir;
			for(int j=num+1; j<4; j++) {
				// 현재의 오른쪽 맞닿은 부분과 다음 왼쪽 맞닿은 부분의 극이 같다면 => 회전 x
				if(r == list[j].get(6)) break;
				
				// 극이 다르다면
				info[j] = -1*rdir; // 반대방향으로 회전
				rdir = rdir*-1;
				
				r = list[j].get(2); // 다음 톱니 기준 오른쪽 맞닿은 부분
			}
			
			// 현재 톱니 기준 좌측 방향 확인
			int l = list[num].get(6); // 현재 톱니의 왼쪽 맞닿은 부분
			int ldir = dir;
			for(int j=num-1; j>=0; j--) {
				// 현재의 왼쪽 맞닿은 부분과 다음 오른쪽 맞닿은 부분의 극이 같다면 => 회전 x
				if(l == list[j].get(2)) break;
				
				// 극이 다르다면
				info[j] = -1*ldir; // 반대방향으로 회전
				ldir = ldir*-1;
				
				l = list[j].get(6); // 다음 톱니 기준 왼쪽 맞닿은 부분
			}
			
			// 회전시킴
			rotate(info);			
		}
		
		// 네 톱니바퀴의 점수의 합을 출력
		int sum = 0;
		for(int i=0; i<4; i++) {
			int cur = list[i].get(0);
			
			if(cur == 0) continue;
			
			sum += Math.pow(2, i);
		}
		
		System.out.println(sum);
	}
}