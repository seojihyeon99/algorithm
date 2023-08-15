import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int nwhite; // 하얀색 색종이의 수
	static int nblue; // 파란색 색종이의 수
	
	static void dividepaper(int[][] map, int row, int col, int size) {
		int sum = 0; // 해당 정사각형 영역의 합
		for(int r=row; r<row+size; r++) {
			for(int c=col; c<col+size; c++) {
				sum += map[r][c];
			}
		}
		
		// 정사각형 영역이 모두 하얀색 색종이
		if(sum == 0) {
			nwhite++;
		}
		// 정사각형 영역이 모두 파란색 색종이
		else if(sum == size*size) {
			nblue++;
		}
		else { // 정사각형 영역에 파란색 색종이도 있고, 하얀색 색종이도 있을 때
			int half = size/2;
			dividepaper(map, row, col, half);
			dividepaper(map, row, col+half, half);
			dividepaper(map, row+half, col, half);
			dividepaper(map, row+half, col+half, half);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 한 변의 길이
		
		// 배열 생성 및 초기화
		int[][] map = new int[n][n];
		for(int r=0; r<n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		dividepaper(map, 0, 0, n);
		
		System.out.println(nwhite + "\n" + nblue);
		
	}
}
