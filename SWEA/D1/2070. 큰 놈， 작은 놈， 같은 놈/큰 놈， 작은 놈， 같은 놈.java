import java.io.*;
import java.util.*;
public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int i=1;i<=t;i++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			if(n1<n2) {
				System.out.printf("#%d <\n", i);
			}
			else if(n1==n2) {
				System.out.printf("#%d =\n", i);
			}
			else if(n1>n2) {
				System.out.printf("#%d >\n", i);
			}

		}
	}
}
