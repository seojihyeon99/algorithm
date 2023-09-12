import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n; // 행렬 한변의 길이
	static int[][] arr; // 종이의 각 칸의 숫자(-1,0,1)를 저장
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 행렬 한변의 길이
		
		// 종이의 각 칸의 숫자를 입력받아 저장
		arr = new int[n][n];
		for(int r=0; r<n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		paper(0, 0, n);
		
		for(int i=0; i<3; i++) {
			System.out.println(count[i]);
		}
	}
	
	static int[] count = new int[3]; // -1,0,1의 개수 저장
	static void paper(int lefttopx, int lefttopy, int size) { // 좌상단 x, 좌상단 y, 현재 보는 종이의 한변의 길이
		if(size == 1) { // 크기가 1이라면
			if(arr[lefttopx][lefttopy] == -1) count[0]++;
			else if(arr[lefttopx][lefttopy] == 0) count[1]++;
			else if(arr[lefttopx][lefttopy] == 1) count[2]++;
			return;
		}
		
		int sum = 0; 
		int zerocnt = 0; // 0의 개수
		for(int r=lefttopx; r<lefttopx + size; r++) {
			for(int c=lefttopy; c<lefttopy + size; c++) {
				sum += arr[r][c];
				if(arr[r][c] == 0) zerocnt++;
			}
		}
		// 모두 -1로만 이루어져 있을경우
		if(sum == -1*size*size) {
			count[0]++;
			return;
		}
		// 모두 1로만 이루어져 있을경우
		else if(sum == 1*size*size) {
			count[2]++;
			return;
		}
		// 모두 0으로만 이루어져 있을경우
		else if(sum == 0 && zerocnt == size*size) {
			count[1]++;
			return;
		}
		// -1, 0, 1 숫자가 섞여있을경우
		else {
			// 다시 같은 크기의 종이 9개로 자름
			for(int r=0; r<3; r++) {
				for(int c=0; c<3; c++) {
					paper(lefttopx + size/3*r, lefttopy + size/3*c, size/3);					
				}
			}
		}
	}
}
