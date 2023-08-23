/**
 * [아이디어]
 * kruskal 알고리즘
 * 처음에 계속 테스트케이스가 1개도 안맞았음 ㅠㅠ
 * 최소 비용인 amount를 int로 선언하면, 
 * 간선개수 E * 최대 가중치 절대값 1,000,000 = 약 2*10^11(int형 표현 가능 범위 = 2^31-1 = 약 2*10^9)이므로
 * int형 표현가능 범위를 벗어나서 원하는 결과 얻지 못함ㅠㅠ
 * 따라서 long으로 선언해야!!
 * 
 * [메모리]
 * 112720 kb
 * [시간]
 * 1967 ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int v; // 정점개수
	static int e; // 간선개수
	static int[] parents; // 부모노드 정보
	
	static class Edge implements Comparable<Edge>{
		int from; // 시작
		int to; // 끝
		int weight; // 가중치
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
//			return this.weight - o.weight;
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static void makeSet() {
		parents = new int[v];
		for(int i=0; i<v; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(a == parents[a]) return a;
		return parents[a] = find(parents[a]);
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
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		for(int t=1; t<=tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			// 간선리스트 생성 및 입력받기
			Edge[] edgelist = new Edge[e];
			for(int i=0; i<e; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken())-1;
				int to = Integer.parseInt(st.nextToken())-1;
				int weight = Integer.parseInt(st.nextToken());
				edgelist[i] = new Edge(from, to, weight);
			}
			
			Arrays.sort(edgelist); // 간선리스트 정렬
			
			makeSet(); // 단위 서로소 집합 만들기
			
			long amount = 0; // 최소 비용
			int count = 0; // 현재까지 뽑힌 간선의 수
			for(Edge edge : edgelist) {	// 간선리스트를 모두 탐색하면서			
				if(union(edge.from, edge.to)) { // 서로 다른 집합에 있어서 합칠수있으면(싸이클 x)
					amount += edge.weight; // 최소비용에 추가
					count++; // 뽑힌 간선수 추가
				}
				if(count == v-1) break; // 뽑힌 간선수가 v-1개라면 -> 완료!!
			}
			sb.append("#" + t + " " + amount + "\n");
		}
		
		System.out.println(sb.toString());
	}
}