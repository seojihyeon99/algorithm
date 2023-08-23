/**
 * @author 서지현
 * [아이디어]
 * 부분집합 + bfs
 * 
 * [메모리]
 * 15392KB
 * [시간]
 * 144ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n; // 구역 개수(정점개수)
	static int[] population; // 각 구역(정점)별 인구수 저장
	static List<Integer>[] adjList; // 인접리스트
	static boolean[] selected; // 정점 선택여부 저장
	static int total; // 총 인구수
	static int minDiff = 1000; // 두 선거구의 인구차이 최솟값
	static int selectedSum = 0; // 선택된것들의 인구수 합 
	
	// 부분집합 구하는 함수
	static void subset(int start) { // start: 현재 몇번째 원소보고있는지
		// 마지막 원소까지 다봤으면 -> 기저조건(종료)
		if(start == n) {
			/* 선택된 원소들 */
			if(!bfsSelected()) return; // bfs 결과 false이면 연결되지 않은것이므로 -> 종료
					
			/* 선택되지 않은 원소들 */
			if(!bfsNotSelected()) return; // bfs 결과 false이면 연결되지 않은것이므로 -> 종료
			
			// 선택된 원소들 구역과 /선택되지 않은 원소들 구역이 모두 true이면 둘 다 모두 연결되어있는 그래프 형태임!!
			if(Math.abs(selectedSum - (total-selectedSum)) <minDiff) { // (선택된것의 개수 - 선택되지않은것의 개수)의 절대값이 현재 최소 차이(minDiff)보다 작다면
				minDiff = Math.abs(selectedSum - (total-selectedSum)); // minDiff 업데이트
			}
			return;
		}
		
		// 재귀가 들어간 부분
		selected[start] = true;
		subset(start+1);
		selected[start] = false;
		subset(start+1);
	}
	
	// 선택된 원소들
	public static boolean bfsSelected() {
		boolean[] visited = new boolean[n]; // 방문 여부 저장
		
		// bfs 시작점 아무거나 1개 구함
		int startv = -1; // 시작 정점 인덱스
		for(int i=0; i<n; i++) {
			if(selected[i]) {
				startv = i;
				break;
			}
		}
		if(startv == -1) return false; // 만약 -1이라면, 선택된게 없으므로 => 문제조건(적어도 하나의 구역을 포함해야 한다)에 위배
		
		int sum = 0; // 선택된 것의 sum
		int count = 0; // 큐에 들어간 원소개수

		Queue<Integer> queue = new ArrayDeque<>();
		
		visited[startv] = true; // 시작점 방문처리
		queue.offer(startv); // 큐에 시작점 추가
		count++; // 큐에 들어간 원소개수 1 증가
		sum += population[startv]; // 선택된 것들의 sum에 해당 정점의 인구수 누적
		
		// 큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			int cur = queue.poll(); // 큐에서 하나 꺼내서
			for(int adjv : adjList[cur]) { // 해당 정점의 인접정점들을 보면서
				if(!visited[adjv] && selected[adjv]) { // 아직 방문하지 않았고, 선택되었으면
					queue.offer(adjv); // 큐에 넣어줌
					visited[adjv] = true; // 방문처리
					sum += population[adjv]; // 선택된 것들의 sum에 해당 정점의 인구수 누적
					count++; // 큐에 들어간 원소개수 1 증가
				}
			}
		}
		
		// 선택된것들의 개수 구함
		int selectedNum = 0;
		for(int i=0; i<n; i++) {
			if(selected[i]) selectedNum++; 
		}
		
		// 큐에 들어간 원소개수와 선택된것들의 개수가 다르다면 -> 이어지지 않은것임
		if(selectedNum != count) {
			return false;
		}
		
		selectedSum = sum; // 현재 선택된것의 개수를 업데이트
		return true;
	}
	
	// 선택되지 않은 원소들
	public static boolean bfsNotSelected() {
		boolean[] visited = new boolean[n]; // 방문 여부 저장
		
		// bfs 시작점 아무거나 1개 구함
		int startv = -1; // 시작 정점 인덱스
		for(int i=0; i<n; i++) {
			if(!selected[i]) {
				startv = i;
				break;
			}
		}
		if(startv == -1) return false; // 만약 -1이라면, 선택되지 않은게 없으므로 => 문제조건(적어도 하나의 구역을 포함해야 한다)에 위배
		
		int count = 0; // 큐에 들어간 원소개수

		Queue<Integer> queue = new ArrayDeque<>();
		
		visited[startv] = true; // 시작점 방문처리
		queue.offer(startv); // 큐에 시작점 추가
		count++; // 큐에 들어간 원소개수 1 증가
		
		// 큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			int cur = queue.poll(); // 큐에서 하나 꺼내서
			for(int adjv : adjList[cur]) { // 해당 정점의 인접정점들을 보면서
				if(!visited[adjv] && !selected[adjv]) { // 아직 방문하지 않았고, 선택되지 않았으면
					queue.offer(adjv); // 큐에 넣어줌
					visited[adjv] = true; // 방문처리
					count++; // 큐에 들어간 원소개수 1 증가
				}
			}
		}
		
		// 선택되지 않은것들의 개수 구함
		int unSelectedNum = 0;
		for(int i=0; i<n; i++) {
			if(!selected[i]) unSelectedNum++; 
		}
		
		// 큐에 들어간 원소개수와 선택된것들의 개수가 다르다면 -> 이어지지 않은것임
		if(unSelectedNum != count) {
			return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine()); // 구역 개수(정점개수)
		
		total = 0; // 총 인구수(나중에 선택되지 않은것들 개수를 구할때, 그냥 "total - 선택된것들의 개수"로 쉽게 구하게 하기 위함)
		
		// 각 구역별(정점) 인구수 배열 생성 및 입력받기
		population = new int[n]; // 각 구역(정점)별 인구수 저장
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			population[i] = Integer.parseInt(st.nextToken()); // 해당 정점의 인구수 업데이트
			total+=population[i]; // 총 인구수에 누적하여 저장
		}
		
		selected = new boolean[n]; // 해당 정점이 선택되었는지 여부 저장하는 selected 배열 생성
		
		// 인접리스트 생성
		adjList = new List[n];
		for(int i=0; i<n; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// 인접리스트에 인접한 정점들 정보 저장
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int it = Integer.parseInt(st.nextToken());
			for(int j=0; j<it; j++) {
				adjList[i].add(Integer.parseInt(st.nextToken())-1); // 정점 0부터 시작한다고 가정
			}
		}
		
		subset(0); // 부분집합 구하기
		
		if(minDiff != 1000) { // 두 선거구의 인구차이 최솟값이 업데이트되었다면 -> 연결되어 있는 두 선거구로 나눌 수 있는것임
			System.out.println(minDiff); // 선택된것들의 인구수 합을 출력
		}
		else { // 두 선거구의 인구차이 최솟값이 업데이트되지않았다면 -> 연결되어 있는 두 선거구로 나눌 수 없는것임
			System.out.println(-1);
		}
	}
}

