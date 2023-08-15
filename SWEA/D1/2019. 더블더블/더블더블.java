import java.io.*;
public class Solution {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int result = 1;
		for(int i=0; i<=n; i++) {
			System.out.print(result + " ");
			result*=2;
		}		
	}

}
