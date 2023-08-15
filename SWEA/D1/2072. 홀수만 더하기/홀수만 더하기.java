import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		for (int i = 1; i <= t; i++) {
			int sum = 0;
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			while (st.hasMoreTokens()) {
				int num = Integer.parseInt(st.nextToken());
				if (num % 2 == 1) {
					sum += num;
				}
			}
			System.out.println("#" + i + " " + sum);
		}

	}
}