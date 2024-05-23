import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 세로 길이
		int m = Integer.parseInt(st.nextToken()); // 가로 길이
		
		// 맵 입력 받기
		int[][] map = new int[n][m];
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<m; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 테르로미노 배열 넣기
		List<int[]>[] list = new List[5]; // x좌표, y좌표, 세로길이, 가로길이
		list[0] = new ArrayList<>();
		list[0].add(new int[] {0,0,1,4});
		list[0].add(new int[] {0,1,1,4});
		list[0].add(new int[] {0,2,1,4});
		list[0].add(new int[] {0,3,1,4});
		
		list[1] = new ArrayList<>();
		list[1].add(new int[] {0,0,2,2});
		list[1].add(new int[] {0,1,2,2});
		list[1].add(new int[] {1,0,2,2});
		list[1].add(new int[] {1,1,2,2});
		
		list[2] = new ArrayList<>();
		list[2].add(new int[] {0,0,3,2});
		list[2].add(new int[] {1,0,3,2});
		list[2].add(new int[] {2,0,3,2});
		list[2].add(new int[] {2,1,3,2});
		
		list[3] = new ArrayList<>();
		list[3].add(new int[] {0,0,3,2});
		list[3].add(new int[] {1,0,3,2});
		list[3].add(new int[] {1,1,3,2});
		list[3].add(new int[] {2,1,3,2});
		
		list[4] = new ArrayList<>();
		list[4].add(new int[] {0,0,2,3});
		list[4].add(new int[] {0,1,2,3});
		list[4].add(new int[] {0,2,2,3});
		list[4].add(new int[] {1,1,2,3});
		
		
		int max = 0; // 최댓값
		// 5개의 테르로미노 중 하나 선택
		for(int i=0; i<5; i++) {
			List<int[]> cur = list[i];
			// 각 테르로미노의 대칭 여부
			for(int j=0; j<4; j++) {
				List<int[]> mirrored = mirrored(cur, j);
				
				// 각 테르로미노의 회전 여부
				for(int k=0; k<4; k++) {
					mirrored = rotated(mirrored);
					
					// 가장 최댓값인지 비교
					max = Math.max(maxValue(map, mirrored, n, m), max);
				}
			}
		}
		
		System.out.println(max);
		
	}
	
	static int maxValue(int[][] map, List<int[]> list, int n, int m) {
		int nlen = list.get(0)[2];
		int mlen = list.get(0)[3];
		
		int result = 0;
		for(int r=0; r<=n-nlen; r++) {
			for(int c=0; c<=m-mlen; c++) {
				int sum = 0;
				for(int i=0; i<list.size(); i++) {
					int cur[] = list.get(i);
					int x = cur[0] + r;
					int y = cur[1] + c;
					
					sum += map[x][y];
				}
				result = Math.max(result, sum);
			}
		}
		return result;
	}
	
	static List<int[]> rotated(List<int[]> list) {
		List<int[]> newList = new ArrayList<>();
		
		for(int i=0; i<4; i++) {
			int[] cur = list.get(i);
			
			newList.add(new int[] {cur[1], (cur[2]-1)-cur[0], cur[3], cur[2]});
		}
		
		return newList;
	}
	
	static List<int[]> mirrored(List<int[]> list, int op) {
		List<int[]> newList = new ArrayList<>();
		
		for(int i=0; i<4; i++) {
			int[] cur = list.get(i);
			switch(op) {
				// 대칭 x
				case 0:
					newList.add(new int[] {cur[0], cur[1], cur[2], cur[3]});
					break;
				// 좌우 대칭
				case 1:
					newList.add(new int[] {cur[0], (cur[3]-1)-cur[1], cur[2], cur[3]});
					break;
			    // 상하 대칭
				case 2:
					newList.add(new int[] {(cur[2]-1)-cur[0], cur[1], cur[2], cur[3]});
					break;
				// 상하 + 좌우 대칭
				case 3:
					newList.add(new int[] {(cur[2]-1)-cur[0], (cur[3]-1)-cur[1], cur[2], cur[3]});
					break;
			}
		}
		
		return newList;
	}
}