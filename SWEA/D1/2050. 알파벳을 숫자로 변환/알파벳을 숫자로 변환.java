
/**
 * @author SSAFY
 *
 */

import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		for(int i=0; i<s.length(); i++) {
			System.out.print(s.charAt(i)-64 + " ");
		}
	}

}
