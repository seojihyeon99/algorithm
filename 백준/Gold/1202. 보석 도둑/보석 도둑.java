import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Jewel implements Comparable<Jewel>{
	int m; // 무게
	int v; // 가치
	
	public Jewel(int m, int v) {
		this.m = m;
		this.v = v;
	}

	// 무게 기준 오름차순 정렬, 무게 같을 경우 가치 기준 내림차순 정렬
	@Override
	public int compareTo(Jewel o) {
		if(this.m == o.m) {
			return o.v - this.v;
		}
		return this.m - o.m;
	}
}

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 보석의 개수
		int k = Integer.parseInt(st.nextToken()); // 가방의 개수
		
		// 보석 정보 입력
		Jewel[] jewels = new Jewel[n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken()); // 보석의 무게
			int v = Integer.parseInt(st.nextToken()); // 보석의 가치
			jewels[i] = new Jewel(m, v);
		}
		Arrays.sort(jewels); // 보석 정렬 (=> NlogN)
		
		// 가방 무게 정보 입력
		int[] bags = new int[k];
		for(int i=0; i<k; i++) {
			int c = Integer.parseInt(br.readLine());
			bags[i] = c;
		}
		Arrays.sort(bags); // 가방 정렬 (=> KlogK)
		
		// 결국 최악의 경우에 모든 보석을 pq에 넣고/빼는 시간복잡도와 같음 (=> NlogN)
		long sum = 0; // 보석의 최대 가치 합
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 가치 기준 내림차순 정렬
		int j = 0; // 현재 보고 있는 보석의 인덱스		
		for(int i=0; i<k; i++) { // i : 현재 보고 있는 가방 (무게 낮은것부터 탐색)
			while(j<n) {
				// 현재 가방에 보석 넣을 수 x
				if(jewels[j].m > bags[i]) break;
				
				// 현재 가방에 보석 넣을 수 o => 가방 무게는 오름차순이므로, 다음 가방에도 해당 보석 넣을 수 있음! 
				pq.offer(jewels[j].v);
				j++;
			}
			
			if(!pq.isEmpty()) {
				sum += pq.poll(); // 가치가 가장 큰 보석 선택
			}
		}
		
		System.out.println(sum);
	}

}