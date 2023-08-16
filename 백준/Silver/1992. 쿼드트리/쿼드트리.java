import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	// 압축 실행하는 함수
	static String zip(int[][] map, int row, int col, int size) { // row:왼쪽위 x좌표, col:왼쪽위 y좌표, size: 크기
//		// 크기가 1일때는 -> 압축완료된 상태
//		if(size == 1) {
//			return String.valueOf(map[row][col]);
//		}
		
		// 크기가 1이 아니라면
		int sum = 0; // 구역의 합
		// 해당 영역을 순회하면서 0과 1의 합을 구함
		for(int r=row; r<row+size; r++) {
			for(int c=col; c<col+size; c++) {
				sum += map[r][c];
			}
		}
		
		if(sum == size*size) { // 해당 영역이 모두 1이면 -> 압축결과 1
			return "1";
		}
		else if(sum == 0) { // 해당 영역이 모두 0이면 -> 압축결과 0
			return "0";
		}
		else { // 해당 영역에 0과 1 섞여있다면 -> 또다시 좌상, 우상, 좌하, 우하 4영역으로 나누어 압축해야
			int half = size/2;
			return "(" + zip(map, row, col, half) + zip(map, row, col+half, half)
					+ zip(map, row+half, col, half) + zip(map, row+half, col+half, half) + ")";
		}
		
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 배열의 한변의 길이
		
		// 배열 생성 및 입력 받기
		int[][] map = new int[n][n];
		for(int r=0; r<n; r++) {
			String s = br.readLine();
			for(int c=0; c<n; c++) {
				map[r][c] = s.charAt(c)-'0';
			}
		}
		
		System.out.println(zip(map, 0, 0, n));
	}
}
