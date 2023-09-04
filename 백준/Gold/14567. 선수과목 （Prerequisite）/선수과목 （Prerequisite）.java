import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 과목의 수
		int m = Integer.parseInt(st.nextToken()); // 선수 조건의 수
		
		int[] subject = new int[n+1]; // indegree 저장. 인덱스 0은 쓰지 x
		int[] result = new int[n+1]; // 해당 정점(과목)의 방문 순서
//		Arrays.fill(result, 1);
		boolean[] visited = new boolean[n+1]; // 해당 정점(과목)의 방문 여부
		
		// 인접 정점(과목) 저장 리스트 생성
		List<Integer>[] list = new List[n+1]; // 인덱스 0은 쓰지 x
		for(int i=0; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 선수과목 조건수만큼 반복
		for(int i=1; i<=m; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			subject[right]++; // indegree 증가
			list[left].add(right);			
		}
		
		// 리스트 순회하며, indegree 0인 애들 찾음
		int idx = 1;
		while(true) {
			boolean changed = false;
			// indegree가 0인 애들 입력함
			for(int i=1; i<=n; i++) {
				if(subject[i] == 0 && !visited[i]) {
					result[i] = idx;
					changed = true;
				}
			}
			if(!changed) break;
			
			// indegree가 0인애들의 인접 정점(과목)의 차수 1 감소
			int[] temp = new int[n+1];
			temp = Arrays.copyOf(subject, n+1);
			for(int i=1; i<=n; i++) {
				if(subject[i] == 0 && !visited[i]) {
					visited[i] = true;
					for(int j=0; j<list[i].size(); j++) {
						temp[list[i].get(j)]--;
					}
				}
			}
			subject = Arrays.copyOf(temp, n+1);
			
			idx++;
		}
		
		for(int i=1; i<=n; i++) {
			sb.append(result[i] + " ");
		}
		System.out.println(sb);
	}
	
}
