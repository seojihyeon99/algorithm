import java.io.*;

public class Solution {

	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		int sum = 0;
		for(int i=0; i<s.length(); i++) {
			sum+=Integer.parseInt(s.substring(i,i+1));
		}
		System.out.println(sum); 
	}

}