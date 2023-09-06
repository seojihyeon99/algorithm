/**
 * [아이디어]
 * 모든 컴퓨터가 연결이 되어 있어야 한다, 컴퓨터를 연결하는 비용을 최소 => MST생각!!
 * 아직 MST를 구하는데 kruscal 알고리즘만 익숙해서 kruscal로 품 -> 주말에 prim 공부해야겠당 ㅜㅜ
 * 
 * 최대 정점수 vs 간선수 = 499,500 vs 100,000 => '인접리스트'로 그래프 표현!!
 * 
 * [메모리]
 * 
 * [시간]
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static Edge[] edgeList; // 간선 리스트
	static int n; // 컴퓨터의 수
	static int m; // 연결 가능한 선(간선)의 수
	static int[] parents; // 정점의 부모 노드 저장
	static int total; // 최소 비용
	
	static class Edge implements Comparable<Edge>{
		int left;
		int right;
		int cost; // 가중치
		
		public Edge(int left, int right, int cost) {
			super();
			this.left = left;
			this.right = right;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
	
	static void makeSet() {
		parents = new int[n];
		for(int i=0; i<n; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int v) {
		if(parents[v] == v) return v;
		
		return parents[v] = find(parents[v]); // path compression 적용
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine()); // 컴퓨터의 수
		m = Integer.parseInt(br.readLine()); // 연결 가능한 선(간선)의 수
		
		parents = new int[n];
		makeSet();
		
		// 그래프 선언
		edgeList = new Edge[m];

		// 간선수만큼 반복
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken())-1; // 정점1
			int right = Integer.parseInt(st.nextToken())-1; // 정점2
			int cost = Integer.parseInt(st.nextToken()); // 가중치
			edgeList[i] = new Edge(left, right, cost);
		}
		
		Arrays.sort(edgeList); // 간선 가중치 오름차순 정렬 => kruscal의 핵심!!
		
		int count = 0; // 선택된 간선수
		// 모든 간선을 순회하면서
		for(int i=0; i<m; i++) {
			Edge cur = edgeList[i];
			if(union(cur.left, cur.right)){ // union 할수 있다면
				total += cur.cost;
				count++;
			}
			if(count == n-1) break; // count가 n-1이 되면 신장트리 완성!! => break;
		}
		
		System.out.println(total);
	}	
}