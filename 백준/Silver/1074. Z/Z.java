import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int result; // 몇번째 방문하는지
	
	static void makeZ(int targetx, int targety, int r, int c, int size) {
		// 크기가 1인 정사각형일때
		if(size == 1) {
			if(targetx == r && targety ==  c) { // 타겟 점의 좌표라면
				return;
			}
			result++;
			return;
		}
		
		// 크기가 1이 아닐때
		else {
			int half = size/2;
			// 타겟 점의 좌표가 1사분면에 있다면
			if(targetx<r+half && targety<c+half) {
				makeZ(targetx, targety, r, c, half);
			}
			// 타겟 점의 좌표가 2사분면에 있다면
			else if(targetx<r+half && targety>=c+half) {
				result+=half*half; // 1사분면은 바로 계산
				makeZ(targetx, targety, r, c+half, half);
			}			
			// 타겟 점의 좌표가 3사분면에 있다면
			else if(targetx>=r+half && targety<c+half) {
				result+=half*half*2; // 1~2사분면은 바로 계산
				makeZ(targetx, targety, r+half, c, half);
			}		
			// 타겟 점의 좌표가 4사분면에 있다면
			else if(targetx>=r+half && targety>=c+half) {
				result+=half*half*3; // 1~3사분면은 바로 계산
				makeZ(targetx, targety, r+half, c+half, half);
			}
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 정사각형 한변(2^n)을 결정하는 값
		int r = Integer.parseInt(st.nextToken()); // 방문하는 점의 x좌표
		int c = Integer.parseInt(st.nextToken()); // 방문하는 점의 y좌표
		
		makeZ(r, c, 0, 0, (int)Math.pow(2, n));
		
		System.out.println(result);
	}
}
