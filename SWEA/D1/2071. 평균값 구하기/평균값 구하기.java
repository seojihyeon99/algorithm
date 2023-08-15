import java.io.*;
import java.util.*;
public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int i=1; i<=t; i++) {
			int sum = 0;
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			
			while(st.hasMoreTokens()) {
				sum+=Integer.parseInt(st.nextToken());
			}
			System.out.printf("#%d %d\n", i, Math.round(sum/10.0));
		}
		
	}
}