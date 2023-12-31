import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int h = Integer.parseInt(st.nextToken()); // 세로 길이
		int w = Integer.parseInt(st.nextToken()); // 가로 길이
		
		// 블록 맵 입력받기
		boolean[][] map = new boolean[h][w];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<w; i++) {
			int height = Integer.parseInt(st.nextToken()); // 현재 입력받은 높이
			for(int r=h-1; r>=h-height; r--) {
				map[r][i] = true;
			}
		}
		
		int sum = 0; // 고이는 빗물의 총량
		for(int r=h-1; r>=0; r--) {
			int left = -1;
			int right = -1;
			for(int c=0; c<=w-1; c++) {
				if(map[r][c] && left == -1) {
					left = c;
				} else if(map[r][c] && right == -1) {
					right = c;
					sum += right - left -1 ;
					left = right;
					right = -1;
				}
			}
		}
		
		System.out.println(sum);
	}
}
