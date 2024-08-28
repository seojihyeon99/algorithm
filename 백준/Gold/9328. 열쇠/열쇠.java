import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Main {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		for(int t=0; t<tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 높이
			int m = Integer.parseInt(st.nextToken()); // 너비
				
			boolean[][] visited = new boolean[n+2][m+2]; // 해당 지역 방문여부

			// 맵 입력 ('.' : 빈공간, '*' : 벽, '$' : 문서, '대문자' : 문, '소문자' : 열쇠)
			char[][] map = new char[n+2][m+2];
			for(int r=1; r<=n; r++) {
				String s = br.readLine();
				for(int c=1; c<=m; c++) {
					map[r][c] = s.charAt(c-1);
				}				
			}
				
			// 열쇠 입력
			boolean[] key = new boolean[26];
			String s = br.readLine();
			if(!s.equals("0")) {
				for(int i=0; i<s.length(); i++) {
					key[s.charAt(i) - 'a'] = true;
				}				
			}

			// 빌딩 밖 입력
			Queue<Node> queue = new ArrayDeque<>();		
			for(int c=1; c<=m; c++) {
				queue.offer(new Node(0, c));
				queue.offer(new Node(n+1, c));
			}
			for(int r=1; r<=n; r++) {
				queue.offer(new Node(r, 0));
				queue.offer(new Node(r, m+1));
			}
			
			// 대기문 (나중에 키 찾을때 열릴 가능성 있는 문)
			List<Node>[] door = new List[26];
			for(int i=0; i<26; i++) {
				door[i] = new ArrayList<>();
			}
			
			int count = 0; // 훔칠 수 있는 문서의 개수
			while(!queue.isEmpty()) {
				Node cur = queue.poll();
				
				for(int i=0; i<4; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];
					
					// 배열의 인덱스 넘는 경우
					if(nx<=0 || nx>n || ny<=0 || ny>m) continue;
					
					// 방문하지 않은 경우
					if(!visited[nx][ny]) {
						if(map[nx][ny] == '.') { // 빈 공간
							queue.offer(new Node(nx, ny));
							visited[nx][ny] = true;
						}
						else if(map[nx][ny] == '$') { // 문서
							count++;
							map[nx][ny] = '.';
							queue.offer(new Node(nx, ny));
							visited[nx][ny] = true;
						}
						else if(isDoor(map[nx][ny])) { // 문
							if(key[map[nx][ny] - 'A']) { // 열수 있는 열쇠 있을 때
								queue.offer(new Node(nx, ny));
								visited[nx][ny] = true;
							}
							else { // 열수 있는 열쇠 없을 때 => 나중을 위해 대기문에 저장
								door[map[nx][ny] - 'A'].add(new Node(nx, ny));
							}
						}
						else if(isKey(map[nx][ny])) { // 열쇠	
							// 처음 줍는 열쇠라면
							if(!key[map[nx][ny] - 'a']) {
								// 해당 열쇠로 열 수 있는 문이 있는 경우
								for(Node d : door[map[nx][ny] - 'a']) {
									queue.offer(new Node(d.x, d.y));
									visited[d.x][d.y] = true;
								}
								
								key[map[nx][ny] - 'a'] = true;							
							}

							map[nx][ny] = '.';
							queue.offer(new Node(nx, ny));
							visited[nx][ny] = true;
						}						
					}	
				}
			}
			
			sb.append(count + "\n");
		}
		
		System.out.println(sb);
	}
	
	static boolean isDoor(char c) {
		if(c - 'A' >= 0 && c - 'A' <=25) return true;
		
		return false;
	}
	
	static boolean isKey(char c) {
		if(c - 'a' >= 0 && c - 'a' <=25) return true;
		
		return false;
	}
}