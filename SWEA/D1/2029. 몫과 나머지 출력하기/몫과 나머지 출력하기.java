/**
 * @author SSAFY
 *
 */
import java.io.*;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int i=1; i<=t; i++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			System.out.printf("#%d %d %d\n", i, n1/n2, n1%n2);
		}
		
	}

}