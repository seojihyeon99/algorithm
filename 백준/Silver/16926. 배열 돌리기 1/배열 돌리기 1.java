import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 방향벡터(좌, 상, 우, 하)
	static int[] dx = {0,-1,0,1};
	static int[] dy = {-1,0,1,0};
	static int nlen; // 배열의 세로 크기
	static int mlen; // 배열의 가로 크기
	
	// 1번 반시계 방향으로 회전시키는 함수
	static void rotate(int[][] arr) {
		int startx = 0;
		int starty = 0;
		
		int n = nlen; // 현재 세로길이
		int m = mlen; // 현재 가로길이
		
		while(true) {
			int temp = arr[startx][starty]; // ㅁ 모양에서 왼쪽 위의 좌표를 임시 저장
			// 윗변 회전
			for(int c=starty+1; c<starty+m; c++) {
				arr[startx][c-1] = arr[startx][c];
			}
			// 오른쪽변 회전
			for(int r=startx+1; r<startx+n; r++) {
				arr[r-1][starty+m-1] = arr[r][starty+m-1];
			}			
			// 아랫변 회전
			for(int c=starty+m-2; c>=starty; c--) {
				arr[startx+n-1][c+1] = arr[startx+n-1][c];
			}
			// 왼쪽변 회전
			for(int r=startx+n-2; r>=startx+1; r--) {
				arr[r+1][starty] = arr[r][starty];
			}
			// ㅁ 모양 왼쪽 위의 좌표도 이동
			arr[startx+1][starty] = temp;
			
			// 시작 좌표 증가
			startx++; 
			starty++;
			
			// 가로 길이, 세로길이 2씩 감소
			n-=2;
			m-=2;
			
			// 둘 중 하나의 길이가 0이면 반복 끝
			if(n==0 || m==0) {
				break;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		nlen = Integer.parseInt(st.nextToken()); // 배열의 세로 크기
		mlen = Integer.parseInt(st.nextToken()); // 배열의 가로 크기
		int r = Integer.parseInt(st.nextToken()); // 회전의 수
		
		// 배열 입력받기
		int[][] arr = new int[nlen][mlen];
		for(int row =0; row<nlen; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0; col<mlen; col++) {
				arr[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 회전수 r만큼 회전
		for(int i=0; i<r; i++) {
			rotate(arr);
		}
		
		// 회전한 배열 출력
		StringBuilder sb = new StringBuilder();
		for(int row =0; row<nlen; row++) {
			for(int col=0; col<mlen; col++) {
				sb.append(arr[row][col] + " ");
			}
			sb.append("\n");
		}		
		System.out.println(sb.toString());
	}
}
