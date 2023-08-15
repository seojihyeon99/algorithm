/**
 * @author SSAFY
 *
 */
import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		for(int i=0; i<s.length(); i++) {
			int n = s.charAt(i);
			if (n>=97 && n<=122) {
				System.out.printf("%c", n-32);
			}
			else {
				System.out.printf("%c",n);			
			}			
		}
	}
}

