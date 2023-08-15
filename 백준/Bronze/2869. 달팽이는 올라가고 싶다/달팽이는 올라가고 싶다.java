import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		StringTokenizer st = new StringTokenizer(s);
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		int day = 1;
		int h = v-a;
		
		if(h%(a-b)==0) {
			day += h/(a-b);
		}
		else {
			day += h/(a-b)+1;
		}
		
		System.out.println(day);
	}

}
