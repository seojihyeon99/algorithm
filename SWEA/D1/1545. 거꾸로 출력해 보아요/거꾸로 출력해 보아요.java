import java.io.*;
public class Solution {
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		for(int i=n; i>=0; i--) {
			System.out.print(i+" ");
		}
	}

}
