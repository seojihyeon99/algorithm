/**
 * @author SSAFY
 *
 */
import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int n1=0, n2=0;
		while(st.hasMoreTokens()) {
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
		}
		
		if(n1-n2 == -1 || n1-n2==2)
			System.out.println("B");
		else if(n1-n2 == -2 || n1-n2==1) {
			System.out.println("A");
		}
		
	}

}
