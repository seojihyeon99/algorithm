import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int start;
	int end;
	int cost;
	
	public Edge(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}

class Main {
	
	public static void init(int[] arr) {
		int size = arr.length;
		for(int i=0; i<size; i++) {
			arr[i] = i;
		}
	}
	
	public static int find(int[] arr, int v) {
		if(arr[v] == v)
			return v;
		
		int ans = find(arr, arr[v]);
		arr[v] = ans;
		return ans;
	}
	
	public static void union(int[] arr, int v1, int v2) {
		int g1 = find(arr, v1);
		int g2 = find(arr, v2);
		
		arr[g1] = g2;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken()); // 정점의 개수
		int e = Integer.parseInt(st.nextToken()); // 간선의 개수
		
		Edge[] list = new Edge[e];
		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[i] = new Edge(start, end, cost);
		}
		
		// 간선 정렬
		Arrays.sort(list);
		
		// 초기화
		int[] arr = new int[v+1];
		init(arr);
		
		// 가중치 가장 작은것부터 순서대로 보면서, 사이클 이루지 않는 간선 선택
		int cnt = 0; // mst에 포함된 간선의 개수
		int sum = 0; // mst의 가중치 합
		for(int i=0; i<e; i++) {
			Edge cur = list[i];
			
			if(find(arr, cur.start) != find(arr, cur.end)) { // 사이클 여부 확인
				union(arr, cur.start, cur.end);				
				sum += cur.cost;
				cnt++;
			}
		}
		
		System.out.println(sum);
	}
}