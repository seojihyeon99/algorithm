import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine()); // 용액의 수

		int minus = 0; // minus 용액의 수 (0 ~ minus-1 인덱스까지)
		int[] arr = new int[n]; // 용액의 특성값 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(minus == 0 && arr[i] > 0) minus = i;
		}
		if(minus == 0 && arr[0] < minus) minus = n;
		int plus = n-minus; // plus 용액의 수 (minus ~ n-1 인덱스까지)
		
		// 모두 산성인 용액일 경우
		if(minus == 0) {
			sb.append(arr[0] + " " + arr[1]); // 절대값 가장 작은 산성 2개를 출력
		}
		// 모두 알칼리인 용액일 경우
		else if(plus == 0) {
			sb.append(arr[arr.length-2] + " " + arr[arr.length-1]); // 절대값 가장 작은 알칼리 2개를 출력 
		}
		// 산성 + 알칼리인 용액일 경우
		else {
			int min = Integer.MAX_VALUE; // 최솟값
			int mIdx = 0; // 마이너스 용액 인덱스
			int pIdx = 0; // 플러스 용액 인덱스
			if(plus >= 2 && Math.min(min, arr[minus] + arr[minus+1]) < min) {
				min = Math.min(min, arr[minus] + arr[minus + 1]);
				mIdx = minus;
				pIdx = minus+1;
			}
			if (minus >= 2 && Math.min(min, Math.abs(arr[minus-1] + arr[minus-2])) < min) {
				min = Math.min(min, Math.abs(arr[minus-1] + arr[minus-2]));
				mIdx = minus-2;
				pIdx = minus-1;
			}
			
			for(int i=0; i<minus; i++) {
				int val = Integer.MAX_VALUE; // 마이너스 용액 고정했을때의 최솟값
				int m = -1; // 마이너스 용액 인덱스
				int p = -1; // 플러스 용액 인덱스
				for(int j=n-1; j>=minus; j--) {
					int temp = Math.abs(arr[i] + arr[j]); // 현재 산성과 알칼리 용액의 특성값 차이
					if(temp < val) {
						val = temp;
						m = i;
						p = j;
					}
					else
						break;
				}
				if(val < min) { // 최솟값보다 작다면 업데이트
					min = val;
					mIdx = m;
					pIdx = p;
				}
			}
			
			sb.append(arr[mIdx] + " " + arr[pIdx]);
		}
		
		System.out.println(sb);
	}
}
