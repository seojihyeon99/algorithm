import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr; // 능력치 2차원 배열
	static int n; // 행 or 열 크기
	static int min = Integer.MAX_VALUE; // 두 팀 능력치 차이 최솟값
	static int[] numbers; // 뽑힌 선수 번호 저장
	
	static void comb(int start, int count) {
		// 기저조건
		if(count == n/2) {
			int selected = 0;
			int notSelected = 0;
			
			// 스타트 팀의 능력치 합 구하기
			for(int left=0; left<n/2-1; left++) {
				for(int right=left+1; right<n/2; right++) {
					selected += arr[numbers[left]][numbers[right]];
					selected += arr[numbers[right]][numbers[left]];
				}
			}
			
			// 링크 팀의 능력치 합 구하기
			boolean[] isStart = new boolean[n];
			for(int i=0; i<n/2; i++) {
				isStart[numbers[i]] = true;
			}
			int[] linkTeam = new int[n/2];
			int idx = 0;
			for(int i=0; i<n; i++) {
				if(!isStart[i]) linkTeam[idx++] = i;
			}
			// 링크 팀의 능력치 합 구하기
			for(int left=0; left<n/2-1; left++) {
				for(int right=left+1; right<n/2; right++) {
					notSelected += arr[linkTeam[left]][linkTeam[right]];
					notSelected += arr[linkTeam[right]][linkTeam[left]];
				}
			}			
			
			int result = Math.abs(selected - notSelected);
			if(result < min) min = result;
			
			return;
		}
		
		for(int i=start; i<n; i++) {
			numbers[count] = i;
			comb(i+1, count+1);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		
		// 능력치 2차원 배열 생성 및 입력받기
		arr = new int[n][n];
		for(int r=0; r<n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		numbers = new int[n/2];
		comb(0,0);
		
		System.out.println(min);
	}
	
}
