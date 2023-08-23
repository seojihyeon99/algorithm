import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n; // 사람의 수
	static int m; // 친구 관계의 수
	static boolean[] visited; // 방문체크
	static boolean flag;
	
	static List<Integer>[] list;
	
	public static void dfs(int v, int depth) {
		if(depth >= 4) {
			flag = true;
			return;
		}
		
		for(int adj : list[v]) {
			if(!visited[adj]) {
				visited[adj] = true;
				dfs(adj, depth+1);
				visited[adj] = false;
				if(flag) return;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 사람의 수
		m = Integer.parseInt(st.nextToken()); // 친구 관계의 수
		
		list = new List[n];
		for(int i=0; i<n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int tmp1 = Integer.parseInt(st.nextToken());
			int tmp2 = Integer.parseInt(st.nextToken());
			list[tmp1].add(tmp2);
			list[tmp2].add(tmp1);			
		}
		
		for(int i=0; i<n; i++) {
			visited = new boolean[n];
			visited[i] = true;
			dfs(i, 0);
			if(flag) break;
		}
		
		if(flag) {
			System.out.println("1");
		}
		else {
			System.out.println("0");
		}
	}
}
