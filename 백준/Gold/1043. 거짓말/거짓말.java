import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 사람의 수
		int m = Integer.parseInt(st.nextToken()); // 파티의 수
		
		boolean[] people = new boolean[n+1]; // 해당 사람에게 진실을 말할지 여부
		
		st = new StringTokenizer(br.readLine());
		int ntrue = Integer.parseInt(st.nextToken()); // 진실을 아는 사람 수
		
		Queue<Integer> queue = new ArrayDeque(); // 진실을 알고 있는 사람
		for(int i=0; i<ntrue; i++) {
			int person = Integer.parseInt(st.nextToken());
			queue.offer(person); // 진실을 알고 있는 사람에 추가
			people[person] = true; // 해당 사람은 진실만 말해야
		}
		
		// 파티 정보 입력받기
		List<Integer>[] list = new List[m];
		for(int i=0; i<m; i++) {
			list[i] = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			int npeople = Integer.parseInt(st.nextToken()); // 각 파티에 오는 사람의 수
			
			for(int j=0; j<npeople; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll(); // 현재 진실을 알고 있는 사람 번호
			
			// 파티마다 진실을 알고있는 사람 있는지 확인
			for(int i=0; i<m; i++) {
				List<Integer> party = list[i]; // 현재 파티 구성원 정보
				
				boolean exist = false;
				// 해당 파티에서 진실을 알고있는 사람 있는지 확인
				for(int j=0; j<party.size(); j++) {
					if(cur == party.get(j)) {
						exist = true;
						break;
					}
				}
				// 진실을 말해야하는 사람 존재하면 => 해당 파티에 있는 모든 구성원들에게도 진실 말해야
				if(exist) {
					for(int j=0; j<party.size(); j++) {
						// 해당 파티에서 진실을 알고있지 않은 사람에게도 진실을 말해야함
						if(!people[party.get(j)]) {
							queue.offer(party.get(j));
							people[party.get(j)] = true;
						}
					}
				}
			}
		}
		
		int cnt = 0; // 과장해서 말해도 되는 사람 수
		for(int i=0; i<m; i++) {
			int first = list[i].get(0); // 각 파티의 첫번째 구성원
			if(!people[first]) cnt++; // 진실을 모른다면, 과장해서 말해도 됨
		}
		
		System.out.println(cnt);
	}
}