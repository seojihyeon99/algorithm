import java.util.Scanner;

public class Solution {
	static int n; //과자 봉지의 수
	static int weight; //무게 합 제한
	static int max; //최대합 저장
	static boolean[] visited; //과자 선택 여부
	
	//idx는 현재 몇번째 과자 고르는지(0부터 시작), start는 현재 몇번째 과자부터 고를 수 있는지, cursum는 현재까지의 과자합
	static void bestComb(int[] arr, int idx, int start, int cursum) { 
		// 무게 제한보다 크면
		if(cursum > weight) {
			return; // 가지 치기~
		}
		
		// 종료조건(과자 2개 다 고르면)
		if(idx == 2) {
			int sum = 0;
			//선택한 과자들의 합을 구해서
			for(int i=0; i<n; i++) {
				if(visited[i]) sum += arr[i];
			}
			//그 합이 현재 max보다 크고, 무게 제한보다 작으면 -> max 값 업데이트
			if(max < sum) {
				max = sum;
			}
			return;
		}
		
		for(int i=start; i<n; i++) { //현재 start번째 과자부터 고를 수 있음
			visited[i] = true; //i번째 과자를 고름
			bestComb(arr, idx+1, i+1, cursum+arr[i]); //그다음 반복에서는 하나 큰 i+1번째 과자부터 고를 수 있음
			visited[i] = false; //i번째 과자를 안고름
		}
	}
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt(); //테스트 케이스수
		
		//테스트 케이스수만큼 반복
		for(int t=1; t<=tc; t++) {
			max = 0; //다음 반복을 위해 max 0으로 초기화함
			
			n = sc.nextInt(); //과자 봉지수
			weight = sc.nextInt(); //무게 제한
			
			visited = new boolean[n]; //과자 선택 여부 저장 배열 생성
			
			//과자 무게를 저장하는 배열 초기화
			int[] snacks = new int[n];
			for(int i=0; i<n; i++) {
				snacks[i] = sc.nextInt();
			}
			
			//과자의 조합 구하는 함수 실행
			bestComb(snacks, 0, 0, 0);
			
			sb.append("#" + t + " ");
			if(max == 0) { //max 변화 없으면 -> 만족하는 과자 조합이 없음 -> -1 출력
				sb.append(-1 + "\n");
			}
			else { //max 변화 있으면 -> 만족하는 과자 조합 있음 -> 해당 max 출력		
				sb.append(max + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}
