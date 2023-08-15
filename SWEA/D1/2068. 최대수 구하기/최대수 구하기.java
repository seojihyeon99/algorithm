import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		int[] arr = new int[10];
		for (int i = 1; i <= t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int j = 0;
			while (st.hasMoreTokens()) {
				arr[j] = Integer.parseInt(st.nextToken());
				j++;
			}
			System.out.printf("#%d %d\n", i, Arrays.stream(arr).max().getAsInt());
		}
	}

}