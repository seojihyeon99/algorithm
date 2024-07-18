import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int n; // 학생의 수
	static int cnt; // 팀을 꾸리지 못한 학생의 수
	static int[] people; // 각 학생마다 함께 팀플 하고 싶은 학생 번호(자기 자신도 가능)
	static boolean[] visited; // 해당 학생 방문 여부
	static boolean[] done; // 검사 완료 여부
	
	static void makeTeam(int cur) {
		// 이미 검사 완료 => 더이상 검사할 필요 x
		if(done[cur]) return;
		
		// 이미 방문한 곳 재방문 => 사이클의 구성원이다
		if(visited[cur]) {
			done[cur] = true; // 검사 완료 표시해줌
			cnt--;
		}
		// 처음 방문했다면
		else {
			visited[cur] = true; // 방문 처리			
		}
		
		// 내가 바라보는 사람이 아직 검사 완료되지 않았다면
		makeTeam(people[cur]);
		done[cur] = true; // 사이클에 포함되지 않는 학생도 검사 완료했으니깐 검사 완료 표시
		visited[cur] = false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for(int i=0; i<t; i++) {
			n = Integer.parseInt(br.readLine()); // 학생의 수
			cnt = n; // 팀을 꾸리지 못한 학생의 수		
			people = new int[n]; // 각 학생마다 함께 팀플 하고 싶은 학생 번호(자기 자신도 가능)
			done = new boolean[n];
			visited = new boolean[n]; // 해당 학생 현재 보는 사이클에 포함되는지 여부
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				people[j] = Integer.parseInt(st.nextToken())-1;
			}
			
			for(int j=0; j<n; j++) {
				if(!done[j]) { // 아직 해당 학생 팀 구성되지 않았다면
					makeTeam(j);
				}
			}
			
			sb.append(cnt + "\n");
		}
		
		System.out.println(sb);
		
	}
}