import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine()); // 도시의 개수
		int m = Integer.parseInt(br.readLine()); // 버스의 개수
		
		int[][] dist = new int[n+1][n+1]; // dist[i][j] : i-> j로 가는 최단거리
		
		for(int i=1; i<=n; i++) {
			Arrays.fill(dist[i], 10000001);		
			dist[i][i] = 0;
		}
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()); // 시작 도시
			int e = Integer.parseInt(st.nextToken()); // 도착 도시
			int c = Integer.parseInt(st.nextToken()); // 버스 비용	
			dist[s][e] = Math.min(dist[s][e], c);
		}
		
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if(dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(dist[i][j] == 10000001) {
					sb.append(0 + " ");
				} else {
					sb.append(dist[i][j] + " ");					
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
}