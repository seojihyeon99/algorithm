import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n; // 재료의 개수
	static int[] sour; 	 //신맛 저장
	static int[] bitter; //쓴맛 저장
	static boolean[] visited;
	static int differ = Integer.MAX_VALUE; //신맛과 쓴맛의 차이
	
	static void partitialSet(int cur) { // cur은 현재 확인하는 원소의 인덱스
		 if(cur == n) {
			 int sourmul = 1;
			 int bittersum = 0;
			 int cnt = 0;
			 for(int i=0; i<n; i++) {
				 if(visited[i]) {
					 sourmul*=sour[i];
					 bittersum+=bitter[i];
					 cnt++;
				 }
			 }
			 // 문제에서 아무 재료도 안넣는 경우는 없다고 하였으므로
			 if(cnt > 0) {
				 // 신맛의 곱과 쓴맛의합의 차의 절대값 구하기
				 int sub = (sourmul-bittersum)>0 ? sourmul-bittersum : bittersum-sourmul;
				 // 차이가 더 작으면 업데이트
				 if(sub<differ) {
					 differ = sub;
				 }			 
			 }
			 
			 return;
		 }
	
		 visited[cur] = true; // 해당 원소를 넣는 경우
		 partitialSet(cur+1);
		 
		 visited[cur] = false; // 해당 원소를 넣지 않는 경우
		 partitialSet(cur+1);
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		visited = new boolean[n];
		sour = new int[n];
		bitter = new int[n];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sour[i] = Integer.parseInt(st.nextToken());
			bitter[i] = Integer.parseInt(st.nextToken());
		}
		
		partitialSet(0);
		System.out.println(differ);
	}
}
