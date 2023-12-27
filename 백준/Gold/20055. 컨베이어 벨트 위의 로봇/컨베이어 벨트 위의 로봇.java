import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 컨베이어 벨트 단면의 길이
		int k = Integer.parseInt(st.nextToken()); // 내구도가 0인 칸의 개수
		
		int[] belt = new int[2*n]; // 해당 칸의 내구도
		boolean[] existed = new boolean[2*n]; // 해당 칸에 로봇이 있는지
		// 내구도 입력
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<2*n; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0; // 내구도가 0인 칸의 개수
		int step = 0; // 단계 수
		while(true) {
			// 단계 수 증가
			step++;
			
			// 1.벨트 로봇과 함께 회전
			int temp = belt[2*n-1];
			boolean robot = false;
			if(existed[2*n-1]) robot = true;
			for(int i=2*n-2; i>=0; i--) {
				belt[i+1] = belt[i];
				if(existed[i]) {
					existed[i+1] = true;
					existed[i] = false;
					
					// 로봇이 내리는 위치에 도달하면 -> 내림
					if(i+1 == n-1) existed[i+1] = false;
				}
			}
			belt[0] = temp;
			existed[0] = robot;
			
			// 2.로봇 이동
			for(int i=2*n-2; i>=0; i--) {
				// 로봇이 없으며 내구도가 1 이상이면 -> 이동 가능
				if(existed[i] && !existed[i+1] && belt[i+1] > 0) {
					belt[i+1]--; // 칸의 내구도 1 감소
					if(belt[i+1] == 0) cnt++; // 내구도 0인 칸이되면, cnt 증가
					existed[i+1] = true;
					existed[i] = false;

					// 로봇이 내리는 위치에 도달하면 -> 내림
					if(i+1 == n-1) existed[i+1] = false;
				}
			}
			
			// 3. 로봇 올리기
			// 올리는 위치의 내구도가 0이 아니면 -> 올리기 가능
			if(belt[0] > 0) {
				belt[0]--;
				if(belt[0] == 0) cnt++; // 내구도 0인 칸이되면, cnt 증가
				existed[0] = true;
			}

			// 4.내구도가 0인 칸의 개수가 k개 이상이라면 과정을 종료함
			if(cnt >= k) break;

		}
		
		System.out.println(step);
	}
}
