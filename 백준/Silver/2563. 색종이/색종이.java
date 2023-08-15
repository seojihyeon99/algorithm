import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 색종이 개수
		
		boolean[][] paper = new boolean[100][100]; // 도화지 생성
		
		// 색종이 개수만큼 입력받기
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int down = Integer.parseInt(st.nextToken());
			
			for(int r=99-down; r>99-down-10; r--) {
				for(int c=left; c<left+10; c++) {
					paper[r][c] = true;
				}			
			}
			
		}
		
		// 색종이가 붙은 영역의 합
		int sum = 0;
		for(int r=0; r<100; r++) {
			for(int c=0; c<100; c++) {
				if(paper[r][c]) sum++;
			}
		}			
		System.out.println(sum);
	}
}
