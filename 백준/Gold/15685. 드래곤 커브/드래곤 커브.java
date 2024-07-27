import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

// 2시간 30분 소요
class Main {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean[][] map = new boolean[101][101]; // 해당 좌표의 드래곤 커브의 존재여부 (교차점)
		
		int n = Integer.parseInt(br.readLine()); // 드래곤 커브의 개수
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // 시작점의 x좌표
			int y = Integer.parseInt(st.nextToken()); // 시작점의 y좌표
			int d = Integer.parseInt(st.nextToken()); // 시작방향
			int g = Integer.parseInt(st.nextToken()); // 세대
			
			List<int[]> list = new ArrayList<>();
			
			// 시작점
			map[y][x] = true;
			list.add(new int[] {y, x});
			
			// 0세대
			// 오른쪽 방향
			if(d == 0) {
				list.add(new int[] {y, x+1});
				map[y][x+1] = true;
			}
			// 위쪽방향
			else if(d == 1) {
				list.add(new int[] {y-1, x});				
				map[y-1][x] = true;
			}
			// 왼쪽방향
			else if(d == 2) {
				list.add(new int[] {y, x-1});								
				map[y][x-1] = true;
			}
			// 아래쪽방향
			else {
				list.add(new int[] {y+1, x});												
				map[y+1][x] = true;
			}
			
			// 세대마다 드래곤 커브 끝 점에 이어 붙이기
			for(int j=1; j<=g; j++) {
				int size = list.size();
				int[] last = list.get(size-1); // 끝점의 좌표
				
				for(int c=size-2; c>=0; c--) {
					int[] cur = list.get(c);
					int dy = last[0] - cur[0]; // y축 차이
					int dx = last[1] - cur[1]; // x축 차이
					
					// 끝점기준 1사분면
					if(dx<0 && dy>0) {
						// 시계방향 90도 회전하면 4사분면에
						list.add(new int[] {last[0]+Math.abs(dx), last[1]+Math.abs(dy)});
						map[last[0]+Math.abs(dx)][last[1]+Math.abs(dy)] = true;
					}
					// 끝점기준 2사분면
					else if(dx>0 && dy>0) {
						// 시계방향 90도 회전하면 1사분면에
						list.add(new int[] {last[0]-Math.abs(dx), last[1]+Math.abs(dy)});		
						map[last[0]-Math.abs(dx)][last[1]+Math.abs(dy)] = true;
					}
					// 끝점기준 3사분면dd
					else if(dx>0 && dy<0) {
						// 시계방향 90도 회전하면 2사분면에
						list.add(new int[] {last[0]-Math.abs(dx), last[1]-Math.abs(dy)});
						map[last[0]-Math.abs(dx)][last[1]-Math.abs(dy)] = true;
					}
					// 끝점기준 4사분면 
					else if(dx<0 && dy<0){
						// 시계방향 90도 회전하면 3사분면에 (x축 - len)
						list.add(new int[] {last[0]+Math.abs(dx), last[1]-Math.abs(dy)});	
						map[last[0]+Math.abs(dx)][last[1]-Math.abs(dy)] = true;
					}
					else {
						int len = (int) Math.sqrt((dx*dx) + (dy*dy));
							
						// 끝점 기준 서쪽 축 위
						if(dx>0 && dy == 0){
							// 시계방향 90도 회전화면 끝점 기준 북쪽 축 위 (y축 - len)
							list.add(new int[] {last[0]-len, last[1]});	
							map[last[0]-len][last[1]] = true;
						}
						// 끝점 기준 북쪽 축 위
						else if(dx == 0 && dy>0){
							// 시계방향 90도 회전화면 끝점 기준 동쪽 축 위 (x축 + len)
							list.add(new int[] {last[0], last[1]+len});	
							map[last[0]][last[1]+len] = true;
						}
						// 끝점 기준 동쪽 축 위
						else if(dx<0 && dy == 0){
							// 시계방향 90도 회전화면 끝점 기준 남쪽 축 위 (y축 + len)
							list.add(new int[] {last[0]+len, last[1]});	
							map[last[0]+len][last[1]] = true;
						}
						// 끝점 기준 남쪽 축 위
						else if(dx == 0 && dy<0){
							// 시계방향 90도 회전화면 끝점 기준 서쪽 축 위 (x축 - len)
							list.add(new int[] {last[0], last[1]-len});	
							map[last[0]][last[1]-len] = true;							
						}
					}
				}
			}
			
		}
		
		// 정사각형 개수 구하기
		int cnt = 0;
		for(int r=0; r<100; r++) {
			for(int c=0; c<100; c++) {
				if(!map[r][c]) continue;
				
				// 정사각형 만들 수 없는 경우
				if(!map[r+1][c] || !map[r][c+1] || !map[r+1][c+1]) continue;
				
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}