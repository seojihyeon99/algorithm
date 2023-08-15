import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int totalChicken;
	static int n; // 배열의 한변 길이
	static int m; // 골라야 하는 치킨집 개수
	static List<int[]> chickenpos = new ArrayList<>();
	static boolean[] choosed;
	static int citytotal = Integer.MAX_VALUE;
	
	static void chooseChicken(int[][] map, int start, int choose) {
		if(choose == m) {
			int citydist = 0;
			for(int r=0; r<n; r++) {
				for(int c=0; c<n; c++) {
					if(map[r][c] == 1) { // 집이면
						int dist = Integer.MAX_VALUE;
						for(int i=0; i<totalChicken; i++) {
							if(choosed[i]) {
								int x = chickenpos.get(i)[0];
								int y = chickenpos.get(i)[1];
								int temp = Math.abs(r-x)+Math.abs(c-y);
								if(temp<dist) {
									dist = temp;
								}
							}
						}
						citydist+=dist;
					}
				}
			}
			if(citydist<citytotal) {
				citytotal = citydist;
			}
					
			return;
		}
		
		for(int i=start; i<totalChicken; i++) {
			choosed[i] = true;
			chooseChicken(map, i+1, choose+1);
			choosed[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 배열의 한변 크기
		m = Integer.parseInt(st.nextToken()); // 골라야 하는 치킨집 개수
		
		
		// 배열 생성 및 입력 받기
		int[][] map = new int[n][n]; // 도시 생성
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 2) { // 입력 받은것이 2(치킨집)라면
					chickenpos.add(new int[] {r,c});
					totalChicken++; // 전체 치킨집 개수 증가
				}
			}
		}
		
		choosed = new boolean[totalChicken];

		// 
		chooseChicken(map, 0,0);
		
		System.out.println(citytotal);
	}
}
