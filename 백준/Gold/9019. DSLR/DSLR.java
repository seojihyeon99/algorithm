import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int num;
	String str;
	
	Node(int num, String str) {
		this.num = num;
		this.str = str;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for(int t=0; t<tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 초기값
			int b = Integer.parseInt(st.nextToken()); // 최종값
			
			boolean[] visited = new boolean[10000];
			Queue<Node> queue = new ArrayDeque<>();
			queue.offer(new Node(a, ""));
			visited[a] = true;
			
			while(!queue.isEmpty()) {
				Node cur = queue.poll();
				int num = cur.num;
				String str = cur.str;
				// 최종값과 같은 경우 종료
				if(num == b) {
					sb.append(str + "\n");
					break;
				}
				
				// D 명령어
				int nd = (num*2)%10000;
				if(!visited[nd]) {
					visited[nd] = true;
					queue.offer(new Node(nd, str+'D'));
				}
				
				// S 명령어
				int ns = (num-1) == -1 ? 9999: num-1;
				if(!visited[ns]) {
					visited[ns] = true;
					queue.offer(new Node(ns, str+'S'));
				}
				
				// L 명령어
				int nl = (num*10)%10000 + num/1000;
				if(!visited[nl]) {
					visited[nl] = true;
					queue.offer(new Node(nl, str+'L'));
				}
				
				// R 명령어
				int nr = (num/10) + (num%10)*1000;
				if(!visited[nr]) {
					visited[nr] = true;
					queue.offer(new Node(nr, str+'R'));
				}				
			}
		}
		
		System.out.println(sb);
	}
}