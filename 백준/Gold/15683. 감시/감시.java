import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n; // 행크기
	static int m; // 열크기
	static char[][] map; // 맵
	
	// 방향벡터(상,하,좌,우)
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static List<int[]> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<m; c++) {
				map[r][c] = st.nextToken().charAt(0);
				if(map[r][c]>='1' && map[r][c]<='5') {
					list.add(new int[] {r,c});
				}
			}
		}
		
		perm(map, 0);
		
		System.out.println(min);
	}
	
	static int min = Integer.MAX_VALUE;
	static void perm(char[][] realmap, int count) {
		if(count >= list.size()) {
			int sum = 0;
			for(int r=0; r<n; r++) {
				for(int c=0; c<m; c++) {
					if(realmap[r][c] == '0') sum++;
				}
			}
			if(sum < min) min = sum;
			
			return;
		}
		
		
		int x = list.get(count)[0];
		int y = list.get(count)[1];
		
		if(map[x][y] == '1') {
			for(int i=0; i<4; i++) {
				char[][] tempmap = new char[n][m];
				for(int r=0; r<n; r++) {
					tempmap[r] = Arrays.copyOf(realmap[r], m);
				}
				cctv1(tempmap, x,y,i);
				perm(tempmap, count+1);
			}
		}
		else if(map[x][y] == '2') {
			for(int i=0; i<2; i++) {
				char[][] tempmap = new char[n][m];
				for(int r=0; r<n; r++) {
					tempmap[r] = Arrays.copyOf(realmap[r], m);
				}
				cctv2(tempmap, x,y,i);
				perm(tempmap, count+1);
			}
		}
		else if(map[x][y] == '3') {
			for(int i=0; i<4; i++) {
				char[][] tempmap = new char[n][m];
				for(int r=0; r<n; r++) {
					tempmap[r] = Arrays.copyOf(realmap[r], m);
				}
				cctv3(tempmap,x,y,i);
				perm(tempmap, count+1);
			}
		}
		else if(map[x][y] == '4') {
			for(int i=0; i<4; i++) {
				char[][] tempmap = new char[n][m];
				for(int r=0; r<n; r++) {
					tempmap[r] = Arrays.copyOf(realmap[r], m);
				}
				cctv4(tempmap,x,y,i);
				perm(tempmap, count+1);
			}
		}
		else if(map[x][y] == '5') {
			for(int i=0; i<1; i++) {
				char[][] tempmap = new char[n][m];
				for(int r=0; r<n; r++) {
					tempmap[r] = Arrays.copyOf(realmap[r], m);
				}
				cctv5(tempmap,x,y,i);
				perm(tempmap, count+1);
			}
		}
		
	}
	
	static void cctv1(char[][] map, int r, int c, int dir) {
		for(int i=1; ;i++) {
			int nextx = r + dx[dir]*i;
			int nexty = c + dy[dir]*i;
			if(nextx<0 || nextx>=n || nexty<0 || nexty>=m || map[nextx][nexty] == '6') {
				break;
			}
			else if(map[nextx][nexty] == '0') {
				map[nextx][nexty] = '#';
			}
		}		
	}
	
	static void cctv2(char[][] map, int r, int c, int dir) {
		for(int d = dir*2; d<dir*2+2; d++) {
			for(int i=1; ;i++) {
				int nextx = r + dx[d]*i;
				int nexty = c + dy[d]*i;
				if(nextx<0 || nextx>=n || nexty<0 || nexty>=m || map[nextx][nexty] == '6') {
					break;
				}
				else if(map[nextx][nexty] == '0') {
					map[nextx][nexty] = '#';
				}						
			}			
		}
	}
	
	static void cctv3(char[][] map, int r, int c, int dir) {
		int first = -1;
		int second = -1;
		if(dir == 0) {
			first = 3;
			second = 0;
		}
		else if(dir == 1) {
			first = 3;
			second = 1;
		}
		else if(dir == 2) {
			first = 2;
			second = 1;
		}
		else if(dir == 3) {
			first = 2;
			second = 0;
		}
		for(int i=1; ;i++) {
			int nextx = r + dx[first]*i;
			int nexty = c + dy[first]*i;
			if(nextx<0 || nextx>=n || nexty<0 || nexty>=m || map[nextx][nexty] == '6') {
				break;
			}
			else if(map[nextx][nexty] == '0') {
				map[nextx][nexty] = '#';
			}						
		}		

		for(int i=1; ;i++) {
			int nextx = r + dx[second]*i;
			int nexty = c + dy[second]*i;
			if(nextx<0 || nextx>=n || nexty<0 || nexty>=m || map[nextx][nexty] == '6') {
				break;
			}
			else if(map[nextx][nexty] == '0') {
				map[nextx][nexty] = '#';
			}						
		}		
	}
	
	static void cctv4(char[][] map, int r, int c, int dir) {
		for(int d=0; d<4; d++) {
			if(d == dir) continue;

			for(int i=1; ;i++) {
				int nextx = r + dx[d]*i;
				int nexty = c + dy[d]*i;
				if(nextx<0 || nextx>=n || nexty<0 || nexty>=m || map[nextx][nexty] == '6') {
					break;
				}
				else if(map[nextx][nexty] == '0') {
					map[nextx][nexty] = '#';
				}						
			}	
		}		
	}
	
	static void cctv5(char[][] map, int r, int c, int dir) {
		for(int d=0; d<4; d++) {
			for(int i=1; ;i++) {
				int nextx = r + dx[d]*i;
				int nexty = c + dy[d]*i;
				if(nextx<0 || nextx>=n || nexty<0 || nexty>=m || map[nextx][nexty] == '6') {
					break;
				}
				else if(map[nextx][nexty] == '0') {
					map[nextx][nexty] = '#';
				}						
			}	
		}	
	}

}
