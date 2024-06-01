import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 이동하려고 하는 채널
		int m = Integer.parseInt(br.readLine()); // 고장난 버튼의 개수
		boolean[] broken = new boolean[10]; // 버튼 고장 여부
		
		if(m > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());			
			for(int i=0; i<m; i++) {
				int cur = Integer.parseInt(st.nextToken());
				broken[cur] = true;
			}
		}
		
		int[] arr = new int[1000001]; // 채널 N으로 이동하기 위해 누르는 횟수	
		
		// 한 자리 숫자일 경우
		for(int i=0; i<10; i++) {
			// 고장난 숫자라면 pass
			if(broken[i]) continue;
			arr[i] = 1; // 1번 눌러서 이동 가능
		}
		
		// 여러 자리 숫자일 경우
		int[] nums = new int[6]; // 현재 눌러진 숫자들 (최대 6개 누를 수 있음)
		perm(arr, 0, broken, nums);	
		
		// 더 빠르게 이동 가능한 경우를 업데이트 : 제자리, -, +, ++
		arr[100] = 0;
		arr[99] = 1;
		arr[101] = 1;
		arr[102] = 2;
		
		int cur = n; // 현재 채널
		
		// 이미 누르는 횟수 구해진 경우 (숫자 눌러서, -, +, ++제자리)
		if(arr[cur] != 0 || cur == 100) {
			System.out.println(arr[cur]);
		} 
		// 아직 누르는 횟수 구해지지 않은 경우 => 가장 가까운 누르는 횟수 정해진 채널 찾고, 여기서 +혹은 -하여 구함
		else {
			// 모든 숫자가 고장난 경우
			if(m == 10) {
				System.out.println(Math.abs(n-100));
			}
			// 모든 숫자가 고장나지 않은 경우
			else {
				int idx = 1; // 몇 칸 떨어진 채널인지
				while(true) {
					int left = cur-idx;
					int right = cur+idx;
					
					if(left >= 0 && arr[left] != 0) {
						idx = left;
						break;
					}else if(right <= 1000000 && arr[right] != 0) {
						idx = right;
						break;
					}
					
					idx++;
				}			
				int result = Math.abs(n-idx) + arr[idx]; // 떨어진 칸수 + 해당 채널에의 누르는 횟수	
				System.out.println(Math.min(Math.abs(100-n), result)); // 숫자 누르지 않는 경우와 중에서 최소			
			}
		}
	}
	
	// arr: 채널 N으로 이동하기 위해 누르는 횟수, cnt: 현재까지 누른 숫자 개수, broken: 고장난 숫자 정보, nums: 현재까지 눌러진 숫자들
	static void perm(int[] arr, int cnt, boolean[] broken, int[] nums) {
		
		if(cnt == 6) {
			return;
		}
		
		for(int i=0; i<10; i++) {
			// 십의자리 이상일 경우에는 제일 처음자리 0일수 x
			if(i==0 && cnt == 0) continue;
			
			// 고장난 숫자라면 pass
			if(broken[i]) continue;
			
			nums[cnt] = i; // 현재 눌린 숫자 
			// 숫자 눌러주는 처리
			int sum = 0;
			for(int j=0; j<cnt+1; j++) {
				sum *= 10;
				sum += nums[j];
			}
			arr[sum] = cnt+1;			
			
			// 그 다음 숫자 누르러
			perm(arr, cnt+1, broken, nums);
		}
	}
}