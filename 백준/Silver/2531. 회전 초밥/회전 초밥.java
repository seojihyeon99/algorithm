import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int ndish = Integer.parseInt(st.nextToken()); // 접시수
		int nsushi = Integer.parseInt(st.nextToken()); // 초밥가짓수
		int len = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
		int coupon = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		
		int[] dish = new int[ndish]; // 벨트 위의 초밥번호 정보
		for(int i=0; i<ndish; i++) {
			dish[i] = Integer.parseInt(br.readLine());
		}
		
		int[] sushi = new int[nsushi+1]; // 초밥 사용 여부
		int cnt = 1; // 현재 초밥 가짓수 (왼쪽 끝 ~ 오른쪽 끝)
		sushi[coupon]++; // 쿠폰 무조건 사용
		// 초기화
		for(int i=0; i<len; i++) {
			if(sushi[dish[i]] == 0) cnt++;	
			sushi[dish[i]]++;	
		}
		
		int max = cnt; // 초밥 가짓수 최대
		for(int i=1; i<ndish; i++) { // i : 왼쪽 끝이 될 수 있는 인덱스
			// 새로운 왼쪽 끝 : i
			if(sushi[dish[i-1]] - 1 == 0) cnt--;
			sushi[dish[i-1]]--;
			
			// 새로운 오른쪽 끝 : i+len
			if(sushi[dish[(i+len-1) % ndish]] == 0) cnt++;
			sushi[dish[(i+len-1) % ndish]]++;
			
			max = Math.max(max, cnt);
		}
		
		System.out.println(max);
	}
}